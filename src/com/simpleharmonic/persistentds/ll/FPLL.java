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
    public int getMaxVersion() {
        return maxVersion;
    }

    @Override
    public int createNew(T element) {
        ++maxVersion;
        versionHead.put(maxVersion, new Node(element));
        return maxVersion;
    }

    @Override
    public int addToVersion(T element, int version) {
        if (versionHead.get(version) == null) {
            throw new RuntimeException("Version does not exist");
        }

        ++maxVersion;
        addSequentially(element, version, versionHead.get(version));
        versionHead.put(maxVersion, versionHead.get(version));

        return maxVersion;
    }

    private void addSequentially(T element, int version, Node node) {
        if (node.getNextNode(version) != null) {
            addSequentially(element, version, node.getNextNode(version));
            node.appendVersion(version, maxVersion);
        } else {
            node.setNextNode(maxVersion, version, new Node(element));
        }
    }

    @Override
    public int addFirstToVersion(T element, int version) {
        if (version > maxVersion) {
            throw new RuntimeException("Version doesnt exist yet");
        }

        ++maxVersion;
        Node node = new Node(element);
        versionHead.put(maxVersion, node);
        node.setNextNode(maxVersion, versionHead.get(version));

        return maxVersion;
    }

    @Override
    public void addAfterToVersion(T element, int version, T predecessor) {

    }

    @Override
    public T removeFromVersion(T element, int version) {
        return null;
    }

    @Override
    public boolean findInVersion(T element, int version) {
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
