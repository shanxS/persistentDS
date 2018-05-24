package com.simpleharmonic.persistentds;

import lombok.Getter;
import lombok.Setter;

public class Node {
    @Setter @Getter private Node left, right;
    @Getter private int value;

    public Node(int v) {
        this.value = v;
    }

}
