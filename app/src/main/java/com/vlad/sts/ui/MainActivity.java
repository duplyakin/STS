package com.vlad.sts.ui;


import android.os.Bundle;

import com.vlad.sts.R;
import com.vlad.sts.engine.eventbus.EBActivity;

public class MainActivity extends EBActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
