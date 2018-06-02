package com.simpleharmonic.persistentds.tree.utils;

import com.simpleharmonic.persistentds.persistence.data.NodeType;
import com.simpleharmonic.persistentds.tree.BST;
import com.simpleharmonic.persistentds.tree.Node;

public class Chronos extends BST {

    public boolean findNodeInVersion(int value, int version) {
        if (version < 0 || version > currentVersion) {
            System.out.println("invalid version, current version " + currentVersion);
        }

        Node versionHead = ((NodeType)headVersions.findEffectiveVersion(version)).getNode();
        return findNodeInVersion(versionHead, value, version);
    }

    private boolean findNodeInVersion(Node node, int value, int version) {
        if (node == null) {
            return false;
        }

        if (node.getValue() == value) {
            return true;
        } else if (node.getValue() > value) {
            Node left = node.getLeftForVersion(version);
            if (left != null) {
                return findNodeInVersion(left, value, version);
            } else {
                return false;
            }
        } else {
            Node left = node.getRightForVersion(version);
            if (left != null) {
                return findNodeInVersion(left, value, version);
            } else {
                return false;
            }
        }
    }

    public void printTreeVersion(int version) {
        if (version < 0 || version > currentVersion) {
            System.out.println("invalid version, current version " + currentVersion);
        }

        Node versionHead = ((NodeType)headVersions.findEffectiveVersion(version)).getNode();
        printTreeVersion(versionHead, version);
    }

    private void printTreeVersion(Node versionNode, int version) {
        if (versionNode == null) {
            return;
        }

        printTreeVersion(versionNode.getLeftForVersion(version), version);
        printVersionNode(versionNode, version);
        printTreeVersion(versionNode.getRightForVersion(version), version);
    }

    private void printVersionNode(Node node, int version) {
        System.out.print(node.getValue());
        Node child;
        if ((child = node.getLeftForVersion(version)) != null) {
            System.out.print(" left -> " + child.getValue());
        }
        if ((child = node.getRightForVersion(version)) != null) {
            System.out.print(" right -> " + child.getValue());
        }
        System.out.println();
    }

}
