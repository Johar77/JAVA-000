package com.johar.geektime.leetcode.addtwonumbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    @Test
    void addTwoNumbers1() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);
        assertNotNull(result);
        assertEquals(result.val, 7);
        assertEquals(result.next.val, 0);
        assertEquals(result.next.next.val, 8);
    }

    @Test
    void addTwoNumbers2() {
        ListNode l1 = new ListNode(0);

        ListNode l2 = new ListNode(0);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);
        assertNotNull(result);
        assertEquals(result.val, 0);
    }
}