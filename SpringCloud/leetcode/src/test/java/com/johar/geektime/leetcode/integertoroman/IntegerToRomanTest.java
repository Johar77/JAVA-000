package com.johar.geektime.leetcode.integertoroman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerToRomanTest {

    @Test
    void intToRoman() {
        IntegerToRoman roman = new IntegerToRoman();

        assertEquals("III", roman.intToRoman(3));

        assertEquals("IV", roman.intToRoman(4));

        assertEquals("IX", roman.intToRoman(9));

        assertEquals("LVIII", roman.intToRoman(58));

        assertEquals("MCMXCIV", roman.intToRoman(1994));
    }
}