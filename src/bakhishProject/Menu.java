package bakhishProject;

import javax.swing.JFrame;  
import javax.swing.JPanel ;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton ;


public class Menu{
	JFrame frame = new JFrame() ;
	JPanel panel = new JPanel() ; 
	JButton button1 = new JButton("Login as Admin") ;
	JButton button2 = new JButton("Login as Student") ;
	
	Menu() {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setTitle("Multiplication Table Practice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel); 
		panel.setLayout(null) ; 
		
		
		button1.setBounds(530, 250 , 300 ,80); 
		button2.setBounds(530,340,300,80);
		button1.setFont(new Font("Verdana" , Font.PLAIN, 15));
		button2.setFont(new Font("Verdana" , Font.PLAIN, 15));
		button1.setFocusable(false);
		button2.setFocusable(false);
		panel.add(button1);
		panel.add(button2);
		
		
		
		button1.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminLogin al = new AdminLogin() ;
				frame.dispose();
			}
			
		});
		
		button2.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentLogin sl = new StudentLogin() ;
				frame.dispose();
			}
			
			
		});
		
	}
	
	
}

