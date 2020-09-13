package com.example.user.feedbackform;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Details extends AppCompatActivity {


    int num=1;
    EditText e1,e2,e3,e4,e5,e6,e7;
    DBAdapter dba;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        dba=new DBAdapter(this);

        db=dba.getWritableDatabase();

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        e6=(EditText)findViewById(R.id.editText6);
        e7=(EditText)findViewById(R.id.editText7);
        String date=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        e2.setText(date+"");
        e2.setFocusable(false);
    }
    public void buttonmeth(View v)
    {
     db.execSQL("Insert into feedbacktable values('"+e1.getText().toString()+"','"+e2.getText().toString()+"','"+e3.getText().toString()+"','"+e4.getText().toString()+"','"+e5.getText().toString()+"','"+e6.getText().toString()+"','"+e7.getText().toString()+"',null);");
    //   db.execSQL("insert into feedbacktable(name) values('asd');");
        Toast.makeText(this,"Thank You for your feedback!",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,imgact2.class);
        i.putExtra("arg",e5.getText()+"");
        startActivity(i);
        super.finish();
    }
}