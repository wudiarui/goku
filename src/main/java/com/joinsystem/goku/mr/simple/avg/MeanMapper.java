package com.joinsystem.goku.mr.simple.avg;

import com.google.common.collect.Lists;
import com.joinsystem.goku.common.constants.Constants;
import com.joinsystem.goku.common.utils.MathUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.DoubleWritable;

import java.io.IOException;
import java.util.List;

/**
 * <p>Date : 16/6/12</p>
 * <p>Time : 下午2:08</p>
 *
 * @author jerry
 */
public class MeanMapper extends TableMapper<ImmutableBytesWritable, DoubleWritable> {

    @Override
    protected void map(ImmutableBytesWritable key, Result values, Context context) throws IOException, InterruptedException {
        Long rowKey = Bytes.toLong(values.getRow());
        Double avgValue;
        Cell[] cells = values.rawCells();
        List<Double> valueList = Lists.newArrayList();
        Double[] numbers = new Double[0];

        for (Cell cell : cells) {
//            cell.getValueArray();
            valueList.add(Bytes.toDouble(CellUtil.cloneValue(cell)));
        }

        avgValue = MathUtils.mean(valueList.toArray(numbers), false);
        context.write(new ImmutableBytesWritable(Bytes.toBytes(rowKey)), new DoubleWritable(avgValue));
    }
}
