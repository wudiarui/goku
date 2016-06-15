package com.joinsystem.goku.common.model;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;

/**
 * HBase MR2 的基础模型类.<br/>
 * 用于设置jobs中的mapper and reducer.<br/>
 * 主要输入、输出都是HBase Table.<br/>
 * 只实现了Simple通用方式, 如果有需求请extends This.
 *
 * <p>Date : 16/6/6</p>
 * <p>Time : 下午2:33</p>
 *
 * @author jerry
 */
public abstract class BaseHBaseModel {

    private Job job;
    private String jobName;
    private Class<?> jarClass;
    private Class<? extends InputFormat> inputFormatClass;

    public Class<? extends InputFormat> getInputFormatClass() {
        return inputFormatClass;
    }

    public void setInputFormatClass(Class<? extends InputFormat> inputFormatClass) {
        this.inputFormatClass = inputFormatClass;
    }

    public Class<?> getJarClass() {
        return jarClass;
    }

    public void setJarClass(Class<?> jarClass) {
        this.jarClass = jarClass;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    // <<<<< There is define parameters for TableMapper >>>>>
    private String inTableName;
    private Scan   scan;
    private Class<? extends TableMapper> mapperClass;
    private Class<?> outputKeyClass;
    private Class<?> outputValueClass;

    // <<<<< There is define parameters for TableReducer >>>>>
    private Class<? extends TableReducer> reducerClass;
    private String outTableName;

    /**
     * 构造工作模型
     *
     * @param job               MapReduce job
     * @param jobName           工作名称
     * @param jarClass          mr的驱动类
     * @param inputFormatClass  输入格式化类
     * @param inTableName       输入表名
     * @param scan              扫描器(请先封装,如有需求)
     * @param mapperClass       HBase的Mapper类
     * @param outputKeyClass    mapper输出时键的类型
     * @param outputValueClass  mapper输出时值的类型
     * @param reducerClass      HBase的Reducer类
     * @param outTableName      输出表名
     */
    public BaseHBaseModel(Job job, String jobName, Class<?> jarClass, Class<? extends InputFormat> inputFormatClass,
                          String inTableName, Scan scan, Class<? extends TableMapper> mapperClass, Class<?> outputKeyClass,
                          Class<?> outputValueClass, Class<? extends TableReducer> reducerClass, String outTableName) {
        this.job = job;
        this.jobName = jobName;
        this.jarClass = jarClass;
        this.inTableName = inTableName;
        this.scan = scan;
        this.mapperClass = mapperClass;
        this.outputKeyClass = outputKeyClass;
        this.outputValueClass = outputValueClass;
        this.reducerClass = reducerClass;
        this.outTableName = outTableName;
    }

    /**
     *
     * @return  MapReduce Job
     */
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    /**
     *
     * @return input table name
     */
    public String getInTableName() {
        return inTableName;
    }

    public void setInTableName(String inTableName) {
        this.inTableName = inTableName;
    }

    /**
     *
     * @return Fields Scanner
     */
    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    /**
     *
     * @return  Mapper Class
     */
    public Class<? extends TableMapper> getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(Class<? extends TableMapper> mapperClass) {
        this.mapperClass = mapperClass;
    }

    /**
     *
     * @return  mapper output type of key
     */
    public Class<?> getOutputKeyClass() {
        return outputKeyClass;
    }

    public void setOutputKeyClass(Class<?> outputKeyClass) {
        this.outputKeyClass = outputKeyClass;
    }

    /**
     *
     * @return  mapper output value of key
     */
    public Class<?> getOutputValueClass() {
        return outputValueClass;
    }

    public void setOutputValueClass(Class<?> outputValueClass) {
        this.outputValueClass = outputValueClass;
    }

    /**
     *
     * @return  Reducer Class
     */
    public Class<? extends TableReducer> getReducerClass() {
        return reducerClass;
    }

    public void setReducerClass(Class<? extends TableReducer> reducerClass) {
        this.reducerClass = reducerClass;
    }

    /**
     *
     * @return output table name
     */
    public String getOutTableName() {
        return outTableName;
    }

    public void setOutTableName(String outTableName) {
        this.outTableName = outTableName;
    }
}
