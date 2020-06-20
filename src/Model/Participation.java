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
public class Participation {
    
    //Dinh nghia cac thuoc tinh
    private String StudentID;
    private String OrganizationID;
    private String Role;
    private Date TimeStart;
    private Date TimeEnd;
    private String Description;
    
    //Dinh nghia phuong thuc khoi tao
    public Participation(){}
    public Participation(Participation Participation)
    {
        this.StudentID=Participation.StudentID;
        this.OrganizationID=Participation.OrganizationID;
        this.Role=Participation.Role;
        this.TimeStart=Participation.TimeStart;
        this.TimeEnd=Participation.TimeEnd;
        this.Description=Participation.Description;
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
     public void SetOrganization(String OrganizationID)
    {
        this.OrganizationID=OrganizationID;
    }
    public String GetOrganizationID()
    {
        return OrganizationID;
    }
     public void SetRole(String Role)
    {
        this.Role=Role;
    }
    public String GetRole()
    {
        return Role;
    }
     public void SetTimeStart(Date TimeStart)
    {
        this.TimeStart=TimeStart;
    }
    public Date GetTimeStart()
    {
        return TimeStart;
    }
     public void SetTimeEnd(Date TimeEnd)
    {
        this.TimeEnd=TimeEnd;
    }
    public Date GetTimeEnd()
    {
        return TimeEnd;
    }
     public void SetDescription(String Description)
    {
        this.Description=Description;
    }
    public String GetDescription()
    {
        return Description;
    }
}
