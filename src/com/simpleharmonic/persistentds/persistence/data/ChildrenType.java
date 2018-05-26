package com.simpleharmonic.persistentds.persistence.data;

import com.simpleharmonic.persistentds.tree.Node;
import lombok.Getter;

public class ChildrenType implements DataType {
    @Getter private final Node left, right;
    private final Integer version;


    public ChildrenType(Node left, Node right, Integer version) {
        this.left = left;
        this.right = right;
        this.version = version;
    }

    @Override
    public int getVersion() {
        return version;
    }
}
