/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

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
public class IOManager {

    public static void SaveFile(String AttachedFile) {

        JOptionPane.showMessageDialog(null, "Saving: " + AttachedFile, "alert", JOptionPane.ERROR_MESSAGE);

        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(AttachedFile)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < FlowManager.getReferredTableModel().getRowCount(); i++) {
            for (int j = 0; j < FlowManager.getReferredTableModel().getColumnCount(); j++) {

                String s;

                if (FlowManager.getReferredTableModel().getValueAt(i, j) != null) {
                    s = FlowManager.getReferredTableModel().getValueAt(i, j).toString();
                } else {
                    s = "\u0000"; /* to indicate table element is empty*/

                }

                try {
                    if (out != null) {
                        if (s != null) {
                            out.writeChars(s);
                            out.writeChar(FlowManager.SEPERATOR); //Remark: writeChar takes an integr as input
                        } else {
                            out.writeChars(s); /* ter vermijding null pointer exeception*/

                        }

                    } else {
                        System.out.println("out=null");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void LoadFile(String SelectedFile) {

        DataInputStream in = null;
        char c;
        StringBuilder sb = new StringBuilder();
        FlowManager.getReferredTableModel().initializeTable();
        boolean emptyfile = true;

        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(SelectedFile)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int i = 0;
            int j = 0;

            while (true) {
                c = in.readChar();
                emptyfile = false;
                System.out.println("Boolean emptyfile set to false");

                if (c != FlowManager.SEPERATOR) {                /* \u0002 sign used as separator*/

                    sb.append(c);
                } else {

                    FlowManager.getReferredTableModel().setValueAt(sb.toString(), i, j);
                    j++;
                    if (j == MyDefaultTableModel.getLastColumn()) {  // getLastColumn is a static method
                        j = 0;
                        i++;
                        if (i == FlowManager.getReferredTableModel().getRowCount()) {
                            FlowManager.getReferredTableModel().addRow(new Object[MyDefaultTableModel.getLastColumn()]);
                        }
                    }
                    sb.delete(0, sb.length());
                }
            }
        } catch (EOFException ex01) { // EOFException is used to exit the while loop, so no call to Logger

            int i = FlowManager.getReferredTableModel().getRowCount();

            if (!emptyfile) {
                System.out.println("Boolean emptyfile is used here");
                try {
                    FlowManager.getReferredTableModel().removeRow(i - 1); //delete extra row created ar end of row
                } catch (ArrayIndexOutOfBoundsException ex02) {           // only when file is not empty
                    Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex02);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(IOManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
