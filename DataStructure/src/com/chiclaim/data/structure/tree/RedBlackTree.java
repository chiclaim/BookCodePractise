package com.chiclaim.data.structure.tree;

/**
 * 红黑树
 * <p>
 * 排序二叉树虽然可以快速检索，但是在最坏的情况下，如果插入的节点本身就是有序的，要么是由小到大排列，要么是由大到小排列， 那么最后得到的排序二叉树将变成链表：
 * 所有节点只有左节点（由大到小排序）或者所有的节点只有右节点（由小到大排序）。这样的话排序二叉树就变成了普通链表，检索效率就会很低。
 * <p>
 * 为了改善二叉排序树存在的不足，需要来了解下，红黑树（一种改进后的排序二叉树），又称为对称二叉B树
 * <p>
 * 红黑树在原来的排序二叉树山更增加了如下几个要求：
 * 1，每个节点要么是红色，要么是黑色
 * 2，根节点永远是黑色
 * 3，所有的叶子节点都是空节点，并且是黑色的
 * 4，每个红色节点的两个子节点都是黑色的（从每个叶子到跟的路径上不会出现两个连续的红色节点）
 * 5，从任一节点到其子树中每个叶子节点的路径都包含相同数量的黑色节点
 * <p>
 * Created by Chiclaim on 2018/3/24.
 */
public class RedBlackTree<T extends Comparable> {


    private static final boolean RED = false;
    private static final boolean BLACK = true;


    static class Node {
        Object data;
        Node parent;
        Node left;
        Node right;
        boolean color = BLACK;//节点默认黑色

        public Node(Object data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (color != node.color) return false;
            if (data != null ? !data.equals(node.data) : node.data != null) return false;
            if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            return right != null ? right.equals(node.right) : node.right == null;
        }

        @Override
        public int hashCode() {
            int result = data != null ? data.hashCode() : 0;
            result = 31 * result + (parent != null ? parent.hashCode() : 0);
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            result = 31 * result + (color ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return data + (color == RED ? " RED" : " BLACK");
        }
    }


    private Node root;


    public RedBlackTree() {

    }

    public RedBlackTree(T data) {
        root = new Node(data, null, null, null);
    }

    public void add(T data) {
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
                //如果新节点的值大于当前节点值
                if (cmp > 0) {
                    //以右节点作为当前节点
                    current = current.right;
                } else {
                    //以左节点作为当前节点
                    current = current.left;
                }
            } while (current != null);

            //构造新节点
            Node newNode = new Node(data, parent, null, null);
            //如果新节点的值大于父节点的值
            if (cmp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }

            //维护红黑树
            fixAfterInsertion(newNode);
        }

    }

    public void remove(T data) {
        //获取要删除的节点
        Node target = getNode(data);
        //如果要删除的节点存在左、右节点
        if (target.left != null && target.right != null) {
            //找到target节点中序遍历的前一个节点
            //s用于保存target节点的左子树中值最大的节点
            Node s = target.left;
            while (s.right != null) {
                s = s.right;
            }
            //用s节点来代替p节点
            target.data = s.data;
            target = s;
        }

        //开始修复它的替换节点，如果该替换节点不为null
        Node replacement = (target.left != null ? target.left : target.right);
        if (replacement != null) {
            //让replacement的parent指向target的parent
            replacement.parent = target.parent;
            //如果target的parent为null，则表明target本身是根节点
            if (target.parent != null) {
                root = replacement;
            }
            //如果target是其父节点的左子节点
            else if (target == target.parent.left) {
                target.parent.left = replacement;
            }
            //如果target是其父节点的右子节点
            else {
                target.parent.right = replacement;
            }

            //彻底删除target节点
            if (target.color == BLACK) {
                fixAfterInsertion(replacement);
            }
        }
        //target本身是根节点
        else if (target.parent == null) {
            root = null;
        } else {
            //target没有子节点，把它当做虚的替换节点
            //修复红黑树
            if (target.color == BLACK) {
                fixAfterInsertion(target);
            }

            if (target.parent != null) {
                //如果target是其父节点的左子节点
                if (target == target.parent.left) {
                    //将target的父节点的left置为null
                    target.parent.left = null;
                }
                //如果target是其父节点的右节点
                else if (target == target.parent.right) {
                    //将target的父节点的right置为null
                    target.parent.right = null;
                }
                //将target的parent设置为null
                target.parent = null;
            }
        }

    }

    private void fixAfterInsertion(Node node) {

    }

    private Node getNode(T data) {
        return null;
    }


}

