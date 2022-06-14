package org.fwx.lambda.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * [ JDK 8 的并行流 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/9 16:39 ]
 */
public class ForkjionJDK8Test {
    @Test
    public void test1(){
        Instant strat = Instant.now();

        long reduce = LongStream.range(0L, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println("reduce = " + reduce);
                /*.forEach((d) -> {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    System.out.println("d = " + d);
                });*/
        Instant end = Instant.now();

        Duration between = Duration.between(strat, end);
        System.out.println("between = " + between.toMillis());
    }
}
