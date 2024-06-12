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
public class D0006合并两个有序链表 {
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

        ListNode l1_cur = list1;
        ListNode l1_next = list1.next;


        ListNode l2_cur = list2;
        ListNode l2_next = list2.next;

        // 情况1：l1、l2都只有一个节点时
        if (l1_next == null && l2_next == null) {
            if (l1_cur.val <= l2_cur.val) {
                l1_cur.next = l2_cur;
                return list1;
            } else {
                l2_cur.next = l1_cur;
                return list2;
            }
        }

        // 情况2：l1只有一个节点，l2有多个节点时
        if (l1_next == null && l2_next != null) {
            if (l1_cur.val <= l2_cur.val) {
                l1_cur.next = l2_cur;
                return l1_cur;
            } else {
                while (l2_cur != null) {
                    if(l2_next == null && l1_cur.val > l2_cur.val){
                        l2_cur.next = l1_cur;
                        return list2;
                    }

                    if (l1_cur.val < l2_next.val) {
                        l1_cur.next = l2_next;
                        l2_cur.next = l1_cur;
                        return list2;
                    } else {
                        l2_cur = l2_cur.next;
                        l2_next = l2_cur.next;
                    }
                }
            }
        }

        // 情况3：l2只有一个节点，l1有多个节点时
        if (l1_next != null && l2_next == null) {
            if (l2_cur.val <= l1_cur.val) {
                l2_cur.next = l1_cur;
                return l2_cur;
            } else {
                while (l1_cur != null) {
                    if(l1_next == null && l2_cur.val > l1_cur.val){
                        l1_cur.next = l2_cur;
                        return list1;
                    }

                    if (l2_cur.val < l1_next.val) {
                        l2_cur.next = l1_next;
                        l1_cur.next = l2_cur;
                        return list1;
                    } else {
                        l1_cur = l1_cur.next;
                        l1_next = l1_cur.next;
                    }
                }
            }
        }

        // 情况4：l1、l2都有多个节点时
        if (l1_next != null && l2_next != null) {

            // 外层循环l2，获取l2中的每个节点去l1中比较
            while (l2_cur != null) {

                if (l2_cur.val <= l1_cur.val) {
                    ListNode tmp = l2_cur;
                    l2_cur = l2_cur.next;

                    tmp.next = l1_cur;
                    list1 = tmp;

                    l1_cur = list1;
                    l1_next = list1.next;
                } else {
                    while (l1_cur != null) {
                        if(l1_next == null && l2_cur.val > l1_cur.val){
                            l1_cur.next = l2_cur;
                            return list1;
                        }

                        if (l2_cur.val <= l1_next.val) {
                            ListNode tmp = l2_cur;
                            l2_cur = l2_cur.next;

                            tmp.next = l1_next;
                            l1_cur.next = tmp;

                            l1_cur = list1;
                            l1_next = list1.next;
                            break;
                        } else {
                            l1_cur = l1_cur.next;
                            l1_next = l1_cur.next;
                        }
                    }
                }
            }
            return list1;
        }

        return new ListNode();
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
