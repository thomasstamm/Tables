/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author Thomas
 */
/**
 * MyTableModelListener checks if the data in the table was changed. The logic
 * behind this complicated. See the description in the MyKeyListener class.
 * 
 * getType returns 0, 1 or 2, which corresponds to UPDATE, INSERT or DELETS
 *
 * In all cases that this method is called, the TableModel indeed is changed.
 * Therefore the flag TableChanged is always set to TRUE
 * 
 * The printing is only done for debugging purposes
 * 
 */
public class MyTableModelListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
        
//        MyDefaultTableModel MyDefaultTableModel = (MyDefaultTableModel) e.getSource();
        
//        if (e.getType() == TableModelEvent.UPDATE) {
//            System.out.println("MyTableModelListener action is UPDATE " + e.getType());
//        } else if (e.getType() == TableModelEvent.INSERT) {
//            System.out.println("MyTableModelListener action is INSERT " + e.getType());
//        } else if (e.getType() == TableModelEvent.DELETE) {
//            System.out.println("MyTableModelListener action is DELETE " + e.getType());
//        } 
        
//        FlowManager.getReferredTableModel().setTableChanged();
        Main.MainScreen.MyDefaultTableModel.setTableChanged();
    }
}
