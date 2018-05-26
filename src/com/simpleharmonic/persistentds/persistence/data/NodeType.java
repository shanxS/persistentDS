package com.simpleharmonic.persistentds.persistence.data;


import com.simpleharmonic.persistentds.tree.Node;
import lombok.Getter;

public class NodeType implements DataType {
    @Getter
    private final Node node;
    private final Integer version;

    public NodeType(Node node, Integer version) {
        this.node = node;
        this.version = version;
    }

    @Override
    public int getVersion() {
        return version;
    }
}
