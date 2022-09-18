package org.fwx.java.datastruct;

/**
 * [
 *  约瑟夫问题：
 *      单向环形链表
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/15 17:23 ]
 */
public class T05JosepfuCircleLinkedList {
    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();

        list.makeLisk(5);
        list.printList();
    }
}

/**
 * 单向环形链表类
 */
class CircleLinkedList{
    private JNode head = new JNode(-1);

    /**
     * 新增节点
     * @param num
     */
    public void makeLisk(int num){
        // 错误数值
        if (num < 1){
            return;
        }

        // 新增第一个节点
        head = new JNode(1);
        head.nextNode = head;

        // 只添加一个节点
        if (1 == num){
            return;
        }

        JNode currNode = head;
        for (int i = 2; i <= num; i++) {
            JNode newNode = new JNode(i);

            currNode.nextNode = newNode;
            newNode.nextNode = head;

            currNode = newNode;
        }
    }

    /**
     * 打印链表
     */
    public void printList(){
        if(head.no == -1){
            System.out.println("链表为空！");
        }

        if (head.nextNode.no == head.no){
            System.out.println(head);
        }

        // 当前数据指针
        JNode temp = head;
        while (true){
            System.out.println(temp);
            temp = temp.nextNode;

            if(temp.no == head.no){
                break;
            }
        }
    }
}

/**
 * 节点类
 */
class JNode{
    public int no;
    public JNode nextNode;

    public JNode(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "JNode{" +
                "no=" + no +
                '}';
    }
}
