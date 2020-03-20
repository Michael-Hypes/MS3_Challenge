/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms3_csv_challenge;
import java.sql.*;

public class SqliteHandler {
     Connection conn;
    
    
    public SqliteHandler(String fname){
        
    }
    
    
    public Connection Connect()
    {
        try 
        {  
            //Class.forName("org.sqlite.JDBC");
            // db parameters  
            String url = "jdbc:sqlite:C:\\Users\\micha\\Desktop\\SQLDB\\MS3_CSV_Challenge\\ms3Interview-JrChallenge2.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            //System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) 
        {  
            System.out.println(e.getMessage());   
        }  
        return conn;
    }
    
    
    public void CloseConnection() //may remove
    {
        try 
        {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (SQLException ex) {  
            System.out.println(ex.getMessage());  
        }
    }
    
    public void insert(String A, String B, String C, String D, String E, String F, String G, String H, String I, String J)
    {
        String sql = "INSERT INTO PersonInfo(A, B, C, D, E, F, G, H, I, J) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //System.out.println(sql);   //Check the sql string
 
        try 
        {
            Connection conn = this.Connect();
                
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, A);
            pstmt.setString(2, B);
            pstmt.setString(3, C);
            pstmt.setString(4, D);
            pstmt.setString(5, E);
            pstmt.setString(6, F);
            pstmt.setString(7, G);
            pstmt.setString(8, H);
            pstmt.setString(9, I);
            pstmt.setString(10, J);
            pstmt.executeUpdate();
            this.CloseConnection(); //may remove
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
}
