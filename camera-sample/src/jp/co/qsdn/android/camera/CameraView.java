package jp.co.qsdn.android.camera;


import android.content.Context;

import android.hardware.Camera;

import android.util.Log;

import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
  private final static String TAG = CameraView.class.getName();
  private Camera camera = null;

  public CameraView(Context context) {
    super(context);
    SurfaceHolder holder = getHolder();
    holder.addCallback(this);
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    if (camera != null) {
      camera.stopPreview();
      camera.release();
    }
    camera = Camera.open();
    try {
      camera.setPreviewDisplay(holder);
    }
    catch (IOException ex) {
      Log.e(TAG, ex.getMessage(), ex);
      throw new RuntimeException("IOException", ex);
    }
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    if (camera != null) {
      camera.stopPreview();
      Camera.Parameters params = camera.getParameters();
      params.setPreviewSize(width, height);
      camera.setParameters(params);
      camera.startPreview();
    }
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    if (camera != null) {
      camera.stopPreview();
      camera.release();
      camera = null;
    }
  }
}
