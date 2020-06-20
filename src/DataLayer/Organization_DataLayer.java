/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Organization;
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
public class Organization_DataLayer {
    
    
     public static ArrayList<Organization> SearchAllOrganization(String HostAddress,String Port,String DatabaseName,String UserName,String PassWord,String OrgName)
    {
        Connection Connection;
    ArrayList<Organization> ArrayList=new ArrayList<Organization>();
    PostGreSQL SQL=new PostGreSQL();
        try {
              Connection=SQL.open(HostAddress,Port,DatabaseName,UserName,PassWord);
              CallableStatement st=Connection.prepareCall("{call get_all_organization(?)}");
              st.setString(1,OrgName);
              ResultSet RS=st.executeQuery();
              while(RS.next())
              {
              Organization org=new Organization();
              org.SetOrganizationID(RS.getString(1));
              org.SetOrganizationName(RS.getString(2));
              org.SetManager(RS.getString(3));
              org.SetEmail(RS.getString(4));
              org.SetMobile(RS.getString(5));
              ArrayList.add(org);
              }
              SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
     
       public static ArrayList<Model.Organization>SearchOrganizationID(String OrganizationID,Login login)
    {
        ArrayList<Model.Organization> ArrayList=new ArrayList<Model.Organization>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call search_organization(?)}");
           st.setString(1,OrganizationID);
           ResultSet RS=st.executeQuery();
           while(RS.next())
           {
          Organization org=new Organization();
          org.SetOrganizationID(RS.getString(1));
          org.SetOrganizationName(RS.getString(2));
          org.SetManager(RS.getString(3));
          org.SetEmail(RS.getString(4));
          org.SetMobile(RS.getString(5));
          ArrayList.add(org);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
            public static ArrayList<Organization> SearchAllOrganizationForChart(String HostAddress,String Port,String DatabaseName,String UserName,String PassWord)
    {
        Connection Connection;
    ArrayList<Organization> ArrayList=new ArrayList<Organization>();
    PostGreSQL SQL=new PostGreSQL();
        try {
              Connection=SQL.open(HostAddress,Port,DatabaseName,UserName,PassWord);
              CallableStatement st=Connection.prepareCall("{call get_all_organization_for_chart()}");
              ResultSet RS=st.executeQuery();
              while(RS.next())
              {
              Organization org=new Organization();
              org.SetOrganizationID(RS.getString(1));
              org.SetOrganizationName(RS.getString(2));
              org.SetManager(RS.getString(3));
              org.SetEmail(RS.getString(4));
              org.SetMobile(RS.getString(5));
              ArrayList.add(org);
              }
              SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
}
