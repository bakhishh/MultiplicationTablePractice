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

import javax.swing.JButton; 
import javax.swing.JFrame;  
import javax.swing.JPanel ;
import javax.swing.WindowConstants;
import javax.swing.JLabel ;
import javax.swing.JTextField;
import javax.swing.JPasswordField ;
public class AdminPanel {
	
	JFrame frame = new JFrame() ;
	JPanel panel = new JPanel() ;
	JLabel titleLabel = new JLabel() ;
	JButton addStd = new JButton("Add new student");
	JButton delStd = new JButton("Delete the student") ;
	JButton listStd = new JButton("List the students") ;
	JButton addExam = new JButton("Add new exam"); 
	JButton delExam = new JButton("Delete the exam") ;
	JButton listExam = new JButton("List the exams");
	JButton viewResults = new JButton("See the results of the students");
	JButton backButton = new JButton("Back to menu") ;
	Connection conn ;
	Statement stmt ;
	ResultSet rs ;
	int find = 0 ;
	String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
	String USER = "root";
	String PASS = "1201";
	AdminPanel(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("Admin Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		panel.setLayout(null);
		titleLabel.setText("Admin Panel");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 40));
		titleLabel.setBounds(20,20,300,50);
		panel.add(titleLabel);
		
		addStd.setBounds(150,130,300,100);
		addStd.setFocusable(false);
		delStd.setBounds(500,130,300,100);
		delStd.setFocusable(false);
		listStd.setBounds(850,130,300,100);
		listStd.setFocusable(false);
		addExam.setBounds(150 , 260,300,100);
		addExam.setFocusable(false);
		delExam.setBounds(500, 260 , 300,100);
		delExam.setFocusable(false);
		listExam.setBounds(850 , 260 , 300,100);
		listExam.setFocusable(false);
		viewResults.setBounds(150,390,1000 ,100);
		viewResults.setFocusable(false);
		backButton.setBounds(150,580,120,40) ;
		backButton.setFocusable(false);
		
		panel.add(addStd);
		panel.add(delStd);
		panel.add(listStd);
		panel.add(addExam);
		panel.add(delExam);
		panel.add(listExam);
		panel.add(viewResults);
		panel.add(backButton);
																									//add student
		addStd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addStdFrame = new JFrame() ;
				JPanel addStdPanel = new JPanel() ;
				JLabel addStdLabel = new JLabel("New Student : ");
				JLabel username = new JLabel("Username");
				username.setFont(new Font("Verdana", Font.BOLD, 15));
				JLabel password = new JLabel("Password");
				password.setFont(new Font("Verdana", Font.BOLD, 15));
				JTextField usernameField = new JTextField() ;
				JPasswordField passwordField = new JPasswordField() ;
				JButton addButton = new JButton("Add");
				JLabel warning = new JLabel() ;
				
				addStdFrame.setTitle("Add New Student");
				addStdFrame.setBounds(450 , 150 , 450,450);
				addStdFrame.setSize(450,450);
				addStdFrame.setVisible(true);
				addStdFrame.add(addStdPanel);
				addStdPanel.setLayout(null);
				addStdFrame.add(addStdPanel);
				addStdLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
				addStdLabel.setBounds(20,5,200,50);
				addStdPanel.add(addStdLabel);
				username.setBounds(70 ,80 ,100,50);
				addStdPanel.add(username);
				addStdPanel.add(password);
				
				usernameField.setBounds(70,120 , 250,40);
				addStdPanel.add(usernameField);
				password.setBounds(70,170 , 100,50);
				passwordField.setBounds(70,210,250,40);
				addStdPanel.add(passwordField);
				
				addButton.setBounds(320 ,300 ,80,40);
				addButton.setFocusable(false);
				addStdPanel.add(addButton);
				
				warning.setBounds(70,220,400,100);
				warning.setVisible(false);
				addStdPanel.add(warning);
				
				try {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
				    rs = stmt.executeQuery("SELECT  * FROM Students;");
				    
		        }catch (SQLException | ClassNotFoundException e1) {
			         e1.printStackTrace();
			     }
				
				
				addButton.addActionListener(new ActionListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							rs = stmt.executeQuery("SELECT  * FROM Students;");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(usernameField.getText().equals("") || passwordField.getText().equals("")) {
							warning.setText("You cannot set the username null");
							warning.setFont(new Font("Verdana", Font.BOLD, 12));
					        warning.setForeground(Color.RED);
							warning.setVisible(true);
						}
						else {
							find = 0 ;
							try {
								
								while(rs.next() && find == 0)  {
									if(usernameField.getText().equals(rs.getString(1))) {
										find = 1 ;
									} 
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(find == 1) {
								warning.setText("This username is alreayd taken");
								warning.setFont(new Font("Verdana", Font.BOLD, 12));
						        warning.setForeground(Color.RED);
								warning.setVisible(true);
							}
							else{
								warning.setVisible(false);
								try {
									stmt.executeUpdate("INSERT INTO Students VALUES ('"+usernameField.getText() +"', '"+passwordField.getText()+"');" );
									addStdFrame.dispose();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
			
						
					}
					
				});
			}
			
		});
		
																									//delete student
		delStd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame delStdFrame = new JFrame() ;
				JPanel delStdPanel = new JPanel() ;
				JLabel delStdLabel = new JLabel("<html>Enter the username of the student</br> you want to delete : </html>");
				JLabel username = new JLabel("Username");
				username.setFont(new Font("Verdana", Font.BOLD, 15));
				JTextField usernameField = new JTextField() ;
				JButton delButton = new JButton("Delete");
				JLabel warning = new JLabel() ;
				
				delStdFrame.setTitle("Delete the Student");
				delStdFrame.setBounds(450 , 150 , 450,450);
				delStdFrame.setSize(450,450);
				delStdFrame.setVisible(true);
				delStdFrame.add(delStdPanel);
				delStdPanel.setLayout(null);
				delStdFrame.add(delStdPanel);
				delStdLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
				delStdLabel.setBounds(20,30,400,50);
				delStdPanel.add(delStdLabel);
				username.setBounds(70 ,130 ,100,50);
				delStdPanel.add(username);
				
				usernameField.setBounds(70,170 , 250,40);
				delStdPanel.add(usernameField);
				
				delButton.setBounds(320 ,250 ,80,40);
				delButton.setFocusable(false);
				delStdPanel.add(delButton);
				
				warning.setBounds(70,180,400,100);
				warning.setVisible(false);
				delStdPanel.add(warning);
				
				try {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
				    rs = stmt.executeQuery("SELECT  * FROM Students;");
				    
		        }catch (SQLException | ClassNotFoundException e1) {
			         e1.printStackTrace();
			     }
				
				
				delButton.addActionListener(new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							rs = stmt.executeQuery("SELECT  * FROM Students;");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(usernameField.getText().equals("")) {
							warning.setText("You cannot set the username null");
							warning.setFont(new Font("Verdana", Font.BOLD, 12));
					        warning.setForeground(Color.RED);
							warning.setVisible(true);
						}
						else {
							find = 0 ;
							try {
								
								while(rs.next() && find == 0)  {
									if(usernameField.getText().equals(rs.getString(1))) {
										find = 1 ;
									} 
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(find == 0) {
								warning.setText("This username is not found");
								warning.setFont(new Font("Verdana", Font.BOLD, 12));
						        warning.setForeground(Color.RED);
								warning.setVisible(true);
							}
							else{
								warning.setVisible(false);
								try {
									stmt.executeUpdate("DELETE FROM Students " +
														"WHERE Username = '" +
														usernameField.getText() +
														"';");
									delStdFrame.dispose();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
			
						
					}
					
				});
				
			}
			
		});
		
																							//add exam
		addExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addExamFrame = new JFrame() ;
				JPanel addExamPanel = new JPanel() ;
				JLabel addExamLabel = new JLabel("New Exam : ");
				JLabel name = new JLabel("Name: ");
				name.setFont(new Font("Verdana", Font.BOLD, 15));
				JLabel no = new JLabel("Number of Questions: ");
				no.setFont(new Font("Verdana", Font.BOLD, 15));
				JLabel min = new JLabel("Min. value of factor: ");
				JLabel max = new JLabel("Max. value of factor: ");
				min.setFont(new Font("Verdana", Font.BOLD, 15));
				max.setFont(new Font("Verdana", Font.BOLD, 15));
				JTextField nameField = new JTextField() ;
				JTextField noField = new JTextField() ;
				JTextField minField = new JTextField() ;
				JTextField maxField = new JTextField() ;
				JButton addButton = new JButton("Add");
				JLabel warning = new JLabel() ;
				
				addExamFrame.setTitle("Add New Exam");
				addExamFrame.setBounds(450 , 100 , 450,550);
				addExamFrame.setSize(450,550);
				addExamFrame.setVisible(true);
				addExamFrame.add(addExamPanel);
				addExamPanel.setLayout(null);
				addExamFrame.add(addExamPanel);
				addExamLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
				addExamLabel.setBounds(20,5,200,50);
				addExamPanel.add(addExamLabel);
				name.setBounds(70 ,80 ,100,50);
				addExamPanel.add(name);
				addExamPanel.add(no);
				
				
				nameField.setBounds(70,120 , 250,40);
				addExamPanel.add(nameField);
				no.setBounds(70,170 , 250,50);
				noField.setBounds(70,210,250,40);
				addExamPanel.add(noField);
				
				min.setBounds(70,280,300,50);
				minField.setBounds(250,290,40,30);
				addExamPanel.add(minField);
				addExamPanel.add(min);
				
				max.setBounds(70,340,300,50);
				maxField.setBounds(250,350,40,30);
				addExamPanel.add(maxField);
				addExamPanel.add(max);
				
				addButton.setBounds(320 ,450 ,80,40);
				addButton.setFocusable(false);
				addExamPanel.add(addButton);
				
				warning.setBounds(70,380,400,100);
				warning.setVisible(false);
				addExamPanel.add(warning);
				
				
				try {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
				    rs = stmt.executeQuery("SELECT  * FROM Exams;");
				    
		        }catch (SQLException | ClassNotFoundException e1) {
			         e1.printStackTrace();
			     }
				
				
				addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							rs = stmt.executeQuery("SELECT  * FROM Exams;");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(nameField.getText().equals("") || noField.getText().equals("") || minField.getText().equals("") || maxField.getText().equals("")) {
							warning.setText("You cannot set any of these null");
							warning.setFont(new Font("Verdana", Font.BOLD, 12));
					        warning.setForeground(Color.RED);
							warning.setVisible(true);
						}
						else {
							find = 0 ;
							try {
								
								while(rs.next() && find == 0)  {
									if(nameField.getText().equals(rs.getString(1))) {
										find = 1 ;
									} 
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(find == 1) {
								warning.setText("This exam is already on the list");
								warning.setFont(new Font("Verdana", Font.BOLD, 12));
						        warning.setForeground(Color.RED);
								warning.setVisible(true);
							}
							else{
								
								if(Integer.parseInt(minField.getText()) >= Integer.parseInt(maxField.getText())) {
									warning.setText("Min. value of factor cannot be larger than Max. value");
									warning.setFont(new Font("Verdana", Font.BOLD, 12));
							        warning.setForeground(Color.RED);
									warning.setVisible(true);
								}
								else {
									warning.setVisible(false);
									try {
										stmt.executeUpdate("INSERT INTO Exams VALUES ('"+nameField.getText() +"', "+Integer.parseInt(noField.getText())+", "+Integer.parseInt(minField.getText())+", "+Integer.parseInt(maxField.getText())+")" );
										addExamFrame.dispose();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								
								
							}
						}
						
			
						
					}
					
				});
			}
			
		});
		
																									//delete exam
		delExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame delExamFrame = new JFrame() ;
				JPanel delExamPanel = new JPanel() ;
				JLabel delExamLabel = new JLabel("<html>Enter the name of the exam</br> you want to delete : </html>");
				JLabel name = new JLabel("Name");
				name.setFont(new Font("Verdana", Font.BOLD, 15));
				JTextField nameField = new JTextField() ;
				JButton delButton = new JButton("Delete");
				JLabel warning = new JLabel() ;
				
				delExamFrame.setTitle("Delete the Exam");
				delExamFrame.setBounds(450 , 150 , 450,450);
				delExamFrame.setSize(450,450);
				delExamFrame.setVisible(true);
				delExamFrame.add(delExamPanel);
				delExamPanel.setLayout(null);
				delExamFrame.add(delExamPanel);
				delExamLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
				delExamLabel.setBounds(20,30,400,50);
				delExamPanel.add(delExamLabel);
				name.setBounds(70 ,130 ,100,50);
				delExamPanel.add(name);
				
				nameField.setBounds(70,170 , 250,40);
				delExamPanel.add(nameField);
				
				delButton.setBounds(320 ,250 ,80,40);
				delButton.setFocusable(false);
				delExamPanel.add(delButton);
				
				warning.setBounds(70,180,400,100);
				warning.setVisible(false);
				delExamPanel.add(warning);
				
				try {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            stmt = conn.createStatement();
				    rs = stmt.executeQuery("SELECT  * FROM Exams;");
				    
		        }catch (SQLException | ClassNotFoundException e1) {
			         e1.printStackTrace();
			     }
				
				
				delButton.addActionListener(new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							rs = stmt.executeQuery("SELECT  * FROM Exams;");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(nameField.getText().equals("")) {
							warning.setText("You cannot set the name null");
							warning.setFont(new Font("Verdana", Font.BOLD, 12));
					        warning.setForeground(Color.RED);
							warning.setVisible(true);
						}
						else {
							find = 0 ;
							try {
								
								while(rs.next() && find == 0)  {
									if(nameField.getText().equals(rs.getString(1))) {
										find = 1 ;
									} 
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(find == 0) {
								warning.setText("This name is not found");
								warning.setFont(new Font("Verdana", Font.BOLD, 12));
						        warning.setForeground(Color.RED);
								warning.setVisible(true);
							}
							else{
								warning.setVisible(false);
								try {
									stmt.executeUpdate("DELETE FROM Exams " +
														"WHERE Name = '" +
														nameField.getText() +
														"';");
									delExamFrame.dispose();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
			
						
					}
					
				});
				
			}
			
		});
			
		
																												//list std
		listStd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stdList sl = new stdList() ;
				
			}
			
		});
																											//list exam
		listExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				examList le = new examList(); 
				
			}
			
		});
		
																											//view results
		viewResults.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				resultList rl = new resultList(); 
				
			}
			
		});
																			
																									//back buttobn
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menu m = new Menu(); 
				
			}
			
		});
		
		
	}
}
