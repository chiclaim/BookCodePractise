package com.chiclaim.data.structure.leetcode.tree;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode211 {


    class WordDictionary {

        class Node {
            boolean isWord;
            Map<Character, Node> next;

            public Node() {
                next = new TreeMap<>();
            }

            public Node(boolean isWord) {
                this();
                this.isWord = isWord;
            }

        }

        private Node root;

        private int size;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new Node();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            Node current = root;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                Node next = current.next.get(c);
                if (next == null) {
                    current.next.put(c, new Node());
                }
                current = current.next.get(c);
            }
            //如果当前的node已经是一个word，则不需要添加
            if (!current.isWord) {
                size++;
                current.isWord = true;
            }
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return search(root, word, 0);
        }

        private boolean search(Node node, String express, int index) {
            if (index == express.length()) {
                return node.isWord;
            }
            char c = express.charAt(index);
            if (c != '.') {
                Node nextChar = node.next.get(c);
                if (nextChar == null) {
                    return false;
                }
                return search(nextChar, express, index + 1);
            } else {
                Map<Character, Node> nextNodes = node.next;
                for (Map.Entry<Character, Node> entry : nextNodes.entrySet()) {
                    if (search(entry.getValue(), express, index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }

    }

    public static void main(String[] args) {
        WordDictionary dictionary = new LeetCode211().new WordDictionary();
        System.out.println("add(apple)");
        dictionary.addWord("apple");
        System.out.println("search(a..le):" + dictionary.search("a..le"));
    }
}
