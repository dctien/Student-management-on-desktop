/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Class;
import Model.Organization;
import Model.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author --Client-ServEr--
 */
public class EditManager {
    
    
    
     public void  EditStudentManager(Student student,Login login)
    {
        
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call update_student(?,?,?,?,?,?,?,?,?,?,?)}");
           st.setString(1,student.GetStudentID());
           st.setString(2,student.GetFirstName());
           st.setString(3,student.GetLastName());
           st.setBoolean(4,student.GetaGender());
           st.setDate(5, (Date) student.GetBirthDay());
           st.setString(6,student.GetMobile());
           st.setString(7,student.GetEmail());
           st.setString(8,student.GetAddress());
           st.setString(9,student.GetClassID());
           st.setString(10,student.GetDescription());
           st.setInt(11,student.GetStatus());
            int n=st.executeUpdate();
            if(n>=0)
            {
                JOptionPane.showMessageDialog(null,"Update Success!");
            }
            else
                JOptionPane.showMessageDialog(null,"Update Falsed!");
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
         
     public void  EditClassManager(Class Cla,Login login)
    {
        
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call update_class(?,?,?,?,?)}");
            st.setString(1,Cla.GetClassID());
            st.setString(2,Cla.GetClassName());
            st.setInt(3,Cla.GetYear());
            st.setString(4,Cla.GetMoniterID());
            st.setString(5,Cla.GetDepartmentID());
            int n=st.executeUpdate();
            if(n>=0)
            {
                JOptionPane.showMessageDialog(null,"Update Success!");
            }
            else
                JOptionPane.showMessageDialog(null,"Update Falsed!");
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void  EditOrganizationManager(Organization org,Login login)
    {
        
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call update_organization(?,?,?,?,?)}");
             st.setString(1,org.GetOrganizationID());
             st.setString(2,org.GetOrganizationName());
             st.setString(3,org.GetManager());
             st.setString(4,org.GetEmail());
             st.setString(5,org.GetMobile());
            int n=st.executeUpdate();
            if(n>=0)
            {
                JOptionPane.showMessageDialog(null,"Update Success!");
            }
            else
                JOptionPane.showMessageDialog(null,"Update Falsed!");
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
