package org.fwx.java.base.d05loop;

/**
 * [ 乘法表 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/6 10:03 ]
 */
public class ForTest02 {
    public static void main(String[] args) {
        // 行
        for (int i = 1; i <= 9; i++) {
            // 列
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
