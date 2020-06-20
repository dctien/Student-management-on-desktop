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
public class Organization {
    
    //Dinh nghia cac thuoc tinh
    private String OrganizationID;
    private String OrganizationName;
    private String Manager;
    private String Mobile;
    private String Email;
    
    //Dinh nghia phuong thuc khoi tao
    public Organization(){}
    public Organization(Organization Organization)
    {
        this.OrganizationID=Organization.OrganizationID;
        this.OrganizationName=Organization.OrganizationName;
        this.Manager=Organization.Manager;
        this.Mobile=Organization.Mobile;
        this.Email=Organization.Email;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetOrganizationID(String OrganizationID)
    {
        this.OrganizationID=OrganizationID;
    }
    public String GetOrganizationID()
    {
        return OrganizationID;
    }
     public void SetOrganizationName(String OrganizationName)
    {
        this.OrganizationName=OrganizationName;
    }
    public String GetOrganizationName()
    {
        return OrganizationName;
    }

    public void SetManager(String Manager)
    {
        this.Manager=Manager;
    }
    public String GetManager()
    {
        return Manager;
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
