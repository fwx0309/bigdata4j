package org.fwx.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * [
 *  Nio Buffer 示例
 *
 *  一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 *  根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 *  ByteBuffer
 *  CharBuffer
 *  ShortBuffer
 *  IntBuffer
 *  LongBuffer
 *  FloatBuffer
 *  DoubleBuffer
 *
 *  上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 *  二、缓冲区存取数据的两个核心方法：
 *  put() : 存入数据到缓冲区中
 *  get() : 获取缓冲区中的数据
 *
 *  三、缓冲区中的四个核心属性：
 *  capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 *  limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 *  position : 位置，表示缓冲区中正在操作数据的位置。
 *
 *  mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 *
 *  0 <= mark <= position <= limit <= capacity
 *
 *  四、直接缓冲区与非直接缓冲区：
 *  非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 *  直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/20 11:12 ]
 */
public class BufferTest {

    /**
     * allocate: 初始化 buffer
     * put: 存数据
     * flip: 切换读模式
     * get: 取数据
     * rewind: 重置 position，重复读
     * clear: 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
     */
    @Test
    public void test(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("-------------------- 初始化信息 ---------------------");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        System.out.println("-------------------- put ---------------------");
        String data = "abcde";
        buffer.put(data.getBytes());
        // buffer.put(data.getBytes(),0,data.getBytes().length);
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        System.out.println("-------------------- flip:切换读取数据模式 ---------------------");
        buffer.flip();
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        System.out.println("-------------------- get ---------------------");
        byte[] bytes = new byte[data.getBytes().length];
        buffer.get(bytes);
        System.out.println("bytes = " + new String(bytes));
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        System.out.println("-------------------- rewind:重复读 ---------------------");
        buffer.rewind();
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        // byte[] bytes1 = new byte[buffer.limit()];
        // buffer.get(bytes1);
        // System.out.println("bytes1 = " + new String(bytes1));
        // System.out.println("buffer.capacity() = " + buffer.capacity());
        // System.out.println("buffer.limit() = " + buffer.limit());
        // System.out.println("buffer.position() = " + buffer.position());


        System.out.println("-------------------- clear: 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态 ---------------------");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.get() = " + (char)buffer.get());
    }

    /**
     * mark: position 标记
     * reset: 重置 position ，至 mark 标记的position。
     */
    @Test
    public void test1(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 存入数据
        String data = "abcde";
        buffer.put(data.getBytes());

        // 切换读模式
        buffer.flip();

        // 读取数据
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes,0,2);
        System.out.println("bytes = " + new String(bytes,0,2));
        System.out.println("buffer.position() = " + buffer.position());

        // *** 使用 mark 标记
        buffer.mark();
        System.out.println("mark: buffer.position() = " + buffer.position());

        // 读取数据
        byte[] bytes1 = new byte[buffer.limit()];
        buffer.get(bytes1,2,2);
        System.out.println("bytes1 = " + new String(bytes1,2,2));
        System.out.println("buffer.position() = " + buffer.position());

        // rewind
        buffer.reset();
        System.out.println("rewind: buffer.position() = " + buffer.position());

        //判断缓冲区中是否还有剩余数据
        if(buffer.hasRemaining()){
            System.out.println("buffer.remaining() = " + buffer.remaining());
        }
    }

    /**
     * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
     * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
     */
    @Test
    public void test2(){
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        System.out.println("allocate.isDirect() = " + allocate.isDirect());

        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);
        System.out.println("allocateDirect.isDirect() = " + allocateDirect.isDirect());
    }
}
