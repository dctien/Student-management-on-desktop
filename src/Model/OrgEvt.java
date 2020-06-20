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
public class OrgEvt {
    
    //Dinh nghia cac thuoc tinh
    private String OrganizationID;
    private String EventID;
    private String Description;
    
    //Dinh nghia phuong thuc khoi tao
    public OrgEvt(){}
    public OrgEvt(OrgEvt OrgEvt)
    {
        this.OrganizationID=OrgEvt.OrganizationID;
        this.EventID=OrgEvt.EventID;
        this.Description=OrgEvt.Description;
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
      public void SetEventID(String EventID)
    {
        this.EventID=EventID;
    }
    public String GetEventID()
    {
        return EventID;
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
