package org.fwx.lambda.optional;

import org.fwx.lambda.bean.Boy;
import org.fwx.lambda.bean.Girl;
import org.junit.Test;

import java.util.Optional;

/**
 * [
 *  Optional 功能测试。
 *  可能为空的参数用 Optional 封装
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/9 20:32 ]
 */
public class OptionalTest {
    @Test
    public void test(){
        // 参数 boy、gril 为 null时
        //String grilName = getGrilName(Optional.ofNullable(null));

        // 参数 gril 为 null时
        // Optional<Boy> boy = Optional.ofNullable(new Boy());
        // String grilName = getGrilName(boy);


        Optional<Girl> gril = Optional.ofNullable(new Girl("lili"));
        Optional<Boy> boy = Optional.ofNullable(new Boy(gril));
        String grilName = getGrilName(boy);
        System.out.println("grilName = " + grilName);

    }

    public String getGrilName(Optional<Boy> boy){
        return boy.orElse(new Boy())
                .getGirl()
                .orElse(new Girl("null"))
                .getName();
        //return "";
    }
}
