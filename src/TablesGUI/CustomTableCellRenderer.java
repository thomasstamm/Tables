/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import Fontmanager.FontCollections;
import UniCode.Conversion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thomas
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        printCellCharacteristics("start", row, column, cell.getFont(),
                cell.getForeground());

        if (value != null) {

            if (value instanceof String || value instanceof StringBuilder) {

                cell.setForeground(Color.MAGENTA);

                String s = value.toString();

                System.out.println("CustomTableCellRenderer: String entered: "
                        + Conversion.HexRepresentationofCharacterString(s));
                System.out.println("CustomTableCellRenderer: String.lenght = "
                        + s.length());
                System.out.println("CustomTableCellRenderer: Number of "
                        + "code points = "
                        + s.codePointCount(0, s.length()));

                ArrayList<Font> result
                        = FontCollections.findSuitableFontsforString(s);

                if (!result.isEmpty()) {

                    Font f = result.get(0);
                    System.out.println("CustomTableCellRenderer: "
                            + "Font Family " + f.getFamily()
                            + " can display "
                            + Conversion.HexRepresentationofCharacterString(s));
                    String fontFamilyName = f.getFamily();
                    Font newFont = new Font(fontFamilyName, Font.PLAIN, 11);
                    cell.setForeground(Color.BLUE);
                    cell.setFont(newFont);
                } else {
                    System.out.println("CustomTableCellRenderer: No fonts"
                            + " found for "
                            + Conversion.HexRepresentationofCharacterString(s));
                }
            } else {
                cell.setBackground(Color.DARK_GRAY);
                System.out.println("CustomTableCellRenderer: Value not =  "
                        + "String or StringBuilder");
            }
        } else {
            System.out.println("CustomTabelCellRenderer: Value =  null");
        }

        printCellCharacteristics("end  ", row, column, cell.getFont(),
                cell.getForeground());

        return cell;

    }

    private void printCellCharacteristics(String position, int row, int column,
            Font currentCellFont, Color currentCellForegroundColor) {
        System.out.println("CustomTableCellRenderer: At " + position + ": "
                + "row = " + row + " column = " + column
                + " Current Cell Font  = " + currentCellFont.getFontName()
                + " Current Cell Foreground Color = " + currentCellForegroundColor);
    }

}
