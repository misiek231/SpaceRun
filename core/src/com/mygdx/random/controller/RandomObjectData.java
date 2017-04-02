package com.mygdx.random.controller;

import java.io.Serializable;

public class RandomObjectData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4147041994858323081L;

	public float x;
	
	public float y;
	
	public int objectType;
	
	public RandomObjectData(float x, float y, int objectType){
		
		this.x = x;
		
		this.y = y;
		
		this.objectType = objectType;
		
	}
}
