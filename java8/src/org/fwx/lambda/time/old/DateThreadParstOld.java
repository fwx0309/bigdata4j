package org.fwx.lambda.time.old;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

/**
 * [
 *  老版的日期工具类会有线程安全问题，需要自己做线程安全处理
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/10 10:17 ]
 */
public class DateThreadParstOld {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 没有处理线程安全的 SimpleDateFormat。 这种方式会有线程安全报错问题！！！
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return dateFormat.parse("20220610");
            }
        };*/

        // 使用 ThreadLocal 封装的 SimpleDateFormat
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return SimpleDateUtil.convert("20220610");
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(10);
        ArrayList<Future<Date>> dates = new ArrayList<>();

        for (int i = 0; i < 10 ; i++){
            dates.add(service.submit(task));
        }

        for (Future<Date> future : dates) {
            System.out.println(future.get());
        }

        service.shutdown();
    }
}

/**
 * 将 SimpleDateFormat 用 ThreadLocal 包装起来
 */
class SimpleDateUtil {

    private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return sdf.get().parse(source);
    }
}
