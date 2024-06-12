package org.fwx.d03builer;

/**
 * @ClassName Animal
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 20:44
 * @Version 1.0
 */
public class Animal {
    private String type;
    private String name;

    public Animal() {
    }

    public Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
