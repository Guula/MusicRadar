package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDrive {
	

	public static Connection getConnection()
	{
			
		Connection conn=null;
		
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("���ݿ��������سɹ���");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			String user="SYSTEM";
			String password="Wjt31853211";
			conn=DriverManager.getConnection(url,user,password);
		
			
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("����ʧ�ܣ�");
			
		}
		if(conn!=null){
			
			System.out.println("���ӳɹ���");
			return conn;
		}
		else{
			System.out.println("����ʧ�ܣ�");
			return null;
		
		}
	         
	
	}
}


