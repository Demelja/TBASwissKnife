package com.mda.tba.swissknife;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentTraverse fragmentTraverse;
    FragmentPitotTube fragmentPitotTube;
    FragmentHowItWorks fragmentHowItWorks;
    FrameLayout content;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.menu_pitot_tube:
                    fTrans.replace(R.id.content, fragmentPitotTube);
                    break;
                case R.id.menu_traverse:
                    fTrans.replace(R.id.content, fragmentTraverse);
                    break;
                case R.id.menu_help:
                    fTrans.replace(R.id.content, fragmentHowItWorks);
                    break;
                default:
            }
            fTrans.addToBackStack(null);
            fTrans.commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fragmentTraverse = new FragmentTraverse();
        fragmentPitotTube = new FragmentPitotTube();
        fragmentHowItWorks = new FragmentHowItWorks();

        content = (FrameLayout) findViewById(R.id.content);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content, fragmentTraverse);
            transaction.commit();
        }



    }

}
