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
import java.io.UTFDataFormatException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class IOManager_Modified_UTF8 {
    /**
     * There are currently 2 values: Mode = "UTF16" and Mode = "Modified UTF8"
     * The default value is set to "UTF16". The value can be changed via the
     * Mode butten on the main screen.
     * 
     * Remark (1): a file created in UTF16 must be read in UTF16 mode and likewise a
     * file created in modified UTF8 mode must be read in modified UTF8 mode.
     * 
     * Remark (2): In the modified UTF8 mode the writeUTF and readUTF methods
     * are used. These methods work on a per token bases. In case of UTF16 mode, 
     * the readChar and writeChars methods are used. The writeChars  writes per 
     * token. After each token a separator (codepoint with unicode 0002) is
     * written tot the outputstream. The readChar reads percharacter until
     * a separator is encountered.
     * 
     * Remark (3) The write methods complete the current table row. If the 
     * remaining elements of the current row are emty, emty elements are written
     * to the file. When this file is read, those empty elements are writte to
     * the table row. In fact, each empty element of the current row is also 
     * handled as a (empty) token.
     */
    
    public static final char SEPARATOR = '\u0002';
    static String Mode = "UTF16"; //default, value can be set via mode button

    public static void SaveFile(String AttachedFile) {

        JOptionPane.showMessageDialog(null, "Saving: " + AttachedFile, "alert",
                JOptionPane.ERROR_MESSAGE);

        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(AttachedFile)));
            MyDefaultTableModel MyDefaultTableModel = Main.MainScreen.MyDefaultTableModel;

            for (int i = 0; i < MyDefaultTableModel.getRowCount(); i++) {
                for (int j = 0; j < MyDefaultTableModel.getColumnCount(); j++) {

                    String s;
                    if (MyDefaultTableModel.getValueAt(i, j) != null) {
                        s = MyDefaultTableModel.getValueAt(i, j).toString();
                        writeToken(out, s);
                    } else {
                        writeToken(out, "\u0000"); // writing remaining elements of the current line                                                  // current tabel line with empty elements
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

            in = new DataInputStream(new BufferedInputStream(new FileInputStream(SelectedFile)));

            while (true) {
                String s;
                s = readToken(in);
                if (s != null) {
                    MyDefaultTableModel.addObjectEndofTable(s);
                } else {
                    MyDefaultTableModel.addObjectEndofTable(null);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOManager_Modified_UTF8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) { // EOFException is used to exit the while loop

        } catch (UTFDataFormatException ex) {
            System.out.println("UTFDataFormatException");
        }
        
        catch (IOException | NullPointerException ex) {
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

    private static String readToken(DataInputStream in) throws 
            IOException, EOFException, UTFDataFormatException {
        switch (Mode) {
            case "Modified UTF8":                
                return in.readUTF();
            case "UTF16":               
                StringBuilder sb = new StringBuilder();
                while (true) {
                    char c = in.readChar();
                    if (c != SEPARATOR) { // \u0002 code point used as separator
                        sb.append(c);
                    } else {
                        return sb.toString();
                    }                  
                }
            default:
                return null;
        }
    }

    private static void writeToken(DataOutputStream out, String token)
            throws IOException {
        switch (Mode) {
            case "Modified UTF8":
                out.writeUTF(token);
                break;
            case "UTF16":
                out.writeChars(token);
                out.writeChar(SEPARATOR); //Remark: writeChar takes an integer as input
                break;
        }

    }

}
