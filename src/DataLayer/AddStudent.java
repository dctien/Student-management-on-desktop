/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import GUILayer.Login;
import Model.Participation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author --Client-ServEr--
 */
public class AddStudent {

    public void AddParticipation(String StudentID, String OrgID, String Role, Date Start, Date End, Login login) {

        Connection connection;
        PostGreSQL SQL = new PostGreSQL();
        try {
            connection = SQL.open(login.GetHostAddress(), login.GetPort(), login.GetDatabaseName(), login.GetUserName(), login.GetPassWord());
            CallableStatement st = connection.prepareCall("{call insert_into_participation(?,?,?,?,?)}");
            st.setString(1, StudentID);
            st.setString(2, OrgID);
            st.setString(3, Role);
            st.setDate(4, Start);
            st.setDate(5, End);
            int n = st.executeUpdate();
            if (n >= 0) {
                JOptionPane.showMessageDialog(null, "Registry Success!");
            } else {
                JOptionPane.showMessageDialog(null, "Registry Falsed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int CheckParticipation(String StudentID, String OrgID, Login login) {
        ArrayList<Participation> ArrayList = new ArrayList<Participation>();
        ArrayList = DataLayer.Participation_DataLayer.SearchOrganizationID(StudentID, OrgID, login);
        if (ArrayList.size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
