package bakhishProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    static String DB_URL = "jdbc:mysql://localhost:3306/MyDatabase";
    static final String USER = "root";
    static final String PASS = "1201";

    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        mySQL.createTablesAndInsertData();
    }

    public void createTablesAndInsertData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Admin " +
                    "(Username VARCHAR(30), " +
                    " Password VARCHAR(30))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS Students " +
                    "(Username VARCHAR(30), " +
                    " Password VARCHAR(30))";
            stmt.executeUpdate(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS Exams "+
            		"(Name VARCHAR(30), "+
            		"noQuestions INT,"+
            		"min INT ,"+
            		"max INT)";
            stmt.executeUpdate(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS Results "+
            		"(StdName VARCHAR(30), "+
            		"ExamName VARCHAR(30),"+
            		"noQuestions INT,"+
            		"noCorrect INT ,"+
            		"Time VARCHAR(10))";
            stmt.executeUpdate(sql);
            
            sql = "INSERT INTO Admin (Username, Password) SELECT 'admin', '2023' FROM dual WHERE NOT EXISTS " +
                    "(SELECT * FROM Admin WHERE Username = 'admin')";
            stmt.executeUpdate(sql);

            
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Exams (Name, noQuestions, min, max) SELECT 'example', 5, 1, 10 FROM dual WHERE NOT EXISTS " +
                    "(SELECT * FROM Exams WHERE Name = 'example')";
            stmt.executeUpdate(sql);
            
            
            
            System.out.println("Tables created and data inserted successfully.");
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
