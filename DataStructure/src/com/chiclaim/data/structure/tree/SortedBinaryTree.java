package com.chiclaim.data.structure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/*
  排序二叉树、二叉排序树（Binary Sort Tree）、二叉查找树（Binary Search Tree），亦称二叉搜索树。

  排序二叉树是一种特殊结构的二叉树，通过它可以非常方便的对树种的所有节点进行排序和检索。

  排序二叉树要么是一棵空二叉树，要么是具有以下性质的二叉树：

       1，若它的左子树不空，则左子树上所有的节点的值均小于它的父节点的值

       2，若它的右子树不空，则右子树上所有的节点的值均大于它的父节点的值

       3，它的左、右子树也分别为排序二叉树

创建排序二叉树的步骤就是不断地向排序二叉树添加节点的过程， 添加过程如下：

      1，以根节点为当前节点开始搜索。

      2，拿新节点的值和当前节点的值比较。

      3，如果新节点的值更大，则以当前节点的右子节点作为新的当前节点；如果新节点的值更小，则以当前节点的左子节点作为新的当前节点。

      4，重复第2,3两个步骤，直到搜索到合适的叶子节点

      5，将新节点添加为第4步找到的叶子节点的子节点，如果新节点更大，则添加为右子节点；否则，添加为左子节点。

当程序从排序二叉树中删除一个节点之后，为了让它依然保持为排序二叉树，必须对该排序二叉树进行维护，删除过程如下：

      1，如果被删除的节点时叶子节点，只需要将它从父节点中删除即可。

      2，如果被删除的节点只有一个子节点（左或右）。用它的子节点来代替要删除的节点。

      3，如果删除节点P的左、右子树均非空，有以下两种做法：

        1）

        2）


 */
public class SortedBinaryTree<T extends Comparable> {

    private static class Node {
        Object data;
        Node parent;
        Node left;
        Node right;

        public Node(Object data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }

    //根节点
    private Node root;

    public SortedBinaryTree() {

    }

    public SortedBinaryTree(T data) {
        root = new Node(data, null, null, null);
    }

    public void add(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(data, null, null, null);
        } else {
            Node current = root;
            Node parent = null;
            int cmp = 0;
            //搜索合适的叶子节点，以该叶子节点作为父节点添加新节点
            do {
                parent = current;
                cmp = data.compareTo(current.data);
                //如果新节点的值大于当前节点的值
                if (cmp > 0) {
                    //以右节点作为当前节点的值
                    current = current.right;
                } else {
                    //以左节点作为当前节点的值
                    current = current.left;
                }
            } while (current != null);
            //创建新节点
            Node newNode = new Node(data, parent, null, null);
            if (cmp > 0) {
                //新节点作为父节点的右子节点
                parent.right = newNode;
            } else {
                //新节点作为父节点的左子节点
                parent.left = newNode;
            }
        }
    }


    /**
     * 删除节点
     *
     * @param data
     */
    public void remove(T data) {
        //获取要删除的节点
        Node target = getNode(data);
        //如果要删除的节点为null，则直接返回
        if (target == null) {
            return;
        }

        //如果要删除节点的左、右树为空
        if (target.left == null && target.right == null) {
            //要删除的节点时根节点
            if (target == root) {
                root = null;
            } else {
                //要删除的节点是父节点的左子节点
                if (target == target.parent.left) {
                    target.parent.left = null;
                } else {
                    //将target的父节点的right设置为null
                    target.parent.right = null;
                }
                //解除引用
                target.parent = null;
            }
        }
        //如果要删除的节点只有右子树
        else if (target.left == null && target.right != null) {
            //如果要删除的节点是根节点
            if (target == root) {
                root = target.right;
            }
            //如果要删除的节点是父节点的左子节点
            if (target == target.parent.left) {
                //让target的父节点的left指向target的右子树
                target.parent.left = target.right;
            } else {
                //让target的父节点的right指向target的右子树
                target.parent.right = target.right;
            }
            //让target的右子树的parent指向target的parent
            target.right.parent = target.parent;
        }
        //如果要删除的节点只有左子树
        else if (target.left != null && target.right == null) {
            //如果要删除的节点是根节点
            if (target == root) {
                root = target.left;
            } else {
                //被删除的节点是父节点的左子节点
                if (target == target.parent.left) {
                    //让target的父节点的left指向target的左子树
                    target.parent.left = target.left;
                } else {
                    //让target的父节点的right指向target的左子树
                    target.parent.right = target.left;
                }
                //让target的左子树的parent指向target的parent
                target.left.parent = target.parent;
            }
        }
        //如果要删除的节点既有左子树，又有右子树
        else {
            //leftMaxNode用于保存target节点的左子树中最大值的节点
            Node leftMaxNode = target.left;
            //搜索target节点的左子树中值最大的节点
            while (leftMaxNode.right != null) {
                leftMaxNode = leftMaxNode.right;
            }

            //从原来的子树中删除leftMaxNode节点
            leftMaxNode.parent.right = null;
            //让leftMaxNode的parent指向target的parent
            leftMaxNode.parent = target.parent;
            //要删除的节点是父节点的左子节点
            if (target == target.parent.left) {
                //让target的父节点的left指向leftMaxNode
                target.parent.left = leftMaxNode;
            }
            //要删除的节点是父节点的右子节点
            else {
                //让target的父节点的right指向leftMaxNode
                target.parent.right = leftMaxNode;
            }

            leftMaxNode.left = target.left;
            leftMaxNode.right = target.right;
            //解除被删除节点的其他引用
            target.parent = target.left = target.right = null;
        }
    }


    public Node getNode(T data) {
        Node p = root;
        while (p != null) {
            int cmp = data.compareTo(p.data);
            //如果搜索的值小于当前p节点的值
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public List<Node> breadthFirst() {
        List<Node> list = new ArrayList<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();

        //把根节点放进栈中
        queue.add(root);

        //以下的逻辑可以概括为：从头部取，尾部放
        while (!queue.isEmpty()) {
            //把根节点放进节点集合中
            list.add(queue.peek());

            Node node = queue.poll();
            //如果存在左节点，加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            //如果存在右节点，加入队列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        SortedBinaryTree<Integer> tree = new SortedBinaryTree<>();
        tree.add(5);
        tree.add(20);
        tree.add(10);
        tree.add(3);
        tree.add(8);
        tree.add(15);
        tree.add(30);
        System.out.println(tree.breadthFirst());
        tree.remove(20);
        System.out.println(tree.breadthFirst());
    }


}
