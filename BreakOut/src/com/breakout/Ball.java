/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Ball.java, Aug 29, 2013, 8:10:19 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import javax.swing.JComponent;

/**
 * @author Tarun Gulati
 *
 */
public class Ball extends Sprite implements Commons, Observer{
	public int xdir;
	public int ydir;
	
	public Paddle paddle;
	public Brick brick;
	public TimerTask timerTask;
	public Board board;
	
	public Ball(Paddle paddle,Brick brick, Board board, TimerTask timerTask)
	{
		this.paddle = paddle;
		this.brick = brick;
		this.board = board;
		this.timerTask = timerTask;
		
		x = 325;
		y = 330;
		xdir = 1;
		ydir = -1;
	}
	
	/*
	 *  Ball data members:
	 *  dx, dy
	 */
	int dx, dy;
	
	public boolean isCollidingWithRightWall()
	{
		if(x >= 548)
		{
			return true;
		}
		return false;
	}

	public boolean isCollidingWithTopWall()
	{
		if(y <= 45)
		{
			return true;
		}
		return false;
	}
	
	public boolean isCollidingWithLeftWall()
	{
		if(x <= 125)
		{
			return true;
		}
		return false;
	}
	
	public boolean isCollidingWithPaddle()
	{
		
		if(x >= paddle.getX() - 30 && x <= paddle.getX() + 80 + 30)
		{
			if(y >= 330 && y <= 335)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isCollidingWithBrick()
	{
		System.out.println("brick x = " + brick.getX());
		System.out.println("brick y: " + brick.getY());
		System.out.println("ball x = " + x);
		System.out.println("ball y: " + y);
		if(x >= brick.getX() - 30  && x <= brick.getX() + brick.width + 30 && y >= brick.getY() - 30 && y <= brick.getY() + brick.height)
		{
			brick.height = 0;
			brick.width = 0;
			return true;
		}
		return false;
	}
	
	public boolean isGameOver()
	{
		if(x <= paddle.getX() - 40 || x >= paddle.getX() + 40)
		{
			if(y >= 400)
			{
				return true;
			}
		}
		return false;
	}
	/*
	 * 	update()
	*/
	@Override
	public void update(){
		
		if(isCollidingWithRightWall())
		{
			xdir = -2;
		}
		
		if(isCollidingWithTopWall())
		{
			ydir = 1;
		}
		
		if(isCollidingWithLeftWall())
		{
			xdir = 1;
		}
		
		if(isCollidingWithPaddle())
		{
			ydir = -1;
		}
		
		if(isCollidingWithBrick())
		{
			System.out.println("Congrats!");
			//timerTask.stop();
			board.panelRight.repaint();
			board.isRunning = false;
			board.msgLabel.setText("You win!  ");
			timerTask.unRegister(this);
			timerTask.unRegister(paddle);
		}
		
		if(isGameOver())
		{
			System.out.println("Game Over");
			//timerTask.stop();
			//timerTask.isRunning = false;
			board.isRunning = false;
			board.msgLabel.setText("Game Over!");
		}
		x += xdir;
		y += ydir;
	}

	/*
	 * 	draw()
	 */
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
