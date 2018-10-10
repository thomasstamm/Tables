/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import Fontmanager.FontCollections;
import TablesLaunch.Main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import UniCode.CodePoint;
import UniCode.Conversion;
import static UniCode.Conversion.ConvertUniCode_intToString;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 *
 */
public class MyPopupMenuItem04Listener implements ActionListener {

//    private static final String NEWLINE = System.getProperty("line.separator");
    JTextArea textAreaNorth;
    JTextArea textAreaSouthCodePointDisplay;
    JTextArea textAreaSouthFontDisplay;
    JTextArea textAreaSouthHexDisplay;
    
    JTextField textField;
    JFrame InsertCharactersFrame;
    int rowAtPoint;
    int columnAtPoint;

    public MyPopupMenuItem04Listener() {
        textAreaNorth = new JTextArea(3, 20);
        textAreaSouthCodePointDisplay = new JTextArea(1, 20);
        textAreaSouthFontDisplay = new JTextArea(1, 20);
        textAreaSouthHexDisplay = new JTextArea(1, 20);
        textField = new JTextField(6);
        createAndShowInsertCharactersWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        executeBody();
        Main.MainScreen.MyPopupMenu.setVisible(false);
        // user can close InsertCharactersWindow by using close button
        // otherwise it will be closed by left mouse click outside the window in MyMouseListener
        //frame.dispatchEvent(new WindowEvent(InsertCharactersFrame, WindowEvent.WINDOW_CLOSING));
    }

    private void executeBody() {

        /*
         each time the user selects MyPopupMenu04, the input fields are cleared
         */
        this.textAreaNorth.setText(null);
        this.textAreaSouthCodePointDisplay.setText(null);
        this.textAreaSouthFontDisplay.setText(null);
        this.textAreaSouthHexDisplay.setText(null);
        this.textField.setText(null);
        
        rowAtPoint = Main.MainScreen.MyPopupMenu.getRowAtPoint();
        columnAtPoint = Main.MainScreen.MyPopupMenu.getColumnAtPoint();
        InsertCharactersFrame.setTitle("Insert Character at row " + rowAtPoint
                + " and column " + columnAtPoint);
        InsertCharactersFrame.setVisible(true);

    }

    private void createAndShowInsertCharactersWindow() {

        InsertCharactersFrame = new JFrame("Insert Unicode HEX value");

        int inset = 200;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
       
        InsertCharactersFrame.setBounds(inset, inset, width - inset * 2, height - inset * 2);
        StandardPanel contentPane = new StandardPanel(width - inset * 2, height - inset * 2);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        InsertCharactersFrame.setContentPane(contentPane);
        
        int southPanelWidth = width - inset * 3;
        int southPanelHeight = height - inset * 3;
        int halfwidth = southPanelWidth / 2;

        StandardPanel northPanel = new StandardPanel(width - inset * 3, height - inset * 3);
        StandardPanel southPanel = new StandardPanel(southPanelWidth, southPanelHeight);  
        
        StandardPanel southWestPanel = new StandardPanel(halfwidth-50, southPanelHeight - 80);
        StandardPanel southEastPanel = new StandardPanel(halfwidth-50, southPanelHeight - 80);
        northPanel.add(textAreaNorth);
  
        southWestPanel.setLayout(new BoxLayout(southWestPanel, BoxLayout.Y_AXIS));
        southWestPanel.add(textAreaSouthCodePointDisplay);
        southWestPanel.add(textAreaSouthFontDisplay);
        southWestPanel.add(textAreaSouthHexDisplay);
        southEastPanel.add(textField);
        textField.addActionListener(new ActionListenerInsertCharacters());
        
        textAreaNorth.setEditable(false);
        textAreaSouthCodePointDisplay.setEditable(false);
        textAreaSouthFontDisplay.setEditable(false);
        textAreaSouthHexDisplay.setEditable(false);
        
        contentPane.add(northPanel);
        contentPane.add(southPanel);
        southPanel.add(southWestPanel);
        southPanel.add(southEastPanel);
        
        InsertCharactersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InsertCharactersFrame.setVisible(false);

    }

    private class ActionListenerInsertCharacters implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int rowAtPoint = Main.MainScreen.MyPopupMenu.getRowAtPoint();
            int columnAtPoint = Main.MainScreen.MyPopupMenu.getColumnAtPoint();

            System.out.println("********* ActionListener of MyPopupMenu04 "
                    + "START ****************");

            System.out.println("ActionListenerInsertCharacter actionPerformed in textField "
                    + " row = " + rowAtPoint + " column = " + columnAtPoint + " "
                    + e.paramString());

            String text = textField.getText(); //input character in hex format

            if (!checkWellFormednessInputHexValueUnicode(text)) {
                return;
            }

            int codePoint = InputHexTextStringtoUDV(text); // input character in decimal value

            if (!CodePoint.isValidCodePoint(codePoint)) {
                System.out.println(" Text " + text + " = not valid Unicode point");
                JOptionPane.showMessageDialog(null,
                        text + "is not valid Unicode point", "Insert Unicode HEX value",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!CodePoint.isDefinedCharacter(codePoint)) {
                /*test with Hex 001779/Dec 6009*/
                System.out.println(" Text " + text + " = not defined Unicode character");
                JOptionPane.showMessageDialog(null, text + " is not "
                        + "defined Unicode character", "Insert Unicode HEX value",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String s = ConvertUniCode_intToString(codePoint);

            String s1 = textAreaNorth.getText();
            String s2 = s1 + s; //new string: old string + entered character
            Font f, newFont;
            String newFontFamilyName;
            ArrayList<Font> result;

            System.out.println("MyPopupMenuItem04Listener: "
                    + "String s2 = "
                    + Conversion.HexRepresentationofCharacterString(s2));

            /**
             * Test the situation that the last entered character does not have
             * a valid font. The user has the option to ignore this. A good way
             * to manage this situation is still to be defined.
             */
            result = FontCollections.findSuitableFontsforString(s);

            if (result.isEmpty()) {
                System.out.println("MyPopupMenuItem04Listener: No fonts"
                        + " found for "
                        + Conversion.HexRepresentationofCharacterString(s));
                if (userIgnoresNoFontforCharacter()) {
                    System.out.println("MyPopupMenuItem04Listener: User ignores"
                            + " that no fonts were found for "
                            + Conversion.HexRepresentationofCharacterString(s));
                } else {
                    return;
                }
            }

            /**
             * Test the situation that the last entered character does not has
             * no common supporting font with the whole group. This may be also
             * be ignored by the user. This will be certainly the case if there
             * is no font at all for this character
             */
            result = FontCollections.findSuitableFontsforString(s2);

            if (result.isEmpty()) {
                System.out.println("MyPopupMenuItem04Listener: No fonts"
                        + " found for "
                        + Conversion.HexRepresentationofCharacterString(s2));
                if (userIgnoresNoFontforGroup()) {
                    System.out.println("MyPopupMenuItem04Listener: User ignores"
                            + " that no fonts were found for group "
                            + Conversion.HexRepresentationofCharacterString(s2));
                    newFontFamilyName = "No common font for string"; //user ignores there is no font for group
                    newFont = null;
                } else {
                    return;
                }
            } else {
                f = result.get(0);
                newFontFamilyName = f.getFamily();
                newFont = new Font(newFontFamilyName, Font.PLAIN, 11);
                System.out.println("MyPopupMenuItem04Listener: "
                        + "Font Family " + newFontFamilyName
                        + " can display "
                        + Conversion.HexRepresentationofCharacterString(s2));
            }

            textAreaSouthCodePointDisplay.setText(null);
            textAreaSouthCodePointDisplay.setText("Code Point = ");
            textAreaSouthCodePointDisplay.append(((Integer) codePoint).toString());

            textAreaSouthFontDisplay.setText(null);
            textAreaSouthFontDisplay.append("Applied font = " + newFontFamilyName);
            textAreaSouthHexDisplay.setText(null);
            textAreaSouthHexDisplay.append("Hex representation character "
                    + "= " + Conversion.HexRepresentationofCharacterString(s));

            textAreaNorth.setFont(newFont);
            textAreaNorth.append(s);

            Object valueAt = Main.MainScreen.MyDefaultTableModel.
                    getValueAt(rowAtPoint, columnAtPoint);
            StringBuilder sb;
            if (valueAt != null) {
                sb = new StringBuilder(valueAt.toString());
            } else {
                sb = new StringBuilder();
            }
            String t = sb.append(s).toString();
            Main.MainScreen.MyDefaultTableModel.setValueAt(t, rowAtPoint, columnAtPoint);
            textField.setText(null); //clear the field
        }
    }

    private boolean checkWellFormednessInputHexValueUnicode(String text) {

        if (text == null) {
            JOptionPane.showMessageDialog(null, "Empty input", "Insert Unicode HEX value",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty input", "Insert Unicode HEX value",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (text.length() != 6) {
            JOptionPane.showMessageDialog(null, "Text lenght not equal to 6",
                    "Insert Unicode HEX value",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        char[] t = text.toCharArray();
        boolean correctInput = true;

        for (int i = 0; i < text.length(); i++) {
            if (t[i] >= 0x0030 && t[i] <= 0x0039) { // value between 0 and 9
                continue;
            }
            if (t[i] >= 0x0041 && t[i] <= 0x0046) { // value between A and F
                continue;
            }
            if (t[i] >= 0x0061 && t[i] <= 0x0066) { // value between a and f
                continue;
            }
            JOptionPane.showMessageDialog(null, "Input not valid HEX character", "Insert Unicode HEX value",
                    JOptionPane.ERROR_MESSAGE);
            correctInput = false;
            break;                                  // returns control to first statement after loop
        }
        return correctInput;
    }

    private Integer InputHexTextStringtoUDV(String text) {
        double unsignedDecimalValue = 0;
        char[] t = text.toCharArray();
        for (int i = 5; i >= 0; i--) {
            double power = (i - 5) * -1;
            if ((t[i] == 0x0046) || (t[i] == 0x0066)) { // f = 15
                double base = 15;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0045) || (t[i] == 0x0065)) { //e = 14
                double base = 14;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0044) || (t[i] == 0x0064)) { //d = 13
                double base = 13;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0043) || (t[i] == 0x0063)) { //c = 12
                double base = 12;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0042) || (t[i] == 0x0062)) { //b = 11
                double base = 11;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0041) || (t[i] == 0x0061)) { //a = 10
                double base = 10;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0039)) { // = 9
                double base = 9;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0038)) { // = 8
                double base = 8;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0037)) { // = 7
                double base = 7;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0036)) { // = 6
                double base = 6;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0035)) { // = 5
                double base = 5;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0034)) { // = 4
                double base = 4;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0033)) { // = 3
                double base = 3;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0032)) { // = 2
                double base = 2;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0031)) { // = 1
                double base = 1;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
                continue;
            }
            if ((t[i] == 0x0030)) { // = 0
                double base = 0;
                unsignedDecimalValue = unsignedDecimalValue + calculateUnsignedDecimalValue(base, power);
            }
        }
        Double d = unsignedDecimalValue;
        return d.intValue();
    }

    private double calculateUnsignedDecimalValue(double base, double power) {
        return base * Math.pow(16, power);
    }

    private boolean userIgnoresNoFontforCharacter() {

        Object[] options = {"OK", "CANCEL"};
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null,
                "Proceed anyway or cancel?",
                "Warning: No suitable font for character",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0])) {
            return true;
        } else {
            return false;
        }
    }

    private boolean userIgnoresNoFontforGroup() {

        Object[] options = {"OK", "CANCEL"};
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null,
                "Proceed anyway or cancel?",
                "Warning: No suitable font for this string",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0])) {
            return true;
        } else {
            return false;
        }
    }
}
