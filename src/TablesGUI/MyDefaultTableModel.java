/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thomas
 */
public class MyDefaultTableModel extends DefaultTableModel {

    private static final int LASTCOLUMN = 6; //initial value of # colums in the tablemodel, now final
    private int columnNumberLastAddedObject = -1;

//
//  Instance Variables
//
    /**
     * Identifies if the table was changed by insert or deleting a row or typing
     * or deleting one or more unicode characters.
     */
    public boolean tableChanged;

//
//  Constructors
//
    public MyDefaultTableModel() {
        super();
        this.addRow(new Object[LASTCOLUMN]);
        this.addColumn("C0");
        this.addColumn("C1");
        this.addColumn("C2");
        this.addColumn("C3");
        this.addColumn("C4");
        this.addColumn("C5");
        this.addTableModelListener(new MyTableModelListener());
        this.tableChanged = false;
    }

    public void PrintMyDefaultTableModel() {
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.getValueAt(i, j));
                System.out.println("i = " + i + " j = " + j + " value = " + sb + " sb.lenght = " + sb.length());
            }
        }
    }

    public static int getLastColumn() {
        return LASTCOLUMN;
    }

    public void setTableChanged() {
        this.tableChanged = true;
        if (FlowManager.getAttachedFile().equals(FlowManager.NO_ATTACHED_FILE)) {
            Main.MainScreen.MySaveJButton.setBackground(Color.orange);
            Main.MainScreen.MySaveAsJButton.setBackground(Color.red);
        } else {
            Main.MainScreen.MySaveJButton.setBackground(Color.red);
            Main.MainScreen.MySaveAsJButton.setBackground(Color.orange);
        }

        Main.MainScreen.displayMessageSouth("Table contains unsaved data");
    }

    public void resetTableChanged() {
        this.tableChanged = false;
        Main.MainScreen.MySaveJButton.setBackground(Main.MainScreen.initialJButtoncolor);
        Main.MainScreen.MySaveAsJButton.setBackground(Main.MainScreen.initialJButtoncolor);
        Main.MainScreen.MyLoadJButton.setBackground(Main.MainScreen.initialJButtoncolor);
        Main.MainScreen.MyNewJButton.setBackground(Main.MainScreen.initialJButtoncolor);
        Main.MainScreen.displayMessageSouth("Table does not contain unsaved data");
    }

    public void initializeTable() {

        for (int j = 0; j < LASTCOLUMN; j++) {   //initialize objects in first row
            this.setValueAt(null, 0, j);
        }

        for (int i = (this.getRowCount() - 1); i > 0; i--) {  // delete all other rows starting at the end 
            this.removeRow(i);                               // the table
        }
        
        columnNumberLastAddedObject = -1;
    }

    public void addObjectEndofTable(Object o) {   //

        columnNumberLastAddedObject++; // initialized at -1 in order not to create a last empty row
                                         // after EOF

        if (columnNumberLastAddedObject == LASTCOLUMN) {
            this.addRow(new Object[LASTCOLUMN]); 
            columnNumberLastAddedObject = 0;
        }
        
        this.setValueAt(o, this.getRowCount() - 1, columnNumberLastAddedObject);

    }
    
    public void insertRow(int row) {
        this.insertRow(row, new Object[LASTCOLUMN]);
    }
    
} // end of class MyDefaultTableModel
