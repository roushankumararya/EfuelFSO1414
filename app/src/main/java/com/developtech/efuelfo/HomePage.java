// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
  Button btnnextpagehome;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main1);
    btnnextpagehome=(Button)findViewById(R.id.continuewithnextnumber);
    btnnextpagehome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(), UserMobileNumber.class);
        startActivity(intent);
        finish();
      }
    });
  }
}


