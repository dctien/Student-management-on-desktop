/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Student;
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
public class Student_DataLayer {
    
    
    //Phuong thuc search student cho vao ArrayList
    public static ArrayList<Student> SearchAllStudent(String HostAddress,String Port,String DatabaseName,String UserName,String PassWord,String LastName)
    {
        Connection Connection;
    ArrayList<Student> ArrayList=new ArrayList<Student>();
    PostGreSQL SQL=new PostGreSQL();
        try {
              Connection=SQL.open(HostAddress,Port,DatabaseName,UserName,PassWord);
              CallableStatement st=Connection.prepareCall("{call get_all_student(?)}");
              st.setString(1,LastName);
              ResultSet RS=st.executeQuery();
              while(RS.next())
              {
                  Student student=new Student();
                  student.SetStudentID(RS.getString(1));
                  student.SetFirstName(RS.getString(2));
                  student.SetLastName(RS.getString(3));
                  student.SetGender(RS.getBoolean(4));
                  student.SetBirthDay(RS.getDate(5));
                  student.SetMobile(RS.getString(6));
                  student.SetEmail(RS.getString(7));
                  student.SetAddress(RS.getString(8));
                  student.SetClassID(RS.getString(9));
                  student.SetDescription(RS.getString(10));
                  student.SetStatus(RS.getInt(11));
                  ArrayList.add(student);
              }
              SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
    
    public static ArrayList<Student> searchStudentBaseOnClass(String classId, Login login)
    {
        Connection Connection;
    ArrayList<Student> ArrayList=new ArrayList<Student>();
    PostGreSQL SQL=new PostGreSQL();
        try {
              Connection=SQL.open(login.GetHostAddress(),
                     login.GetPort(),
                     login.GetDatabaseName(),
                     login.GetUserName(),
                     login.GetPassWord());
               Statement stm=Connection.createStatement();
               String query=String.format("select * from student where claid='%s'", classId);
          //  ResultSet RS = st.executeQuery();
              ResultSet RS=stm.executeQuery(query);
              while(RS.next())
              {
                  Student student=new Student();
                  student.SetStudentID(RS.getString(1));
                  student.SetFirstName(RS.getString(2));
                  student.SetLastName(RS.getString(3));
                  student.SetGender(RS.getBoolean(4));
                  student.SetBirthDay(RS.getDate(5));
                  student.SetMobile(RS.getString(6));
                  student.SetEmail(RS.getString(7));
                  student.SetAddress(RS.getString(8));
                  student.SetClassID(RS.getString(9));
                  student.SetDescription(RS.getString(10));
                  student.SetStatus(RS.getInt(11));
                  ArrayList.add(student);
              }
              SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
    
    public static ArrayList<Model.Student>SearchStudentID(String StudentID,Login login)
    {
        ArrayList<Model.Student> ArrayList=new ArrayList<Model.Student>();
        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call search_student(?)}");
           st.setString(1,StudentID);
           ResultSet RS=st.executeQuery();
           while(RS.next())
           {
               Model.Student student=new Model.Student();
               student.SetStudentID(RS.getString(1));
               student.SetFirstName(RS.getString(2));
               student.SetLastName(RS.getString(3));
               student.SetGender(RS.getBoolean(4));
               student.SetBirthDay(RS.getDate(5));
               student.SetMobile(RS.getString(6));
               student.SetEmail(RS.getString(7));
               student.SetAddress(RS.getString(8));
               student.SetClassID(RS.getString(9));
               student.SetDescription(RS.getString(10));
               student.SetStatus(RS.getInt(11));
               ArrayList.add(student);
           }
          SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
        public static ArrayList<Student> SearchAllStudentForChart(String HostAddress,String Port,String DatabaseName,String UserName,String PassWord)
    {
        Connection Connection;
    ArrayList<Student> ArrayList=new ArrayList<Student>();
    PostGreSQL SQL=new PostGreSQL();
        try {
              Connection=SQL.open(HostAddress,Port,DatabaseName,UserName,PassWord);
              CallableStatement st=Connection.prepareCall("{call get_all_student_for_chart()}");
              ResultSet RS=st.executeQuery();
              while(RS.next())
              {
                  Student student=new Student();
                  student.SetStudentID(RS.getString(1));
                  student.SetFirstName(RS.getString(2));
                  student.SetLastName(RS.getString(3));
                  student.SetGender(RS.getBoolean(4));
                  student.SetBirthDay(RS.getDate(5));
                  student.SetMobile(RS.getString(6));
                  student.SetEmail(RS.getString(7));
                  student.SetAddress(RS.getString(8));
                  student.SetClassID(RS.getString(9));
                  student.SetDescription(RS.getString(10));
                  student.SetStatus(RS.getInt(11));
                  ArrayList.add(student);
              }
              SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
}
