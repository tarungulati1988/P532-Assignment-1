/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Sprite.java, Aug 29, 2013, 8:12:42 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

/**
 * @author Tarun Gulati
 *
 */
public class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	/*
	 * 	implements the collison functionality, checks for boundary collisions,
	 * 	and floor fall through
	 */
	public boolean collision(){
		return true;
	}
	/*
	 * 	setX()
	 */
	public void setX(int x){
		this.x = x;
	}
	/*
	 * 	setY()
	 */
	public void setY(int y){
		this.y = y;
	}
	/*
	 * 	getX()
	 */
	public int getX(){
		return x;
	}
	/*
	 * 	getY()
	 */
	public int getY(){
		return y;
	}
}
