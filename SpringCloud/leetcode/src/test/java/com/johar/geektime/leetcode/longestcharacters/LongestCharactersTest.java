package com.johar.geektime.leetcode.longestcharacters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestCharactersTest {

    @Test
    void lengthOfLongestSubstring() {
        String s1 = "abcabcbb";
        String s2 = "bbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = new String("");
        String s6 = "au";
        String s7 = new String("jbpnbwwd");
        LongestCharacters longestCharacters = new LongestCharacters();
        assertEquals(longestCharacters.lengthOfLongestSubstring(s1), 3);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s2), 1);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s3), 3);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s4), 0);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s5), 0);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s6), 2);
        assertEquals(longestCharacters.lengthOfLongestSubstring(s7), 4);
    }
}