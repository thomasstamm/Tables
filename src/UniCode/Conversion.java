/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniCode;

import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Character.isBmpCodePoint;
import static java.lang.Character.codePointAt;

/**
 *
 * @author Thomas
 */
public class Conversion {

    public static String HexRepresentationofCodePoint(int CodePoint) {
        String s = Integer.toString(CodePoint, 16);
        return s;
    }

    /**
     * Converts the specified character (Unicode code point) to its UTF-16
     * representation stored in a char array. If the specified code point is a
     * BMP (Basic Multilingual Plane or Plane 0) value, the resulting char array
     * has the same value as codePoint. If the specified code point is a
     * supplementary code point, the resulting char array has the corresponding
     * surrogate pair. If the specified code point is not valid, an exception is
     * created by the toChars method and thrown to the calling method.
     *
     * @param codePoint
     * @return string representation of the code point.
     * @throws IllegalArgumentException if the specified codePoint is not a
     * valid Unicode code point.
     */
    public static String ConvertUniCode_intToString(int codePoint) throws
            IllegalArgumentException {
        /*
            * char[] array can be one or two positions, depending on the value
            of codePoint, see above. With the String constructor and char-array
            unicodeCharacter as argument, a String is created. 
         */

        char[] unicodeCharacter = Character.toChars(codePoint);
        return new String(unicodeCharacter);

    }

    /**
     * Returns the hex representation of a string of characters that can be in
     * the BMP or in planes 2 - 16.
     * To do: unit test on negative input, zero input, unmatched surrgate pairs
     * Tp do: use the getUniCodeforSupplementaryCharacters in Conversion, in order
     * to properly handle situations with unmatched surrogate pairs
     * @param s String of characters to be respresente in hex format
     * @return String with hex format representation of the input string
     */
    public static String HexRepresentationofCharacterString(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int codePoint = s.codePointAt(i); 
            sb.append("\\u");
            sb.append(Conversion.HexRepresentationofCodePoint(codePoint));
            if (isBmpCodePoint(codePoint)) {
                continue;
            }
            i++; //skip lower surrogate pair
        }
        return sb.toString();
    }

    /**
     * Returns the hex representation of a character in the BMP
     *
     * @param c
     * @return
     * @deprecated
     */
    public static String getUniCode(char c) {

        char[] u;
        u = new char[1];
        try {
            Array.setChar(u, 0, c);
        } catch (NullPointerException | IllegalArgumentException ex) {
            Logger.getLogger(Conversion.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = codePointAt(u, 0);
        String s = String.format("\\u00%x", i);
        return s;
    }

    /**
     * @deprecated replaced by ConvertUniCode_intToString throws
     * IllegalArgumentException
     * @param unsignedDecimalValue
     * @return unicode character corresponding with the code point in decimal
     * representation. Remark: before calling this method it must be checked if
     * it concerns a valid codepoint.
     */
    public static String ConvertUniCodeIntegerToString(Integer unsignedDecimalValue) {

        String s = String.format("%c", unsignedDecimalValue); // to be fixed
        return s;
    }

    /**
     *
     * @param UDV
     * @return
     * @deprecated
     */
    public static String getHexPresentation(int UDV) {

        String s = String.format("\\u00%x", UDV);
        return s;
    }

}
