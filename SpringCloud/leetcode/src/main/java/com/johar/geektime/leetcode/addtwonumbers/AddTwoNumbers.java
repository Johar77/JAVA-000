package com.johar.geektime.leetcode.addtwonumbers;

/**
 * @ClassName: AddTwoNumbers
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/12 15:51
 * @Since: 1.0.0
 */
public class AddTwoNumbers {

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null){
            throw new IllegalArgumentException("l1 or l2 can't is null");
        }

        ListNode result = new ListNode();
        int total = 0, carry = 0;
        int num1, num2;
        ListNode temp = result;
        do {
            num1 = l1 == null ? 0 : l1.val;
            num2 = l2 == null ? 0 : l2.val;
            total = carry + num1 + num2;
            temp.val = total % 10;
            carry = total / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if (l1 == null && l2 == null && carry == 0){
                break;
            }
            temp.next = new ListNode();
            temp = temp.next;
        } while (true);

        return result;
    }
}