/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author --Client-ServEr--
 */
public class Student {
    
    //Dinh nghia cac thuoc tinh
    private String StudentID;
    private String First_Name;
    private String Last_Name;
    private boolean Gender;
    private Date BirthDay;
    private String Mobile;
    private String Email;
    private String Address;
    private String ClassID;
    private String Description;
    private int Status;
    
    public Student(){}
    
    //Phuong thuc khoi tao
    public Student(Student st)
    {
        this.StudentID=st.StudentID;
        this.First_Name=st.First_Name;
        this.Last_Name=st.Last_Name;
        this.Gender=st.Gender;
        this.BirthDay=st.BirthDay;
        this.Mobile=st.Mobile;
        this.Email=st.Email;
        this.Address=st.Address;
        this.ClassID=st.ClassID;
        this.Description=st.Description;
        this.Status=st.Status;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetStudentID(String StudentID)
    {
        this.StudentID=StudentID;
    }
    public String GetStudentID()
    {
        return StudentID;
    }
    public void SetFirstName(String FirstName)
    {
        this.First_Name=FirstName;
    }
    public String GetFirstName()
    {
        return First_Name;
    }
    public void SetLastName(String LastName)
    {
        this.Last_Name=LastName;
    }
    public String GetLastName()
    {
        return Last_Name;
    }
    public void SetGender(boolean Gender)
    {
        this.Gender=Gender;
    }
    public Boolean GetaGender()
    {
        return Gender;
    }
    public void SetBirthDay(Date BirthDay)
    {
        this.BirthDay=BirthDay;
    }
    public Date GetBirthDay()
    {
        return BirthDay;
    }
    public void SetMobile(String Mobile)
    {
        this.Mobile=Mobile;
    }
    public String GetMobile()
    {
        return Mobile;
    }
    public void SetEmail(String Email)
    {
        this.Email=Email;
    }
    public String GetEmail()
    {
        return Email;
    }
    public void SetAddress(String Address)
    {
        this.Address=Address;
    }
    public String GetAddress()
    {
        return Address;
    }
    public void SetClassID(String ClassID)
    {
        this.ClassID=ClassID;
    }
    public String GetClassID()
    {
        return ClassID;
    }
    public void SetDescription(String Description)
    {
        this.Description=Description;
    }
    public String GetDescription()
    {
        return Description;
    }
    public void SetStatus(int Status)
    {
        this.Status=Status;
    }
    public int GetStatus()
    {
        return Status;
    }
}
