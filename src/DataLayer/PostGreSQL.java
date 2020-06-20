/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author --Client-ServEr--
 */
public class PostGreSQL {
    
    //Dinh nghia cac thuoc tinh
   private Connection Connection;
    
    
    //Dinh nghia phuong thuc ket noi voi co so du lieu
     public Connection open(String HostAddress,String Port,String DatabaseName,String UserName,String PassWord) throws SQLException
      {
          String getsource;
        try {
        Class.forName("org.postgresql.Driver");
        getsource="jdbc:postgresql://"+HostAddress+":"+Port+"/"+DatabaseName;
        Connection=DriverManager.getConnection(getsource,"postgres",PassWord);
         } catch (ClassNotFoundException ex) {
        }
      return Connection;
      }
     
     //Dinh nghia phuong thuc dong ket noi voi co so du lieu
     public void close() {
           try {
              this.Connection.close();
                } catch (Exception ex) {
               System.out.println(ex.getMessage());
                }
        }
     
     //Dinh nghia phuong thuc ExecuteQuery
     public ResultSet ExecuteQuery(String SQL)
     {
         ResultSet RS=null;
        try {
            Statement ST=this.Connection.createStatement();
          RS=ST.executeQuery(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return RS;
     }
     
     //Dinh nghia phuong thuc ExecuteUpdate
         public int executeUpdate(String sql) {
            int n=-1;
            try{
                Statement sm=this.Connection.createStatement();
              n=sm.executeUpdate(sql);
               }catch(Exception ex){
             System.out.println(ex.getMessage());
              }
             return n;
       }
}

