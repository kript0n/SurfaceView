package com.kripton.surfaceview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	MyRender surf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		surf = new MyRender(getBaseContext());
		setContentView(surf);
		surf.resume();
	}
	
	@Override 
	protected void onResume() {
		super.onResume();
		//surf.resume();
	}
	
	@Override 
	protected void onPause() {
		super.onResume();
		surf.pause();
	}

}
