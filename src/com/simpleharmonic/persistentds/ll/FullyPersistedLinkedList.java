package com.simpleharmonic.persistentds.ll;

import java.util.List;

public interface FullyPersistedLinkedList <T> {

    int add(T element, int version);
    void addFirst(T element, int version);
    void addAfter(T element, int version, T predecessor);
    T remove(T element, int version);
    boolean find(T element, int version);
    List<Integer> findVersions(T element);
    void print(int version);

}
