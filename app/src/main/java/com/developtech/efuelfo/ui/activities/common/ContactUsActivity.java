package com.developtech.efuelfo.ui.activities.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developtech.efuelfo.R;

public class ContactUsActivity extends MyActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        init();
        initComponents();
        setHomeTitle(getString(R.string.contact_us_title));
        setHomeImage(true);
        showNotification(false);
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void retry() {

    }
}
