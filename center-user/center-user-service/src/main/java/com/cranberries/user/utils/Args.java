package com.cranberries.user.utils;

/**
 * @author ：lidemin
 * @version ：V1.0
 * @program ：cranberries
 * @date ：Created in 2021/03/02 11:30
 * @description ：参数枚举类
 */
public enum  Args {

    corePoolSize(1,4),

    maxPoolSize(2,256);

    private Integer index;

    private Integer info;


    Args(int i, Integer s) {
        this.index = i;
        this.info = s;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }
}
