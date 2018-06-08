package com.simpleharmonic.persistentds;

import com.simpleharmonic.persistentds.ll.FPLL;
import com.simpleharmonic.persistentds.ll.FullyPersistedLinkedList;

public class Main {

    public static void main(String[] args) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170,190};
        int[] arr2 = {100,200,50};
        int[] arr3 = {100,50,200,25,75,150,300,125,112,135};

        FullyPersistedLinkedList<Integer> ll = new FPLL();
        for (int i=0; i<arr.length; ++i) {
            ll.add(arr[i], 1);
        }

        for (int i=0; i<arr2.length; ++i) {
            ll.add(arr2[i], 2);
        }

        for (int i=0; i<arr3.length; ++i) {
            ll.add(arr3[i], 3);
        }


        ll.print(1);
        ll.print(2);
        ll.print(3);
    }

}
