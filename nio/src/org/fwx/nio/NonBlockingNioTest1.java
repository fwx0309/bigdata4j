package org.fwx.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * [ udp 协议，非阻塞 nio ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/10 20:03 ]
 */
public class NonBlockingNioTest1 {

    /**
     * 发送端
     * @throws IOException
     */
    @Test
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("翔哥牛皮！".getBytes());
        buffer.flip();

        datagramChannel.send(buffer, new InetSocketAddress("localhost", 8989));

        datagramChannel.close();
    }

    /**
     * 接收端
     * @throws IOException
     */
    @Test
    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(8989));

        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isReadable()) {
                    datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println("buffer = " + new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
            }

            iterator.remove();
        }
    }
}
