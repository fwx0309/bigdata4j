package org.fwx.jvm.c8;

import java.util.ArrayList;
import java.util.Random;

/**
 * [ 使用 java visualVM 或 jprofile 查看 minorGc 情况 ]
 * 这段Java代码用于演示内存溢出（Out of Memory）的情况。
 * OOMTest 类包含一个 main 方法，该方法创建一个 ArrayList<PicObj>，并在无限循环中不断向列表中添加 PicObj 对象。
 * PicObj 类表示一个图片对象，包含一个 byte 数组 pic，用于存储图片数据。
 * 在每次循环中，会创建一个大小随机的 PicObj 对象，并将其添加到列表中，然后暂停线程200毫秒。
 * 由于没有进行任何内存释放操作，且不断向列表中添加对象，最终会导致内存溢出错误。
 *
 * 测试参数 -Xms256m -Xmx256m
 */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<PicObj> picObjs = new ArrayList<>();
        while (true){
            try {
                PicObj picObj = new PicObj(new Random().nextInt(1024 * 1024));

                picObjs.add(picObj);

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PicObj{
    private byte[] pic;

    public PicObj(int data){
        this.pic = new byte[data];
    }
}
