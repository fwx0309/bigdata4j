package org.fwx.jvm.c8;

import java.util.ArrayList;
import java.util.Random;

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
