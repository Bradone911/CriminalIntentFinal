package com.bignerdranch.android.criminalintentfinal;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    //    This is a singleton 7-15, exist as long as app stays in memory
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }
    //       loaded model layer with 100 crimes
    private CrimeLab(Context context)   {
        mCrimes = new ArrayList<>();
//        for (int i = 0; i < 100; i++)   {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);//every other one
//            mCrimes.add(crime);
//        }
    }

    public void addCrime(Crime c)   {
        mCrimes.add(c);
    }

    public List<Crime> getCrimes()  {
        return mCrimes;
    }

    public Crime getCrime(UUID id)  {
        for (Crime crime : mCrimes) {
            if(crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
