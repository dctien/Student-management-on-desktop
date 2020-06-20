/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author --Client-ServEr--
 */
public class Department {
    
    //Dinh nghia cac thuoc tinh
    private String DepartmentID;
    private String DepartmentName;
    private String Mobile;
    private String Email;
    
    //Dinh nghia cac phuong thuc khoi tao
    public Department(){}
    public Department(Department dept)
    {
        this.DepartmentID=dept.DepartmentID;
        this.DepartmentName=dept.DepartmentName;
        this.Mobile=dept.Mobile;
        this.Email=dept.Email;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetDepartmentID(String DepartmentID)
    {
        this.DepartmentID=DepartmentID;
    }
    public String GetDepartmentID()
    {
        return DepartmentID;
    }
    public void SetDepartmentName(String DepartmentName)
    {
        this.DepartmentName=DepartmentName;
    }
    public String GetDepartmentName()
    {
        return DepartmentName;
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
}
            
