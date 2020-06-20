/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Participation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author --Client-ServEr--
 */
public class Participation_DataLayer {
    
     public static ArrayList<Model.Participation>SearchOrganizationID(String StudentID,String OrganizationID,Login login)
    {
        ArrayList<Model.Participation> ArrayList=new ArrayList<Model.Participation>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call search_participation(?,?)}");
           st.setString(1,StudentID);
           st.setString(2,OrganizationID);
           ResultSet RS=st.executeQuery();
           while(RS.next())
           {
             Participation par=new Participation();
             par.SetStudentID(RS.getString(1));
             par.SetOrganization(RS.getString(2));
             par.SetRole(RS.getString(3));
             par.SetTimeStart(RS.getDate(4));
             par.SetTimeEnd(RS.getDate(5));
          ArrayList.add(par);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
     
     
     
         
     public static ArrayList<Model.Participation>SearchParticipationDelete(String StudentID,String OrgID,Login login)
    {
        ArrayList<Model.Participation> ArrayList=new ArrayList<Model.Participation>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call search_participation_delete(?,?)}");
            st.setString(1,StudentID);
            st.setString(2, OrgID);
           ResultSet RS=st.executeQuery();
           while(RS.next())
           {
             Participation par=new Participation();
             par.SetStudentID(RS.getString(1));
             par.SetOrganization(RS.getString(2));
             par.SetRole(RS.getString(3));
             par.SetTimeStart(RS.getDate(4));
             par.SetTimeEnd(RS.getDate(5));
          ArrayList.add(par);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
     
     public static ArrayList<Participation>searchStudentInOrg(Login login)
    {
        ArrayList<Model.Participation> ArrayList=new ArrayList<Model.Participation>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection = SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            Statement stm = connection.createStatement();
            String query = String.format("select * from participation where orgid='%s'", login.GetUserName());
            ResultSet RS=stm.executeQuery(query);
           while(RS.next())
           {
             Participation par=new Participation();
             par.SetStudentID(RS.getString(1));
             par.SetOrganization(RS.getString(2));
             par.SetRole(RS.getString(3));
             par.SetTimeStart(RS.getDate(4));
             par.SetTimeEnd(RS.getDate(5));
          ArrayList.add(par);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
}
