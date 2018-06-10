package com.simpleharmonic.persistentds.ll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class MainTests {

    @Test
    public void testMultipleLists() {
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
        for (int i=1; i<arr3.length; ++i) {
            ll.addToVersion(arr3[i], version);
        }


        for (int i=1; i<=ll.getMaxVersion(); ++i) {
            ArrayList<Integer> list = ll.get(i);
            list.forEach(x -> System.out.print(" " + x));

            if (i==1) {
                assertEquals(list.size(), arr.length);
            } else if (i>1 && i<17){
                assertEquals(list.size(), i);
            } else if (i==17) {
                assertEquals(list.size(), arr2.length);
            } else if (i>17 && i<20) {
                assertEquals(list.size(), i-17+1);
            } else if (i==20) {
                assertEquals(list.size(), arr3.length);
            } else if (i>20) {
                assertEquals(list.size(), i-20+1);
            }

            System.out.println();
        }

    }

}
