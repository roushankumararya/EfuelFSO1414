package com.developtech.efuelfo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class UserPersonalDeatil extends AppCompatActivity  implements View.OnClickListener {

    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText username, userlastname, useremailaddress, dateofbirth;
    private String valid_email;
    Button btn1, btnnext;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RadioButton radiobtn1,radiobtn2;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        username = (EditText) findViewById(R.id.userfirstenternamefill);
        userlastname = (EditText) findViewById(R.id.userlastenternamefill);
        useremailaddress = (EditText) findViewById(R.id.useremailaddressfill);
        radiobtn1=(RadioButton)findViewById(R.id.male);
        radiobtn2=(RadioButton)findViewById(R.id.female);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroupbtn);
        dateofbirth = (EditText) findViewById(R.id.userdateofbirthfill);
        btnnext= (Button) findViewById(R.id.personaldetailfill);
        dateofbirth.setOnClickListener(this);

       /* Pattern ps=Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher( username.getText().toString());*/



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    username.setError("Empty field not alowed");
                    username.requestFocus();
                    return;
                }else if(TextUtils.isEmpty(userlastname.getText().toString())){
                    userlastname.setError("Empty field not allowed");
                    userlastname.requestFocus();
                    return;
                }else if(TextUtils.isEmpty(useremailaddress.getText().toString())) {
                    useremailaddress.setError("Empty field not allowed");
                    useremailaddress.requestFocus();
                    return;
                }
                else  if(!useremailaddress.getText().toString().trim().matches(emailPattern)){
                        useremailaddress.setError("Invalid email id");
                        useremailaddress.requestFocus();
                        return;
                }
                /*else if(radioGroup.getCheckedRadioButtonId() == -1){

                    Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                }*/
                else if(radioGroup.getCheckedRadioButtonId() == -1){
                    if(!radiobtn1.isChecked()){
                      //  radiobtn2.setError("Please select Gender");
                       // radiobtn2.requestFocus();
                        Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   // radiobtn1.setError("Please select Gender");
                   // radiobtn1.requestFocus();
                 //   Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                   // return;
                }/*else if(radioGroup.getCheckedRadioButtonId() == 0){
                    if(radiobtn1.isChecked()){
                        radiobtn1.getText().toString();
                    }else{
                        radiobtn2.getText().toString();
                    }
                }*/
                else if(TextUtils.isEmpty(dateofbirth.getText().toString())){
                    dateofbirth.setError("Empty field not allowed");
                    dateofbirth.requestFocus();
                }else{



                    Intent intent = new Intent(getApplicationContext(), EfuelStationDteail.class);
                    if ( ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText()!=null){
                         String value =((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()))
                                 .getText().toString();
                        intent.putExtra("GetOption",value);

                    }
                    Log.e("kkk","hello"+username);
                      String usename=username.getText().toString();
                      String uselastname=userlastname.getText().toString();
                      String useemai=useremailaddress.getText().toString();
                      String datebirth=dateofbirth.getText().toString();
                    /*  String option1=radiobtn1.getText().toString();
                      String option2=radiobtn2.getText().toString();*/
                     intent.putExtra("UserName",usename);
                      intent.putExtra("UserLastNAme",uselastname);
                      intent.putExtra("UserEmail",useemai);
                      intent.putExtra("DateBirth", datebirth);
                      startActivity(intent);
                     /* finish();*/
                      /*appComponent.getServiceCaller().callService(appComponent.getAllApis().
                              getFuelStations(),intent);*/


                }

            }
        });
    }
     @Override
        public void onClick(View view) {

            if (view.getId() ==R.id.userdateofbirthfill) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateofbirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, 1950, mMonth, mDay);

                datePickerDialog.show();
            }
    }
}
