package org.fwx.jvm.c8;

/**
 * JVM 常用参数
 *
 * -XX:+PrintFlagsInitial : 查看所有参数的默认初始值
 * -XX:+PrintFlagsFinal : 查看所有参数的最终值（可能存在修改，不在是初始值）
 *      具体查看某个参数的指令 : 1.jps 查看当前运行的进程
 *                           2.jinfo -flag SurviverRatio PID （SurviverRatio 为 jvm 参数名）
 *                           3.jinfo -flag NewRatio 2508 （2508 为进程ID，）
 * -XX:+PrintGCDetails ： 输出详细的 GC 处理日志
 *
 * -Xms:初始堆空间内存值，例如 -Xms512m（默认为物理内存的1/64）
 * -Xmx:最大堆空间内存值，例如 -Xmx1g （默认为物理内存的1/4）
 * -Xmn:设置新生代的大小 （初始值及最大值，一般不会手动设置）
 * -XX:NewRatio : 配置新生代和老年代在堆结构的占比，默认新生代：老年代 = 1:2
 * -XX:ServiverRatio : 配置新生代中 Eden 和 S0、S1 空间占比，默认Eden:S0:S1 = 8:1:1，但是实际是：Eden:S0:S1 = 6:1:1
 * -XX:MaxTenuringThreshold : 设置新生代垃圾的最大年龄
 * -XX:+PrintGCDetails : 输出详细的 GC 处理日志
 *      打印简要 GC 信息: 1.-XX:+PrintGC
 *                      2.-verbose:gc
 * -XX:HandlePromotionFailure: 是否配置空间分配担保 （！！！JDK7及以后这个参数就失效了）
 *
 * 逃逸分析参数：
 *      在JDK 6u32 版本之后，HotSpot 中默认就已经开启了逃逸分析。
 *      如果使用的是较早的版本，开发人员则可以通过：
 *      选项 -XX:+DoEscapeAnalysis 显示开启逃逸分析
 *      通过 -XX:+PrintEscapeAnalysis 查看逃逸分析的筛选结果。
 *      -XX:+EliminateAllocations : 开启标量替换
 *
 * -XX:+HeapDumpOnOutOfMemoryError: 当内存溢出时，会自动将堆转储快照保存到文件中
 *
 * GC相关参数：
 *  -XX:+PrintCommandLineFlags : 打印启动参数
 *  -XX:+UseSerialGC:表明新生代使用Serial GC ，同时老年代使用Serial Old GC
 *  -XX:+UseParNewGC：标明新生代使用ParNew GC
 *  -XX:+UseParallelGC:表明新生代使用Parallel GC
 *  -XX:+UseParallelOldGC : 表明老年代使用 Parallel Old GC
 *  说明：二者可以相互激活
 *  -XX:+UseConcMarkSweepGC：表明老年代使用CMS GC。同时，年轻代会触发对ParNew 的使用
 *
 * 标量替换参数设置：
 *      参数 -XX:+EliminateAllocations : 开启了标量替换（默认打开），允许将对象打散分配在栈上。
 */
public class D03JvmOptions {
    public static void main(String[] args) {
        System.out.println("我只是个打酱油的！");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
