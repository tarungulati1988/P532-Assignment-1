/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Board.java, Aug 29, 2013, 8:12:55 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Tarun Gulati
 *
 */
public class Board extends JFrame implements Commons{
	
	public static boolean isRunning;
	public static boolean isFirstTime;
	private JPanel contentPane;
	public static Board boardObj;
	public static JPanel panelRight;
	public JPanel panelLeft;
	public JLabel timeLabel;
	public static JLabel msgLabel;
	
	Ball ball;
    Paddle paddle;
    Brick brick;
    TimerTask timerTask;
	
	/*
	 * 	Board()
	 */
	public Board() {
		
		isRunning = false;
		isFirstTime = true;
		
		panelRight = new JPanel();
		panelLeft = new JPanel();
		timeLabel = new JLabel();
		msgLabel = new JLabel();
		
		paddle = new Paddle();
		brick = new Brick();
		timerTask = new TimerTask(boardObj);
		
		ball = new Ball(paddle, brick, this.boardObj, timerTask);
		
		setTitle("Break Out Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(Commons.START_X, Commons.START_Y, Commons.WIDTH, Commons.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		contentPane.add(layeredPane, BorderLayout.CENTER);
		//JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.LIGHT_GRAY);
		panelRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRight.setBounds(110, 11, 454, 330);
		layeredPane.add(panelRight);
		
		//JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.GRAY);
		panelLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLeft.setBounds(10, 11, 90, 330);
		layeredPane.add(panelLeft);
		
		timeLabel.setFont(new Font("SansSerif",Font.BOLD, 22));
		timeLabel.setText("00:00");
		panelLeft.add(timeLabel);
		
		msgLabel.setFont(new Font("SansSerif",Font.BOLD, 12));
		msgLabel.setForeground(Color.RED);
		panelLeft.add(msgLabel);
		
		//timerTask = new TimerTask(boardObj);
		addKeyListener(new StrokeAdapter());
	}
	
	/** 
	 * Key Listener adapter class
	 */
	class StrokeAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE && isFirstTime == true)
			{
				//Register the gameTimer
		    	GameTimer gameTimer = new GameTimer();
		    	timerTask.register(gameTimer, boardObj);
		    	
		    	//Register ball
		    	timerTask.register(ball, boardObj);
		    	
		    	//Register Paddle
		    	timerTask.register(paddle, boardObj);
		    	
				timerTask.run();
		    	isRunning = true;
		    	isFirstTime = false;
		    	
			}if(key == KeyEvent.VK_LEFT && isRunning == true){
				paddle.move("left");
				//Board boardObj = new Board();
				//boardObj.repaint();
				//timerTask.run();
			}else if(key == KeyEvent.VK_RIGHT && isRunning == true){
				paddle.move("right");
			}
		}
		
		public void keyReleased(KeyEvent ke){
			if(isRunning == true)
			{
				paddle.KeyReleased();
			}
		}
	}
	
	
	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				boardObj = new Board();
				boardObj.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		/*
		 * (non-Javadoc)
		 * @see java.awt.Window#paint(java.awt.Graphics)
		 */
		public void paint(Graphics g) {
	    	super.paint(g);
	        String message = "Game Over";
	    	/*
	    	 * Paddle
	    	 * fillRect(x, y, width, height)
	    	 */
	        g.setColor(Color.BLACK);
			g.fillRect(paddle.getX(), paddle.getY(), Commons.PADDLE_WIDTH, Commons.PADDLE_HEIGHT);
			/*
			 * Ball
			 * fillOval(x, y, width, height)
			 */
			g.setColor(Color.RED);
			g.fillOval(ball.getX(), ball.getY(), 30, 30);
			/*
	    	 * Brick
	    	 * fillRect(x, y, width, height)
	    	 */
			g.setColor(Color.BLUE);
			g.fillRect(brick.getX(), brick.getY(), brick.width, brick.height);
			
	    }

}
