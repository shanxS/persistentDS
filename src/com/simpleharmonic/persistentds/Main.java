package com.simpleharmonic.persistentds;

public class Main {

    public static void main(String[] args) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170};

        BST bst = new BST();
        for(int i : arr) {
            bst.insert(i);
        }

        bst.print();
        bst.delete(170);
        System.out.println();
        bst.print();

    }

}
