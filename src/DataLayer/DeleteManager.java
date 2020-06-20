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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author --Client-ServEr--
 */
public class DeleteManager {
            int n,n1;
    
    public int  DeleteStudentManager(Student student,Login login)
    {

        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_student(?)}");
            st.setString(1,student.GetStudentID());
            n=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
        public int  DeleteParticipationManager(Student student,Login login)
    {
        
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_participation(?)}");
            st.setString(1,student.GetStudentID());
            int n1=st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                return n1;
    }
       
        
        
   public int  DeleteOrganizationManager(Organization org,Login login)
    {

        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_organization(?)}");
            st.setString(1,org.GetOrganizationID());
            n=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
 public int  DeleteParticipation_OrganizationManager(Organization org,Login login)
    {
        
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_participation_organization(?)}");
            st.setString(1,org.GetOrganizationID());
            int n1=st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                return n1;
    }
 
 
   public int  DeleteClassManager(Class cl,Login login)
    {

        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_class(?)}");
            st.setString(1,cl.GetClassID());
            n=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
      public int  DeleteStudent_ClassManager(Class cl,Login login)
    {

        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_student_class(?)}");
            st.setString(1,cl.GetClassID());
            n=st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
   
}
