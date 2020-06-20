/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import static DataLayer.Participation_DataLayer.SearchParticipationDelete;
import GUILayer.Login;
import Model.Participation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author --Client-ServEr--
 */
public class DeleteStudent {
 
    int n;
    public int  DeleteparticipationStudent(String StudentID,String OrgID,Login login)
    {

        Connection connection;
        PostGreSQL SQL=new PostGreSQL();
        try {
            connection=SQL.open(login.GetHostAddress(),login.GetPort(),login.GetDatabaseName(),login.GetUserName(),login.GetPassWord());
            CallableStatement st=connection.prepareCall("{call delete_participation(?,?)}");
            st.setString(1,StudentID);
            st.setString(2,OrgID);
            n=st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
   public DefaultTableModel SearchParticipationTextField(String StudentID,String OrgID,Login login)
    {
        int n,i;
    ArrayList<Participation> ArrayList=new ArrayList<Participation>();
    ArrayList=SearchParticipationDelete(StudentID,OrgID,login);
    DefaultTableModel DTM=new DefaultTableModel();
    DTM.addColumn("Organization ID");
    DTM.addColumn("Role");
    DTM.addColumn("Time Start");
    DTM.addColumn("Time End");
    DTM.addColumn("Description");
    n=ArrayList.size();
    for(i=0;i<n;i++)
    {
        Vector vt=new Vector();
        vt.add(ArrayList.get(i).GetOrganizationID());
        vt.add(ArrayList.get(i).GetRole());
        vt.add(ArrayList.get(i).GetTimeStart());
        vt.add(ArrayList.get(i).GetTimeEnd());
        vt.add(ArrayList.get(i).GetDescription());
        DTM.addRow(vt);
    }
    return DTM;
    }  
   
      public DefaultTableModel searchStudentInOrg(Login login)
    {
        int n,i;
    ArrayList<Participation> ArrayList=new ArrayList<Participation>();
    ArrayList=Participation_DataLayer.searchStudentInOrg(login);
    DefaultTableModel DTM=new DefaultTableModel();
    DTM.addColumn("Student ID");
    DTM.addColumn("Organization ID");
    DTM.addColumn("Role");
    DTM.addColumn("Time Start");
    DTM.addColumn("Time End");
    DTM.addColumn("Description");
    n=ArrayList.size();
    for(i=0;i<n;i++)
    {
        Vector vt=new Vector();
        vt.add(ArrayList.get(i).GetStudentID());
        vt.add(ArrayList.get(i).GetOrganizationID());
        vt.add(ArrayList.get(i).GetRole());
        vt.add(ArrayList.get(i).GetTimeStart());
        vt.add(ArrayList.get(i).GetTimeEnd());
        vt.add(ArrayList.get(i).GetDescription());
        DTM.addRow(vt);
    }
    return DTM;
    }  

   
   
      public DefaultTableModel SearchParticipationButton(String StudentID,String OrgID,Login login)
    {
        int n,i;
    ArrayList<Participation> ArrayList=new ArrayList<Participation>();
    ArrayList=SearchParticipationDelete(StudentID,OrgID,login);
    DefaultTableModel DTM=new DefaultTableModel();
    DTM.addColumn("Organization ID");
    DTM.addColumn("Role");
    DTM.addColumn("Time Start");
    DTM.addColumn("Time End");
    DTM.addColumn("Description");
    n=ArrayList.size();
    if(n==0)
        JOptionPane.showMessageDialog(null,"Cannot find any Organization!");
    for(i=0;i<n;i++)
    {
        Vector vt=new Vector();
        vt.add(ArrayList.get(i).GetOrganizationID());
        vt.add(ArrayList.get(i).GetRole());
        vt.add(ArrayList.get(i).GetTimeStart());
        vt.add(ArrayList.get(i).GetTimeEnd());
        vt.add(ArrayList.get(i).GetDescription());
        DTM.addRow(vt);
    }
    return DTM;
    } 
}
