package com.bignerdranch.android.criminalintentfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintentfinal.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintentfinal.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintentfinal.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    //    This is a singleton 7-15, exist as long as app stays in memory
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context)   {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
//        mCrimes = new ArrayList<>();  have a db now 14.7

//        for (int i = 0; i < 100; i++)   {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);//every other one
//            mCrimes.add(crime);
//        }
    }

    public void addCrime(Crime c)   {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
//        mCrimes.add(c); db now
    }

    public List<Crime> getCrimes()  {
//        return mCrimes;
//        return new ArrayList<>();
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())   {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }   finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id)  {
//        for (Crime crime : mCrimes) {
//            if(crime.getId().equals(id)) {
//                return crime;
//            }
//        }
//        return null;
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[]    { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }  finally {
            cursor.close();
        }
    }

    public  void updateCrime(Crime crime)   {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

//    private Cursor queryCrimes(String whereClause, String[] whereArgs)  {
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs)  {
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,       //columns, null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

//        return cursor;
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime)  {
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }
}
