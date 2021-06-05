package com.hadirahimi.flashlight;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView ivChangeStatus;
    private int REQ_CODE = 100;
    private boolean isFlash = false;
    private Drawable drawableLight=null,drawableBack=null;
    private LinearLayout linearMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        permission();
        OnClick();

    }

    private void permission()
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQ_CODE);
        }
    }
    private boolean checkCameraPermission()
    {
        return ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
    private void OnClick() {
        ivChangeStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    if (checkCameraPermission())
                    {
                        myFlashManager myFlashManager=new myFlashManager(MainActivity.this);
                        if (!isFlash)
                        {
                            //flash is off Turn On
                            isFlash=true;
                            myFlashManager.turnOnFlash();
                            drawableLight=getResources().getDrawable(R.drawable.light_on);
                            ivChangeStatus.setImageDrawable(drawableLight);
                            drawableBack=getResources().getDrawable(R.drawable.lightonback);
                            linearMain.setBackground(drawableBack);


                        }else
                        {
                            //flash is on turn off
                            myFlashManager.turnOffFlash();
                            isFlash=false;
                            drawableLight=getResources().getDrawable(R.drawable.light_off);
                            ivChangeStatus.setImageDrawable(drawableLight);

                             drawableBack=getResources().getDrawable(R.drawable.background);
                            linearMain.setBackground(drawableBack);
                        }



                } else {
                    Toast.makeText(MainActivity.this, "شما مجوز های لازم جهت فعالسازی چراغ قوه را تایید نکرده اید", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        ivChangeStatus = findViewById(R.id.iv_Light_Main);
        drawableLight=getResources().getDrawable(R.drawable.light_off);
        ivChangeStatus.setImageDrawable(drawableLight);
        linearMain=findViewById(R.id.linear_Main);
    }
}