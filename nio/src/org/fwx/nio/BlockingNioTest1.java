package org.fwx.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * [ 阻塞 nio 获取服务器返回信息 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/9 17:52 ]
 */
public class BlockingNioTest1 {

    /**
     * 客户端
     * @throws IOException
     */
    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8989));
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (fileChannel.read(buffer) != -1){
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }

        // 关闭输出通道，表示传输数据完成
        sChannel.shutdownOutput();

        //接收服务端的反馈
        while (sChannel.read(buffer) != -1){
            buffer.flip();
            System.out.println("buffer = " + new String(buffer.array(),0,buffer.limit()));
            buffer.clear();
        }

        sChannel.close();
        fileChannel.close();
    }

    /**
     * 服务器端
     * @throws IOException
     */
    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(8989));
        SocketChannel sChannel = ssChannel.accept();

        FileChannel fileChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (sChannel.read(buffer) != -1){
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        // 发送反馈给客户端
        buffer.put("接收数据成功！".getBytes());
        buffer.flip();
        sChannel.write(buffer);

        ssChannel.close();
        sChannel.close();
        fileChannel.close();

        System.out.println("server close!");
    }
}
