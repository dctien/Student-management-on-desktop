/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Department;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author --Client-ServEr--
 */
public class Department_DateLayer {
    
    
        public static ArrayList<Department>SearchDepartmentID(String DepartmentID,Login login)
    {
        ArrayList<Model.Department> ArrayList=new ArrayList<Model.Department>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call search_Department(?)}");
           st.setString(1,DepartmentID);
           ResultSet RS=st.executeQuery();
           while(RS.next())
           {
                Department Department=new Model.Department();
                Department.SetDepartmentID(RS.getString(1));
                Department.SetDepartmentName(RS.getString(2));
                Department.SetMobile(RS.getString(3));
                Department.SetEmail(RS.getString(4));
                ArrayList.add(Department);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
}
