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
}
