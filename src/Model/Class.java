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
public class Class {
    
    //Dinh nghia cac thuoc tinh
    private String ClassID;
    private String ClassName;
    private int Year;
    private String MoniterID;
    private String DepartmentID;
    
    
    public Class(){}
    
    //Dinh nghia phuong thuc khoi tao 
    public Class (Class cl)
    {
     this.ClassID=cl.ClassID;
     this.ClassName=cl.ClassName;
     this.Year=cl.Year;
     this.MoniterID=cl.MoniterID;
     this.DepartmentID=cl.DepartmentID;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetClassID(String ClassID)
    {
        this.ClassID=ClassID;
    }
    public String GetClassID()
    {
        return ClassID;
    }
    public void SetClassName(String ClassName)
    {
        this.ClassName=ClassName;
    }
    public String GetClassName()
    {
        return ClassName;
    }
    public void SetYear(int Year)
    {
        this.Year=Year;
    }
    public int GetYear()
    {
        return Year;
    }
    public void SetMoniterID(String MoniterID)
    {
        this.MoniterID=MoniterID;
    }
    public String GetMoniterID()
    {
        return MoniterID;
    }
    public void SetDepartmentID(String DepartmentID)
    {
        this.DepartmentID=DepartmentID;
    }
    public String GetDepartmentID()
    {
        return DepartmentID;
    }
}

