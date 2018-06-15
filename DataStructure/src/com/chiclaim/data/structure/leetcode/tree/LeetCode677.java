package com.chiclaim.data.structure.leetcode.tree;

import com.chiclaim.data.structure.tree.Trie;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode677 {

    class MapSum {

        class Node {
            public int value;
            public Map<Character, Node> next;

            public Node() {
                next = new TreeMap<>();
            }

            public Node(int value) {
                this();
                this.value = value;
            }

        }

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                Node node = cur.next.get(c);
                if (node == null) {
                    cur.next.put(c, node = new Node());
                }
                cur = node;
            }
            cur.value = val;
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                Node node = cur.next.get(c);
                if (node == null) {
                    return 0;
                }
                cur = node;
            }
            return countValue(cur);
        }

        private int countValue(Node node) {
            int result = node.value;
            for (char c : node.next.keySet()) {
                result += countValue(node.next.get(c));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new LeetCode677().new MapSum();
        mapSum.insert("pain", 2);
        mapSum.insert("paine", 4);
        mapSum.insert("paint", 9);
        System.out.println(mapSum.sum("pain"));
    }
}
