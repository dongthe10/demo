package com.hollly.example;

import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @author hollly
 * @date 2021/12/19 23:37
 */
public class CuratorLockMain {


    public static void main(String[] args) {
        ;
        new InterProcessMutex(ClientFactory.createSimple("localhost:2181"), "mutex");
    }
}
