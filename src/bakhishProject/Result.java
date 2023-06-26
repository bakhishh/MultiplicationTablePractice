package bakhishProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame ; 
import javax.swing.JPanel ;
import javax.swing.WindowConstants;
import javax.swing.JLabel ; 
import javax.swing.JButton;

public class Result {
	Connection conn ; 
	Statement stmt ;
	JFrame frame = new JFrame() ;
	JPanel panel = new JPanel() ;
	JLabel label = new JLabel() ;
	JButton backButton = new JButton ("Back"); 
	static String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
    static final String USER = "root";
    static final String PASS = "1201";
    String sql ;
	Result(){
		frame.add(panel); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.setLayout(null);
		frame.setVisible(true);
		
		
		label.setText("Number of Correct Answers : "+String.valueOf(Question.numberOfCorrect) +"      "+ String.format("%02d", Question.hours) + ":"+String.format("%02d", Question.minutes) + ":"+String.format("%02d", Question.seconds)); 
		label.setFont(new Font("Verdana" , Font.BOLD , 30));
		label.setBounds(200,250,1000,50);
		label.setVisible(true);
		panel.add(label);
		
		backButton.setFocusable(false);
		backButton.setBounds(250, 350 , 150,50);
		panel.add(backButton);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        stmt = conn.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		sql = "INSERT INTO Results VALUES ('"+
				StudentLogin.stdName + "' ,'"+
				StudentPanel.examName +"', "+
				Question.noQuestion +" , "+
				Question.numberOfCorrect +" , '"+
				String.format("%02d", Question.hours) + ":"+String.format("%02d", Question.minutes) + ":"+String.format("%02d", Question.seconds) +"')";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question.hours = 0;
		Question.minutes = 0;
		Question.seconds = 0;
		Question.numberOfCorrect = 0;
		Question.noQuestion = 1;
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StudentLogin sl = new StudentLogin(); 
				
			}
			
		});
				
		
		
	}
}
