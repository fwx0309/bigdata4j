package org.fwx.lambda.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * [ Boy 实体类 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/9 20:29 ]
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Boy {
    private Optional<Girl> girl = Optional.empty();

    public Boy() {
    }

    public Boy(Optional<Girl> girl) {
        this.girl = girl;
    }

    public Optional<Girl> getGirl() {
        return girl;
    }

    public void setGirl(Optional<Girl> girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
