package com.simpleharmonic.persistentds.ll;

import lombok.Getter;
import java.util.HashMap;

public class Node<T> {
    @Getter private HashMap<Integer, Node> versionNode;
    @Getter private T value;

    public Node(T value, int version) {
        this.value = value;
        versionNode = new HashMap<>();
        versionNode.put(version, null);
    }

    public void setNextNode(int version, Node node) {
        if (versionNode.get(version) != null) {
            throw new RuntimeException("This version already exists,");
        }

        versionNode.put(version, node);
    }

    public Node getNextNode(int version) {
        return versionNode.get(version);
    }

    public void appendVersion(int fromVersion, int toVersion) {
        versionNode.put(toVersion, versionNode.get(fromVersion));
    }
}
