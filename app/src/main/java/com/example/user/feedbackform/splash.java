package com.example.user.feedbackform;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.transition.Transition;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class splash extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
/*try {
   synchronized (this){wait(6000);}
}
        catch (Exception e)
        {}
        super.finish();
*/

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
              startActivity(mainIntent);
               splash.this.finish();
           //     overridePendingTransition(R.anim.push_down_in,R.anim.nothing);

            }
        }, 4000);







}}