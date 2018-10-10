/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Thomas
 */
public class FlowManager {

    /**
     * Constants
     */
    
    public static final String NO_ATTACHED_FILE = "No_Attached_File";
    public static final char SEPERATOR = '\u0002';
    
    /**
     * Static Variables
     */
    
    private static MyDefaultTableModel ReferredTableModel;
    private static MyDefaultByteTableModel ReferredByteTableModel;
    private static String AttachedFile;
    private static JFrame ReferredInsertCharactersFrame;
    private static JButton ReferredLoadJButton;
    private static JButton ReferredSaveJButton;
    private static JButton ReferredSaveAsJButton;
    private static JButton ReferredNewJButton;
    private static JTable ReferredTable;
    private static Color initialJButtonColor;
    private static MyPopupMenu ReferredPopupMenu;
    private static MyMouseListener ReferredMouseListener;
    private static MyPopupMenuItem01Listener ReferredMenuItem01Listener;
    private static MyPopupMenuItem02Listener ReferredMenuItem02Listener;
    private static MyPopupMenuItem03Listener ReferredMenuItem03Listener;
    private static MyPopupMenuItem04Listener ReferredMenuItem04Listener;
    private static MyPopupMenuItem05Listener ReferredMenuItem05Listener;
   
    public static MyDefaultTableModel getReferredTableModel() {
        return ReferredTableModel;
    }

    public static void setReferredTableModel(MyDefaultTableModel ReferredTableModel) {
        FlowManager.ReferredTableModel = ReferredTableModel;
    }

    public static String getAttachedFile() {
        return AttachedFile;
    }

    public static void setAttachedFile(String AttachedFile) {
        FlowManager.AttachedFile = AttachedFile;
    }

//    public static JFrame getReferredFrame() {       
//        return StartGUI.MainScreen_Frame;
//////        return ReferredFrame;
//        
//    }

    

    public static JFrame getReferredInsertCharactersFrame() {
        return ReferredInsertCharactersFrame;
    }

    public static void setReferredInsertCharactersFrame(JFrame ReferredInsertCharactersFrame) {
        FlowManager.ReferredInsertCharactersFrame = ReferredInsertCharactersFrame;
    }

    public static JButton getReferredLoadJButton() {
        return ReferredLoadJButton;
    }

    public static void setReferredLoadJButton(JButton ReferredLoadJButton) {
        FlowManager.ReferredLoadJButton = ReferredLoadJButton;
    }

    public static JButton getReferredSaveJButton() {
        return ReferredSaveJButton;
    }

    public static void setReferredSaveJButton(JButton ReferredSaveJButton) {
        FlowManager.ReferredSaveJButton = ReferredSaveJButton;
    }

    public static JButton getReferredNewJButton() {
        return ReferredNewJButton;
    }

    public static void setReferredNewJButton(JButton ReferredNewJButton) {
        FlowManager.ReferredNewJButton = ReferredNewJButton;
    }

    public static JTable getReferredTable() {
        return ReferredTable;
    }

    public static void setReferredTable(JTable ReferredTable) {
        FlowManager.ReferredTable = ReferredTable;
    }

    public static Color getInitialJButtonColor() {
        return initialJButtonColor;
    }

    public static void setInitialJButtonColor(Color initialJButtonColor) {
        FlowManager.initialJButtonColor = initialJButtonColor;
    }

    public static JButton getReferredSaveAsJButton() {
        return ReferredSaveAsJButton;
    }

    public static void setReferredSaveAsJButton(JButton ReferredSaveAsJButton) {
        FlowManager.ReferredSaveAsJButton = ReferredSaveAsJButton;
    }

    public static MyPopupMenuItem01Listener getReferredMenuItem01Listener() {
        return ReferredMenuItem01Listener;
    }

    public static void setReferredMenuItem01Listener(MyPopupMenuItem01Listener ReferredMenuItem01Listener) {
        FlowManager.ReferredMenuItem01Listener = ReferredMenuItem01Listener;
    }

    public static MyPopupMenuItem02Listener getReferredMenuItem02Listener() {
        return ReferredMenuItem02Listener;
    }

    public static void setReferredMenuItem02Listener(MyPopupMenuItem02Listener ReferredMenuItem02Listener) {
        FlowManager.ReferredMenuItem02Listener = ReferredMenuItem02Listener;
    }

    public static MyPopupMenuItem03Listener getReferredMenuItem03Listener() {
        return ReferredMenuItem03Listener;
    }

    public static void setReferredMenuItem03Listener(MyPopupMenuItem03Listener ReferredMenuItem03Listener) {
        FlowManager.ReferredMenuItem03Listener = ReferredMenuItem03Listener;
    }

    public static MyPopupMenuItem04Listener getReferredMenuItem04Listener() {
        return ReferredMenuItem04Listener;
    }

    public static void setReferredMenuItem04Listener(MyPopupMenuItem04Listener ReferredMenuItem04Listener) {
        FlowManager.ReferredMenuItem04Listener = ReferredMenuItem04Listener;
    }

    /**
     * Show Font of current cell
     * @return 
     */
    public static MyPopupMenuItem05Listener getReferredMenuItem05Listener() {
        return ReferredMenuItem05Listener;
    }
    /**
     * Show Font of current cell
     * @param ReferredMenuItem05Listener
    */
    public static void setReferredMenuItem05Listener(MyPopupMenuItem05Listener ReferredMenuItem05Listener) {
        FlowManager.ReferredMenuItem05Listener = ReferredMenuItem05Listener;
    }
    
    private static String trimLeadingZeros(String source) {
        int length = source.length();

        if (length < 2) {
            return source;
        }

        int i;
        for (i = 0; i < length - 1; i++) {
            char c = source.charAt(i);
            if (c != '0') {
                break;
            }
        }

        if (i == 0) {
            return source;
        }

        return source.substring(i);
    }

    public static boolean safelyResetTableArea() {
        if (!Main.MainScreen.MyDefaultTableModel.tableChanged) {
            return true;
        } else {
            if (FlowManager.userIgnoresTableContainsUnsavedData()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean userIgnoresTableContainsUnsavedData() {

        Object[] options = {"OK", "CANCEL"};
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, "Table contains unsaved data", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0])) {
            return true;
        } else {
            return false;
        }
    }

    public static String SelectFileforOpenDialog() {

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

    public static String SelectFileforSaveDialog() {

        JFileChooser chooser = new JFileChooser();
//        int returnVal = chooser.showSaveDialog(null);
        int returnVal = chooser.showDialog(null, "Save As");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (f.exists()) {
                JOptionPane.showMessageDialog(null, "File already exist in this directory,"
                        + " choose another name",
                        "alert", JOptionPane.ERROR_MESSAGE);
                return null;
            } else {
                return chooser.getSelectedFile().getPath();
            }
        } else {
            return null;
        }
    }

    public static boolean userIgnoresTableDoesNotContainUnsavedData() {

        Object[] options = {"OK", "CANCEL"};
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, "Table does not contain unsaved data", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0])) {
            return true;
        } else {
            return false;
        }
    }

    public static MyPopupMenu getReferredPopupMenu() {
        return ReferredPopupMenu;
    }

    public static void setReferredPopupMenu(MyPopupMenu ReferredPopupMenu) {
        FlowManager.ReferredPopupMenu = ReferredPopupMenu;
    }

    public static MyMouseListener getReferredMouseListener() {
        return ReferredMouseListener;
    }

    public static void setReferredMouseListener(MyMouseListener ReferredMouseListener) {
        FlowManager.ReferredMouseListener = ReferredMouseListener;
    }

    public static MyDefaultByteTableModel getReferredByteTableModel() {
        return ReferredByteTableModel;
    }

    public static void setReferredByteTableModel(MyDefaultByteTableModel ReferredByteTableModel) {
        FlowManager.ReferredByteTableModel = ReferredByteTableModel;
    }

}
