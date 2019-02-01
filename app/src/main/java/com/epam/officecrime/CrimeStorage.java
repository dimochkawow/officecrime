package com.epam.officecrime;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dmytro_Torianik on 1/31/2019.
 */

public class CrimeStorage {

    private static CrimeStorage storage;

    private List<Crime> crimes;

    public static CrimeStorage get(Context context) {
        if (storage == null) {
            storage = new CrimeStorage(context);
        }
        return storage;
    }

    private CrimeStorage(Context context) {
        crimes = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crime.setRequiresPolice(i % 3 == 0);
            crimes.add(crime);
        }
    }

    public List<Crime> findAll() {
        return crimes;
    }

    public Crime byId(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }

        return null;
    }
}
