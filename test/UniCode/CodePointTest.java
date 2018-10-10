/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniCode;

import Fontmanager.FontCollections;
import Fontmanager.NoSuitableFontException;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Thomas
 */
public class CodePointTest extends TestCase {

    public CodePointTest(String testName) {
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
     * Test of getCodePoint method, of class CodePoint.
     */
    public void testGetCodePoint() {
        System.out.println("getCodePoint");
        CodePoint instance;
        Integer expResult;
        Integer result;
        Font expectedFont;
        Font resultFont;

        //* first test - no-arg constructor      
        expResult = 0;
        expectedFont = null;
        instance = new CodePoint();
        result = instance.getCodePoint();
        resultFont = instance.getSuitableFont();
        assertEquals(expResult, result);
        assertEquals(expectedFont, resultFont);

        //* second test - int argument for constructor
        expResult = 10;
        expectedFont = null;
        instance = new CodePoint(expResult);
        result = instance.getCodePoint();
        resultFont = instance.getSuitableFont();
        assertEquals(expResult, result);
        assertEquals(expectedFont, resultFont);

        //* third test - int argument for constructor and implicit test of 
        //* getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        expResult = 20; //Integer 20 is 0x14 or Device Control 4: no fonts expected
        try {
            expectedFont = FontCollections.getSuitableFontforUniCode(expResult);
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
            expectedFont = null;
        }
        instance = new CodePoint(expResult, expectedFont);
        result = instance.getCodePoint();
        resultFont = instance.getSuitableFont();
        assertEquals(expResult, result);
        assertEquals(expectedFont, resultFont);

        //* fourth test - int argument for constructor and implicit test of getSuitableFontforUnicode
        //* getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        expResult = 1114109; //corresponds to 0x10FFFD the highest valid Uni code point, no fonts expected
        try {
            expectedFont = FontCollections.getSuitableFontforUniCode(expResult);
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
            expectedFont = null;
        }
        instance = new CodePoint(expResult, expectedFont);
        result = instance.getCodePoint();
        resultFont = instance.getSuitableFont();
        assertEquals(expResult, result);
        assertEquals(expectedFont, resultFont);

        //* fifth test - int argument for constructor and implicit test of getSuitableFontforUnicode
        //* getSuitableFontforUnicode(Integer)with VALID expectedFont
        expResult = 194586;    //corresponds to 0x02F81A. This is a supplementary
        //character whose code point is outside the BPM 
        //or greater than U+FFFF
        try {
            expectedFont = FontCollections.getSuitableFontforUniCode(expResult);
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
            expectedFont = null;
        }
        instance = new CodePoint(expResult, expectedFont);
        result = instance.getCodePoint();
        resultFont = instance.getSuitableFont();
        assertEquals(expResult, result);
        assertEquals(expectedFont, resultFont);

    }

    /**
     * Test of setCodePoint method, of class CodePoint.
     */
    public void testSetCodePoint() {
        System.out.println("setCodePoint");
        CodePoint instance;
        Integer expResult;
        Integer result;

        expResult = 194586;     //corresponds to 0x02F81A. This is a supplementary
        //character whose code point is outside the BPM 
        //or greater than U+FFFF
        instance = new CodePoint();
        instance.setCodePoint(expResult);
        result = instance.getCodePoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuitableFont method, of class CodePoint.
     */
    public void testGetSuitableFont() {
        System.out.println("getSuitableFont");
        CodePoint instance = new CodePoint();
        Font expResult = null;
        Font result = instance.getSuitableFont();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuitableFont method, of class CodePoint.
     */
    public void testSetSuitableFont() {
        System.out.println("setSuitableFont");
        Font SuitableFont = null;
        CodePoint instance = new CodePoint();
        instance.setSuitableFont(SuitableFont);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidCodePoint method, of class CodePoint.
     */
    public void testIsValidCodePoint() {
        System.out.println("isValidCodePoint");
        Integer codePoint = null;
        boolean expResult = false;
        boolean result = CodePoint.isValidCodePoint(codePoint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDefinedCharacter method, of class CodePoint.
     */
    public void testIsDefinedCharacter() {
        System.out.println("isDefinedCharacter");
        Integer codePoint = null;
        boolean expResult = false;
        boolean result = CodePoint.isDefinedCharacter(codePoint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuitableFontforUniCode method, of class CodePoint.
     *
     * @throws java.lang.Exception
     */
    public void testGetSuitableFontforUniCode_Integer() throws Exception {
        System.out.println("getSuitableFontforUniCode");
        Integer TestCodePoint;
        Font resultFont;
        String expectedFontFamily;
        String expectedFontName;

        //* Test -getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        //* Integer 20 is 0x14 or Device Control 4
        //* No fonts expected
        TestCodePoint = 20;
        expectedFontFamily = null;
        expectedFontName = null;
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());

        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with VALID expectedFont
        //* Integer 48 is 0x000030
        //* font java.awt.Font[family=Arial Unicode MS,name=Arial Unicode MS,style=plain,size=24]found for 000030
        TestCodePoint = 48;
        expectedFontFamily = "Arial Unicode MS";
        expectedFontName = "Arial Unicode MS";
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        //* Integer 7043 is 0x001b83
        //* font not found for \u001b83
        TestCodePoint = 7043;
        expectedFontFamily = null;
        expectedFontName = null;
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        //* Integer 7164 is 0x001bfc
        //* font not found for \u001bfc
        TestCodePoint = 7164;
        expectedFontFamily = null;
        expectedFontName = null;
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with NOT VALID expectedFont
        //* Integer 9730 is 0x002602
        //* font java.awt.Font[family=Arial Unicode MS,name=Arial Unicode MS,style=plain,size=24]found for 002602
        TestCodePoint = 9730;
        expectedFontFamily = "Arial Unicode MS";
        expectedFontName = "Arial Unicode MS";
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with VALID expectedFont
        //* Integer 11648 is 0x002d80
        //* font java.awt.Font[family=Ebrima,name=Ebrima,style=plain,size=24]found for 002d80
        TestCodePoint = 11648;
        expectedFontFamily = "Ebrima";
        expectedFontName = "Ebrima";
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

        //* Test - getSuitableFontforUnicode(Integer)with VALID expectedFont
        //* Integer 12161 is 0x002f81
        //* font java.awt.Font[family=Microsoft JhengHei,name=Microsoft JhengHei,style=plain,size=24]found for 002f81
        TestCodePoint = 12161;
        expectedFontFamily = "Microsoft JhengHei";
        expectedFontName = "Microsoft JhengHei";
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }
              
        //* Test - getSuitableFontforUnicode(Integer)with VALID expectedFont
        //* Integer 194586 is 0x02f81a
        //* font java.awt.Font[family=Yu Gothic,name=Yu Gothic,style=plain,size=24]found for 02f81a
        TestCodePoint = 194586;
        expectedFontFamily = "Yu Gothic";
        expectedFontName = "Yu Gothic";
        try {
            resultFont = FontCollections.getSuitableFontforUniCode(TestCodePoint);
            assertEquals(expectedFontFamily, resultFont.getFamily());
            assertEquals(expectedFontName, resultFont.getName());
        } catch (NoSuitableFontException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.WARNING, null, ex);
        }

    }

   
    /**
     * Test of getUniCodeforCharwithinBMP method, of class CodePoint.
     */
    public void testGetUniCodeforCharwithinBMP() {
        System.out.println("getUniCode");
        char c;
        int expResult;
        int result;

        //* Integer 0 is 0x0000 or NULL character, the lowest passible character
        //* value in Java
        c = '\u0000';
        expResult = 0;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 20 is 0x14 or Device Control 4
        c = '\u0014';
        expResult = 20;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 32 is 0x20 or SPACE
        c = ' '; // \u0020
        expResult = 32;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 38 is 0x26 or AMPERSAND
        c = '&';
        expResult = 38;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 48 is 0x30 or '0' (digit zero)
        c = '0';
        expResult = 48;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 78 is 0x4e or letter 'N'
        c = 'N';
        expResult = 78;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 78 is 0x4e or letter 'N'
        //* now with HEX representation for character
        c = '\u004e';
        expResult = 0x4e;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 7043 is 0x1b83 or Sudanese letter 'A'
        c = '\u1b83';
        expResult = 7043;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 7164 is 0x1bfc or Batak Symbol Bindu Na Metek
        c = '\u1bfc';
        expResult = 7164;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 9730 is 0x2602 or Umbrella U+2602 
        c = '\u2602';
        expResult = 9730;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 11648 is 0x2d80 or Ethiopic Syllable Zza U+2DB0 
        c = '\u2d80';
        expResult = 11648;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 12161 is 0x2f81 or Kangxi Radical Meat U+2F81 
        c = '\u2f81';
        expResult = 12161;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        /**
         * Integer 56360 is 0xd840 from the high-surrogate range U+D800 -U+DBFF
         * If this is the case, the underlying method expects a valid surrogate
         * pair and expects also a valid trailing character. Because this
         * function expects a character as a Java primitive as input (encoded in
         * 16 bits), the trailing character does not have any meaning and is set
         * to 0x0000. Therefor the underlying method throws an exception for
         * trailing character out of bounds and there is no result
         */
        c = '\ud840';
        expResult = 0; // see above
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        /**
         * See above, the leadin character is allowed to be filled with a
         * character in the low-surrogate range U+DC00 - U+DFFF. It that case
         * the underlying method does not evaluate the value in the trailing
         * character and the Unicode code pint integer corresponding to the
         * character is returned.
         */
        c = '\ude30';
        expResult = 56880;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 65510 is 0xffe6 or Fullwidth Won Sign U+FFE6  
        c = '\uffe6';
        expResult = 65510;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 65533 is 0xfffd or Replacement Character, highest 
        //* character in the UTF-16 Basic Multilingual Plane  
        c = '\ufffd';
        expResult = 65533;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);

        //* Integer 65535 is 0xffff, highest value in the UTF-16 Basic
        //* Multilingual Plane but not a defined character  
        c = '\uffff';
        expResult = 65535;
        result = CodePoint.getUniCodeforCharwithinBMP(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUniCodeforCharwithinBMP method, of class CodePoint.
     */
    public void testGetUniCodeforSupplementaryCharacters() {
        System.out.println("getUniCodeforSupplementaryCharacters");
        char[] surrogatePair = new char[2];
        int expResult;
        int result;

        /**
         * Integer 67974 is 0x010986 - testcase of a character outside the BMP
         * Meroitic Hieroglyphic Letter Ba
         * from plane 1 0x10000 - 0x1FFFF Supplementary Multilingual Plane
         * leading character 0xd802 trailing character 0xdd86
         * leading character 55298 trailing character 56710
         */
             
        surrogatePair[0] = '\ud802';
        surrogatePair[1] = '\udd86';
        expResult = 67974;
        try {
            result = CodePoint.getUniCodeforSupplementaryCharacters(surrogatePair);
            assertEquals(expResult, result);
        } catch (InvalidSurrogatePairException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * Integer 194586 is 0x02f81a - testcase of a character outside the BMP
         * from plane 2 0x20000 - 0x2FFFF Supplementary Ideographic Plane
         * leading character 0xd87e trailing character 0xdc1a
         */
        surrogatePair[0] = '\ud87e';
        surrogatePair[1] = '\udc1a';
        expResult = 194586;
        try {
            result = CodePoint.getUniCodeforSupplementaryCharacters(surrogatePair);
            assertEquals(expResult, result);
        } catch (InvalidSurrogatePairException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         /**
         * Integer 1114109 is 0x10fffd - testcase of a character outside the BMP
         * from plane 16 0x100000 - 0x10FFFF Supplementary Private Use Areae
         * leading character 0xdbff trailing character 0xdffd
         * leading character 56319 trailing character 57341
         */
             
        surrogatePair[0] = '\udbff';
        surrogatePair[1] = '\udffd';
        expResult = 1114109;
        try {
            result = CodePoint.getUniCodeforSupplementaryCharacters(surrogatePair);
            assertEquals(expResult, result);
        } catch (InvalidSurrogatePairException ex) {
            Logger.getLogger(CodePointTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }

    

}
