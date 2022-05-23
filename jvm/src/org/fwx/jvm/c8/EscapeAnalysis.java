package org.fwx.jvm.c8;

/**
 * 逃逸分析：
 *
 * 一、栈上分配。将堆分配转化为栈分配。如果一个对象在子程序中被分配，要使指向该对象的指针永远不会逃逸，
 * 对象可能是栈分配的候选，而不是堆分配。
 *
 * JIT编译器在编译期间根据逃逸分析的结果，发现如果一个对象并没有逃逸出方法的话，就可能被优化成栈上分配。分配完成后，
 * 继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。这样就无须进行垃圾回收了。
 *
 * 常见的栈上分配的场景：给成员变量赋值、方法返回值、实例引用传递
 *
 *
 * 二、同步省略。如果一个对象被发现只能从一个线程被访问到，那么对于这个对象的操作可以不考虑同步。
 *
 * 线程同步的代价是相当高的，同步的后果是降低并发性和性能。
 *
 * 在动态编译同步块的时候，JIT编译器可以借助逃逸分析来判断同步块所使用的锁对象是否只能够被一个线程访问而没有被发布到其他线程。
 * 如果没有，那么JIT编译器在编译这个同步块的时候就会取消对这部分代码的同步。这样就能大大提高并发性和性能。这个取消同步的过程就叫同步省略，也叫锁消除。
 *
 * 三、分离对象或标量替换。有的对象可能不需要作为一个连续的内存结构存在也可以被访问到，
 * 那么对象的部分（或全部）可以不存储在内存，而是存储在CPU寄存器中。
 *
 * 标量是一个无法再分解成更小的数据的数据。Java中原始数据类型就是标量。
 *
 * 相对的，那些还可以分解的数据叫做聚合量，Java中的对象就是聚合量，因为它可以分解成其它聚合量和标量
 *
 * 在JIT阶段，如果经过逃逸分析，发现一个对象不会被外界访问的话，那么经过JIT优化，就会把这个对象拆解成若干个其中包含的若干个成员变量来代替。
 * 这个过程就是标量替换。
 *
 * 样例jvm参数：
 * -Xms1g -Xmx1g -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms1g -Xmx1g -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms256m -Xmx256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms256m -Xmx256m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * 从 jvisualvm.exe 的抽样器中查看内存中 user 实例数，观察 GC 信息
 */
public class EscapeAnalysis {
    public static void main(String[] args) {

        // 开始时间
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            User user = new User();
        }

        // 结束时间
        long endTime = System.currentTimeMillis();

        // 耗时
        System.out.println("耗时：" + (endTime - startTime));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class User {}