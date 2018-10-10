/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
         Unless otherwise specified, the behavior with respect to supplementary characters
         and surrogate char values is as follows:
         - The methods that only accept a char value cannot support supplementary characters.
         They treat char values from the surrogate ranges as undefined characters.
         For example, Character.isLetter('\uD840') returns false, even though this
         specific value if followed by any low-surrogate value in a string would represent
         a letter.
         - The methods that accept an int value support all Unicode characters, including
         supplementary characters. For example, Character.isLetter(0x2F81A)
         returns true because the code point value represents a letter (a CJK ideograph).
         - Not al valid code points are letters. For instance Character.isLetter('\u0030') returns false,
         but Character.isValidCodePoint('\u0030') returns true. This code point is the '0'character.
         - The difference between methods 'isDefined'and 'isValidCodePoint' is not yet clear.
         But I have the impression that Character.isValidCodePointis the right test
        Because when I test it with the highest unicodepoint u+1F9c0 (cheese wdege) the overloaded
        isDefined method with integer input retuns false and the not-overloaded isValidCodePoint method with
        integer input returns true
        For the time being we will run both tests with an int as input for the 
        overloaded isCharacter method
        
        isValidCodePoint(int codePoint) true is between MIN_CODE_POINT and MAX_CODE_POINT
        
        tests:
        * U+000000/&#0000 = valid code point -- MIN_CODE_POINT
        
        * U+001770/&#6000 = valid code point & not valid character -- name: Tagbanwa letter SA
        * U+001773/&#6003 = valid code point & not valid character -- name: unknown
        * U+001779/&#6009 = valid code point & not valid character -- name: unknown 

        * U+1F984/&#129412 = valid code point & not valid character -- name: unicorn face
        * U+1F9C0/&#129472 = valid code point & not valid character -- name: cheese wedge
        * U+1F9C1/&#129473 = valid code point -- no name
        
        * U+10FFFF/7#1114111 = valid code point MAX_CODE_POINT
        * U+110000/          = not valid code point, outside the range
        
 */
package UniCode;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Character.codePointAt;

/**
 *
 * @author Thomas
 */
public class CodePoint {

    private Integer CodePoint;
    private Font SuitableFont;

    public CodePoint() {
        super();
        this.CodePoint = 0;
        this.SuitableFont = null;
    }

    public CodePoint(Integer UDV) {
        super();
        this.CodePoint = UDV;
        this.SuitableFont = null;
    }

    public CodePoint(Integer CodePoint, Font SuitableFont) {
        super();
        this.CodePoint = CodePoint;
        this.SuitableFont = SuitableFont;
    }
    
    @Override
    public String toString() {
        char[] unicodeCharacter = Character.toChars(this.CodePoint); 
        return new String(unicodeCharacter);
    }

    public Integer getCodePoint() {
        return CodePoint;
    }

    public void setCodePoint(Integer CodePoint) {
        this.CodePoint = CodePoint;
    }
   
       
    public Font getSuitableFont() {
        return SuitableFont;
    }

    public void setSuitableFont(Font SuitableFont) {
        this.SuitableFont = SuitableFont;
    }

    // checks if codePoint is between MIN_CODE_POINT and MAX_CODE_POINT
    public static boolean isValidCodePoint(Integer codePoint) {
        if (Character.isValidCodePoint(codePoint)) {
            return true;
        } else {
            return false;
        }
    }

    //Checks if A character is defined if at least one of the following is true:
    // * It has an entry in the UnicodeData file.
    // * It has a value in a range defined by the UnicodeData file.
    public static boolean isDefinedCharacter(Integer codePoint) {
        if (Character.isDefined(codePoint)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Character encoding is the process for assigning a number for every
     * character. The central objective of Unicode is to unify different
     * language encoding schemes in order to avoid confusion among computer
     * systems that uses limited encoding standards such as ASCII, EBCDIC etc.
     *
     * The char data type (and therefore the value that a Character object
     * encapsulates) are based on the original Unicode specification, which
     * defined characters as fixed-width 16-bit entities. The Unicode Standard
     * has since been changed to allow for characters whose representation
     * requires more than 16 bits. The range of legal code points is now U+0000
     * to U+10FFFF, known as Unicode scalar value. So, in the original Unicode
     * specification, character holds 2 byte, so Java also uses 2 byte for
     * characters.
     *
     * This method returns the int value of all legal code points in the
     * original Unicode specification for chacaters defined in 16 bits.
     *
     * @param c: character for which the Unicode Code Point value is desired
     * @return int: the Unicode Code Point numeric value of the character
     */
    public static int getUniCodeforCharwithinBMP(char c) {

        char[] surrogatePair = new char[2];
        surrogatePair[0] = c;
        surrogatePair[1] = '\u0000';
        int i;
        try {
            i = getUniCodeforSupplementaryCharacters(surrogatePair);
        } catch (InvalidSurrogatePairException ex) {
            i = 0;
            Logger.getLogger(CodePoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    /**
     * Character encoding is the process for assigning a number for every
     * character. The central objective of Unicode is to unify different
     * language encoding schemes in order to avoid confusion among computer
     * systems that uses limited encoding standards such as ASCII, EBCDIC etc.
     *
     * The char data type (and therefore the value that a Character object
     * encapsulates) are based on the original Unicode specification, which
     * defined characters as fixed-width 16-bit entities. The Unicode Standard
     * has since been changed to allow for characters whose representation
     * requires more than 16 bits. The range of legal code points is now U+0000
     * to U+10FFFF, known as Unicode scalar value. So, in the original Unicode
     * specification, character holds 2 byte, so Java also uses 2 byte for
     * characters.
     *
     * This method returns the int value of all legal code points in the
     * complete Unicode Scalar value.
     *
     * @param surrogatePair[]: two-element character array containing either in
     * the first element a character with Code Point less than
     * MIN_HIGH_SURROGATE (and a meaningless second element) or a surrogate pair
     * with the leading character in the first element ande the trailing
     * character in the second element, between the defined bound, used by the
     * UTF-16 encoding system to code characters with a Unicode Code Point
     * greater than 0x10000 or a character that cannot be represented in the 16
     * bits character as defined by Java.
     * @return int: the Unicode Code Point numeric value of the character. This
     * can be values in the complete Unicode scalar value.
     * @throws UniCode.InvalidSurrogatePairException
     */
    public static int getUniCodeforSupplementaryCharacters(char[] surrogatePair)
            throws InvalidSurrogatePairException {

        if (surrogatePair.length > 2) {
            throw new InvalidSurrogatePairException("SurrogatePair lenght is > 2");
        }

        /**
         * If the first element is in the high-surrogate range, it is a leading
         * character of a supplementary character pair. The second, trailing,
         * must be in the low-surrogate range. Together they form a surrogate
         * pair, used by UTF-16 encoding to depict a character with a Code Point
         * value outside the BMP range, i.e. greather than 0x10000. The static
         * Character.codePointAt returns the code point value of the pair
         * according to the complictaed formula: 1000016 + (H − D80016) × 40016
         * + (L − DC0016) documented in
         * http://unicode-table.com/en/blocks/high-surrogates/
         */
        char leading_surrogate_pair = surrogatePair[0];
        char trailing_surrogate_pair = surrogatePair[1];

        if (Character.isHighSurrogate(leading_surrogate_pair)) {
            if (Character.isLowSurrogate(trailing_surrogate_pair)) {
                return codePointAt(surrogatePair, 0);
            } else {
                throw new InvalidSurrogatePairException(
                        "SurrogatePair - trailing character not within"
                        + "bounds");
            }
        } else {
            return codePointAt(surrogatePair, 0);
        }

    }

   
}
