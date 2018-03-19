package com.chiclaim.data.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的遍历
 * Created by Chiclaim on 2018/3/19.
 */
public class LoopTree {

    private ThreeLinkedBinaryTree<String> threeLinkedBinaryTree = new ThreeLinkedBinaryTree<>("0");


    //先序遍历
    public List<ThreeLinkedBinaryTree.Node> preIterator() {
        return preIterator(threeLinkedBinaryTree.getRoot());
    }

    //中序遍历
    public List<ThreeLinkedBinaryTree.Node> inIterator() {
        return inIterator(threeLinkedBinaryTree.getRoot());
    }

    //后序遍历
    public List<ThreeLinkedBinaryTree.Node> postIterator() {
        return postIterator(threeLinkedBinaryTree.getRoot());
    }

    private List<ThreeLinkedBinaryTree.Node> preIterator(ThreeLinkedBinaryTree.Node node) {
        List<ThreeLinkedBinaryTree.Node> list = new ArrayList<>();
        //1,先处理根节点
        list.add(node);

        //2,递归处理左子节点
        if (node.left != null) {
            list.addAll(preIterator(node.left));
        }

        //3,递归处理右子节点
        if (node.right != null) {
            list.addAll(preIterator(node.right));
        }

        return list;
    }


    private List<ThreeLinkedBinaryTree.Node> inIterator(ThreeLinkedBinaryTree.Node node) {
        List<ThreeLinkedBinaryTree.Node> list = new ArrayList<>();

        //1,递归处理左子节点
        if (node.left != null) {
            list.addAll(preIterator(node.left));
        }

        //2,先处理根节点
        list.add(node);

        //3,递归处理右子节点
        if (node.right != null) {
            list.addAll(preIterator(node.right));
        }

        return list;
    }

    private List<ThreeLinkedBinaryTree.Node> postIterator(ThreeLinkedBinaryTree.Node node) {
        List<ThreeLinkedBinaryTree.Node> list = new ArrayList<>();

        //1,递归处理左子节点
        if (node.left != null) {
            list.addAll(preIterator(node.left));
        }

        //2,递归处理右子节点
        if (node.right != null) {
            list.addAll(preIterator(node.right));
        }

        //3,先处理根节点
        list.add(node);

        return list;
    }

    public void loop() {
        ThreeLinkedBinaryTree.Node left = threeLinkedBinaryTree.addNode(threeLinkedBinaryTree.getRoot(), "1", true);
        ThreeLinkedBinaryTree.Node right = threeLinkedBinaryTree.addNode(threeLinkedBinaryTree.getRoot(), "2", false);

        threeLinkedBinaryTree.addNode(left, "3", true);
        threeLinkedBinaryTree.addNode(left, "4", false);

        threeLinkedBinaryTree.addNode(right, "5", true);
        threeLinkedBinaryTree.addNode(right, "6", false);

        System.out.println("先序遍历：" + preIterator());
        System.out.println("中序遍历：" + inIterator());
        System.out.println("后序遍历" + postIterator());
    }


    public static void main(String[] args) {
        LoopTree loopTree = new LoopTree();
        loopTree.loop();
    }


}
