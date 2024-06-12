package com.fwx.leecode;

/**
 * @ClassName 合并两个有序链表
 * @Description 
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @Author Fwx
 * @Date 2023/3/29 14:02
 * @Version 1.0
 */
public class D0006合并两个有序链表_虚拟头节点_优化 {
    public static void main(String[] args) {
        // 输入：l1 = [1,2,4], l2 = [1,3,4]
        // l1
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(4);

        l1_1.next = l1_2;
        l1_2.next = l1_3;

        // l2
        ListNode l2_1 = new ListNode(1);
        ListNode l2_2 = new ListNode(2);
        ListNode l2_3 = new ListNode(4);

        l2_1.next = l2_2;
        l2_2.next = l2_3;

        ListNode listNode = mergeTwoLists(l1_1, l2_1);

        System.out.println(listNode.val);
        ListNode next = listNode.next;
        while (next != null){
            System.out.println(next.val);
            next = next.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if ( list1 == null ){
            return list2;
        }

        if (list2 == null){
            return list1;
        }

        // 虚拟头节点
        ListNode virTualTop = new ListNode(-1);
        ListNode virTualNext = virTualTop;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val){
                virTualNext.next = list1;
                list1 = list1.next;
                virTualNext = virTualNext.next;
            } else {
                virTualNext.next = list2;
                list2 = list2.next;
                virTualNext = virTualNext.next;
            }
        }

        // 最后一个点处理
        virTualNext.next = list1 == null ? list2 : list1;

        return virTualTop.next;
    }
}


