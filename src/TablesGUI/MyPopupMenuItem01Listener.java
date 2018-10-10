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
/**
 * Inserts a line in the table above the given row
 */
public class MyPopupMenuItem01Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        executeBody();
        Main.MainScreen.MyPopupMenu.setVisible(false);
    }

    private void executeBody() {

        int rowAtPoint = Main.MainScreen.MyPopupMenu.getRowAtPoint();
//        int columnAtPoint = Main.MainScreen.MyPopupMenu.getColumnAtPoint();
//        System.out.println("Popup menu item 01 occured on row: " + rowAtPoint
//                + " Column: " + columnAtPoint);
        Main.MainScreen.MyDefaultTableModel.insertRow(rowAtPoint);

    }

}
