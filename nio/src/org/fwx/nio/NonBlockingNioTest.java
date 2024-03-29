package org.fwx.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * [
 *  一、使用 NIO 完成网络通信的三个核心：
 *
 *  1. 通道（Channel）：负责连接
 *
 *  	   java.nio.channels.Channel 接口：
 *  			|--SelectableChannel
 *  				|--SocketChannel
 *  				|--ServerSocketChannel
 *  				|--DatagramChannel
 *
 *  				|--Pipe.SinkChannel
 *  				|--Pipe.SourceChannel
 *
 *  2. 缓冲区（Buffer）：负责数据的存取
 *
 *  3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/9 22:47 ]
 */
public class NonBlockingNioTest {

    @Test
    public void client() throws IOException {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("localhost", 8989));

        //2. 切换非阻塞模式
        sChannel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4. 发送数据给服务端
        buffer.put(new Date().toString().getBytes());
        buffer.flip();
        sChannel.write(buffer);

        //5. 关闭通道
        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(8989));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {

            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                //8. 获取准备“就绪”的是事件
                SelectionKey selectionKey = iterator.next();

                //9. 判断具体是什么事件准备就绪
                if(selectionKey.isAcceptable()){
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector,SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();

                    //14. 读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (sChannel.read(buffer) != -1){
                        buffer.flip();
                        System.out.println("buffer = " + new String(buffer.array(),0,buffer.limit()));
                        buffer.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                iterator.remove();
            }
        }
    }
}
