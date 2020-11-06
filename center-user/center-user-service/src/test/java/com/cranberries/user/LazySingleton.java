package com.cranberries.user;

public class LazySingleton {


    /**
     * 懒汉单例
     */
    // 初始化
    private static LazySingleton lazySingleton;

    // 锁对象
    private static final String key = "lock_key";

    // 构造函数
    private LazySingleton() {

    }

    ;

    // 对外提供实例化方法
    public LazySingleton getInstance() {
        if (lazySingleton == null) {
            // 加同步锁，保证线程安全
            synchronized (key) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }
}
