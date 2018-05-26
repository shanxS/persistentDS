package com.simpleharmonic.persistentds.persistence;

import com.simpleharmonic.persistentds.persistence.data.DataType;

import java.util.ArrayList;

public abstract class Historical {
    protected final ArrayList<DataType> instances;

    protected Historical() {
        this.instances = new ArrayList<>();
    }


    public DataType findEffectiveVersion(int targetVersion) {

        int start = 0, end = instances.size()-1;
        int mid = (start + end)/2;
        DataType potentialInstance = null;
        while (start <= end) {
            int thisVersion = instances.get(mid).getVersion();
            if (thisVersion == targetVersion) {
                return instances.get(mid);
            } else if (thisVersion < targetVersion) {

                if (potentialInstance == null || thisVersion > potentialInstance.getVersion()) {
                    potentialInstance = instances.get(mid);
                }

                start = mid+1;
            } else {
                end = mid-1;
            }

            mid = (start + end)/2;
        }


        return potentialInstance;
    }
}
