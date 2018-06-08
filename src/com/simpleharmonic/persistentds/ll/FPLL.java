package com.simpleharmonic.persistentds.ll;

import java.util.HashMap;
import java.util.List;

public class FPLL <T> implements FullyPersistedLinkedList<T> {

    private final HashMap<Integer, Node> versionHead;
    private int maxVersion;


    public FPLL() {
        versionHead = new HashMap<>();
        maxVersion = 0;
    }


    @Override
    public int add(T element, int version) {
        ++maxVersion;
        if (versionHead.get(version) == null) {
            versionHead.put(version, new Node(element, version));
        } else {
            addSequentially(element, version, versionHead.get(version));
        }

        return maxVersion;
    }

    private void addSequentially(T element, int version, Node node) {
        if (node.getNextNode(version) != null) {
            addSequentially(element, version, node.getNextNode(version));
            node.appendVersion(version, maxVersion);
        } else {
            node.setNextNode(version, new Node(element, maxVersion));
        }
    }

    @Override
    public void addFirst(T element, int version) {
    }

    @Override
    public void addAfter(T element, int version, T predecessor) {

    }

    @Override
    public T remove(T element, int version) {
        return null;
    }

    @Override
    public boolean find(T element, int version) {
        return false;
    }

    @Override
    public List<Integer> findVersions(T element) {
        return null;
    }

    @Override
    public void print(int version) {
        if (versionHead.get(version) == null) {
            System.out.println("This version does not exist " + version);
        } else {
            System.out.println();
            printSequentially(versionHead.get(version), version);
        }
    }

    private void printSequentially(Node node, int version) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue() + " ");
        printSequentially(node.getNextNode(version), version);
    }
}
