package org.example;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Random;

public class ImplicitTreap {

    Node root;

    public Integer search(int pos) {
        Node cur = root;
        while (cur != null) {
            int sizeLeft = Node.sizeOf(cur.left);

            if (sizeLeft == pos)
                return cur.value;

            cur = sizeLeft > pos ? cur.left : cur.right;
            if (sizeLeft < pos)
                pos -= sizeLeft + 1;
        }
        return null;
    }

    public static Node merge(Node l, Node r) {
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }

        Node res;
        if (l.priority > r.priority) {
            var newR = merge(l.right, r);
            res = new Node(l.priority, l.value, l.left, newR);
        } else {
            var newL = merge(l, r.left);
            res = new Node(r.priority, r.value, newL, r.right);
        }

        res.recalc();

        return res;
    }

    public Node[] split(int pos) {
        return root.split(pos);
    }

    public void add(int pos, int val) {
        root = add(root, pos, val);
    }

    public Node add(Node node, int pos, int val) {
        Node l = null;
        Node r = null;
        if (node != null) {
            Node[] res = node.split(pos);
            l = res[0];
            r = res[1];
        }

        Node m = new Node(val);
        return merge(merge(l, m), r);
    }

    public void remove(int pos) {
        root = removePos(pos);
    }

    public Node removePos(int pos) {
        Node[] res = split(pos);
        Node l = res[0];
        Node r = res[1];
        r = r.split(1)[1];

        return merge(l, r);
    }

    @Getter
    public static class Node {
        static Random RND = new Random();

        int priority;
        int value;

        int size = 1;

        Node left;
        Node right;

        public Node(int value) {
            this(RND.nextInt(), value);
        }

        public Node(int priority, int value) {
            this(priority, value, null, null);
        }

        public Node(int priority, int value, Node left, Node right) {
            this.priority = priority;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public static int sizeOf(Node treap) {
            return treap == null ? 0 : treap.size;
        }

        public void recalc() {
            size = sizeOf(left) + sizeOf(right) + 1;
        }

        public Node[] split(int x) {
            Node l, r, newTree = null;
            int curIndex = sizeOf(left) + 1;
            Node[] res = (Node[]) Array.newInstance(this.getClass(), 2);

            if (curIndex <= x) {
                if (right == null) {
                    r = null;
                } else {
                    Node[] res1 = right.split(x - curIndex);
                    newTree = res1[0];
                    r = res1[1];
                }

                l = new Node(priority, value, left, newTree);
                l.recalc();
            } else {
                if (left == null) {
                    l = null;
                } else {
                    Node[] res1 = left.split(x);
                    l = res1[0];
                    newTree = res1[1];

                }

                r = new Node(priority, value, newTree, right);
                r.recalc();
            }

            res[0] = l;
            res[1] = r;
            return res;
        }
    }
}