package com.chiclaim.data.structure.leetcode.tree;


import java.util.Map;
import java.util.TreeMap;

public class LeetCode208 {

    class Trie {

        private class Node {
            public boolean isWord;
            public Map<Character, Node> next;

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
        public Trie() {
            root = new Node();
        }


        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 插入操作
         *
         * @param word 单词
         */
        public void add(String word) {
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
         * 是否包含某个单词
         *
         * @param word 单词
         * @return 存在返回true，反之false
         */
        public boolean contains(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node node = current.next.get(c);
                if (node == null) {
                    return false;
                }
                current = node;
            }
            //如果只存在 panda这个词，查询 pan，虽然有这3个字母，但是并不存在该单词
            return current.isWord;
        }


        /**
         * Trie是否包含某个前缀
         *
         * @param prefix 前缀
         * @return
         */
        public boolean containsPrefix(String prefix) {
            Node current = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                Node node = current.next.get(c);
                if (node == null) {
                    return false;
                }
                current = node;
            }
            return true;
        }


        /*
         * 1，如果单词是另一个单词的前缀，只需要把该word的最后一个节点的isWord的改成false
         * 2，如果单词的所有字母的都没有多个分支，删除整个单词
         * 3，如果单词的除了最后一个字母，其他的字母有多个分支，
         */

        /**
         * 删除操作
         *
         * @param word
         * @return
         */
        public boolean remove(String word) {
            Node multiChildNode = null;
            int multiChildNodeIndex = -1;
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                Node child = current.next.get(word.charAt(i));
                //如果Trie中没有这个单词
                if (child == null) {
                    return false;
                }
                //当前节点的子节点大于1个
                if (child.next.size() > 1) {
                    multiChildNodeIndex = i;
                    multiChildNode = child;
                }
                current = child;
            }
            //如果单词后面还有子节点
            if (current.next.size() > 0) {
                if (current.isWord) {
                    current.isWord = false;
                    size--;
                    return true;
                }
                //不存在该单词，该单词只是前缀
                return false;
            }
            //如果单词的所有字母的都没有多个分支，删除整个单词
            if (multiChildNodeIndex == -1) {
                root.next.remove(word.charAt(0));
                size--;
                return true;
            }
            //如果单词的除了最后一个字母，其他的字母有分支
            if (multiChildNodeIndex != word.length() - 1) {
                multiChildNode.next.remove(word.charAt(multiChildNodeIndex + 1));
                size--;
                return true;
            }
            return false;
        }

        /**
         * 查询操作(通过..作为通配符)
         *
         * @param express 如 p..d
         * @return
         */
        public boolean searchByWildCard(String express) {
            return search(root, express, 0);
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

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            add(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return contains(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return containsPrefix(prefix);
        }
    }

    public static void main(String[] args) {
        Trie trie = new LeetCode208().new Trie();
        System.out.println("insert(panic)");
        trie.insert("panic");
        System.out.println("search(panic):" + trie.search("panic"));
        System.out.println("startWith(pan):" + trie.startsWith("pan"));
    }
}
