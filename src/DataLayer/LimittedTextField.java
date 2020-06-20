/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author --Client-ServEr--
 */
public class LimittedTextField  extends PlainDocument{
    private int Limit;
    public LimittedTextField(int Limition)
    {
        this.Limit=Limition;
    }
    public void insertString(int Offset,String str,AttributeSet set) throws BadLocationException
    {
        if(str==null)
            return;
        else
        {
            if((getLength()+str.length())<=Limit)
            {
                str.toUpperCase();
                super.insertString(Offset, str, set);
            }
        }
    }
}
