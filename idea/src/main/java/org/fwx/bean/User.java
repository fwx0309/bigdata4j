package org.fwx.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [使用 GSONFormat插件，对 json 数据生成的实体类。
 * 1.首先新建你要的JavaBean实体类，类名根据你的需求来定，无任何内容，就一个空类；
 * 2.然后直接使用快捷键ALT+S或则对着类名右击—>Generate—>点击GsonFormat；
 * 3.把你要解析的Json数据直接粘过来，点击就OK就行了(如果觉得丑不利查看，可以还可格式化json)。]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/6 9:03]
 */
@lombok.NoArgsConstructor
@lombok.Data
public class User {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Double age;
    @JsonProperty("six")
    private String six;
}
