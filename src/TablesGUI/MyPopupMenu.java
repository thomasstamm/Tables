/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Thomas
 */
public class MyPopupMenu extends JPopupMenu {

    private int rowAtPoint;
    private int columnAtPoint;

    public MyPopupMenu() {
        this.columnAtPoint = 0;
        this.rowAtPoint = 0;

        createPopup();

    }

    private void createPopup() {
        JMenuItem menuItem01 = this.add(new JMenuItem("Insert Line Above"));
        menuItem01.addActionListener(new MyPopupMenuItem01Listener());

        JMenuItem menuItem02 = this.add(new JMenuItem("Display Unicode HEX value"));
        menuItem02.addActionListener(new MyPopupMenuItem02Listener());
        
        JMenuItem menuItem03 = this.add(new JMenuItem("Analyze File Stream"));
        menuItem03.addActionListener(new MyPopupMenuItem03Listener());
        
        JMenuItem menuItem04 = this.add(new JMenuItem("Insert Unicode HEX value"));
        menuItem04.addActionListener(new MyPopupMenuItem04Listener());
        
        JMenuItem menuItem05 = this.add(new JMenuItem("Display font"));
        menuItem05.addActionListener(new MyPopupMenuItem05Listener());
 
    }

    public int getRowAtPoint() {
        return rowAtPoint;
    }

    public void setRowAtPoint(int rowAtPoint) {
        this.rowAtPoint = rowAtPoint;
    }

    public int getColumnAtPoint() {
        return columnAtPoint;
    }

    public void setColumnAtPoint(int columnAtPoint) {
        this.columnAtPoint = columnAtPoint;
    }

}
