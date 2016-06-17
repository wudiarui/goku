package com.joinsystem.goku.mr.simple.avg;

import com.joinsystem.goku.common.constants.Constants;
import com.joinsystem.goku.common.driver.HBaseMapReduceDriver;
import com.joinsystem.goku.common.model.BaseHBaseModel;
import com.joinsystem.goku.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.DoubleWritable;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * <p>Date : 16/6/14</p>
 * <p>Time : 上午10:33</p>
 *
 * @author jerry
 */
public class MeanJob {

    private static final String JOB_NAME = "<<Mean Computing Task>>";
    private static final String INPUT_TABLE_NAME = "table1";
    private static final String OUTPUT_TABLE_NAME = "table2";

    public boolean demoMeanRunner(Set<Integer> pointIds, Date startTime, Date endTime, String qualitier) throws IOException {
        if (pointIds.isEmpty() || StringUtils.isEmpty(qualitier) || startTime == null) {
            System.out.println("throw new NullPointerException(\"参数规则不符, 存在NULL\");");
            return false;
        }
        HBaseMapReduceDriver driver = new HBaseMapReduceDriver();
        Configuration configuration = driver.getConfiguration();
        configuration.set(Constants.DEFAULT_SIMPLE_QUALIFIER_TOKEN, qualitier);
        driver.setConfiguration(configuration);

        Scan scan = new Scan();
        scan.setCaching(500);
        scan.setCacheBlocks(false);
        scan.addFamily(Constants.DEFAULT_COLUMN_FAMILY);

        FilterList filters = new FilterList();

        /* 增加行过滤器 */
        if (DateUtil.diffDay(endTime, startTime) == 0) {
            for (Integer pointId : pointIds) {
                byte[] rowkey = getQueryRowKeyByThisDay(pointId, startTime);
                RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(rowkey));
                filters.addFilter(rowFilter);
            }
        }

        /* 增加列区间过滤器 */
        ColumnRangeFilter columnRangeFilter = new ColumnRangeFilter(convertTimeByQualifier(startTime), true,
                convertTimeByQualifier(endTime), true);
        filters.addFilter(columnRangeFilter);

        scan.setFilter(filters);

        BaseHBaseModel meanTask = new BaseHBaseModel(null, JOB_NAME, getClass(), null, INPUT_TABLE_NAME,
                scan, MeanMapper.class, ImmutableBytesWritable.class, DoubleWritable.class,
                MeanReducer.class, OUTPUT_TABLE_NAME);
        try {
            BaseHBaseModel[] tasks = new BaseHBaseModel[]{meanTask};
            driver.init(tasks);
            driver.run(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            driver.destroy();
        }
        return driver.isSuccess();
    }

    private byte[] getQueryRowKeyByThisDay(Integer pointId, Date date) {
        String tempStr = String.valueOf(pointId) + DateUtil.dateFmtToString(date, "yyyyMMdd");
        return Bytes.toBytes(Long.parseLong(tempStr));
    }

    private byte[] convertTimeByQualifier(Date date) {
        String tempStr = DateUtil.dateFmtToString(date, "HHmmss");
        return Bytes.toBytes(Integer.valueOf(tempStr));
    }
}
