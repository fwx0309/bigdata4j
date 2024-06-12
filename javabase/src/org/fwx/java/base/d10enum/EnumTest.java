package org.fwx.java.base.d10enum;

/**
 * [ Enum 测试类 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/7/3 15:58 ]
 */
public class EnumTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println("spring = " + spring);

        Statu online = Statu.ONLINE;
        System.out.println("online = " + online);

        System.out.println("-----------------------------------");

        // 获取所有枚举对象
        for (Statu value : Statu.values()) {
            System.out.println("value = " + value);
        }

        // 获取指定对象化
        Statu online1 = Statu.valueOf("ONLINE");
        System.out.println("online1 = " + online1);
    }
}

/**
 * 带属性的枚举类
 */
enum Season{

    // 春天
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","冰天雪地");
    
    private final String name;
    private final String description;

    private Season(String name,String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

/**
 * 不带属性的枚举类
 */
enum Statu {
    // 在线
    ONLINE,
    // 离线
    OFFLINE;
}
