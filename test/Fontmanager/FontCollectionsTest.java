/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fontmanager;

import UniCode.Conversion;
import java.awt.Font;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Thomas
 */
public class FontCollectionsTest extends TestCase {

    public FontCollectionsTest(String testName) {
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
     * Test of findSuitableFontsforString method, of class FontCollections.
     */
    public void testFindSuitableFontsforString() {
        System.out.println("findSuitableFontsforString");
        char c1 = '\u2600';
        char c2 = '\u2601';
        char c3 = '\u2602';
        char c4 = '\u2f81';
        char[] data = {c1,c2,c3,c4};
        int codePoint = 194586; // 02f81a
        String s3 = Conversion.ConvertUniCode_intToString(codePoint);
        String s4 = s3 + new String(data);
        ArrayList< Font> expResult = null;
        ArrayList<Font> result = FontCollections.findSuitableFontsforString(s4);
        int counter = 0;
        System.out.println(Conversion.HexRepresentationofCharacterString(s4));
        if (!result.isEmpty()) {
            for (Font f : result) {
                counter++;
                System.out.println("Font number: " + counter + " is " + f.getName());
            }
        } else {
            System.out.println("No fonts found for "
                    + Conversion.HexRepresentationofCharacterString(s4));
        }


//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSuitableFontforUniCode method, of class FontCollections.
     */
    public void testGetSuitableFontsforUniCode() {
        System.out.print("getSuitableFontsforUniCode: ");
        int codePoint = 194586; // 02f81a
        Font expResult = null;
        ArrayList<Font> result = FontCollections.getSuitableFontsforUniCode(codePoint);

        int counter = 0;
        System.out.println(Conversion.HexRepresentationofCodePoint(codePoint));
         if (!result.isEmpty()) {
            for (Font f : result) {
                counter++;
                System.out.println("Font number: " + counter + " is " + f.getName());
            }
        } else {
            System.out.println("No fonts found for "
                    + Conversion.HexRepresentationofCodePoint(codePoint));
        }

        
        
        
        
        System.out.print("getSuitableFontsforUniCode: ");
        codePoint = 9728;  //002600
        expResult = null;
        result = FontCollections.getSuitableFontsforUniCode(codePoint);

        counter = 0;
        System.out.println(Conversion.HexRepresentationofCodePoint(codePoint));
        if (!result.isEmpty()) {
            for (Font f : result) {
                counter++;
                System.out.println("Font number: " + counter + " is " + f.getName());
            }
        } else {
            System.out.println("No fonts found for "
                    + Conversion.HexRepresentationofCodePoint(codePoint));
        }

        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
