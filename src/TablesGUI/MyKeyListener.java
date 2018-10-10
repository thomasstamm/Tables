/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import static TablesLaunch.Main.MainScreen;
import UniCode.CodePoint;
import UniCode.Conversion;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;

/**
 * Objective: to check if the user wants to add,insert or delete lines. If the
 * user wants this, the table is actually changed by adding or deleting lines in
 * the underlying tablemodel. Then, apparrantly the changed model is also
 * displayed. How this works it not yet clear to me. MyKeyListener currently
 * only implements adding lines, by pressing the TAB key in the right corner of
 * the table.
 *
 * Combinations of keyPressed, keyTyped and keyReleased cause the TableChanged
 * event and the TableCellUpdated method to be fired. At this moment the only
 * TableChanged event is trapped in the clas MyTableModelListener.
 *
 * The logic is described in the KeyEvent class:
 *
 * Pressing and releasing a key on the keyboard results in the generating the
 * following key events (in order): KEY_PRESSED KEY_TYPED (is only generated if
 * a valid Unicode character could be generated.) KEY_RELEASED The getKeyChar
 * method always returns a valid Unicode character or CHAR_UNDEFINED. Character
 * input is reported by KEY_TYPED events. KEY_PRESSED and KEY_RELEASED events
 * are not necessarily associated with character input. Therefore, the result of
 * the getKeyChar method is guaranteed to be meaningful only for KEY_TYPED
 * events.  *
 * For key pressed and key released events, the getKeyCode method returns the
 * event's keyCode. For key typed events, the getKeyCode method always returns
 * VK_UNDEFINED.
 *
 *
 *
 * Special situation with ESCAPE With ESCAPE you get all 3 signals (keyPressed,
 * keyTyped and keyReleased). Pressing ESCAPE makes that the system is ready to
 * undo the last pressed key(s) (since the last COMMIT) by erasing the last
 * entered keys (since the last commit).It will do this only after pressing a
 * COMMIT key. After that it will cause the JRE to erase the last pressed key(s)
 * since the last COMMIT and call the fireTableChanged method (part of the
 * AbstractTableModel) and the fireTableCellUpdated method (called in the
 * setValueAt method). This will cause the tableChanged method in the
 * TableModelListener to be called.
 *
 * Attention: if ESCAPE is pressed in a cell that contains the NULL value
 * (directly after initialization) it will lead to the erasure of the NULL value
 * and in fact emptying the call. This also causes the tablemodellistener to get
 * fired!
 *
 * When TAB is pressed, you get 3 signals: keyPressed, keyTyped and keyReleased.
 * With some other keys, like for instance F1, you get only keyPressed and
 * keyReleased. With ESCAPE you get keyPressed and keyTyped (which is not
 * logical because the keyTyped event is only generated if a valid Unicode
 * character could be generated.
 *
 * We have to include the keyTyped, keyPressed and keyReleased methods because
 * all three methods are defined in the interface and therefore must be
 * overriden when we implement interface KeyListener.
 *
 * We are only interested in the right-TAB in the last cell. We have to test
 * this in the method keyPressed. The other 2 methods may be ignored in this
 * context. If we do not select only the right-TAB, a new row will always be
 * appended with any key in the last cell, including typing a symbol
 */
class MyKeyListener implements KeyListener {

    /**
     * Invoked when a key has been pressed. See the class description for
     * {@link KeyEvent} for a definition of a key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
   
//        JTable t = FlowManager.getReferredTable();
        JTable t = MainScreen.myStandardTable;
//        MyDefaultTableModel x = FlowManager.getReferredTableModel();
        MyDefaultTableModel x = MainScreen.MyDefaultTableModel;

        System.out.println("MyKeyListener - action keyPressed - " + "getKeyChar: " + e.getKeyChar()
                + " getKeyCode: " + e.getKeyCode() + " getKeyText: " + KeyEvent.getKeyText(e.getKeyCode())
        );

        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            if (t.getSelectedRow() == (x.getRowCount() - 1)) {            // the returend selected row in tabel is one less than rows in underlying model
                if (t.getSelectedColumn() == (x.getColumnCount() - 1)) {  // the returend selected column in table is one less than the columns in underlying model
                    x.addRow(new Object[MyDefaultTableModel.getLastColumn()]); //static constant
                }
            }
        }
    }

    @Override
    // KEY_TYPED: is only generated if a valid Unicode character could be generated.
    // Therefore, the result of the getKeyChar method is guaranteed to be 
    // meaningful only for KEY_TYPED events.
    
    public void keyTyped(KeyEvent e) {
        
        System.out.println("MyKeyListener - action keyTyped - " + "getKeyChar: " + e.getKeyChar()
                + " getKeyCode: " + e.getKeyCode() + " getKeyText: " + KeyEvent.getKeyText(e.getKeyCode())
        );
                
        int codePoint = CodePoint.getUniCodeforCharwithinBMP(e.getKeyChar());
        System.out.println("MyKeyListener - The UDV value is " + codePoint);
        
        System.out.println("MyKeyListener - The HEX representation is " + 
                Conversion.HexRepresentationofCodePoint(codePoint));
        
    }

    /**
     * Invoked when a key has been released. See the class description for
     * {@link KeyEvent} for a definition of a key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //JTable t = (JTable) e.getSource();

        System.out.println("MyKeyListener - action keyReleased - " + "getKeyChar: " + e.getKeyChar()
                + " getKeyCode: " + e.getKeyCode() + " getKeyText: " + KeyEvent.getKeyText(e.getKeyCode())
        );
    }

} /* end of class MyLeyListener */
