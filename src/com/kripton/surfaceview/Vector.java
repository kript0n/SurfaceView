package com.kripton.surfaceview;

public class Vector {
	
	public float x;
	public float y;
	
	public Vector() {
		
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoords(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length() {
		float length = (float)Math.sqrt((x*x+y*y));
		return length;
	}
	
	public float multiple(Vector vec) {
		float result = this.x*vec.x+this.y*vec.y;
		return result;
	}
	
	public float angle(Vector vec) {
		float cos = this.multiple(vec)/this.length()*vec.length();
		float angle = (float) Math.acos(cos);
		return angle;
	}
	
}
