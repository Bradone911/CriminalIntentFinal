package com.bignerdranch.android.criminalintentfinal;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity   {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
