/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thomas
 */
public class MyDefaultByteTableModel extends DefaultTableModel {

    private static final int lastColumn = 7;

    MyDefaultByteTableModel() {
        super();
//        this.addRow(new Object[lastColumn]); // loop in 03 listener starts with adding a roww
        this.addColumn("Element");
        this.addColumn("Byte");
        this.addColumn("Bit pattern");
        this.addColumn("Decimal Value");
        this.addColumn("Unsigned Decimal Value");
        this.addColumn("Hex value");
        this.addColumn("Unicode character");
    }

    public void addRowforNewByte() {
        this.addRow(new Object[lastColumn]);
    }

}
