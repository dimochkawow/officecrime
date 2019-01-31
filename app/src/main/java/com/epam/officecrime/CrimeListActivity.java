package com.epam.officecrime;

import android.support.v4.app.Fragment;

/**
 * Created by Dmytro_Torianik on 1/31/2019.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
