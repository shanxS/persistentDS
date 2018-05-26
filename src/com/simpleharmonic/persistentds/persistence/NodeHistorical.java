package com.simpleharmonic.persistentds.persistence;

import com.simpleharmonic.persistentds.tree.Node;
import com.simpleharmonic.persistentds.persistence.data.ChildrenType;

public class NodeHistorical extends Historical {
    public void addVersion(Node left, Node right, Integer version) {
        instances.add(new ChildrenType(left, right, version));
    }
}
