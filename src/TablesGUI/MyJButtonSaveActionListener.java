/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Thomas
 */
public class MyJButtonSaveActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Main.MainScreen.MyPopupMenu != null) {
            Main.MainScreen.MyPopupMenu.setVisible(false);
        }

        ExecuteBody();
    }

    public void ExecuteBody() {

        String AttachedFile = FlowManager.getAttachedFile();

        if (!AttachedFile.equals(FlowManager.NO_ATTACHED_FILE)) {
            IOManager_Modified_UTF8.SaveFile(AttachedFile);
            Main.MainScreen.MyDefaultTableModel.resetTableChanged();
        } else {
            Main.MainScreen.MySaveAsJButtonActionListener.ExecuteBody();
        }
    }
}
