package org.fwx.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.SortedMap;

/**
 * [
 *  Channel 通道相关示例
 *  一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 *  二、通道的主要实现类
 *  	java.nio.channels.Channel 接口：
 *  		|--FileChannel
 *  		|--SocketChannel
 *  		|--ServerSocketChannel
 *  		|--DatagramChannel
 *
 *  三、获取通道
 *  1. Java 针对支持通道的类提供了 getChannel() 方法
 *  		本地 IO：
 *  		FileInputStream/FileOutputStream
 *  		RandomAccessFile
 *
 *  		网络IO：
 *  		Socket
 *  		ServerSocket
 *  		DatagramSocket
 *
 *  2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 *  3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 *  四、通道之间的数据传输
 *  transferFrom()
 *  transferTo()
 *
 *  五、分散(Scatter)与聚集(Gather)
 *  分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 *  聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 *  六、字符集：Charset
 *  编码：字符串 -> 字节数组
 *  解码：字节数组  -> 字符串
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/3 21:33 ]
 */
public class D02ChannelTest {

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    @Test
    public void test(){

        // 创建输入输出流
        FileInputStream is = null;
        FileOutputStream os = null;
        FileChannel isChannel = null;
        FileChannel osChannel = null;
        try {
            is = new FileInputStream("1.jpg");
            os = new FileOutputStream("2.jpg");

            // 根据输入输出流获取对应的通道
            isChannel = is.getChannel();
            osChannel = os.getChannel();

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);


            // 执行文件拷贝
            while (isChannel.read(buffer) != -1){
                // 缓冲区转为读模式
                buffer.flip();
                // 写数据
                osChannel.write(buffer);
                // 清空缓冲区
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 释放资源
        try {
            if(osChannel != null){osChannel.close();}
            if(Objects.nonNull(isChannel)){isChannel.close();}
            if(Objects.nonNull(os)){os.close();}
            if(Objects.nonNull(is)){is.close();}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    @Test
    public void test1() throws IOException {
        FileChannel isChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel osChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.CREATE_NEW,StandardOpenOption.READ,StandardOpenOption.WRITE);

        // 内存映射文件
        MappedByteBuffer ismap = isChannel.map(FileChannel.MapMode.READ_ONLY, 0, isChannel.size());
        MappedByteBuffer osmap = osChannel.map(FileChannel.MapMode.READ_WRITE, 0, isChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[ismap.limit()];
        ismap.get(bytes);
        osmap.put(bytes);

        osChannel.close();
        isChannel.close();
    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    @Test
    public void test2() throws IOException {
        FileChannel isChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel osChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        isChannel.transferTo(0,isChannel.size(),osChannel);

        osChannel.close();
        isChannel.close();
    }

    /**
     * 分散和聚集
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile file = new RandomAccessFile("reportData.sh", "rw");

        // *** 数据分散写到通道中
        // 1.获取通道
        FileChannel inChannel = file.getChannel();

        // 2.获取缓冲区
        ByteBuffer allocate1 = ByteBuffer.allocate(512);
        ByteBuffer allocate2 = ByteBuffer.allocate(1024);

        // 3.缓冲区数组
        ByteBuffer[] byteBuffers = new ByteBuffer[]{allocate1,allocate2};

        // 4.读数据到缓冲区
        inChannel.read(byteBuffers);
        for (ByteBuffer byteBuffer : byteBuffers) {
            // 切换读模式
            byteBuffer.flip();
//            System.out.println("byteBuffer = " + new String(byteBuffer.array(),0,byteBuffer.limit()));
            System.out.println("byteBuffer = " + new String(byteBuffer.array()));
            System.out.println("-------------- 数据分割线 -------------");
        }

        // *** 数据聚集写到文件中
        RandomAccessFile file1 = new RandomAccessFile("2.sh", "rw");
        FileChannel outChannel = file1.getChannel();
        outChannel.write(byteBuffers);
    }

    /**
     * 字符集
     */
    @Test
    public void test4() throws CharacterCodingException {
        // *** 支持的字符集列表
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        // charsets.entrySet().stream().forEach(kv -> System.out.println(kv.getKey() + ":" + kv.getValue()));

        // *** 字符集编解码
        // 编码
        Charset charset = Charset.forName("GBK");
        // 编码器
        CharsetEncoder encoder = charset.newEncoder();
        // 解码器
        CharsetDecoder decoder = charset.newDecoder();

        // 造数据
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("翔哥牛皮！");
        buffer.flip();

        // 数据编码
        ByteBuffer encode = encoder.encode(buffer);
        // encode.flip(); 这里转成读模式会报错，没有理解！！！
        for (int i = 0; i < 10; i++) {
            System.out.println(encode.get(i));
        }

        System.out.println("-------------- 数据分割线 -------------");

        // 数据解码
        CharBuffer decode = decoder.decode(encode);
        System.out.println("decode = " + decode);

        System.out.println("-------------- 数据分割线 -------------");
        Charset charset1 = Charset.forName("UTF-8");
//        Charset charset1 = Charset.forName("GBK");
        // 重置 position，重复读
        encode.rewind();
        CharBuffer decode1 = charset1.decode(encode);
        System.out.println("decode1 = " + decode1);

    }
}
