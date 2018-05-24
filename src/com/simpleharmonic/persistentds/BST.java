package com.simpleharmonic.persistentds;

public class BST {
    private Node head;

    public BST() {}

    public void insert(int v) {
        if (head == null) {
            head = new Node(v);
        } else {
            insert(head, v);
        }
    }

    private void insert(Node node, int v) {
        if (node == null) {
            node = new Node(v);
        } else if (node.getValue() < v) {
            if (node.getRight() != null) {
                insert(node.getRight(), v);
            } else {
                node.setRight(new Node(v));
            }
        } else if (node.getValue() > v) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), v);
            } else {
                node.setLeft(new Node(v));
            }
        }
    }

    public void print() {
        print(head);
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }

        print(node.getLeft());

        System.out.print(node.getValue());
        if (node.getLeft() != null) {
            System.out.print(" left -> " + node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            System.out.print(" right -> " + node.getRight().getValue());
        }
        System.out.println();

        print(node.getRight());
    }

}
