package com.developtech.efuelfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.developtech.efuelfo.ui.activities.common.HomeActivity;

public class UserAccountDetail extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    TextView str;
    Button back,done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        str=(TextView)findViewById(R.id.skipthispage);
        back=(Button)findViewById(R.id.btnbackaccountdetail);
        done=(Button)findViewById(R.id.accountdetail);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserAccountDetail.this,EfuelStationDteail.class);
                startActivity(intent);
               /* finish();*/
            }
        });

        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                /*finish();*/
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et1=(EditText)findViewById(R.id.useraccountname);
                et2=(EditText)findViewById(R.id.useraccountholder);
                et3=(EditText)findViewById(R.id.userbankname);
                et4=(EditText)findViewById(R.id.userifccode);
                str=(TextView) findViewById(R.id.skipthispage);



                if(TextUtils.isEmpty(et1.getText().toString())){
                    et1.setError("Empty field not allowed");
                    et1.requestFocus();
                    return;
                }else if(TextUtils.isEmpty(et2.getText().toString())){
                    et2.setError("Empty field not allowed");
                    et2.requestFocus();
                    return;
                }/*else if(TextUtils.getTrimmedLength(et2.getText().toString())!=12){
                    et2.setError("Please enter 10 digit!");
                    et2.requestFocus();
                    return;
                }*/else if(TextUtils.isEmpty(et3.getText().toString())){
                    et3.setError("Empty field not allowed");
                    et3.requestFocus();
                    return;
                }else if(TextUtils.isEmpty(et4.getText().toString())){
                    et4.setError("Empty field is not allowed");
                    et4.requestFocus();
                }else{

                    String st1=et1.getText().toString();
                    String st2=et2.getText().toString();
                    String st3=et3.getText().toString();
                    String st4=et4.getText().toString();


                    Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("k1",st1);
                    intent.putExtra("k2",st2);
                    intent.putExtra("k3",st3);
                    intent.putExtra("k4",st4);
                    startActivity(intent);
                  /*  finish();*/
                }


            }
        });
    }
}
