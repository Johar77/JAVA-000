package com.johar.geektime.leetcode.regularexpressionmatching;

import org.springframework.util.StringUtils;

/**
 * @ClassName: ExpressionMatch
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/6/17 23:38
 * @Since: 1.0.0
 */
public class ExpressionMatch {

    public boolean isMatch(String s, String p) {
        if (s == null || s.isEmpty() || p == null || p.isEmpty()){
            throw new IllegalArgumentException("error parameter");
        }

        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m+1][n+1];
        f[0][0] = true;
        for (int i = 0; i<= m; i++){
            for (int j = 1; j<= n; j++){
                if (p.charAt(j - 1) == '*'){
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j-1)){
                        f[i][j] = f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else{
                    if (matches(s,p,i,j)){
                        f[i][j] = f[i -1][j - 1];
                    }
                }
            }
        }

        return f[m][n];
    }

    private boolean matches(String s, String p, int i, int j){
        if (i == 0){
            return false;
        }

        if (p.charAt(j -1) == '.'){
            return true;
        }

        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}