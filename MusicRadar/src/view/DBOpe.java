package view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DataPoint;

public class DBOpe {
    private static Connection conn = DBDrive.getConnection();
    
	static int n=0;
	public static void add_hashMap(long h,DataPoint point) 
	{   
//		String sql = String.format("insert into hashmap values('%d','%d','%d');",h,point.getSongId(),point.getTime());
		String sql ="insert into hashmap values(?,?,?)";
		
	
		try {
			PreparedStatement stml=conn.prepareStatement(sql);
			stml.setInt(1, (int) h);
			stml.setInt(2,point.getSongId() );
			stml.setInt(3, point.getTime());
			
			stml.execute();
			
				
				
			System.err.println(++n);
			
			
			stml.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
	
	public static List<DataPoint> get_hashMap(long h) throws SQLException
	{
        ArrayList<DataPoint> listPoints = new ArrayList<>();
        String sql = "select songId, time from hashmap where hashId=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try  {
            pstmt.setInt(1, (int) h);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	DataPoint dp=new DataPoint(rs.getInt("songId"), rs.getInt("time"));
            	listPoints.add(dp);
            	dp=null;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pstmt.close();
        return listPoints;		
	}
	
	public static String getsongName(int songId)
	{
		 String ret = "";
	        try {
//	            ResultSet rs = stmt.executeQuery(String.format("select name from songmap where songId='%d';", songId));
	        	String sql="select name from songmap where songId=?";
	        	PreparedStatement stml=conn.prepareStatement(sql);
	        	stml.setInt(1, songId);
	        	ResultSet rs=stml.executeQuery();
	        	if (rs.next()) {
	                ret = rs.getString("name");
	            }
	        	rs.close();
	        	stml.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	            ret = URLDecoder.decode(ret, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	        return ret;
	}
	
	public static void addsong(int songId,String name)
	{
//		Statement stmt = conn.createStatement();
//		String sql = String.format("insert into songmap values('%d','%s');",songId,name);
		String sql ="insert into songmap values(?,?)";
		
		
		try {
			PreparedStatement stml=conn.prepareStatement(sql);
			stml.setInt(1, songId);
			stml.setString(2, name);
			stml.execute();
			stml.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public static int Init_nrSong() 
	{
        int ret = 0;
        
        try {
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) max from songmap");
            if (rs.next()) {
                ret = rs.getInt("max");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(ret);
        return ret+1;
	}

}
