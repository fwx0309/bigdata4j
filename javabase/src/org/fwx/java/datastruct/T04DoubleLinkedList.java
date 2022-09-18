package org.fwx.java.datastruct;

/**
 * [
 *  双向链表
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/15 8:36 ]
 */
public class T04DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();

        // 1. 添加数据
        /*linkedList.addNode(new DoubleNode(2, "lisi"));
        linkedList.addNode(new DoubleNode(4, "zhaoliu"));
        linkedList.addNode(new DoubleNode(3, "wangwu"));
        linkedList.addNode(new DoubleNode(1, "zhangsan"));*/

        // 2. 添加数据（有序）
        linkedList.addNodeByOrder(new DoubleNode(2, "lisi"));
//        linkedList.addNodeByOrder(new DoubleNode(2, "lisi2"));
        linkedList.addNodeByOrder(new DoubleNode(4, "zhaoliu"));
        linkedList.addNodeByOrder(new DoubleNode(3, "wangwu"));
        linkedList.addNodeByOrder(new DoubleNode(1, "zhangsan"));
//        linkedList.addNodeByOrder(new DoubleNode(4, "zhaoliu2"));
        linkedList.printList();


        // 3.删除数据
        System.out.println("-----------------");
        linkedList.deleteNode(new DoubleNode(1,""));
        linkedList.deleteNode(new DoubleNode(4,""));
        linkedList.printList();
    }
}

class DoubleLinkedList{
    private DoubleNode head = new DoubleNode(0,"head");

    /**
     * 添加一个节点
     * @param newNode
     */
    public void addNode(DoubleNode newNode){
        // 数据指针
        DoubleNode temp = head;

        while (true) {
            // 链表最后添加数据
            if(temp.nextNode == null){
                // 设置新数据的头节点
                newNode.preNode = temp;
                // 设置前面一个节点的 nextNode
                temp.nextNode = newNode;
                break;
            }
            temp = temp.nextNode;
        }

    }

    /**
     * 添加一个节点(有序)
     * @param newNode
     */
    public void addNodeByOrder(DoubleNode newNode){
        // 空链表添加数据
        if(head.nextNode == null){
            newNode.preNode = head;
            head.nextNode = newNode;
            return;
        }

        // 数据指针
        DoubleNode temp = head;
        // 匹配标记
        boolean flag = false;
        while (true){
            // 链表末尾
            if(temp.nextNode == null){
                break;
            }

            // 有相同数据
            if (temp.nextNode.num == newNode.num){
                System.out.println("重复数据：" + newNode);
                return;
            }

            // 匹配到插入位置。每次用当前数据的下一个节点去做比较
            if(temp.nextNode.num > newNode.num){
                flag = true;
                break;
            } else {
                temp = temp.nextNode;
            }
        }

        if(flag){
            newNode.nextNode = temp.nextNode;
            newNode.preNode = temp;
            // *** 这里顺序不能错，
            temp.nextNode.preNode = newNode;
            temp.nextNode = newNode;

        } else {
            newNode.preNode = temp;
            temp.nextNode = newNode;
        }

    }

    /**
     * 删除节点
     * @param delNode
     */
    public void deleteNode(DoubleNode delNode){
        if(head.nextNode == null){
            System.out.println("链表为空，无数据删除！");
            return;
        }

        // 数据指针
        DoubleNode temp = head.nextNode;

        while (true){
            if(temp == null){
                System.out.println("没有找到需要删除的数据！");
                return;
            }

            if (temp.num == delNode.num){
                temp.preNode.nextNode = temp.nextNode;

                if(temp.nextNode != null){
                    temp.nextNode.preNode = temp.preNode;
                }
                return;
            }

            temp = temp.nextNode;
        }
    }

    /**
     * 修改功能 （比较简单，先不做实现）
     * @param updataNode
     */
    public void updataNode(DoubleNode updataNode){

    }

    /**
     * 显示链表中的数据
     */
    public void printList(){
        // 链表为空
        if(head.nextNode == null){
            System.out.println("链表为空!");
        }

        // 数据指针
        DoubleNode temp = head.nextNode;

        while (temp != null){
            System.out.println(temp);
            temp = temp.nextNode;
        }
    }
}

class DoubleNode{
    public int num;
    public String name;
    public DoubleNode preNode;
    public DoubleNode nextNode;

    public DoubleNode(int num,String name){
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
