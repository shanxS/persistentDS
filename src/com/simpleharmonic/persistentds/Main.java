package com.simpleharmonic.persistentds;

public class Main {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(9);
        bst.insert(8);
        bst.insert(4);
        bst.insert(1);

        bst.print();

    }

}
