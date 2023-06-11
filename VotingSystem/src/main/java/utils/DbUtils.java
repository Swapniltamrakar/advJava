package utils;
import java.sql.*;
public class DbUtils {

	static Connection cn;
	public static Connection openConnection() throws SQLException {
		String dbUrl="jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		cn=DriverManager.getConnection(dbUrl,"root","Swap@1998");
		return cn;
		
	}
	public static void closeConnection() throws SQLException {
		
		if(cn!=null) {
			cn.close();
		}
	}
	
	
}
