package com.johar.geektime.leetcode.regularexpressionmatching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionMatchTest {

    @Test
    void isMatch1() {
        String s = "aa";
        String p = "a";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(false,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch2() {
        String s = "aa";
        String p = "a*";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(true,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch3() {
        String s = "ab";
        String p = ".*";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(true,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch4() {
        String s = "aab";
        String p = "c*a*b";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(true,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch5() {
        String s = "mississippi";
        String p = "mis*is*p*.";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(false,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch6() {
        String s = "ab";
        String p = ".*c";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(false,expressionMatch.isMatch(s, p));
    }

    @Test
    void isMatch7() {
        String s = "a";
        String p = "ab*";
        ExpressionMatch expressionMatch = new ExpressionMatch();
        assertEquals(true,expressionMatch.isMatch(s, p));
    }
}