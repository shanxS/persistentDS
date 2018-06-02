package com.simpleharmonic.persistentds.tree;

import com.simpleharmonic.persistentds.persistence.NodeHistorical;
import com.simpleharmonic.persistentds.persistence.data.ChildrenType;
import lombok.Getter;

public class Node {
    @Getter private Node left, right;
    @Getter private int value;
    @Getter private int currentVersion;
    private NodeHistorical historical;

    public Node(int v, int version) {
        this.value = v;
        this.currentVersion = version;
        this.historical = new NodeHistorical();
        historical.addVersion(left, right, currentVersion);
        System.out.println("node created " + v + " ver " + version);
    }

    public Node getLeftForVersion(int version) {
        ChildrenType children = (ChildrenType) historical.findEffectiveVersion(version);
        if (children == null) {
            return null;
        }

//        if (children.getLeft() != null) {
//            System.out.println(" Node " + value + ":" + currentVersion
//                               + " left version selected "
//                               + children.getVersion());
//        }

        return children.getLeft();
    }

    public Node getRightForVersion(int version) {
        ChildrenType children = (ChildrenType) historical.findEffectiveVersion(version);
        if (children == null) {
            return null;
        }

//        if (children.getRight() != null) {
//            System.out.println(" Node " + value + ":" + currentVersion
//                               + " right version selected "
//                               + children.getVersion());
//        }

        return children.getRight();
    }

    public void setLeft(Node node, int version) {
        currentVersion = version;
        left = node;
        historical.addVersion(left, right, currentVersion);
    }

    public void setRight(Node node, int version) {
        currentVersion = version;
        right = node;
        historical.addVersion(left, right, currentVersion);
    }

}
