package org.fwx;

import java.util.ArrayList;
import java.util.Arrays;

public class TemplateDemo {
    // 4.prsf
    private static final String PRSF = "";
    // psf
    public static final String PSF = "";
    // psfi
    public static final int NUM = 1;
    // psfs
    public static final String PSFS = "";

    // 1.psvm maim
    public static void main(String[] args) {
        // 2.sout
        System.out.println();
        // soutm
        System.out.println("TemplateDemo.main");
        // soutp
        System.out.println("args = " + Arrays.deepToString(args));
        // soutv
        int mun = 10;
        System.out.println("mun = " + mun);


        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");


    }

    public void printData(ArrayList<String> list){
        // 3.ifn / list.null
        if (list == null) {
            System.out.println("list is null! ");
        }

        // inn / list.nn
        if (list != null) {
            System.out.println("list is not null! ");
        }

    }
}
