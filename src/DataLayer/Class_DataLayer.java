/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Class;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Class_DataLayer {

    //Phuong thuc search student cho vao ArrayList
    //Phuong thuc search student cho vao ArrayList
    public static ArrayList<Class> SearchAllClass(String HostAddress, String Port, String DatabaseName, String UserName, String PassWord, String ClassName) {
        Connection Connection;
        ArrayList<Class> ArrayList = new ArrayList<Class>();
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(HostAddress, Port, DatabaseName, UserName, PassWord);
            PreparedStatement st = Connection.prepareStatement("SELECT * FROM class WHERE claname LIKE '%" + ClassName + "%' ORDER BY claname ASC");
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Class cla = new Class();
                cla.SetClassID(RS.getString(1));
                cla.SetClassName(RS.getString(2));
                cla.SetYear(RS.getInt(3));
                PreparedStatement st_monitor = Connection.prepareStatement("SELECT * FROM monitor WHERE claid = ?");
                st_monitor.setString(1, RS.getString(1));
                ResultSet RS_monitor = st_monitor.executeQuery();
                while (RS_monitor.next()) {
                    cla.SetMoniterID(RS_monitor.getString(2));
                }
                cla.SetDepartmentID(RS.getString(4));
                ArrayList.add(cla);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Class_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static ArrayList<Class> searchCLassIdBaseOnDeptId(String ClassID, String deptId, Login login) {
        ArrayList<Class> ArrayList = new ArrayList<Class>();
        Connection connection;
        PostGreSQL SQL = new PostGreSQL();
        try {
            connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            PreparedStatement st = connection.prepareStatement("SELECT * FROM class WHERE deptid = ?");
            st.setString(1, deptId);
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Class cla = new Class();
                cla.SetClassID(RS.getString(1));
                cla.SetClassName(RS.getString(2));
                cla.SetYear(RS.getInt(3));
                PreparedStatement st_monitor = connection.prepareStatement("SELECT * FROM monitor WHERE claid = ?");
                st_monitor.setString(1, RS.getString(1));
                ResultSet RS_monitor = st_monitor.executeQuery();
                while (RS_monitor.next()) {
                    cla.SetMoniterID(RS_monitor.getString(2));
                }
                cla.SetDepartmentID(RS.getString(4));
                ArrayList.add(cla);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static ArrayList<Class> SearchCLassID(String ClassID, Login login) {
        ArrayList<Class> ArrayList = new ArrayList<Class>();
        Connection connection;
        PostGreSQL SQL = new PostGreSQL();
        try {
            connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            PreparedStatement st = connection.prepareStatement("SELECT * FROM class WHERE claid = ?");
            st.setString(1, ClassID);
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Class cla = new Class();
                cla.SetClassID(RS.getString(1));
                cla.SetClassName(RS.getString(2));
                cla.SetYear(RS.getInt(3));
                PreparedStatement st_monitor = connection.prepareStatement("SELECT * FROM monitor WHERE claid = ?");
                st_monitor.setString(1, RS.getString(1));
                ResultSet RS_monitor = st_monitor.executeQuery();
                while (RS_monitor.next()) {
                    cla.SetMoniterID(RS_monitor.getString(2));
                }
                cla.SetDepartmentID(RS.getString(4));
                ArrayList.add(cla);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static ArrayList<Class> SearchClassBaseOnDept(String deptId, Login login) {
        ArrayList<Class> ArrayList = new ArrayList<Class>();
        Connection connection;
        PostGreSQL SQL = new PostGreSQL();
        try {
            connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            PreparedStatement st = connection.prepareStatement("SELECT * FROM class WHERE deptid = ?");
            st.setString(1, deptId);
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Class cla = new Class();
                cla.SetClassID(RS.getString(1));
                cla.SetClassName(RS.getString(2));
                cla.SetYear(RS.getInt(3));
                PreparedStatement st_monitor = connection.prepareStatement("SELECT * FROM monitor WHERE claid = ?");
                st_monitor.setString(1, RS.getString(1));
                ResultSet RS_monitor = st_monitor.executeQuery();
                while (RS_monitor.next()) {
                    cla.SetMoniterID(RS_monitor.getString(2));
                }
                cla.SetDepartmentID(RS.getString(4));
                ArrayList.add(cla);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static ArrayList<Class> SearchAllClassForChart(String HostAddress, String Port, String DatabaseName, String UserName, String PassWord) {
        Connection Connection;
        ArrayList<Class> ArrayList = new ArrayList<Class>();
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(HostAddress, Port, DatabaseName, UserName, PassWord);
            PreparedStatement st = Connection.prepareStatement("SELECT * FROM class");
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Class cla = new Class();
                cla.SetClassID(RS.getString(1));
                cla.SetClassName(RS.getString(2));
                cla.SetYear(RS.getInt(3));
                PreparedStatement st_monitor = Connection.prepareStatement("SELECT * FROM monitor WHERE claid = ?");
                st_monitor.setString(1, RS.getString(1));
                ResultSet RS_monitor = st_monitor.executeQuery();
                while (RS_monitor.next()) {
                    cla.SetMoniterID(RS_monitor.getString(2));
                }
                cla.SetDepartmentID(RS.getString(4));
                ArrayList.add(cla);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Class_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
}
