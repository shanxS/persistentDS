package com.simpleharmonic.persistentds;

import com.simpleharmonic.persistentds.tree.BST;
import com.simpleharmonic.persistentds.tree.utils.Chronos;

public class Main {

    public static void main(String[] args) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170};
//        int[] arr = {100,50,200,25,75,150,300,125,112,135};

        BST bst = new Chronos();
        for(int i : arr) {
            bst.insert(i);
        }

        bst.printTree();
        System.out.println();
        bst.findNode(123);

        for (int i=0; i<arr.length; ++i) {
            System.out.println("For version " + i);
            ((Chronos)bst).printTreeVersion(i);
            System.out.println();
        }
    }

}
