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
public class Event {
    
    //Dinh nghia cac thuoc tinh
    private String EventID;
    private String EventName;
    private String Location;
    private Date TimeStart;
    private Date TimeEnd;
    private int approved;
    private String orgId;
    
    //Dinh nghia phuong thuc khoi tao
    public Event(){}
    public Event(Event event)
    {
        this.EventID=event.EventID;
        this.EventName=event.EventName;
        this.Location=event.Location;
        this.TimeStart=event.TimeStart;
        this.TimeEnd=event.TimeEnd;
        this.orgId = event.orgId;
        this.approved = event.approved;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetEventID(String EventID)
    {
        this.EventID=EventID;
    }
    public String GetEventID()
    {
        return EventID;
    }
    public void SetEventName(String EventName)
    {
        this.EventName=EventName;
    }
    public String GetEventName()
    {
        return EventName;
    }
    public void SetLocation(String Location)
    {
        this.Location=Location;
    }
    public String GetLocation()
    {
        return Location;
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
    
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
}
