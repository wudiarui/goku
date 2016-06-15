package com.joinsystem.goku.common.driver;

import com.joinsystem.goku.common.model.BaseHBaseModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * HBase MapReduce Driver
 *
 * <p>Date : 16/6/6</p>
 * <p>Time : 下午4:29</p>
 *
 * @author jerry
 */
public abstract class BaseHBaseDriver {
//    private String hadoopHDFSUrl;
    private final static Logger LOGGER = LoggerFactory.getLogger("goku-base");

    private String hbaseZookeeperQuorum;

    private Configuration configuration;

    public BaseHBaseDriver() {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("classpath: hbase_conf.properties");
        try {
            properties.load(inputStream);
            hbaseZookeeperQuorum = properties.getProperty("hbase.zookeeper.quorum");
            configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", hbaseZookeeperQuorum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BaseHBaseDriver(String hbaseZookeeperQuorum) {
        if (StringUtils.isNotEmpty(hbaseZookeeperQuorum)) {
            this.hbaseZookeeperQuorum = hbaseZookeeperQuorum;
            configuration.set("hbase.zookeeper.quorum", hbaseZookeeperQuorum);
        }
    }

    /**
     * HBase MapReduce初始化任务集
     *
     * @param models    任务集合
     * @throws IOException
     */
    public void init(BaseHBaseModel models[]) throws IOException {
        if (ArrayUtils.isEmpty(models) || models.length <= 0) {
            return;
        }

        Job job;
        for (BaseHBaseModel model : models) {
            if (model.getJob() == null) {
                job = Job.getInstance(configuration, model.getJobName());
                model.setJob(job);
            } else {
                job = model.getJob();
            }
            job.setJarByClass(model.getJarClass());

            if (model.getInputFormatClass() != null) {
                job.setInputFormatClass(model.getInputFormatClass());
            } else {
                job.setInputFormatClass(TableInputFormat.class);
            }
        }
    }

    /**
     * 主驱动的运行方法,如果一切封装好,可以跳过初始化,直接调用。
     *
     * @param models    任务集合
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public void run(final BaseHBaseModel models[]) throws IOException, InterruptedException, ClassNotFoundException {
        Job job;
        for (BaseHBaseModel model : models) {
            job = model.getJob();
            /*设置Mapper*/
            TableMapReduceUtil.initTableMapperJob(model.getInTableName(), model.getScan(), model.getMapperClass(),
                    model.getOutputKeyClass(), model.getOutputValueClass(), job);
            /*设置Reducer*/
            TableMapReduceUtil.initTableReducerJob(model.getOutTableName(), model.getReducerClass(), job);

            boolean isSuccess = job.waitForCompletion(true);

            if (isSuccess) {
                LOGGER.info("The job is complate. [" + job.getJobName() + "]");
            } else {
                LOGGER.error("The job is fail of executed. [" + job.getJobName() + "]");
                destroy();
                throw new IOException("[Job Fail]Detail see: zookeeper or hbase or hdp mr of dot log files.");
            }
        }
    }

    public void destroy() {

    }

    public String getHbaseZookeeperQuorum() {
        return hbaseZookeeperQuorum;
    }

    public void setHbaseZookeeperQuorum(String hbaseZookeeperQuorum) {
        this.hbaseZookeeperQuorum = hbaseZookeeperQuorum;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        configuration.set("hbase.zookeeper.quorum", hbaseZookeeperQuorum);
    }
}
