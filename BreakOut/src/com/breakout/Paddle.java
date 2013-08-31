/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Paddle.java, Aug 29, 2013, 8:09:51 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;

/**
 * @author Tarun Gulati
 *
 */
public class Paddle extends Sprite implements Commons, Observer{
	
	public Paddle()
	{
		x = 300;
		y = 360;
	}
	
	Sprite spriteObj = new Sprite();
	/*
	 *	class data memeber:
	 *	dx 
	 */
	int dx;
	/*
	 * 	keyPressed()
	 */
	public void KeyPressed(){
		
	}
	/*
	 * 	keyReleased()
	 */
	public void KeyReleased(){
		dx = 0;
	}
	/*
	 * 	move()
	 */
	public void move(String directionToMove){
		if(directionToMove.equals("left")){
			dx = -2;
		}else if (directionToMove.equals("right")){
			dx = 2;
		}
	}

	/*
	 * 	paint()
	 */
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		// fil;Rect(int x, int y, int width, int height)
		g.fillRect(250, 480, 80, 5);
	}
	
	@Override
	public void update(){
		x += dx;
		if(x <= 2){
			x = 2;
		}else if(x >= Commons.PADDLE_RIGHT){
			x = Commons.PADDLE_RIGHT;
		}else if(x <= Commons.PADDLE_LEFT){
			x = Commons.PADDLE_LEFT;
		}
	}
	
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
