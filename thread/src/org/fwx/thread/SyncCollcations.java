package org.fwx.thread;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 安全集合类
 */
public class SyncCollcations {
    public static void main(String[] args) {

        // list
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

        // set
        CopyOnWriteArraySet<String> hashSet = new CopyOnWriteArraySet<>();

        // map
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();


        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                arrayList.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("list:"+arrayList);

            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                hashSet.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("set:"+hashSet);

            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                hashMap.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println("map:"+hashMap);
            },String.valueOf(i)).start();
        }
    }
}
