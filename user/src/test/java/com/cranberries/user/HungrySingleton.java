package com.cranberries.user;

public class HungrySingleton {
    // 实例化对象
    private static HungrySingleton hungrySingleton = new HungrySingleton();
    // 构造函数
    private HungrySingleton(){

    }
    // 公开获取实例对象同步方法
    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

}
