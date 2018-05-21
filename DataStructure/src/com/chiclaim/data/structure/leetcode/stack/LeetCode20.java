package com.chiclaim.data.structure.leetcode.stack;

import java.util.Stack;

/**
 * LeetCode 第20号题：有效的括号
 */
public class LeetCode20 {

    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char[] cs = s.toCharArray();
            for (Character c : cs) {
                switch (c) {
                    case '(':
                    case '{':
                    case '[':
                        stack.push(c);
                        break;
                    case ')':
                        if (stack.isEmpty() || stack.pop() != '(')
                            return false;
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.pop() != '{')
                            return false;
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.pop() != '[')
                            return false;
                        break;
                }
            }
            return stack.isEmpty();
        }


    }

    public static void main(String[] args) {
        Solution code20 = new LeetCode20().new Solution();

        System.out.println(code20.isValid("()"));//true
        System.out.println(code20.isValid("()[]{}"));//true
        System.out.println(code20.isValid("(]"));//false
        System.out.println(code20.isValid("([)]"));//false
        System.out.println(code20.isValid("(]"));//false
        System.out.println(code20.isValid("{[]}"));//true
        System.out.println(code20.isValid("]"));//false
        System.out.println(code20.isValid(")"));//false
        System.out.println(code20.isValid("}"));//false
    }
}