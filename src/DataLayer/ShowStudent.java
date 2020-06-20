/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import static DataLayer.Student_DataLayer.SearchStudentID;
import GUILayer.Login;
import Model.Student;
import java.util.ArrayList;

/**
 *
 * @author --Client-ServEr--
 */
public class ShowStudent {
    
    
    public Student ShowInformationStudent(Student student,Login login)
    {
        ArrayList<Student> ArrayList=new ArrayList<Student>();
        ArrayList=SearchStudentID(login.GetUserName(),login);
        student.SetStudentID(ArrayList.get(0).GetStudentID());
        student.SetFirstName(ArrayList.get(0).GetFirstName());
        student.SetLastName(ArrayList.get(0).GetLastName());
        student.SetGender(ArrayList.get(0).GetaGender());
        student.SetBirthDay(ArrayList.get(0).GetBirthDay());
        student.SetMobile(ArrayList.get(0).GetMobile());
        student.SetEmail(ArrayList.get(0).GetEmail());
        student.SetClassID(ArrayList.get(0).GetClassID());
        student.SetAddress(ArrayList.get(0).GetAddress());
        student.SetDescription(ArrayList.get(0).GetDescription());
        student.SetStatus(ArrayList.get(0).GetStatus());
        return student;        
    }
}
