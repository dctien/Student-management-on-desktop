/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILayer;

import DataLayer.PostGreSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author --Client-ServEr--
 */
public class Log {
    
    public void insertlog(Login login,Date day) throws SQLException
    {
        PostGreSQL sql=new PostGreSQL();
        Connection con;
        con=sql.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
        CallableStatement st=con.prepareCall("{call insert_into_log(?,?)}");
        st.setString(1,login.GetUserName());
        st.setDate(2, day);
        int n=st.executeUpdate();
        sql.close();
}
}
