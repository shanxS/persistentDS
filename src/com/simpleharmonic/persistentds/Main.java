package com.simpleharmonic.persistentds;

import com.simpleharmonic.persistentds.tree.BST;
import com.simpleharmonic.persistentds.tree.utils.Chronos;

public class Main {

    public static void main(String[] args) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170,190};
//        int[] arr = {100,200,50};
//        int[] arr = {100,50,200,25,75,150,300,125,112,135};


        BST bst = new Chronos();
        for(int i : arr) {
            bst.insert(i);
        }

        bst.printTree();
        System.out.println();

        for (int i=0; i<arr.length; ++i) {
            System.out.println("For version " + i);
            ((Chronos)bst).printTreeVersion(i);
            System.out.println();
        }

        System.out.println("Starting deletion ");
        bst.delete(150);
        System.out.println("for version " + arr.length);
        ((Chronos)bst).printTreeVersion(arr.length);

        bst.delete(180);

        System.out.println("for version " + (arr.length + 1));
        ((Chronos)bst).printTreeVersion(arr.length + 1);

        int version = arr.length + 1;
        System.out.println("180 in " + version + " is " + ((Chronos)bst).findNodeInVersion(180, version));

        --version;
        System.out.println("180 in " + version + " is " + ((Chronos)bst).findNodeInVersion(180, version));
    }

}
