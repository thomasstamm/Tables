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
public class MyJButtonNewActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
//         if (FlowManager.getReferredPopupMenu() != null) {
//                    FlowManager.getReferredPopupMenu().setVisible(false);
//                }
         
         if (Main.MainScreen.MyPopupMenu != null) {
            Main.MainScreen.MyPopupMenu.setVisible(false);
        }

        if (FlowManager.safelyResetTableArea()) {
            ExecuteBody();
        }
    }

    private static void ExecuteBody() {

        Main.MainScreen.MyDefaultTableModel.initializeTable();
        FlowManager.setAttachedFile(FlowManager.NO_ATTACHED_FILE);
        Main.MainScreen.Frame.setTitle(Screen.NO_ATTACHED_FILE);
        Main.MainScreen.MyDefaultTableModel.resetTableChanged();
        
        

    }

}
