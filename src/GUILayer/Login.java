/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILayer;

import DataLayer.PostGreSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author --Client-ServEr--
 */
public class Login extends javax.swing.JDialog {

    //Dinh nghia cac thuoc tinh
    private String HostAddress;
    private String Port;
    private String DatabaseName;
    private String UserName;
    private String PassWord;
    private String Name;
    Boolean LoginValue = false;
    int type = -1;
    public static int accountType;

//Dinh nghia cac phuong thuc Get/Set
    public String GetHostAddress() {
        return HostAddress;
    }

    public String GetPort() {
        return Port;
    }

    public String GetDatabaseName() {
        return DatabaseName;
    }

    public String GetUserName() {
        return UserName;
    }

    public String GetPassWord() {
        return PassWord;
    }

    public String GetName() {
        return Name;
    }

    public Boolean GetLoginvalue() {
        return LoginValue;
    }

//Phuong thuc khoi tao
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.LoginValue = false;
        initDefaultView();
    }

    /**
     * Creates new form Login
     *
     * @param parent
     * @param modal
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBTLogin = new javax.swing.JButton();
        JBTCancel = new javax.swing.JButton();
        JTFHostAddress = new javax.swing.JTextField();
        JTFPort = new javax.swing.JTextField();
        JTFDatabaseName = new javax.swing.JTextField();
        JTFUserName = new javax.swing.JTextField();
        JLBHostAddress = new javax.swing.JLabel();
        JLBPort = new javax.swing.JLabel();
        JLBDatabaseName = new javax.swing.JLabel();
        JLBUserName = new javax.swing.JLabel();
        JLBPassWord = new javax.swing.JLabel();
        JTFPassWord = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(10, 16, 32));
        setFont(new java.awt.Font("Aharoni", 0, 18)); // NOI18N

        JBTLogin.setText("Login");
        JBTLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTLoginActionPerformed(evt);
            }
        });

        JBTCancel.setText("Cancel");
        JBTCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTCancelActionPerformed(evt);
            }
        });

        JLBHostAddress.setBackground(new java.awt.Color(255, 255, 255));
        JLBHostAddress.setForeground(new java.awt.Color(255, 255, 255));
        JLBHostAddress.setText("Host Address");

        JLBPort.setForeground(new java.awt.Color(255, 255, 255));
        JLBPort.setText("Port");

        JLBDatabaseName.setForeground(new java.awt.Color(255, 255, 255));
        JLBDatabaseName.setText("DatabaseName");
        JLBDatabaseName.setToolTipText("");

        JLBUserName.setForeground(new java.awt.Color(255, 255, 255));
        JLBUserName.setText("User Name");

        JLBPassWord.setForeground(new java.awt.Color(255, 255, 255));
        JLBPassWord.setText("PassWord");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBHostAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBDatabaseName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBPassWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTFPort)
                    .addComponent(JTFDatabaseName)
                    .addComponent(JTFUserName)
                    .addComponent(JTFHostAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(JBTLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTCancel))
                    .addComponent(JTFPassWord))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFHostAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBHostAddress))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBPort)
                    .addComponent(JTFPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBDatabaseName)
                    .addComponent(JTFDatabaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBUserName))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBPassWord)
                    .addComponent(JTFPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTLogin)
                    .addComponent(JBTCancel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setBounds(500, 200, 402, 314);
    }// </editor-fold>//GEN-END:initComponents

    private void initDefaultView() {
        this.JTFPassWord.setText("12345");
        this.JTFDatabaseName.setText("SVDBver3");
        this.JTFHostAddress.setText("localhost");
        this.JTFPort.setText("5432");
    }

    //Phuong thuc su kien Login
    private void JBTLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTLoginActionPerformed
        // TODO add your handling code here:
        try {
            Connection Connection;

            this.HostAddress = this.JTFHostAddress.getText();
            this.Port = this.JTFPort.getText();
            this.DatabaseName = this.JTFDatabaseName.getText();
            this.UserName = this.JTFUserName.getText();
            this.PassWord = this.JTFPassWord.getText();
            PostGreSQL SQL = new PostGreSQL();
            Connection = SQL.open(HostAddress, Port, DatabaseName, UserName, PassWord);
            Statement stm = Connection.createStatement();
            String login = String.format("select * from account where username='%s' and password='%s'", this.UserName, this.PassWord);
            ResultSet RS = stm.executeQuery(login);
            while (RS.next()) {
                String s1 = RS.getString("username");
                this.Name = RS.getString("contact");
                String s2 = RS.getString("password");
                this.type = RS.getInt("role");
                this.LoginValue = true;
                Login.accountType = this.type;
            }
            SQL.close();
            if (this.LoginValue == true) {
                this.setVisible(false);
                this.JTFHostAddress.setText("");
                this.JTFPort.setText("");
                this.JTFDatabaseName.setText("");
                this.JTFUserName.setText("");
                this.JTFPassWord.setText("");
                Log log = new Log();
            } else {
                JOptionPane.showMessageDialog(this, "Login Falsed!Please try again!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Login Falsed!Please try again!");
        }
    }//GEN-LAST:event_JBTLoginActionPerformed

    //Dinh nghia phuong thuc su kien Cancel
    private void JBTCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTCancelActionPerformed
        // TODO add your handling code here:
        this.LoginValue = false;
        this.setVisible(false);
        this.JTFHostAddress.setText("");
        this.JTFPort.setText("");
        this.JTFDatabaseName.setText("");
        this.JTFUserName.setText("");
        this.JTFPassWord.setText("");
    }//GEN-LAST:event_JBTCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTCancel;
    private javax.swing.JButton JBTLogin;
    private javax.swing.JLabel JLBDatabaseName;
    private javax.swing.JLabel JLBHostAddress;
    private javax.swing.JLabel JLBPassWord;
    private javax.swing.JLabel JLBPort;
    private javax.swing.JLabel JLBUserName;
    private javax.swing.JTextField JTFDatabaseName;
    private javax.swing.JTextField JTFHostAddress;
    private javax.swing.JPasswordField JTFPassWord;
    private javax.swing.JTextField JTFPort;
    private javax.swing.JTextField JTFUserName;
    // End of variables declaration//GEN-END:variables
}