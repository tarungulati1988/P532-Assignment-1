/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	TimerTask.java, Aug 30, 2013, 5:51:42 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

/**
 * @author Tarun Gulati
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.Timer;

public class TimerTask implements Observable{
	public Timer t;
	public Board board;
	static boolean isRunning = true;
	//List<Observer> observersList;
	Map<Observer, Board> observerList;
	
	public TimerTask(Board board)
	{	
		t = new Timer(10, new TimerTaskListener());
		observerList = new HashMap<Observer, Board>();
		this.board = board;
	}
	
	public void run()
	{
		t.start();
	}
	
	/*public void stop()
	{
		isRunning = false;
	}*/
	
	class TimerTaskListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObservers();
			
			if(board.isRunning == false)
			{
				t.stop();
			}
				
		}	
	}

	@Override
	public void register(Observer observer, Board com) {
		observerList.put(observer, com);
	}

	@Override
	public void unRegister(Observer observer) {
		//observerList.remove(observer);
		/*Iterator it = observerList.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry)it.next();
			Observer ob = (Observer)entry.getKey();
			if(ob.equals(observer))
			{
				it.remove();
			}
		}*/
	}

	@Override
	public void notifyObservers() {
		Iterator it = observerList.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry)it.next();
			Observer ob = (Observer)entry.getKey();
			Board com = (Board)entry.getValue();
			ob.update();
			ob.draw(com);
		}
		/*for(Observer observer: observersList)
		{
			observer.update();
			observer.draw();
		}*/
	}
}
