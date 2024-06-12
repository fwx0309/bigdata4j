package org.fwx.java.base.d09compar;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName ComparTest
 * @Description TODO
 * @Author Fwx
 * @Date 2023/3/26 17:33
 * @Version 1.0
 */
public class ComparTest {
    public static void main(String[] args) {
        User[] users = new User[4];
        users[0] = new User("zhangsan" , 13);
        users[1] = new User("zhangsan" , 12);
        users[2] = new User("lisi" , 14);
        users[3] = new User("wangwu" , 14);

        Arrays.sort(users);

        // 接口实现的比较方式
        System.out.println(Arrays.toString(users));

        Arrays.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    if (o1.getAge() < o2.getAge()) {
                        return 1;
                    } else if (o1.getAge() > o2.getAge()) {
                        return -1;
                    } else {
                        return -o1.getName().compareTo(o2.getName());
                    }
                }
                throw new RuntimeException("class type mismatch !");
            }
        });

        // 自定义比较器方式
        System.out.println(Arrays.toString(users));
    }
}

/**
 * User 类 实现 Comparable 接口
 */
class User implements Comparable{
    private String name;
    private int age;

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {
            User o1 = (User) o;
            if(this.age > o1.age){
                return 1;
            } else if (this.age < o1.age) {
                return -1;
            } else {
                return this.name.compareTo(o1.name);
            }
        }

        throw new RuntimeException("class type mismatch !");
    }
}
