package com.simpleharmonic.persistentds.ll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class MainTests {

    /**
     * Takes a random target verion, adds random head to it
     * random number of times. Repeats this 3 times.
     */
    @Test
    public void addAfterTest() {
        FullyPersistedLinkedList<Integer> ll = new FPLL();
        create3SeparateLists(ll);

        Random rand = new Random();

        for (int i=0; i<3; ++i) {
            int listCount = ll.getListCountSoFar();
            int targetVersion = rand.nextInt(listCount + 1) + 1;
            ArrayList<Integer> list = ll.get(targetVersion);
            System.out.println("Target version " + targetVersion + " : ");
            printList(list);

            int oldLen = list.size();
            int attamptCount = 3;
            while (attamptCount > 0) {
                Integer newHead = rand.nextInt(1000); // i dont care what this numver is
                System.out.println("New head " + newHead);
                int newVersion = ll.addFirstToVersion(newHead, targetVersion);

                list = ll.get(newVersion);
                assertEquals(list.size(), oldLen+1);
                assertEquals(list.get(0), newHead);
                printList(list);

                targetVersion = newVersion;
                oldLen = list.size();

                --attamptCount;
            }

        }

    }

    @Test
    public void multipleListsSimpleTest() {
        FullyPersistedLinkedList<Integer> ll = new FPLL();
        create3SeparateLists(ll);

        assertEquals(ll.getListCountSoFar(), 3);

        for (int i=1; i<=ll.getMaxVersion(); ++i) {
            ArrayList<Integer> list = ll.get(i);
            list.forEach(x -> System.out.print(" " + x));

            if (i<17) {
                assertEquals(list.size(), i);
            } else if (i>=17 && i<20) {
                assertEquals(list.size(), i-17+1);
            } else if (i>=20) {
                assertEquals(list.size(), i-20+1);
            }

            System.out.println();
        }

    }

    private void printList(ArrayList<Integer> list) {
        list.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    private void create3SeparateLists(FullyPersistedLinkedList<Integer> ll) {
        int[] arr = {100,50,200,25,75,150,300,125,175,112,135,165,180,160,170,190};
        int[] arr2 = {100,200,50};
        int[] arr3 = {100,50,200,25,75,150,300,125,112,135};

        int version = ll.createNew(arr[0]);
        System.out.println("version created " + version);
        for (int i=1; i<arr.length; ++i) {
            version = ll.addToVersion(arr[i], version);
        }

        version = ll.createNew(arr2[0]);
        System.out.println("version created " + version);
        for (int i=1; i<arr2.length; ++i) {
            version = ll.addToVersion(arr2[i], version);
        }

        version = ll.createNew(arr3[0]);
        System.out.println("version created " + version);
        for (int i=1; i<arr3.length; ++i) {
            version = ll.addToVersion(arr3[i], version);
        }
    }

}
