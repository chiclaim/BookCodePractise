package com.chiclaim.data.structure.tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * Trie字典树
 */
public class Trie {

    private Node root;

    private int size;

    private static class Node {
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

    public Trie() {
        root = new Node();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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


    public void remove(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node = current.next.get(c);
            if (node == null) {
                return;
            }
            current = node;
        }

        if (current.next.size() != 0) {
            if (current.isWord) {
                current.isWord = false;
            }
        } else {
            // TODO: 2018/6/13 Trie删除
//            Map<Character,Node> childern = root.next;
//            Node child = null;
//            for(int i=word.length();i>=1 ;i--){
//                String subWord = word.substring(0,i);
//                Node parent  = searchNode(subWord);
//                if(child!=null
//                        && parent.next.size() !=1
//                        && child.next.size()==1){
//                    parent.next.remove(child.c);
//                    System.out.println("删除成功:"+word);
//                    return;
//                }else{
//                    child = parent;
//                }
//            }
        }
    }

    /**
     * 通过..作为通配符
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


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("panda");
        System.out.println("add panda to trie");
        System.out.println("searchByWildCard(p..da) = " + trie.searchByWildCard("p..da"));
        System.out.println("contains(panda) = " + trie.contains("panda"));
        System.out.println("contains(pan) = " + trie.contains("pan"));
        System.out.println("containsPrefix(pan) = " + trie.containsPrefix("pan"));
        System.out.println("remove panda");
        trie.remove("panda");
        System.out.println("contains(panda) = " + trie.contains("panda"));

    }


}
