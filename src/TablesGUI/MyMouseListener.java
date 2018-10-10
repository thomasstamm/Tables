/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class MyMouseListener implements MouseListener {

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (e.getButton()) {
            case MouseEvent.BUTTON1: //set popup invisible regardsless if source was table or frame

                if (Main.MainScreen.MyPopupMenu != null) {
                    Main.MainScreen.MyPopupMenu.setVisible(false);
                }

                if (FlowManager.getReferredInsertCharactersFrame() != null) {
                    FlowManager.getReferredInsertCharactersFrame().setVisible(false);
                }

                viewActiveFrames();
                break;
            case MouseEvent.BUTTON2:
                Logger.getLogger(MyMouseListener.class.getName()).log(Level.SEVERE, null, "Button 2 "
                        + "not implemented");
                break;
            case MouseEvent.BUTTON3:
                String s = e.getSource().toString().substring(12, 18);

                if (s.compareTo("JTable") == 0) { //set popup visible only when source was table
                    createAndShowPopup(e);
                }
                //close any open windows for MyPopMenuItem04listener

                break;
            default:
                Logger.getLogger(MyMouseListener.class.getName()).log(Level.SEVERE, null, "Button "
                        + "not implemented");
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    private void createAndShowPopup(MouseEvent e) {

        int row = Main.MainScreen.myStandardTable.rowAtPoint(e.getPoint());
        int col = Main.MainScreen.myStandardTable.columnAtPoint(e.getPoint());
        int rowHeight = Main.MainScreen.myStandardTable.getRowHeight();

        int indentY = e.getYOnScreen() - 3 * rowHeight;
        if (indentY < 100) {
            System.out.println("indentY limited to 100");
            indentY = 100;
        }
       
        Main.MainScreen.MyPopupMenu.setRowAtPoint(row);
        Main.MainScreen.MyPopupMenu.setColumnAtPoint(col);
        Main.MainScreen.MyPopupMenu.setLocation(e.getXOnScreen(), indentY);
        Main.MainScreen.MyPopupMenu.pack();
        Main.MainScreen.MyPopupMenu.setVisible(true);

    }

    private void viewActiveFrames() {
        System.out.println("MyMouseListener - viewActiveFrames");
        Frame CurrentFrames[] = Frame.getFrames();
        for (Frame f : CurrentFrames) {
            System.out.println(f);
        }
    }

}
