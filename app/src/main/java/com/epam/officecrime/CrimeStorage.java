package com.epam.officecrime;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Dmytro_Torianik on 1/31/2019.
 */

public class CrimeStorage {

    private static CrimeStorage storage;

    private List<Crime> crimes;
    private Map<UUID, Integer> idsToPositionMapping;

    public static CrimeStorage get(Context context) {
        if (storage == null) {
            storage = new CrimeStorage(context);
        }
        return storage;
    }

    private CrimeStorage(Context context) {
        crimes = new ArrayList<>();
        idsToPositionMapping = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crime.setRequiresPolice(i % 3 == 0);
            crimes.add(crime);
            idsToPositionMapping.put(crime.getId(), i);
        }
    }

    public List<Crime> findAll() {
        return crimes;
    }

    public Crime byId(UUID id) {
        int crimePosition = idsToPositionMapping.get(id);
        return crimes.get(crimePosition);
    }

    public int positionById(UUID id) {
        return idsToPositionMapping.get(id);
    }
}
