/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Timer.java, Aug 29, 2013, 8:11:25 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * @author Tarun Gulati
 *
 */
public class GameTimer implements Observer{
	public static long startTime = System.currentTimeMillis();
	public static long timeElapsed;
	
	@Override
	public void update(){
		timeElapsed = System.currentTimeMillis() - startTime;
	}
	
	@Override
	public void draw(Board com) {
		JLabel label = (JLabel)com.timeLabel;
		
		String sec = String.valueOf((timeElapsed/1000)%60);
		String min = String.valueOf(timeElapsed/(60*1000));
		
		label.setText(min+":"+sec);
		label.repaint();
	}

}
