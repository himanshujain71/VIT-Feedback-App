package com.example.user.feedbackform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class imgact4 extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgview4);
        imageView = (ImageView) findViewById(R.id.img);

callgallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==1 && resultCode==RESULT_OK && data!=null)
    {Uri selectedImage=data.getData();
        try {
            Bitmap bitmapImage=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
        imageView.setImageBitmap(bitmapImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
    public void callgallery()
    {
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i,1);
    }
    public  void onClick(View v)
    {callgallery();}
}