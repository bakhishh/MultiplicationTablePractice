package bakhishProject;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class examList {

	private JFrame frame;
	private JTable table;
	ResultSet rs;
	String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
	String USER = "root";
	String PASS = "1201";
	Connection conn;
	Statement stmt;

	examList() {
		frame = new JFrame();
		frame.setBounds(400, 100, 530, 450);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 29, 434, 314);
		frame.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Number Of Questions" ,"Min. value of factor", "Max. value of factor"}));

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT  * FROM Exams;");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getInt(2) ,rs.getInt(3), rs.getInt(4) });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stdList window = new stdList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
