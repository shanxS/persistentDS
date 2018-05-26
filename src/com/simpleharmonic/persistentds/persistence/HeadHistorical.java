package com.simpleharmonic.persistentds.persistence;

import com.simpleharmonic.persistentds.tree.Node;
import com.simpleharmonic.persistentds.persistence.data.NodeType;

public class HeadHistorical extends Historical {

    public void addNewVersion(Node head, int version) {
        instances.add(new NodeType(head, version));
    }
}
