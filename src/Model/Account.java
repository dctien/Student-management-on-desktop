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
public class Account {
    
    //Dinh nghia cac thuoc tinh
    private String UserName;
    private String Contact;
    private String PassWord;
    private int Role;
    
    //Dinh nghia phuong thuc khoi tao
    public Account(){}
    public Account(Account Account)
    {
        this.UserName=Account.UserName;
        this.Contact=Account.Contact;
        this.PassWord=Account.PassWord;
        this.Role=Account.Role;
    }
    
    //Dinh nghia cac phuong thuc Get/Set
    public void SetUserName(String UsetName)
    {
        this.UserName=UserName;
    }
    public String GetUserName()
    {
        return UserName;
    }
     public void SetContact(String Contact)
    {
        this.Contact=Contact;
    }
    public String GetContact()
    {
        return Contact;
    }
     public void SetPassWord(String PassWord)
    {
        this.PassWord=PassWord;
    }
    public String GetPassWord()
    {
        return PassWord;
    }
     public void SetRole(int Role)
    {
        this.Role=Role;
    }
    public int GetRole()
    {
        return Role;
    }
}
