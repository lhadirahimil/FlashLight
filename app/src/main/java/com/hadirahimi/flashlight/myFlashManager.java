package com.hadirahimi.flashlight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

public class myFlashManager {
    private CameraManager cameraManager;
    private Camera camera;
    private Context context;

    public myFlashManager(Context context) {
        this.context = context;

    }

    public void turnOnFlash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            try {
                cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                String camera_id = null;
                if (cameraManager != null) {
                    camera_id = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(camera_id, true);

                }
            } catch (@SuppressLint("NewApi") CameraAccessException e) {
                e.printStackTrace();
            }
        } else turnOnCameraInlowVersion();

    }

    private void turnOnCameraInlowVersion()
    {
        camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
    }

    public void turnOffFlash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            try {
                cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                String camera_id = null;
                if (cameraManager != null) {
                    camera_id = cameraManager.getCameraIdList()[0];
                    cameraManager.setTorchMode(camera_id, false);

                }
            } catch (@SuppressLint("NewApi") CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.stopPreview();
            camera.release();

        }
    }
}
