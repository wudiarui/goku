package com.joinsystem.goku.remote;

import com.joinsystem.goku.service.SimpleComputingService;
import com.joinsystem.goku.service.impl.SimpleComputingServiceImpl;
import com.joinsystem.goku.service.type.SimpleComputeType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Set;

/**
 * <p>Date : 16/6/15</p>
 * <p>Time : 下午4:48</p>
 *
 * @author jerry
 */
public class SimpleComputeRemote extends UnicastRemoteObject implements ISimpleComputeRemote {
    private SimpleComputingService simpleComputingService;

    public SimpleComputeRemote() throws RemoteException {
        simpleComputingService = new SimpleComputingServiceImpl();
    }

    public boolean processSimpleComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier, SimpleComputeType type) throws RemoteException {
        return simpleComputingService.processSimpleComputing(pointIds, startTime, endTime, qualifier, type);
    }

    public boolean processMeanComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws RemoteException {
        boolean isSuccess = false;
        try {
            isSuccess = simpleComputingService.processMeanComputing(pointIds, startTime, endTime, qualifier);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean processMaxComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws RemoteException {
        return simpleComputingService.processMaxComputing(pointIds, startTime, endTime, qualifier);
    }

    public boolean processSumComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws RemoteException {
        return simpleComputingService.processSumComputing(pointIds, startTime, endTime, qualifier);
    }

    public boolean processMinComputing(Set<Integer> pointIds, Date startTime, Date endTime, String qualifier) throws RemoteException {
        return simpleComputingService.processMinComputing(pointIds, startTime, endTime, qualifier);
    }

    public void setSimpleComputingService(SimpleComputingService simpleComputingService) {
        this.simpleComputingService = simpleComputingService;
    }

    public SimpleComputingService getSimpleComputingService() {
        return simpleComputingService;
    }
}
