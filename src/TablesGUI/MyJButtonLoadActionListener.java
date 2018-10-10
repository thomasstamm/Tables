/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

/**
 *
 * @author Thomas
 */
public class MyJButtonLoadActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (Main.MainScreen.MyPopupMenu != null) {
            Main.MainScreen.MyPopupMenu.setVisible(false);
        }
        
        if (FlowManager.safelyResetTableArea()) {
            ExecuteBody();
        }
    }

    private void ExecuteBody() {

        String SelectedFile = SelectFileforOpenDialog();
        

        if (SelectedFile != null) { //check if user did not choose Cancel or Terminate the open file dialog
            IOManager_Modified_UTF8.LoadFile(SelectedFile);
            FlowManager.setAttachedFile(SelectedFile);
            Main.MainScreen.Frame.setTitle(SelectedFile);
            Main.MainScreen.MyDefaultTableModel.resetTableChanged();
        }
    }
    
    public String SelectFileforOpenDialog() {

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

}
