package com.example.flashlight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CameraManager cameraManager;
    Button tb;
    String cameraId;
    boolean isTorchOn = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.tb);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }


        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTorchOn = !isTorchOn;
                try{
                    cameraManager.setTorchMode(cameraId,isTorchOn);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}