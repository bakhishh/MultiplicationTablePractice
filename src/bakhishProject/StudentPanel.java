package bakhishProject;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel ;
import javax.swing.WindowConstants;

import javax.swing.JLabel ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
public class StudentPanel {
	public static String examName ;
	JFrame frame = new JFrame() ;
	JPanel panel = new JPanel() ;
	JLabel selectLabel = new JLabel() ;
	JLabel titleLabel = new JLabel() ;
	Choice choBox = new Choice();
	JButton subButton = new JButton("Submit"); 
	JButton backButton = new JButton("Back");
	ResultSet rs ;
    String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
	String USER = "root";
	String PASS = "1201";
    Connection conn;
    Statement stmt;
	StudentPanel(){
		frame.add(panel); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.setLayout(null);
		frame.setVisible(true);
		
		titleLabel.setText("Student Panel") ;
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 40));
		titleLabel.setBounds(20,20,400,50);
		panel.add(titleLabel);
		
		selectLabel.setText("Select the exam!"); 
		selectLabel.setBounds(500,250,300,50);
		selectLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		selectLabel.setVisible(true);
		panel.add(selectLabel);
		
		try {
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = (Statement) conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM Exams;";
	         rs = stmt.executeQuery(sql);
	         while (rs.next()) {
	        	 choBox.add(rs.getString(1));
	         }
	         rs.close();
	         conn.close();
	         stmt.close();
	      }catch (SQLException | ClassNotFoundException e) {
		         e.printStackTrace();
		  }
		
		choBox.setBounds(500,310,150,100);
		choBox.setSize(300, 100);
		panel.add(choBox) ;
		
		backButton.setPreferredSize(new Dimension(200,100));
		backButton.setBounds(400,400,100,40);
		backButton.setFocusable(false);
		panel.add(backButton);
		backButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentLogin sl = new StudentLogin() ;
				frame.dispose();
				
			}
			
		});
		
		
		subButton.setPreferredSize(new Dimension(200,100));
		subButton.setBounds(800 , 400 , 100 , 40 );
		subButton.setFocusable(false);
		subButton.addActionListener(new ActionListener() {
			int noQuest;
			int min;
			int max;
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				int index = choBox.getSelectedIndex() ;
				String sql = "SELECT * FROM Exams WHERE Name ='" +
							choBox.getItem(index)+"'"  ; 
				examName = choBox.getItem(index) ;
 				try {
					Class.forName("com.mysql.jdbc.Driver");
			        conn = DriverManager.getConnection(DB_URL, USER, PASS);
			        stmt = (Statement) conn.createStatement();
					rs= stmt.executeQuery(sql) ;
					if(rs.next()) {
						noQuest= rs.getInt("noQuestions");
						min=rs.getInt("min");
						max = rs.getInt("max");
					}
					
				} catch (SQLException | ClassNotFoundException e1 ) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				//exams.getList().get(choBox.getSelectedIndex()).run(exams.getList().get(choBox.getSelectedIndex()).getNumberOfQuestion());
				Question quest = new Question(noQuest,min,max) ;
			}
			
		});
		panel.add(subButton);
	}
}
