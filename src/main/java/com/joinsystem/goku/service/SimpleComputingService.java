package com.joinsystem.goku.service;

import com.joinsystem.goku.service.type.SimpleComputeType;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * <p>Date : 16/6/15</p>
 * <p>Time : 下午4:08</p>
 *
 * @author jerry
 */
public interface SimpleComputingService {

    /**
     * 根据条件, 执行相应的计算
     *
     * @param pointIds          测点ID集合
     * @param startTime         区间开始时间
     * @param endTime           区间结束时间
     * @param qualifier         列标识
     * @param type              计算类型. See {@link SimpleComputeType}
     * @return                  true成功, false失败
     */
    boolean processSimpleComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier, SimpleComputeType type);

    /**
     * 根据条件, 执行平均值计算
     *
     * @param pointIds          测点ID集合
     * @param startTime         区间开始时间
     * @param endTime           区间结束时间
     * @param qualifier         列标识
     * @return                  true成功, false失败
     */
    boolean processMeanComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws IOException;

    /**
     * 根据条件, 执行最大值计算
     *
     * @param pointIds          测点ID集合
     * @param startTime         区间开始时间
     * @param endTime           区间结束时间
     * @param qualifier         列标识
     * @return                  true成功, false失败
     */
    boolean processMaxComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier);

    /**
     * 根据条件, 执行合计值计算
     *
     * @param pointIds          测点ID集合
     * @param startTime         区间开始时间
     * @param endTime           区间结束时间
     * @param qualifier         列标识
     * @return                  true成功, false失败
     */
    boolean processSumComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier);

    /**
     * 根据条件, 执行最小值计算
     *
     * @param pointIds          测点ID集合
     * @param startTime         区间开始时间
     * @param endTime           区间结束时间
     * @param qualifier         列标识
     * @return                  true成功, false失败
     */
    boolean processMinComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier);
}
