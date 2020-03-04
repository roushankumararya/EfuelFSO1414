package com.developtech.efuelfo.ui.adapters.stationOwner;

import android.os.Bundle;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.ui.activities.common.MyActionBar;

public class CashTransactionActivity extends MyActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_transaction);
        init();
        setHomeTitle("Cash Transactions");
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void retry() {

    }
}
