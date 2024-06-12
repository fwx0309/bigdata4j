package org.fwx.d03builer;

/**
 * @ClassName DogBuiler
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 20:50
 * @Version 1.0
 */
public class DogBuiler extends AnimalBuiler{
    @Override
    public void buildType() {
        animal.setType("狗");
    }

    @Override
    public void buildName(String name) {
        animal.setName(name);
    }
}
