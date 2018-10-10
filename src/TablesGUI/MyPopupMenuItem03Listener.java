/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import TablesLaunch.Main;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Thomas
 */
/**
 * Analyzes on byte level the attached file, in progress
 */
public class MyPopupMenuItem03Listener implements ActionListener {

    /**
     * Instance variables
     */
    MyDefaultByteTableModel MyDefaultByteTableModel;

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        executeBody();
        Main.MainScreen.MyPopupMenu.setVisible(false);
    }

    private void executeBody() {

        if (FlowManager.getAttachedFile().equals(FlowManager.NO_ATTACHED_FILE)) {
            JOptionPane.showMessageDialog(null, "No file attached", "Analyze File Stream",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            createAndShowAnalyzeFileStreamWindow();
            displayFileStream();
        }
    }

    private void createAndShowAnalyzeFileStreamWindow() {

        JFrame frame = new JFrame("Analyze stream of file " + FlowManager.getAttachedFile());

        int inset = 200;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        frame.setBounds(inset, inset, width - inset * 2, height - inset * 2);
        StandardPanel contentPane = new StandardPanel(width - inset * 2, height - inset * 2);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        frame.setContentPane(contentPane);

        MyDefaultByteTableModel = new MyDefaultByteTableModel();
        JTable myStandardTable = new JTable(MyDefaultByteTableModel);

        JScrollPane scrollPane = new JScrollPane(myStandardTable);
        contentPane.add(myStandardTable.getTableHeader());
        contentPane.add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    private void displayFileStream() {

        FileInputStream in = null;

        try {

            in = new FileInputStream(FlowManager.getAttachedFile());

            byte inputArea[] = new byte[1000];
            int bytecount = 0;
            int charcount = 0;
            int j = 0;

            while ((j = in.read(inputArea, bytecount, 1)) != -1) {

                byte currentbyte = inputArea[bytecount];
                MyDefaultByteTableModel.addRowforNewByte();
                displayCommonFieldsofCurrentByte(currentbyte, bytecount);

                switch (typeOfByte(currentbyte)) {
                    case "isNewCharacterByte": {
                        charcount++;
                        displayExtraFieldsofNewCharacterByte(charcount, bytecount);

                        // read next byte = length byte
                        // test on correctness - starts with 0 - to be implemented
                        // not to be distinguished from ASCI byte, therfore read next in the loop
                        bytecount++;
                        MyDefaultByteTableModel.addRowforNewByte();
                        if ((j = in.read(inputArea, bytecount, 1)) != -1) {
                            currentbyte = inputArea[bytecount];
                            displayCommonFieldsofCurrentByte(currentbyte, bytecount);
                            displayExtraFieldsofLenghtByte(currentbyte, bytecount);
                        } else {
                            Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                                    .log(Level.SEVERE, null, "Input file invalid UTF8 format");
                        }
                        break;
                    }
                    case "is1ByteASCICodePoint": {
                        displayExtraFieldsofASCIByte(currentbyte, bytecount);
                        break;
                    }
                    case "is2BytesNONASCICodePoint": {
//                        displayByte1of2ByteNonASCICharacter(currentbyte, bytecount);
                        byte firstbyte = currentbyte;
                        int bytecount_firstbyte = bytecount;

                        // read next byte = follow on byte
                        // test on correctness - starts with 10 - to be implemented
                        // byte 1 and byte 2 of 2bytes code point must be interpreted 
                        // together, therefore read next in the loop   
                        bytecount++;
                        MyDefaultByteTableModel.addRowforNewByte();
                        if ((j = in.read(inputArea, bytecount, 1)) != -1) {
                            currentbyte = inputArea[bytecount];
                            displayCommonFieldsofCurrentByte(currentbyte, bytecount);
                            byte secondbyte = currentbyte;
                            int unsignedDecimalValue11Bits = unsignedDecimalVauleOf11bits(firstbyte, secondbyte);
                            displayExtraFieldsOf2BytesCodePoint(bytecount_firstbyte,
                                    unsignedDecimalValue11Bits);
                        } else {
                            Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                                    .log(Level.SEVERE, null, "Input file invalid UTF8 format");
                        }
                        break;
                    }
                    case "is3BytesNONASCICodePoint": {
                        // to be implemented
                        byte firstbyte = currentbyte;
                        byte secondbyte = 0;
                        byte thirdbyte = 0;
                        int bytecount_firstbyte = bytecount;

                        // read next byte = follow on byte
                        // test on correctness - starts with 10 - to be implemented
                        // byte 1 and byte 2 of 2bytes code point must be interpreted 
                        // together, therefore read next in the loop 
                        bytecount++;
                        MyDefaultByteTableModel.addRowforNewByte();
                        if ((j = in.read(inputArea, bytecount, 1)) != -1) {
                            currentbyte = inputArea[bytecount];
                            displayCommonFieldsofCurrentByte(currentbyte, bytecount);
                            secondbyte = currentbyte;
                        } else {
                            Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                                    .log(Level.SEVERE, null, "Input file invalid UTF8 format");
                        }

                        // read next byte = follow on byte
                        // test on correctness - starts with 10 - to be implemented
                        // byte 1 and byte 2 and byte 3 of 3bytes code point must be interpreted 
                        // together, therefore read next in the loop 
                        bytecount++;
                        MyDefaultByteTableModel.addRowforNewByte();
                        if ((j = in.read(inputArea, bytecount, 1)) != -1) {
                            currentbyte = inputArea[bytecount];
                            displayCommonFieldsofCurrentByte(currentbyte, bytecount);
                            thirdbyte = currentbyte;
                            int unsignedDecimalValue16Bits = unsignedDecimalVauleOf16bits(firstbyte,
                                    secondbyte, thirdbyte);
                            displayExtraFieldsOf3BytesCodePoint(bytecount_firstbyte,
                                    unsignedDecimalValue16Bits);
                        } else {
                            Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                                    .log(Level.SEVERE, null, "Input file invalid UTF8 format");
                        }

                        break;
                    }
                    case "is4BytesNonASCICodePoint": {
                        // to be implemented
                        System.out.println("4bytesNONASCICodePoint");
                        break;
                    }
                    case "isFollowOnByte": { // followon bytes are read via the read next in the loop
                        Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                                .log(Level.SEVERE, null, "Input file invalid UTF8 format");
                        break;
                    }
                    case "isInvalidUTF8Format": {
                        // to be implemented
                        break;
                    }
                    default: {
                        // to be implemented
                        break;
                    }

                } //eof switch

                if (bytecount < 1000) {
                    bytecount++;
                } else {
                    Logger.getLogger(MyPopupMenuItem03Listener.class.getName())
                            .log(Level.SEVERE, null, "Input file > 1000 bytes");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyPopupMenuItem03Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            Logger.getLogger(MyPopupMenuItem03Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(MyPopupMenuItem03Listener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {  // evading derefencing possible null pointer
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MyPopupMenuItem03Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private int unsignedDecimalValueofByte(byte b) {

        int unsignedDecimalValue = 0;

        if ((b & 0b10000000) == 0b10000000) { //test if the highest bit = 1, meaning a negative value
            unsignedDecimalValue = unsignedDecimalValue + 128;
        }
        if ((b & 0b01000000) == 0b01000000) { //test if bit 2 = 1
            unsignedDecimalValue = unsignedDecimalValue + 64;
        }
        if ((b & 0b00100000) == 0b00100000) { //test if bit 3 = 1
            unsignedDecimalValue = unsignedDecimalValue + 32;
        }
        if ((b & 0b00010000) == 0b00010000) { //test if bit 4 = 1
            unsignedDecimalValue = unsignedDecimalValue + 16;
        }
        if ((b & 0b00001000) == 0b00001000) { //test if bit 5 = 1
            unsignedDecimalValue = unsignedDecimalValue + 8;
        }
        if ((b & 0b00000100) == 0b00000100) { //test if bit 6 = 1
            unsignedDecimalValue = unsignedDecimalValue + 4;
        }
        if ((b & 0b00000010) == 0b00000010) { //test if bit 7 = 1
            unsignedDecimalValue = unsignedDecimalValue + 2;
        }
        if ((b & 0b00000001) == 0b00000001) { //test if bit 8 = 1       
            unsignedDecimalValue = unsignedDecimalValue + 1;
        }

        return unsignedDecimalValue;

    }

    private String bitPatternofByte(byte b) {

        String s;

        if ((b & 0b10000000) == 0b10000000) { //test if the highest bit = 1, meaning a negative value
            s = "1";
        } else {
            s = "0";
        }
        if ((b & 0b01000000) == 0b01000000) { //test if bit 2 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00100000) == 0b00100000) { //test if bit 3 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00010000) == 0b00010000) { //test if bit 4 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00001000) == 0b00001000) { //test if bit 5 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00000100) == 0b00000100) { //test if bit 6 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00000010) == 0b00000010) { //test if bit 7 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }
        if ((b & 0b00000001) == 0b00000001) { //test if bit 8 = 1
            s = s.concat("1");
        } else {
            s = s.concat("0");
        }

        return s;

    }

    private boolean isASCICharacter(byte b) {
        if ((b & 0b10000000) == 0b10000000) { //test if the highest bit = 1: not an ASCI sign
            return false;
        } else {
            return true;
        }
    }

    private boolean is2BytesNonASCICodePoint(byte b) { //first byte starts with 110, following byte start with 10
        if ((b & 0b10000000) == 0b10000000) {
            if ((b & 0b01000000) == 0b01000000) {
                if ((b & 0b00100000) == 0b00100000) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean is3BytesNonASCICodePoint(byte b) { //first byte starts with 1110
        if ((b & 0b10000000) == 0b10000000) {
            if ((b & 0b01000000) == 0b01000000) {
                if ((b & 0b00100000) == 0b00100000) {
                    if ((b & 0b00010000) == 0b00010000) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean is4BytesNonASCICodePoint(byte b) { //first byte starts with 11110
        if ((b & 0b10000000) == 0b10000000) {
            if ((b & 0b01000000) == 0b01000000) {
                if ((b & 0b00100000) == 0b00100000) {
                    if ((b & 0b00010000) == 0b00010000) {
                        if ((b & 0b00001000) == 0b00001000) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isFollowOnCharacter(byte b) {
        if ((b & 0b10000000) == 0b10000000) { //byte starts with 10
            if ((b & 0b01000000) == 0b01000000) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private String typeOfByte(byte b) {
        if ((b & 0b10000000) == 0b10000000) {                    // = 1.......
            if ((b & 0b01000000) == 0b01000000) {                // = 11......
                if ((b & 0b00100000) == 0b00100000) {
                    if ((b & 0b00010000) == 0b00010000) {
                        if ((b & 0b00001000) == 0b00001000) {
                            return "isInvalidUTF8Format";        // = 11111...
                        } else {
                            return "is4BytesNonASCICodePoint";   // = 11110...
                        }
                    } else {                                     // = 1110....
                        return "is3BytesNONASCICodePoint";
                    }
                } else {                                          // = 110.....
                    return "is2BytesNONASCICodePoint";
                }
            } else {                                              // = 10......
                return "isFollowOnByte";
            }
        } else // = 0.......
        {
            if (b == 0) {
                return "isNewCharacterByte";                      // = 00000000
            } else {
                return "is1ByteASCICodePoint";                    // = 0.......
            }
        }

    }

    private void displayCommonFieldsofCurrentByte(byte currentbyte, int bufcount) {
        MyDefaultByteTableModel.setValueAt(bufcount, bufcount, 1);
        MyDefaultByteTableModel.setValueAt(bitPatternofByte(currentbyte), bufcount, 2);
        MyDefaultByteTableModel.setValueAt(currentbyte, bufcount, 3);
    }

    private void displayExtraFieldsofNewCharacterByte(int charcount, int bufcount) {
        MyDefaultByteTableModel.setValueAt(charcount, bufcount, 0);
        MyDefaultByteTableModel.setValueAt("Startbyte", bufcount, 6);
    }

    private void displayExtraFieldsofLenghtByte(byte currentbyte, int bufcount) {
        MyDefaultByteTableModel.setValueAt(unsignedDecimalValueofByte(currentbyte), bufcount, 4);
        MyDefaultByteTableModel.setValueAt("Number of bytes", bufcount, 6);
    }

    private void displayExtraFieldsofASCIByte(byte currentbyte, int bufcount) {
        MyDefaultByteTableModel.setValueAt(currentbyte, bufcount, 3);
        MyDefaultByteTableModel.setValueAt(unsignedDecimalValueofByte(currentbyte), bufcount, 4);
        MyDefaultByteTableModel.setValueAt(Integer.toHexString(unsignedDecimalValueofByte(currentbyte)), bufcount, 5);
        MyDefaultByteTableModel.setValueAt((char) unsignedDecimalValueofByte(currentbyte), bufcount, 6);
    }

    private void displayExtraFieldsOf2BytesCodePoint(int bytecount_firstbyte, int unsignedDecimalValue11Bits) {
        String hex_value = Integer.toHexString(unsignedDecimalValue11Bits);
        MyDefaultByteTableModel.setValueAt(unsignedDecimalValue11Bits, bytecount_firstbyte, 4);
        MyDefaultByteTableModel.setValueAt(hex_value, bytecount_firstbyte, 5);
        MyDefaultByteTableModel.setValueAt((char) unsignedDecimalValue11Bits, bytecount_firstbyte, 6);
//        char c = (char) unsignedDecimalValue11Bits;
//        String s = String.valueOf(c);
//        JLabel b = new JLabel(s);
//        b.setFont(new Font("Arial", Font.PLAIN, 14));
//        b.setLocale(Locale.KOREA);
//        FlowManager.getReferredByteTableModel().setValueAt(b, bytecount_firstbyte, 6);
//        System.out.println(b);
    }

    private void displayExtraFieldsOf3BytesCodePoint(int bytecount_firstbyte, int unsignedDecimalValue16Bits) {
        String hex_value = Integer.toHexString(unsignedDecimalValue16Bits);
        MyDefaultByteTableModel.setValueAt(unsignedDecimalValue16Bits, bytecount_firstbyte, 4);
        MyDefaultByteTableModel.setValueAt(hex_value, bytecount_firstbyte, 5);
        MyDefaultByteTableModel.setValueAt((char) unsignedDecimalValue16Bits, bytecount_firstbyte, 6);
    }

    private int unsignedDecimalVauleOf11bits(byte b1, byte b2) {

        int unsignedDecimalValue = 0;

        // Byte 1 has format 110xxxxx ==> 5 relevant bits
        if ((b1 & 0b00010000) == 0b00010000) { //test if bit 11 = 1  bytes 1, 2 and 3 are 110 indictating first byte of 2 bytes code point
            unsignedDecimalValue = unsignedDecimalValue + 1024;
        }
        if ((b1 & 0b00001000) == 0b00001000) { //test if bit 10 = 1
            unsignedDecimalValue = unsignedDecimalValue + 512;
        }
        if ((b1 & 0b00000100) == 0b00000100) { //test if bit 9 = 1
            unsignedDecimalValue = unsignedDecimalValue + 256;
        }
        if ((b1 & 0b00000010) == 0b00000010) { //test if bit 8 = 1
            unsignedDecimalValue = unsignedDecimalValue + 128;
        }
        if ((b1 & 0b00000001) == 0b00000001) { //test if bit 7 = 1       
            unsignedDecimalValue = unsignedDecimalValue + 64;
        }

        // Byte 2 has format 10xxxxxx ==> 6 relevant bits
        if ((b2 & 0b00100000) == 0b00100000) { //test if bit 6 = 1
            unsignedDecimalValue = unsignedDecimalValue + 32;
        }
        if ((b2 & 0b00010000) == 0b00010000) { //test if bit 5 = 1  
            unsignedDecimalValue = unsignedDecimalValue + 16;
        }
        if ((b2 & 0b00001000) == 0b00001000) { //test if bit 4 = 1
            unsignedDecimalValue = unsignedDecimalValue + 8;
        }
        if ((b2 & 0b00000100) == 0b00000100) { //test if bit 3 = 1
            unsignedDecimalValue = unsignedDecimalValue + 4;
        }
        if ((b2 & 0b00000010) == 0b00000010) { //test if bit 12 = 1
            unsignedDecimalValue = unsignedDecimalValue + 2;
        }
        if ((b2 & 0b00000001) == 0b00000001) { //test if bit 1 = 1       
            unsignedDecimalValue = unsignedDecimalValue + 1;
        }

        return unsignedDecimalValue;
    }

    private int unsignedDecimalVauleOf16bits(byte b1, byte b2, byte b3) {

        int unsignedDecimalValue = 0;

        // Byte 1 has format 1110xxxx ==> 4 relevant bits
        if ((b1 & 0b00001000) == 0b00001000) { //test if bit 16 = 1
            unsignedDecimalValue = unsignedDecimalValue + 32768;
        }
        if ((b1 & 0b00000100) == 0b00000100) { //test if bit 15 = 1
            unsignedDecimalValue = unsignedDecimalValue + 16384;
        }
        if ((b1 & 0b00000010) == 0b00000010) { //test if bit 14 = 1
            unsignedDecimalValue = unsignedDecimalValue + 8192;
        }
        if ((b1 & 0b0000001) == 0b00000001) { //test if bit 13 = 1
            unsignedDecimalValue = unsignedDecimalValue + 4096;
        }

        // Byte 2 has format 10xxxxxx ==> 6 relevant bits
        if ((b2 & 0b00100000) == 0b00100000) { //test if bit 12 = 1
            unsignedDecimalValue = unsignedDecimalValue + 2048;
        }
        if ((b2 & 0b00010000) == 0b00010000) { //test if bit 11 = 1  
            unsignedDecimalValue = unsignedDecimalValue + 1024;
        }
        if ((b2 & 0b00001000) == 0b00001000) { //test if bit 10 = 1
            unsignedDecimalValue = unsignedDecimalValue + 512;
        }
        if ((b2 & 0b00000100) == 0b00000100) { //test if bit 9 = 1
            unsignedDecimalValue = unsignedDecimalValue + 256;
        }
        if ((b2 & 0b00000010) == 0b00000010) { //test if bit 8 = 1
            unsignedDecimalValue = unsignedDecimalValue + 128;
        }
        if ((b2 & 0b00000001) == 0b00000001) { //test if bit 7 = 1       
            unsignedDecimalValue = unsignedDecimalValue + 64;
        }

        // Byte 3 has format 10xxxxxx ==> 6 relevant bits
        if ((b3 & 0b00100000) == 0b00100000) { //test if bit 6 = 1
            unsignedDecimalValue = unsignedDecimalValue + 32;
        }
        if ((b3 & 0b00010000) == 0b00010000) { //test if bit 5 = 1  
            unsignedDecimalValue = unsignedDecimalValue + 16;
        }
        if ((b3 & 0b00001000) == 0b00001000) { //test if bit 4 = 1
            unsignedDecimalValue = unsignedDecimalValue + 8;
        }
        if ((b3 & 0b00000100) == 0b00000100) { //test if bit 3 = 1
            unsignedDecimalValue = unsignedDecimalValue + 4;
        }
        if ((b3 & 0b00000010) == 0b00000010) { //test if bit 2 = 1
            unsignedDecimalValue = unsignedDecimalValue + 2;
        }
        if ((b3 & 0b00000001) == 0b00000001) { //test if bit 1 = 1       
            unsignedDecimalValue = unsignedDecimalValue + 1;
        }

        return unsignedDecimalValue;
    }

}
