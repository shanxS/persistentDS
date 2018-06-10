package com.simpleharmonic.persistentds;

import com.simpleharmonic.persistentds.ll.FPLL;
import com.simpleharmonic.persistentds.ll.FullyPersistedLinkedList;

public class Main {

    public static void main(String[] args) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170,190};
        int[] arr2 = {100,200,50};
        int[] arr3 = {100,50,200,25,75,150,300,125,112,135};

        FullyPersistedLinkedList<Integer> ll = new FPLL();
        int version = ll.createNew(arr[0]);
        System.out.println("version created " + version);
        for (int i=1; i<arr.length; ++i) {
            ll.addToVersion(arr[i], version);
        }

        version = ll.createNew(arr2[0]);
        System.out.println("version created " + version);
        for (int i=1; i<arr2.length; ++i) {
            ll.addToVersion(arr2[i], version);
        }

        version = ll.createNew(arr3[0]);
        System.out.println("version created " + version);
        for (int i=0; i<arr3.length; ++i) {
            ll.addToVersion(arr3[i], version);
        }


        for (int i=1; i<=ll.getMaxVersion(); ++i) {
            ll.get(i).forEach(x -> System.out.print(" " + x));
            System.out.println();
        }
    }

}
