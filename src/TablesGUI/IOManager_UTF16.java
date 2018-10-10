/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class IOManager_UTF16 {

    public static void SaveFile(String AttachedFile) {

        JOptionPane.showMessageDialog(null, "Saving: " + AttachedFile, "alert", JOptionPane.ERROR_MESSAGE);

        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(AttachedFile)));
            MyDefaultTableModel MyDefaultTableModel = Main.MainScreen.MyDefaultTableModel;

            for (int i = 0; i < MyDefaultTableModel.getRowCount(); i++) {
                for (int j = 0; j < MyDefaultTableModel.getColumnCount(); j++) {

                    String s;
                    if (MyDefaultTableModel.getValueAt(i, j) != null) {
                        s = MyDefaultTableModel.getValueAt(i, j).toString();
                        out.writeChars(s);
                    } else {
                        out.writeUTF("\u0000"); //evade NullPointeExceptiontion;
//                        out.writeUTF(null);     // out.writeUTF(null) causes a NullPointerException
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void LoadFile(String SelectedFile) {

        DataInputStream in = null;
        MyDefaultTableModel MyDefaultTableModel = Main.MainScreen.MyDefaultTableModel;

        MyDefaultTableModel.initializeTable();

        try {

            int i = 0;
            int j = 0;

            in = new DataInputStream(new BufferedInputStream(new FileInputStream(SelectedFile)));

            while (true) {

                StringBuilder sb = new StringBuilder();
                char c = in.readChar();

                if (c != FlowManager.SEPERATOR) {
                    /* \u0002 sign used as separator*/

                    sb.append(c);
                } else {

                    FlowManager.getReferredTableModel().setValueAt(sb.toString(), i, j);
                    j++;
                    if (j == MyDefaultTableModel.getLastColumn()) {  // getLastColumn is a static method
                        j = 0;
                        i++;
                        if (i == MyDefaultTableModel.getRowCount()) {
                            MyDefaultTableModel.addRow(new Object[MyDefaultTableModel.getLastColumn()]);
                        }
                    }
                    sb.delete(0, sb.length());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) { // EOFException is used to exit the while loop
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {  // evading derefencing possible null pointer
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
