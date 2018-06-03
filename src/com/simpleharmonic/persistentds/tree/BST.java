package com.simpleharmonic.persistentds.tree;


import com.simpleharmonic.persistentds.persistence.HeadHistorical;
import com.simpleharmonic.persistentds.persistence.data.NodeType;
import lombok.AllArgsConstructor;

public class BST {
    protected Node head;
    protected HeadHistorical headVersions;
    protected int currentVersion;

    public BST() {
        currentVersion = 0;
        headVersions = new HeadHistorical();
    }

    public void insert(int v) {
        if (head == null) {
            head = new Node(v, currentVersion);
            headVersions.addNewVersion(head, currentVersion);
        } else {
            ++currentVersion;
            insert(head, v);
        }
    }

    private void insert(Node node, int v) {
        if (node.getValue() < v) {
            if (node.getRight() != null) {
                insert(node.getRight(), v);
            } else {
                node.setRight(new Node(v, currentVersion), currentVersion);
            }
        } else if (node.getValue() > v) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), v);
            } else {
                node.setLeft(new Node(v, currentVersion), currentVersion);
            }
        } else {
            System.out.println("Value already exist in BST");
        }
    }

    public boolean findNode(int v) {
        return findNode(head, v);
    }

    private boolean findNode(Node node, int v) {
        if (node == null) {
            System.out.println("cant find node with value " + v);
            return false;
        } else if (node.getValue() == v) {
            printNode(node);
            return true;
        } else if (node.getValue() > v) {
            return findNode(node.getLeft(), v);
        } else {
            return findNode(node.getRight(), v);
        }
    }

    public void printTree() {
        printTree(head);
    }

    @AllArgsConstructor
    private class DeleteVector {
        final Node parent;
        final Node replacement;
    }

    public void delete(int v) {
        if (!findNode(v)) {
            System.out.println("node does not exist");
            return;
        }
        ++currentVersion;

        DeleteVector delVector = computeDeletionVector(head, v);
        if (delVector == null) {
            System.out.println("cant find node with target value");
        } else {



            if (delVector.parent == null) {
                head = delVector.replacement;
            } else {

                if (delVector.parent.getValue() < v) {
                    delVector.parent.setRight(delVector.replacement, currentVersion);
                } else if (delVector.parent.getValue() > v) {
                    delVector.parent.setLeft(delVector.replacement, currentVersion);
                } else {
                    System.out.println("we should not have reached here");
                }

            }
        }

    }

    private DeleteVector computeDeletionVector(Node node, int v) {
        if (node == null) {
            return null;
        }

        if (node.getValue() == v) {
            return computeThisDeletionVector(null, node);
        } else if (node.getValue() < v) {
            if (node.getRight() != null && node.getRight().getValue() == v) {
                Node target = node.getRight();
                return computeThisDeletionVector(node, target);
            } else {
                return computeDeletionVector(node.getRight(), v);
            }
        } else {
            if (node.getLeft() != null && node.getLeft().getValue() == v) {
                Node target = node.getLeft();
                return computeThisDeletionVector(node, target);
            } else {
                return computeDeletionVector(node.getLeft(), v);
            }
        }
    }

    private DeleteVector computeThisDeletionVector(Node parent, Node target) {
        Node replacement = null;
        if (target.getRight() != null) {
            replacement = target.getRight();
            Node orphan = replacement.getLeft();
            replacement.setLeft(target.getLeft(), currentVersion);
            reParent(replacement, orphan);

            return new DeleteVector(parent, replacement);
        }  else if (target.getLeft() != null) {
            replacement = target.getLeft();
            Node orphan = replacement.getRight();
            replacement.setRight(target.getRight(), currentVersion);
            reParent(replacement, orphan);

            return new DeleteVector(parent, replacement);
        }

        target.setLeft(null, currentVersion);
        target.setRight(null, currentVersion);

        return new DeleteVector(parent, replacement);
    }

    private void reParent(Node replacement, Node orphan) {
        if (orphan == null) {
            return;
        }

        if (replacement.getValue() < orphan.getValue()) {
            if (replacement.getRight() == null) {
                replacement.setRight(orphan, currentVersion);
            } else {
                reParent(replacement.getRight(), orphan);
            }
        } else if (replacement.getValue() > orphan.getValue()) {
            if (replacement.getLeft() == null) {
                replacement.setLeft(orphan, currentVersion);
            } else {
                reParent(replacement.getLeft(), orphan);
            }
        }
    }

    private void printTree(Node node) {
        if (node == null) {
            return;
        }

        printTree(node.getLeft());
        printNode(node);
        printTree(node.getRight());
    }

    private void printNode(Node node) {
        System.out.print(node.getValue() + " ver: " + node.getCurrentVersion());
        if (node.getLeft() != null) {
            System.out.print(" left -> " + node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            System.out.print(" right -> " + node.getRight().getValue());
        }
        System.out.println();
    }

}
