package com.example.user.feedbackform;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class viewimgact extends AppCompatActivity {
    String passedArg;
    ImageView img;
    Cursor c;
    DBAdapter dba;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewimg);
        dba=new DBAdapter(this);

        db=dba.getWritableDatabase();

   img=(ImageView)findViewById(R.id.img3);
      passedArg= getIntent().getExtras().getString("takeval");
      c=db.rawQuery("Select image from feedbacktable where phone='"+passedArg+"';",null);
       // c.moveToFirst();
        //Uri selectedImage = Uri.parse(c.getString(0)+"");
       // getContentResolver().notifyChange(selectedImage, null);
       // ContentResolver cr = getContentResolver();
meth();
        //Bitmap bitmap;
        try {
       //     bitmap = android.provider.MediaStore.Images.Media
         //           .getBitmap(cr, selectedImage);
        /*    Toast.makeText(this, ""+selectedImage.toString(),
                    Toast.LENGTH_LONG).show();
         */
          //  bitmap=getImage();
           //img.setImageBitmap(bitmap);
           // c.moveToFirst();
            //Toast.makeText(viewimgact.this, c.getString(0)+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(viewimgact.this, c.getString(0)+"", Toast.LENGTH_SHORT).show();
            //bitmap = BitmapFactory.decodeByteArray(c.getBlob(0), 0, c.getBlob(0).length);

            //img.setImageBitmap(bitmap);

        }
        catch (Exception e) {
                               //   Log.e("Camera", e.toString());
        }

    }
public void onClick21(View v)
{super.finish();}

    public Bitmap getImage(){


c.moveToFirst();
       {
            byte[] imgByte =c.getBlob(0);
            c.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }



    }
    public void meth()
    {c.moveToFirst();
        File imgFile = new File(c.getString(0)+"/"+passedArg+"profile.jpg");

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());



            img.setImageBitmap(myBitmap);

        }}

}
