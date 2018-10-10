/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import Fontmanager.FontCollections;
import TablesLaunch.Main;
import UniCode.Conversion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Thomas
 */
/**
 * Displays the hex values of the contents at the given point in the tabel.
 * Under construction
 */
public class MyPopupMenuItem05Listener implements ActionListener {

    //
    //  Instance Variables
    //
    JFrame DisplayHexValuesFrame;
    JTextArea textAreaNorth;
    JTextArea textAreaSouth;
    int rowAtPoint;
    int columnAtPoint;

    private static final String NEWLINE = System.getProperty("line.separator");

    public MyPopupMenuItem05Listener() {
        CreateAndShowDisplayHexValuesFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        executeBody();
        Main.MainScreen.MyPopupMenu.setVisible(false);
    }

    private void executeBody() {

        rowAtPoint = Main.MainScreen.MyPopupMenu.getRowAtPoint();
        columnAtPoint = Main.MainScreen.MyPopupMenu.getColumnAtPoint();

        this.textAreaNorth.setText(null);
        this.textAreaSouth.setText(null);

        DisplayHexValuesFrame.setTitle("Display Hex value of row: " + rowAtPoint
                + " and column " + columnAtPoint);
        DisplayHexValuesFrame.setVisible(true);

        if (Main.MainScreen.MyDefaultTableModel.getValueAt(rowAtPoint, columnAtPoint) != null) {
            String s = Main.MainScreen.MyDefaultTableModel.getValueAt(rowAtPoint, columnAtPoint).toString();
            if (s.length() > 0) {

                int codePoint = 0;
                ArrayList<Font> result = FontCollections.findSuitableFontsforString(s);

                if (result.isEmpty()) {
                    System.out.println("MyPopupMenuItem04Listener: No fonts"
                            + " found for "
                            + Conversion.HexRepresentationofCharacterString(s));
                } else {
                    Font f = result.get(0);
                    String newFontFamilyName = f.getFamily();
                    Font newFont = new Font(newFontFamilyName, Font.PLAIN, 11);
                    textAreaNorth.setBackground(Color.WHITE);
                    textAreaNorth.setForeground(Color.BLUE);
                    textAreaNorth.setFont(newFont);
                }

                textAreaNorth.insert(s, 0);
                textAreaNorth.insert(NEWLINE, 0);
                textAreaSouth.insert(Conversion.HexRepresentationofCharacterString(s), 0);
                textAreaSouth.insert(NEWLINE, 0);
            } else {
                textAreaNorth.insert("Lenght of element = 0", 0);
                textAreaNorth.insert(NEWLINE, 0);
                textAreaSouth.insert("Lenght of element = 0", 0);
                textAreaSouth.insert(NEWLINE, 0);
            }
        } else {
            textAreaNorth.insert("Empty cell", 0);
            textAreaNorth.insert(NEWLINE, 0);
            textAreaSouth.insert("Empty cell", 0);
            textAreaSouth.insert(NEWLINE, 0);
        }

    }

    private void CreateAndShowDisplayHexValuesFrame() {
        textAreaNorth = new JTextArea(3, 20);
        textAreaSouth = new JTextArea(3, 20);

        DisplayHexValuesFrame = new JFrame("Display font");

        int inset = 200;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        DisplayHexValuesFrame.setBounds(inset, inset, width - inset * 2, height - inset * 2);
        StandardPanel contentPane = new StandardPanel(width - inset * 2, height - inset * 2);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        DisplayHexValuesFrame.setContentPane(contentPane);

        StandardPanel northPanel = new StandardPanel(width - inset * 3, height - inset * 3);
        StandardPanel southPanel = new StandardPanel(width - inset * 3, height - inset * 3);
        northPanel.add(textAreaNorth);
        southPanel.add(textAreaSouth);
        contentPane.add(northPanel);
        contentPane.add(southPanel);

        DisplayHexValuesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DisplayHexValuesFrame.setVisible(false);
    }
}
