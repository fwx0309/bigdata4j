package org.fwx.java.datastruct;

import java.util.Scanner;

/**
 * [
 *  环形数组队列：
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/4 16:31 ]
 */
public class T02CircleArrayQueue {
    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(4);

        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("-------------------------");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            System.out.println("请输入：");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addElement(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getElement();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.showHead();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");


    }
}

class CircleArrayQueue{
    // 表示数组的最大容量大容量
    private int maxSive;

    // front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    // front 的初始值 = 0
    private int front;

    // 队列尾，最后一个元素加一的位置
    private int rear;

    // 该数据用于存放数据, 模拟队列
    int[] arr;

    /**
     * 构造方法中初始化队列数组的大小
     * @param maxSive
     */
    public CircleArrayQueue(int maxSive){
        //说明设置4, 其队列的有效数据最大是3
        this.maxSive = maxSive + 1;
        front = 0;
        rear = 0;
        arr = new int[maxSive];
    }

    /**
     * 队列是否为空: front == rear
     * @return
     */
    public boolean isEmpty(){
        boolean flag = false;
        if(front == rear){
            System.out.println("队列为空");
            flag = true;
        }
        return flag;
    }

    /**
     * 队列是否为满
     * @return
     */
    public boolean isFull(){
        boolean flag = false;
        if((rear + 1) % maxSive == front){
            System.out.println("队列已满");
            flag = true;
        }
        return flag;
    }

    /**
     * 添加一个元素
     */
    public void addElement(int data){
        if(isFull()){
            return;
        }
        //直接将数据加入
        arr[rear] = data;
        //将 rear 后移, 这里必须考虑取模
        rear = (rear + 1) % maxSive;
    }

    /**
     * 取出一个元素
     */
    public int getElement(){
        int data = -1;
        if(!isEmpty()){
            // 这里需要分析出 front是指向队列的第一个元素
            // 1. 先把 front 对应的值保留到一个临时变量
            // 2. 将 front 后移, 考虑取模
            // 3. 将临时保存的变量返回
            data = arr[front];
            front = (front + 1) % maxSive;
        }
        return data;
    }

    /**
     * 查看第一个数据
     * @return
     */
    public int showHead(){
        int data = -1;
        if (!isEmpty()){
            data = arr[front];
        }
        return data;
    }

    /**
     * 有效数据量
     * @return
     */
    public int validNum(){
        /**
         * 如：
         *  maxSize= 3 + 1
         *  front=0
         *  rear=2
         */
        int data = 0;
        if (!isEmpty()){
            data = ((rear + maxSive - front) % maxSive );
        }
        return data;
    }

    /**
     * 显示队列数据
     */
    public void show(){
        for (int i = front; i < front + validNum(); i++) {
            System.out.println("data = " + arr[i % maxSive] + ",i=" + i % maxSive);
        }
    }

}
