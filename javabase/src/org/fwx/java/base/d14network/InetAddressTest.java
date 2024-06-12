package org.fwx.java.base.d14network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @ClassName InetaAddressTest
 * @Description TODO
 * @Author Fwx
 * @Date 2023/6/1 17:24
 * @Version 1.0
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress name = InetAddress.getByName("192.168.0.1");
        System.out.println("name = " + name);
        System.out.println("----------------");

        InetAddress name1 = InetAddress.getByName("www.baidu.com");
        System.out.println("name1 = " + name1);
        String hostName = name1.getHostName();
        System.out.println("hostName = " + hostName);
        String hostAddress = name1.getHostAddress();
        System.out.println("address = " + hostAddress);
        System.out.println("----------------");


        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);


    }
}
