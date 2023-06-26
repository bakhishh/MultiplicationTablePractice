package bakhishProject;

import javax.swing.JFrame ; 
import javax.swing.JPanel ;
import javax.swing.WindowConstants;
import javax.swing.JLabel ; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton ;
import java.util.Random;
import java.util.Timer ;
import java.util.TimerTask ;
import javax.swing.JTextField ; 

public class Question {
	public int a , b ;
	public static int numberOfCorrect = 0 ;
	public static int noQuestion = 1 ;
	public static int seconds = 0 ;
	public static int minutes = 0 ;
	public static int hours = 0 ;
	Random rand = new Random() ;
	JFrame frame = new JFrame() ;
	
	JPanel panel = new JPanel() ;
	JLabel label = new JLabel() ;
	Choice choBox = new Choice();
	JButton button = new JButton("Submit");  
	JTextField tx = new JTextField(20) ;
	JLabel no = new JLabel() ;
	Timer timer ;
	JLabel timeLabel = new JLabel() ;
	
 	Question(int numberOfQuestion , int minOfFactor, int maxOfFactor){
		frame.add(panel); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.setLayout(null);
		frame.setVisible(true);
		
		timeLabel.setBounds(850 , 150 , 300,50) ;
		timeLabel.setVisible(true);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		panel.add(timeLabel);
		
		Timer timer = new Timer() ;
		TimerTask task = new TimerTask() {
			
		
			@Override
			public void run() {
				
				String seconds_string = String.format("%02d", seconds);
				String minutes_string = String.format("%02d", minutes);
				String hours_string = String.format("%02d", hours);
				
				if(seconds>=60) {
					minutes++ ;
					seconds = seconds - 60 ;
				}
				
				if(minutes>=60) {
					hours++ ; 
					minutes = minutes - 60 ;
				}
				
				timeLabel.setText(hours_string + ":" +minutes_string+":"+seconds_string); 
				seconds++ ;
				
			}
			
		};
		timer.scheduleAtFixedRate(task, 0 , 1000);
		no.setText(String.valueOf(noQuestion)+"/"+String.valueOf(numberOfQuestion)) ;
		no.setBounds(400 , 150 , 300,50);
		no.setFont(new Font("Times New Roman", Font.BOLD, 30));
		no.setVisible(true);
		panel.add(no);
		a = rand.nextInt(maxOfFactor - minOfFactor + 1) + minOfFactor ; 
		b= rand.nextInt(maxOfFactor - minOfFactor + 1) + minOfFactor ; 
		label.setText(a + " x " + b + " = ");
		label.setFont(new Font("Verdana", Font.BOLD, 30));
		label.setBounds(500,250,300,50);
		label.setVisible(true);
		panel.add(label);
		
		tx.setBounds(640,250,50,50);
		panel.add(tx);
		
		
		
		button.setPreferredSize(new Dimension(200,100));
		button.setBounds(700 , 250 , 100 , 50 );
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(a*b == Integer.parseInt(tx.getText())) {
					numberOfCorrect++ ;
				}
				
				
				if(noQuestion < numberOfQuestion) {
					frame.dispose(); 
					noQuestion++ ;
					Question quest = new Question(numberOfQuestion, minOfFactor, maxOfFactor) ;
					timer.cancel();
				}
				else {
					System.out.println(numberOfCorrect);
					frame.dispose();
					timer.cancel();
					Result res = new Result() ;
				}
				
				
			}
			
		});
		panel.add(button);
		
		
	}
	
	
}

