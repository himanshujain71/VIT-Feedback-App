package com.example.user.feedbackform;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class imgact2 extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private Bitmap bitmap;
    private ImageView imageView;
    Uri selectedImage;
    DBAdapter dba;
    SQLiteDatabase db;
    String passedArg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgview2);
       // imageView = (ImageView) findViewById(R.id.img);
        dba=new DBAdapter(this);
        Button b=(Button)findViewById(R.id.btn1);
        b.setVisibility(View.VISIBLE);
        Button b2=(Button)findViewById(R.id.button5);
        b2.setVisibility(View.INVISIBLE);

        db=dba.getWritableDatabase();
passedArg= getIntent().getExtras().getString("arg");
    }
    public void takePhoto(View view) {


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);

    }
public void exit(View v)
{
    super.finish();
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) findViewById(R.id.img);
                    ContentResolver cr = getContentResolver();

                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        imageView.setImageBitmap(bitmap);
                       /* Toast.makeText(this,passedArg+"**"+ selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
*/
                        Button b=(Button)findViewById(R.id.btn1);
                        b.setVisibility(View.INVISIBLE);
                        Button b2=(Button)findViewById(R.id.button5);
                        b2.setVisibility(View.VISIBLE);


                    } catch (Exception e) {
                       /* Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();*/
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

public void updatefinal(View v)
{//byte[] data = getBitmapAsByteArray(bitmap);
   String as= saveToInternalStorage(bitmap);
    db.execSQL("update feedbacktable set image='"+as +"' where phone='"+passedArg+"';");
    Toast.makeText(imgact2.this, "Your details have been saved!", Toast.LENGTH_SHORT).show();
//saveImage();
   // Toast.makeText(imgact2.this, saveToInternalStorage(bitmap)+"", Toast.LENGTH_SHORT).show();
    super.finish();
}

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }
    private void saveImage() {

        File myDir=new File("/sdcard/saved_images");
        myDir.mkdirs();
        String fname = "Image-"+ passedArg +".jpeg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,passedArg+"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}