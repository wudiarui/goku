package com.joinsystem.goku.service.impl;

import com.joinsystem.goku.mr.simple.avg.MeanJob;
import com.joinsystem.goku.service.SimpleComputingService;
import com.joinsystem.goku.service.type.SimpleComputeType;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * <p>Date : 16/6/15</p>
 * <p>Time : 下午4:31</p>
 *
 * @author jerry
 */
public class SimpleComputingServiceImpl implements SimpleComputingService {
    public boolean processSimpleComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier, SimpleComputeType type) {
        return false;
    }

    public boolean processMeanComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws IOException {
        MeanJob meanComputing = new MeanJob();
        return meanComputing.demoMeanRunner(pointIds, startTime, endTime, qualifier);
    }

    public boolean processMaxComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) {
        return false;
    }

    public boolean processSumComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) {
        return false;
    }

    public boolean processMinComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) {
        return false;
    }
}
