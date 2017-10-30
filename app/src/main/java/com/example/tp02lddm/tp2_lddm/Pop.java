package com.example.tp02lddm.tp2_lddm;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by giovannariqueti on 20/10/17.
 */

class Pop extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int heigh = dm.heightPixels;

        getWindow().setLayout((int)(width*.6), (int)(heigh*.6));
    }
}
