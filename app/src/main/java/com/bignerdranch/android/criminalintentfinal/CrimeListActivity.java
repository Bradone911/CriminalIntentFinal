package com.bignerdranch.android.criminalintentfinal;

public class CrimeListActivity extends SingleFragmentActivity   {

    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return new CrimeFragment();
    }
}
