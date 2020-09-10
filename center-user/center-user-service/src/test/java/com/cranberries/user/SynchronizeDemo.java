package com.cranberries.user;

import com.cranberries.user.model.User;

public class SynchronizeDemo {
    public static void main(String[] args) {
        synchronized (User.class) {
            System.out.println("Synchronize");
        }
    }
}
