package jp.co.qsdn.android.camera;

import android.app.Activity;

import android.os.Bundle;

import android.util.Log;

import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity
{
  private final static String TAG = MainActivity.class.getName();

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate開始");
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(new CameraView(this));
    Log.d(TAG, "onCreate終了");
  }
}
