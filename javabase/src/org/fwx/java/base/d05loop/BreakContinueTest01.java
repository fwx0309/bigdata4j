package org.fwx.java.base.d05loop;

/**
 * [ BreakContinue ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/12 19:34 ]
 */
public class BreakContinueTest01 {
    public static void main(String[] args) {

        // 跳出多重循环，标签名字可以自定义
        here:
        for (int i = 0; i < 5; i++) {
            // here
            for (int j = 0; j < 5; j++) {
                if(j == 1){
                    break here;
                }
                System.out.print(j);
            }
            System.out.println(" fori换行");
        }

        System.out.println("-----------");

        here1:
        for (int i = 0; i < 5; i++) {
            // here
            for (int j = 0; j < 5; j++) {
                if(j == 1){
                    continue here1;
                }
                System.out.print(j);
            }
            System.out.println(" fori换行");
        }
    }
}
