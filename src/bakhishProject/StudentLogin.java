package bakhishProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
public class StudentLogin {
	public static String stdName ;
	JFrame frame;
    JPanel panel;
    JLabel username;
    JTextField usernameField;
    JLabel password;
    JPasswordField passwordField;
    JButton button_log;
    JButton backButton ;
    JLabel errorMessage;
    JLabel studentLabel ;
    ResultSet rs ;
    int find = 0 ;
    String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
	String USER = "root";
	String PASS = "1201";
    Connection conn;
    Statement stmt;
    StudentLogin() {
        frame = new JFrame();
        panel = new JPanel();
        username = new JLabel("Username");
        usernameField = new JTextField(30);
        password = new JLabel("Password");
        passwordField = new JPasswordField(30);
        button_log = new JButton("Login");
        backButton = new JButton("Back") ;
        errorMessage = new JLabel();
        studentLabel = new JLabel() ;
        errorMessage.setText("Username or password incorrect !!!");
        errorMessage.setFont(new Font("Verdana", Font.BOLD, 15));
        errorMessage.setVisible(false);
        errorMessage.setForeground(Color.RED);
        panel.add(errorMessage);
        frame.setTitle("Admin Login Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        studentLabel.setText("Student Login");
        studentLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        studentLabel.setBounds(20,20,450,50);
        panel.add(studentLabel) ;
        
        username.setBounds(550, 215 , 100 ,50);
        username.setFont(new Font("Verdana", Font.BOLD, 15));
        panel.add(username);

        usernameField.setBounds(550, 255, 300, 40);
        panel.add(usernameField);

        password.setBounds(550, 295, 100, 50);
        password.setFont(new Font("Verdana", Font.BOLD, 15));
        panel.add(password);

        passwordField.setBounds(550, 335, 300, 40);
        panel.add(passwordField);

        button_log.setBounds(550, 390, 300, 40);
        backButton.setBounds(470,550,80,30);
        button_log.setFocusable(false);
        errorMessage.setBounds(550, 440, 300, 30);
        
        backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu() ;
				frame.dispose() ;
			}
        	
        });
        
        																	//database
        
    	
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT  * FROM Students;");
		    
        }catch (SQLException | ClassNotFoundException e) {
	         e.printStackTrace();
	     }
        
        
        button_log.addActionListener(new ActionListener() {
        	@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText() ;
				String password = passwordField.getText() ;
				try {
					rs = stmt.executeQuery("SELECT  * FROM Students;");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				find = 0 ;
				try {
					
					while(rs.next() && find == 0)  {
						if(username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
							find = 1 ;
						} 
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	if(find == 1) {
            		errorMessage.setVisible(false);
            		stdName = usernameField.getText();
            		StudentPanel sp = new StudentPanel() ;
            		frame.dispose();
            	}
            	else {
            		errorMessage.setVisible(true) ;
            	}
            }
        });
        panel.add(button_log);
        panel.add(backButton) ;
        

        frame.setVisible(true);
    }

}
