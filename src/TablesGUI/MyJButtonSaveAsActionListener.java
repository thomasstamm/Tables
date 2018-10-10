/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class MyJButtonSaveAsActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Main.MainScreen.MyPopupMenu != null) {
            Main.MainScreen.MyPopupMenu.setVisible(false);
        }

        ExecuteBody();  // The only relevant check is on double file names, part of SeclectFileforSaveDialog
    }

    public void ExecuteBody() {

        String SelectedFile = SelectFileforSaveDialog();

        if (SelectedFile != null) { //check Cancel, Terminate or Duplicte File name in the save file dialog
            IOManager_Modified_UTF8.SaveFile(SelectedFile);
            Main.MainScreen.MyDefaultTableModel.resetTableChanged();
            FlowManager.setAttachedFile(SelectedFile);
            Main.MainScreen.Frame.setTitle(SelectedFile);
        }
    }
    
    public String SelectFileforSaveDialog() {

        JFileChooser chooser = new JFileChooser();
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

}
