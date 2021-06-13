package com.johar.geektime.leetcode.longestpalindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromeTest {

    @Test
    void longestPalindrome1() {
        String s = "babad";
        LongestPalindrome palindrome = new LongestPalindrome();
        assertEquals("bab", palindrome.longestPalindrome(s));
    }

    @Test
    void longestPalindrome2() {
        String s = "cbbd";
        LongestPalindrome palindrome = new LongestPalindrome();
        assertEquals("bb", palindrome.longestPalindrome(s));
    }

    @Test
    void longestPalindrome3() {
        String s = "a";
        LongestPalindrome palindrome = new LongestPalindrome();
        assertEquals("a", palindrome.longestPalindrome(s));
    }

    @Test
    void longestPalindrome4() {
        String s = "ac";
        LongestPalindrome palindrome = new LongestPalindrome();
        assertEquals("a", palindrome.longestPalindrome(s));
    }
}