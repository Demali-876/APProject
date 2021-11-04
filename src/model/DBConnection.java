package Hibernate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection 
{
public static void main(String[] args) // remove main method 
	{
	
		String url="jdbc:mysql://localhost:3306/grizzlydb";
		String username = "root";
		String password = "";
		try {
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.print("Connected to database");
		
		String sql = "INSERT INTO employee (EmpID,EmpName,EmpPassword) VALUES (?,?,?)";// test connectivity
		
		// used to test database connectivity
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, 11987);
		statement.setString(2, " blue boy ");
		statement.setString(3, "tets");
		statement.executeUpdate();
						
			}catch (SQLException e) 
			{
			System.out.print("Database did not connect\n");
			e.printStackTrace();
			}
	}
}   