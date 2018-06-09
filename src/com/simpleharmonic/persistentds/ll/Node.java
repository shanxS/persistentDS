package com.simpleharmonic.persistentds.ll;

import lombok.Getter;
import java.util.HashMap;

public class Node<T> {
    @Getter private HashMap<Integer, Node> versionNode;
    @Getter private T value;

    public Node(T value) {
        this.value = value;
        versionNode = new HashMap<>();
    }

    public void setNextNode(int newVersion, int parentVersion, Node node) {
        if (versionNode.get(newVersion) != null) {
            throw new RuntimeException("This version already exists,");
        }

        versionNode.put(newVersion, node);
        versionNode.put(parentVersion, node);
    }

    public void setNextNode(int newVersion, Node node) {
        if (versionNode.get(newVersion) != null) {
            throw new RuntimeException("This version already exists,");
        }

        versionNode.put(newVersion, node);
    }

    public Node getNextNode(int version) {
        return versionNode.get(version);
    }

    public void appendVersion(int fromVersion, int toVersion) {
        versionNode.put(toVersion, versionNode.get(fromVersion));
    }
}
