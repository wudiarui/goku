package com.joinsystem.goku.service.type;

/**
 * <p>Date : 16/6/15</p>
 * <p>Time : 下午4:25</p>
 *
 * @author jerry
 */
public enum SimpleComputeType {
    MEAN("mean"),SUM("sum"),MAX("max"),MIN("min");

    private String info;

    SimpleComputeType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
