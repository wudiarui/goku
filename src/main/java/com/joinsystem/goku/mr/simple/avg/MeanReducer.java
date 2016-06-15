package com.joinsystem.goku.mr.simple.avg;

import com.joinsystem.goku.common.constants.Constants;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.DoubleWritable;

import java.io.IOException;

/**
 * <p>Date : 16/6/13</p>
 * <p>Time : 上午11:16</p>
 *
 * @author jerry
 */
public class MeanReducer extends TableReducer<ImmutableBytesWritable, DoubleWritable, ImmutableBytesWritable> {


    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        // 得到要计算的数据存储标记
        String qualifierStr = context.getConfiguration().get(Constants.DEFAULT_SIMPLE_QUALIFIER_TOKEN);
        int qualifier = 0;
        if (NumberUtils.isNumber(qualifierStr)) {
            qualifier = Integer.parseInt(qualifierStr);
        }
        Put put = new Put(key.get());

        for (DoubleWritable value :values) {
            put.addColumn(Constants.DEFAULT_COLUMN_FAMILY, Bytes.toBytes(qualifier), Bytes.toBytes(value.get()));
        }

        context.write(key, put);
    }
}
