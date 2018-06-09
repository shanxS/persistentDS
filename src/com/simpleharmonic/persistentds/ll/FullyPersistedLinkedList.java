package com.simpleharmonic.persistentds.ll;

import java.util.List;

public interface FullyPersistedLinkedList <T> {

    int getMaxVersion();
    int createNew(T element);
    int addToVersion(T element, int version);
    int addFirstToVersion(T element, int version);
    void addAfterToVersion(T element, int version, T predecessor);
    T removeFromVersion(T element, int version);
    boolean findInVersion(T element, int version);
    List<Integer> findVersions(T element);
    void print(int version);

}
