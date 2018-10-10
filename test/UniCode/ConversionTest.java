/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniCode;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Thomas
 */
public class ConversionTest extends TestCase {

    public ConversionTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of HexRepresentationofCodePoint method, of class Conversion.
     */
    public void testHexRepresentationofCodePoint() {
        System.out.println("HexRepresentationofCodePoint");
        int codePoint;
        String expResult, result;

        codePoint = 12161;
        expResult = "2f81";
        result = Conversion.HexRepresentationofCodePoint(codePoint);
        assertEquals(expResult, result);

        codePoint = 194586;
        expResult = "2f81a";
        result = Conversion.HexRepresentationofCodePoint(codePoint);
        assertEquals(expResult, result);

    }

    /**
     * Test of ConvertUniCode_intToString method, of class Conversion.
     */
    public void testConvertUniCodeIntegerToString() {
        System.out.println("ConvertUniCodeIntegerToString");
        int codePoint;
        String expResult, result;

        codePoint = 67;
        expResult = "C";
        result = Conversion.ConvertUniCode_intToString(codePoint);
        assertEquals(expResult, result);

        codePoint = 12161;
        expResult = "\u2f81";
        result = Conversion.ConvertUniCode_intToString(codePoint);
        assertEquals(expResult, result);

        /**
         * Integer 194586 is 0x02f81a - testcase of a character outside the BMP
         * from plane 2 0x20000 - 0x2FFFF Supplementary Ideographic Plane
         * leading character 0xd87e trailing character 0xdc1a
         */
        codePoint = 194586;

        expResult = "\ud87e" + "\udc1a";
        result = Conversion.ConvertUniCode_intToString(codePoint);
        assertEquals(expResult, result);

        try {
            result = Conversion.ConvertUniCode_intToString(codePoint);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING,
                    "The specified codePoint is not a valid code point.", ex);
        }
        assertEquals(expResult, result);

        /**
         * Integer 1114109 is 0x10fffd - testcase of a character outside the BMP
         * from plane 16 0x100000 - 0x10FFFF Supplementary Private Use Areae
         * leading character 0xdbff trailing character 0xdffd leading character
         * 56319 trailing character 57341
         */
        codePoint = 1114109;
        expResult = "\udbff" + "\udffd";
        result = Conversion.ConvertUniCode_intToString(codePoint);
        assertEquals(expResult, result);

    }

}
