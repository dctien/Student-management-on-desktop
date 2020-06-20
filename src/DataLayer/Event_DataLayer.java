/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Event;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author --Client-ServEr--
 */
public class Event_DataLayer {

    public static ArrayList<Event> SearchAllEvent(Login login, String EventName) {
        Connection Connection;
        ArrayList<Event> ArrayList = new ArrayList<Event>();
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            CallableStatement st = Connection.prepareCall("{call get_all_event(?)}");
            st.setString(1, EventName);
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Event event = new Event();
                event.SetEventID(RS.getString(1));
                event.SetEventName(RS.getString(2));
                event.SetLocation(RS.getString(3));
                event.SetTimeStart(RS.getDate(4));
                event.SetTimeEnd(RS.getDate(5));
                event.setApproved(RS.getInt(6));
                event.setOrgId(RS.getString(7));
                ArrayList.add(event);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }
    
    public static ArrayList<Event> SearchAllEventForChart(Login login) {
        Connection Connection;
        ArrayList<Event> ArrayList = new ArrayList<Event>();
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            CallableStatement st = Connection.prepareCall("{call get_all_event_for_chart()}");
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Event event = new Event();
                event.SetEventID(RS.getString(1));
                event.SetEventName(RS.getString(2));
                event.SetLocation(RS.getString(3));
                event.SetTimeStart(RS.getDate(4));
                event.SetTimeEnd(RS.getDate(5));
                event.setApproved(RS.getInt(6));
                event.setOrgId(RS.getString(7));
                ArrayList.add(event);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static ArrayList<Event> getNotApprovedEvent(Login login) {
        Connection Connection;
        ArrayList<Event> ArrayList = new ArrayList<Event>();
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            PreparedStatement st = Connection.prepareStatement("SELECT * FROM event WHERE approved = 0 ORDER BY eventid ASC");
            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                Event event = new Event();
                event.SetEventID(RS.getString(1));
                event.SetEventName(RS.getString(2));
                event.SetLocation(RS.getString(3));
                event.SetTimeStart(RS.getDate(4));
                event.SetTimeEnd(RS.getDate(5));
                event.setApproved(RS.getInt(6));
                event.setOrgId(RS.getString(7));
                ArrayList.add(event);
            }
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayList;
    }

    public static boolean canAddEvent(Login login) {
        ArrayList<Event> allEvent = Event_DataLayer.SearchAllEventForChart(login);
        boolean canAdd = true;
        for (int i = 0; i < allEvent.size(); i++) {
            Event dummyEvent = allEvent.get(i);
            if (dummyEvent.getOrgId().compareTo(login.GetUserName()) == 0) {
                if (dummyEvent.GetTimeStart().compareTo(new Date()) <= 0
                        && dummyEvent.GetTimeEnd().compareTo(new Date()) >= 0) {
                    canAdd = false;
                } else {
                    canAdd = true;
                }
            } else {
                canAdd = true;
            }
        }
        return canAdd;
    }
    
        public static void approve(Login login, String eventID) {
        Connection Connection;
        PostGreSQL SQL = new PostGreSQL();
        try {
            Connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            PreparedStatement st = Connection.prepareStatement("UPDATE event SET approved = ? WHERE eventid = '" + eventID + "'");
            st.setInt(1, 1);
            ResultSet RS = st.executeQuery();
            SQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student_DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
