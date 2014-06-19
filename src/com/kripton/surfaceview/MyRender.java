package com.kripton.surfaceview;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyRender extends SurfaceView implements Runnable {

	SurfaceHolder holder;
	Thread renderThread = null;
	int width = 0;
	int height = 0;
	Vector normalVer;
	Vector normalHor;
	volatile boolean running = false;
		
	public MyRender(Context context) {
		super(context);
		holder = getHolder();
		// TODO Auto-generated constructor stub
	}
	
	public MyRender() {
		super(null);
		holder = getHolder();
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		Paint paint = new Paint();
		boolean first = true;
		float radius = 15.0f;
		float speed = 0.2f;
		float currentCenterX = 0.0f;
		float currentCenterY = 0.0f;
		while(running) {
			if(!holder.getSurface().isValid()) {
				continue;
			}			
			Canvas canvas = holder.lockCanvas();
			if(first) {
				paint.setColor(Color.WHITE);
				canvas.drawPaint(paint);
				paint.setColor(Color.RED);
				paint.setStrokeWidth(3);
				currentCenterX = (float) (radius+Math.random()*(width-radius));
				currentCenterY = (float) (radius+Math.random()*(height-radius));
				canvas.drawCircle(currentCenterX, currentCenterY, radius, paint);
				first = false;
			}
			else {
				paint.setColor(Color.WHITE);
				canvas.drawPaint(paint);
				currentCenterX += speed;
				currentCenterY += speed;
				paint.setColor(Color.RED);
				paint.setStrokeWidth(3);
				canvas.drawCircle(currentCenterX, currentCenterY, radius, paint);
			}
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		normalVer = new Vector(0, height);
		normalHor = new Vector(width, 0);
		setMeasuredDimension(width, height);
	}
	
	public void pause() {
		running = false;
		while(true) {
			try {
				renderThread.join();
				break;
			} catch(InterruptedException e) {
				
			}
		}
	}

}
