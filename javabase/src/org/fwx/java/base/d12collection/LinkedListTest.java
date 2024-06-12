package org.fwx.java.base.d12collection;

import java.util.LinkedList;

/**
 * @ClassName LinkedListTest
 * @Description 走读 LinkedList 源码
 * @Author Fwx
 * @Date 2023/4/4 15:11
 * @Version 1.0
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");

        for (String l : linkedList) {
            System.out.println("l = " + l);
        }

    }
}
