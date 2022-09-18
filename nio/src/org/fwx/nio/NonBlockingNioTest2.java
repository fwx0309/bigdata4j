package org.fwx.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * [
 *  Pipe: 两个线程间的单向数据传输
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/10 20:26 ]
 */
public class NonBlockingNioTest2 {

    /*@Test
    public void test() throws IOException {

    }*/

    /**
     * 以下代码用 junit 执行会有问题，结果出不来！！！
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("main......");
        Pipe pipe = Pipe.open();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("翔哥牛皮！".getBytes());

        /**
         * 接收数据
         */
        new Thread(){
            @Override
            public void run() {
                System.out.println("recieve...");
                Pipe.SourceChannel source = pipe.source();
                try {
                    Thread.sleep(1000);
                    System.out.println("recieve1...sleep...");
                    buffer.flip();
                    source.read(buffer);
                    System.out.println("buffer = " + new String(buffer.array(),0,buffer.limit()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        source.close();
                        System.out.println("recieve close!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }.start();

        /**
         * 发送数据
         */
        new Thread(){
            @Override
            public void run() {
                Pipe.SinkChannel sink = pipe.sink();
                try {
                    System.out.println("send...");
                    buffer.remaining();
                    buffer.flip();
                    sink.write(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        sink.close();
                        System.out.println("send close!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

        System.out.println("main close!");
    }
}
