package org.fwx.java.datastruct;

/**
 * [
 *  单向链表：
 *      前一个节点的地址，指向后一个节点
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/8 15:00 ]
 */
public class T03SingleLinkedLisk {
    public static void main(String[] args) {
        SingleLinkedLisk linkedLisk = new SingleLinkedLisk();

        // 1.测试普通的添加数据
        /*linkedLisk.addNode(new Node(2, "lisi"));
        linkedLisk.addNode(new Node(4, "zhaoliu"));
        linkedLisk.addNode(new Node(1, "zanshan"));
        linkedLisk.addNode(new Node(3, "wanwu"));*/

        // 2.测试有序的添加数据
        linkedLisk.addNodeByOrder(new Node(2, "lisi"));
        linkedLisk.addNodeByOrder(new Node(2, "lisi"));
        linkedLisk.addNodeByOrder(new Node(4, "zhaoliu"));
        linkedLisk.addNodeByOrder(new Node(1, "zanshan"));
        linkedLisk.addNodeByOrder(new Node(3, "wanwu"));
        linkedLisk.addNodeByOrder(new Node(3, "wanwu"));
        linkedLisk.printList();

        // 3.测试修改数据
//        System.out.println("修改后的数据：");
//        linkedLisk.updateNode(new Node(5, "wanwu1--"));
//        linkedLisk.printList();

        // 4.测试删除数据
//        System.out.println("删除后的数据：");
        linkedLisk.deleteNode(new Node(1, "wanwu1--"));
        linkedLisk.deleteNode(new Node(4, "wanwu1--"));
//        linkedLisk.deleteNode(new Node(2, "wanwu1--"));
//        linkedLisk.deleteNode(new Node(3, "wanwu1--"));
//        linkedLisk.printList();

        // 5.获取有效数据个数
//        System.out.println("当前链表中有效数据条数： " + linkedLisk.getNodeNum());

        // 6.获取指定倒数位置的数据
//        Node lastIndexOfNode = linkedLisk.findLastIndexOfNode(1);
        /*Node lastIndexOfNode = linkedLisk.findLastIndexOfNode(2);
        System.out.println("lastIndexOfNode = " + lastIndexOfNode);*/

        // 7.反转链表
        System.out.println("反转链表");
        linkedLisk.revers();
        linkedLisk.printList();


    }
}

class SingleLinkedLisk{
    // 固定初始化一个头结点
    private Node head = new Node(0,"head");

    /**
     * 判断列表是否为空
     * @return
     */
    public boolean isNull(){
        if(head.nextNode == null) {
            System.out.println("链表为空");
            return true;
        }
        return false;
    }

    /**
     * 添加一个节点，不考虑数据顺序
     * @param currentNode
     */
    public void addNode(Node currentNode){
        // 设置一个指针
        Node temp = head;

        // 退出循环标记
        while (true){
            // 到最后一个节点退出
            if(null == temp.nextNode){
                break;
            }
            // 将指针移到后一个节点
            temp = temp.nextNode;
        }

        // 当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next 指向 新的节点
        temp.nextNode = currentNode;

        System.out.println("添加一个数据成功："+currentNode);
    }

    /**
     * 添加一个节点，按顺序排序
     * @param currentNode
     */
    public void addNodeByOrder(Node currentNode){
        // 设置一个指针
        // 因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        Node temp = head;

        // 相同的编号标记
        // flag标志添加的编号是否存在，默认为false
        boolean sameNoFlag = false;

        // 退出循环标记
        while (true){
            // 到最后一个节点退出
            if(null == temp.nextNode){
                break;
            }
            // 位置找到，就在temp的后面，temp.nextNode前面插入
            else if(temp.nextNode.no > currentNode.no){
                break;
            }
            // 说明希望添加的heroNode的编号已然存在
            else if (temp.nextNode.no == currentNode.no){
                // 说明编号存在
                sameNoFlag = true;
                break;
            } else {
                // 将指针移到后一个节点
                temp = temp.nextNode;
            }
        }

        // 判断flag 的值，不能添加，说明编号存在
        if (sameNoFlag) {
            System.out.println("有相同编号的值！不添加改数据:" + currentNode);
            return;
        }

        // 插入到链表中, temp的后面
        currentNode.nextNode = temp.nextNode;
        temp.nextNode = currentNode;

        System.out.println("添加一个数据成功："+currentNode);
    }

    /**
     * 更新节点
     */
    public void updateNode(Node node){
        //判断链表是否为空
        if(isNull()) {return;}

        Node temp = head.nextNode;
        // 更新节点匹配标记
        boolean flag = false;
        while (true){
            // 循环完都没匹配上
            if(temp.nextNode == null){
                break;
            }

            // 匹配上修改标记
            if(temp.no == node.no){
                flag = true;
                break;
            }

            // 将指针移到后一个节点
            temp = temp.nextNode;
        }

        if (flag){
            temp.name = node.name;
        } else {
            System.out.println("没有改编号数据："  + node);
        }
    }

    /**
     * 删除一个节点
     */
    public void deleteNode(Node node){
        // 判断链表是否为空
        if(head.nextNode == null) {
            System.out.println("链表为空");
            return;
        }

        Node temp = head;
        // 删除节点匹配标记
        boolean flag = false;
        while (true){
            // 循环完都没匹配上
            if(temp.nextNode == null){
                break;
            }

            // 匹配上修改标记
            if(temp.nextNode.no == node.no){
                flag = true;
                break;
            }
            // 将指针移到后一个节点
            temp = temp.nextNode;
        }

        if(flag){
            temp.nextNode = temp.nextNode.nextNode;
        } else {
            System.out.println("没有查找到需要删除的数据！");
        }
    }

    /**
     * 查看链表中的数据
     */
    public void printList(){
        //判断链表是否为空
        if(head.nextNode == null) {
            System.out.println("链表为空");
            return;
        }

        // 设置一个指针
        Node temp = head.nextNode;

        while (true){
            if (null == temp){
                break;
            }
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.nextNode;
        }
    }

    /**
     * 获取有效节点个数
     * @return
     */
    public int getNodeNum(){
        if(isNull()){
            return 0;
        }

        int num = 0;
        Node temp = head;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            num ++;
            temp = temp.nextNode;
        }

        return num;
    }

    /**
     * 获取指定倒数位置的数据
     * @return
     */
    public Node findLastIndexOfNode(int index){
        // 链表为空返回空
        if (isNull()) { return null;}

        // 非有效index返回空
        int nodeNums = getNodeNum();
        if (index <= 0 || index > nodeNums) { return null;}

        // 正确 index，返回结果
        Node temp = head.nextNode;

        for (int i = 0; i < nodeNums - index; i++) {
            temp = temp.nextNode;
        }

        return temp;
    }

    /**
     * 反转链表
     */
    public void revers(){
        if (isNull()){return;}

        // 原列链表的指针
        Node temp = head.nextNode;
        // 只有一个数据
        if (temp.nextNode == null){
            return;
        }

        // *** 原链表中有多个数据时
        // 反转链表的头
        Node revers = new Node(0, "revers");

        while (true){
            if (temp == null){
                break;
            }

            // 取出原链表中的数据,指针后移
            Node stemp = temp;
            temp = temp.nextNode;

            // 存到反转链表中
            Node dtemp = revers.nextNode;
            stemp.nextNode = dtemp;
            revers.nextNode = stemp;
        }

        head.nextNode = revers.nextNode;
    }
}

class Node{
    public int no;
    public String name;
    public Node nextNode;

    public Node(int no,String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
