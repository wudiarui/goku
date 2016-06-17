package com.joinsystem.goku.common.constants;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * <p>Date : 16/6/13</p>
 * <p>Time : 上午11:34</p>
 *
 * @author jerry
 */
public interface Constants {
    /**
     * 默认列簇名称
     */
    byte[] DEFAULT_COLUMN_FAMILY = Bytes.toBytes("cf");

    /**
     * 默认简单算法生成中间表的列标记
     */
    String DEFAULT_SIMPLE_QUALIFIER_TOKEN = "qualifier";

    /**
     * 默认RMI服务的端口
     */
    int DEFAULT_RMI_PORT = 9876;

    /**
     * 默认RMI服务主机地址
     */
    String DEFAULT_SERVER_HOST = "172.16.38.177";

    /**
     * 默认RMI服务简单计算服务路径
     */
    String DEFAULT_SIMPLE_SERVICE_PATH = "/simple";
}
