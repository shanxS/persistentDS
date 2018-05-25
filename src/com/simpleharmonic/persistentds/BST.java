package com.simpleharmonic.persistentds;


import lombok.AllArgsConstructor;
import lombok.Getter;

public class BST {
    private Node head;

    public BST() {}

    public void insert(int v) {
        if (head == null) {
            head = new Node(v);
        } else {
            insert(head, v);
        }
    }

    private void insert(Node node, int v) {
        if (node.getValue() < v) {
            if (node.getRight() != null) {
                insert(node.getRight(), v);
            } else {
                node.setRight(new Node(v));
            }
        } else if (node.getValue() > v) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), v);
            } else {
                node.setLeft(new Node(v));
            }
        } else {
            System.out.println("Value already exist in BST");
        }
    }

    public void print() {
        print(head);
    }

    @AllArgsConstructor
    private class DeleteVector {
        final Node parent;
        final Node replacement;
    }

    public void delete(int v) {
        DeleteVector delVector = computeDeletionVector(head, v);
        if (delVector == null) {
            System.out.println("cant find node with target value");
        } else {
            if (delVector.parent == null) {
                head = delVector.replacement;
            } else {

                if (delVector.parent.getValue() < v) {
                    delVector.parent.setRight(delVector.replacement);
                } else if (delVector.parent.getValue() > v) {
                    delVector.parent.setLeft(delVector.replacement);
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
            replacement.setLeft(target.getLeft());
            reParent(replacement, orphan);

            return new DeleteVector(parent, replacement);
        }  else if (target.getLeft() != null) {
            replacement = target.getLeft();
            Node orphan = replacement.getRight();
            replacement.setRight(target.getRight());
            reParent(replacement, orphan);

            return new DeleteVector(parent, replacement);
        }

        return new DeleteVector(parent, replacement);
    }

    private void reParent(Node node, Node orphan) {
        if (node.getValue() < orphan.getValue()) {
            if (node.getRight() == null) {
                node.setRight(orphan);
            } else {
                reParent(node.getRight(), orphan);
            }
        } else if (node.getValue() > orphan.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(orphan);
            } else {
                reParent(node.getLeft(), orphan);
            }
        }
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }

        print(node.getLeft());

        System.out.print(node.getValue());
        if (node.getLeft() != null) {
            System.out.print(" left -> " + node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            System.out.print(" right -> " + node.getRight().getValue());
        }
        System.out.println();

        print(node.getRight());
    }

}
