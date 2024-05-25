package org.fwx.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> synchronizedMap = Collections.synchronizedMap(hashMap);
//        synchronizedMap.put("kay","value");

        final char[] chars = new char[]{'a','b','c'};

        chars[0] = 'd';

        System.out.println(chars);

    }
}
