/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class MyJButtonModeActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Main.MainScreen.MyPopupMenu != null) {
            Main.MainScreen.MyPopupMenu.setVisible(false);
        }

        ExecuteBody();
    }

    public void ExecuteBody() {
        
        String[] selectionValues = {"UTF16", "Modified UTF8"};
        
//        if (Main.MainScreen.MyModeJButton.getText().equalsIgnoreCase(selectionValues[0])) {            
//            Main.MainScreen.MyModeJButton.setText(selectionValues[1]);
//        } else {
//             Main.MainScreen.MyModeJButton.setText(selectionValues[0]);
//        }
//        
        String initialSelectionValue ="UTF16";
        
        Component parentComponent = Main.MainScreen.MyModeJButton;
        Object chosenMode = JOptionPane.showInternalInputDialog(parentComponent,
                "Select file mode modified UTF8 or UTF16",
                "Select file mode", JOptionPane.QUESTION_MESSAGE, null, 
                selectionValues, initialSelectionValue);
        Main.MainScreen.MyModeJButton.setText(chosenMode.toString());
        IOManager_Modified_UTF8.Mode = chosenMode.toString();
        
    }
;
}
