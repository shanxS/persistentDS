package com.simpleharmonic.persistentds.ll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FPLL <T> implements FullyPersistedLinkedList<T> {

    private final HashMap<Integer, Node> versionHead;
    private int maxVersion, listCount;


    public FPLL() {
        versionHead = new HashMap<>();
        maxVersion = 0;
        listCount = 0;
    }


    @Override
    public int getListCountSoFar() {
        return listCount;
    }

    @Override
    public int getMaxVersion() {
        return maxVersion;
    }

    @Override
    public int createNew(T element) {
        ++maxVersion;
        ++listCount;
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
            node.setNextNode(maxVersion, new Node(element));
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

        Node next = node.getNextNode(maxVersion);
        while (next != null) {
            next.appendVersion(version, maxVersion);
            next = next.getNextNode(version);
        }

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
    public ArrayList<T> get(int version) {
        if (versionHead.get(version) == null) {
            System.out.println("This version does not exist " + version);
            return null;
        } else {
            ArrayList<T> list = new ArrayList<>();
            getSequentially(versionHead.get(version), version, list);
            return list;
        }
    }

    private void getSequentially(Node<T> node, int version, ArrayList<T> list) {
        if (node == null) {
            return;
        }

        list.add(node.getValue());
        getSequentially(node.getNextNode(version), version, list);
    }
}
