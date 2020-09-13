package com.example.user.feedbackform;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class imgact1 extends AppCompatActivity {

    private final static String DEBUG_TAG = "MakePhotoActivity";
    private Camera camera;
    private int cameraId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imgview1);
      try {
          if (!getPackageManager()
                  .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
              Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                      .show();
          } else {
              cameraId = findFrontFacingCamera();
              if (cameraId < 0) {
                  Toast.makeText(this, "No front facing camera found.",
                          Toast.LENGTH_LONG).show();
              } else {
                  camera = Camera.open(cameraId);
              }
          }
      }
    catch(Exception e)
    {;}
    }

    public void onClick(View view) {
        camera.startPreview();
     //  camera.takePicture(null, null);
                ;
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onPause() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onPause();
    }

}