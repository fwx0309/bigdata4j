package org.fwx.d03builer;

/**
 * @ClassName AnimalBuiler
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 20:45
 * @Version 1.0
 */
public abstract class AnimalBuiler {
    protected Animal animal = new Animal();

    public abstract void buildType();

    public abstract void buildName(String name);

    public Animal build(){
        return animal;
    }

}
