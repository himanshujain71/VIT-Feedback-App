package com.example.user.feedbackform;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class View_Details extends AppCompatActivity {

    DBAdapter dba;
TextView txt,txt1;
    int na=1;
EditText e1,e2,e3,e4,e5,e6,e7;
    String quer="Select* from feedbacktable;";
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);

       dba=new DBAdapter(this);

         db=dba.getWritableDatabase();

        //Toast.makeText(this, na+"/"+c.getCount(), Toast.LENGTH_SHORT).show();
        txt=(TextView)findViewById(R.id.textView2);
        txt1=(TextView)findViewById(R.id.textView3);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        e7 = (EditText) findViewById(R.id.editText7);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        callthecurosr();
        nofeedbacks();
        txt.setText("Total:"+c.getCount());
updatecounter();

    }
    public void backmeth(View v)
    {
        super.finish();
    }
    public void nextmeth(View v)
    {
try {
na+=1;
  //  Toast.makeText(this,""+na,Toast.LENGTH_SHORT).show();
if(na>c.getCount())
{c.moveToFirst();na=1;}
     else
        c.moveToNext();

    e1.setText(c.getString(0));
    e2.setText(c.getString(1));
    e3.setText(c.getString(2));
    e4.setText(c.getString(3));
    e5.setText(c.getString(4));
    e6.setText(c.getString(5));
    e7.setText(c.getString(6));
updatecounter();

}
catch (Exception e)
{

}

    }
    public void prevmeth(View v)
    {
try {na-=1;
//    Toast.makeText(this,na+"/"+c.getCount(),Toast.LENGTH_SHORT).show();
    if(na<1)
    {c.moveToLast();na=c.getCount();}
    else
        c.moveToPrevious();

    e1.setText(c.getString(0));
    e2.setText(c.getString(1));
    e3.setText(c.getString(2));
    e4.setText(c.getString(3));
    e5.setText(c.getString(4));
    e6.setText(c.getString(5));
    e7.setText(c.getString(6));
updatecounter();
}
catch(Exception e)
{}

    }

    public void deletefab(View v)
    {
       try {
           db.execSQL("Delete from feedbacktable where email='" + c.getString(3) + "' AND phone='"+c.getString(4)+"';");
           Toast.makeText(this, "Record has been successfully deleted!", Toast.LENGTH_SHORT).show();
          // c.moveToFirst();
           callthecurosr();
           txt.setText("Total:"+c.getCount());
nofeedbacks();
updatecounter();
       }
    catch(Exception e)
    {}
    }
public void callthecurosr(){
    try {
        c = db.rawQuery(quer, null);
        c.moveToFirst();

        e1.setText(c.getString(0) + "");
        e2.setText(c.getString(1) + "");
        e3.setText(c.getString(2) + "");
        e4.setText(c.getString(3) + "");
        e5.setText(c.getString(4) + "");
        e6.setText(c.getString(5) + "");
        e7.setText(c.getString(6) + "");
    updatecounter();
    }
catch(Exception e)
{}

}
   public void updatecounter()
   {
       txt1.setText(na+"/"+c.getCount());
   }
public void nofeedbacks()
{if(c.getCount()==0)
{  Toast.makeText(this, "No feedbacks recorded", Toast.LENGTH_SHORT).show();super.finish();}}

public void onimgclick(View v)
{
   // Toast.makeText(this,c.getString(7)+"", Toast.LENGTH_SHORT).show();
    Intent i=new Intent(this,viewimgact.class);
    i.putExtra("takeval",c.getString(4)+"");
    startActivity(i);
}

}