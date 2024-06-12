package org.fwx.d03builer;

/**
 * @ClassName ClientTest
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 20:52
 * @Version 1.0
 */
public class ClientTest {
    public static void main(String[] args) {
        AnimalBuiler animalBuiler = new DogBuiler();
        animalBuiler.buildType();
        animalBuiler.buildName("旺财");
        Animal dog = animalBuiler.build();
        System.out.println("dog = " + dog);
    }
}
