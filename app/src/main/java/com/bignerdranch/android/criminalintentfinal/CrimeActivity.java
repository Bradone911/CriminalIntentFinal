package com.bignerdranch.android.criminalintentfinal;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    /*    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fragment);

            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);

            if (fragment == null)   {
                fragment = new CrimeFragment();
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
    //        above 3 lines says create a new fragment transaction, include one add operation in it, then commit it
            }
        }*/
    @Override
    protected Fragment createFragment() {
        return  new CrimeFragment();
    }
}