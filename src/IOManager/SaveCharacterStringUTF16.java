/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOManager;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class SaveCharacterStringUTF16 {
    /**
     * There are currently 2 values: Mode = "UTF16" and Mode = "Modified UTF8"
     * The default value of Java is "UTF16".
     */
    static String Mode = "UTF16"; //default, value can be set via mode button

    public static void SaveFile(String FileName, String CharacterString ) {
        
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FileName)));
            
            int NumberCodePoints = CharacterString.length();
            
            for (int i = 0; i < NumberCodePoints; i++) {
                
                    int s = CharacterString.codePointAt(i);
                    out.writeChar(s);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveCharacterStringUTF16.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(SaveCharacterStringUTF16.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SaveCharacterStringUTF16.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
