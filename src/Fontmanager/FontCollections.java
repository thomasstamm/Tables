/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fontmanager;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import static java.lang.Character.isBmpCodePoint;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class FontCollections {

    /**
     * Finds the collection of fonts that supports a collection of characters,
     * defined in a string.
     *
     * @param s: the string of characters for which suitable fonts must be found
     * @return: the TreeSet containing all fonts that are suitable for the set
     * of characters as defined in the input string. If no suitable font is
     * found, the returned TreeSet is empty.
     */
    public static ArrayList<Font> findSuitableFontsforString(String s) {

        ArrayList<Font> SuitableFontsforCodePoint = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int codePoint = s.codePointAt(i);

            /**
             * Insert the desired functionality here
             */
            ArrayList<Font> t = getSuitableFontsforUniCode(codePoint);
            if (i == 0) {
                SuitableFontsforCodePoint.addAll(t);
            } else {
                SuitableFontsforCodePoint.retainAll(t);
            }
            /**
             * Check if it concerns a surrogate pair for a code point outside
             * the BMP and, if so, skip de lower character of the surrogate pair
             */
            if (isBmpCodePoint(codePoint)) {
                continue;
            }
            i++;
        }

        return SuitableFontsforCodePoint;

    }

    /**
     *
     * @param UnsignedDecimalValue
     * @return
     * @throws NoSuitableFontException
     */
    public static Font getSuitableFontforUniCode(Integer UnsignedDecimalValue)
            throws NoSuitableFontException {
        GraphicsEnvironment graphicsEnvironment
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] s1 = graphicsEnvironment.getAvailableFontFamilyNames();
        Font f = Font.decode(s1[6]); //Arial Unicode
        if (f.canDisplay(UnsignedDecimalValue)) {
            return f;
        }
        for (String s11 : s1) {
            f = Font.decode(s11);
            if (f.canDisplay(UnsignedDecimalValue)) {
                return f;
            }
        }
        throw new NoSuitableFontException("No Suitable font found for Integer "
                + UnsignedDecimalValue);
    }

    /**
     * Finds all suitable fonts for s specifice code point If no suitable font
     * is found for the specified code point an empty set is returned.
     *
     * @param UnsignedDecimalValue
     * @return the set of suitable fonts as a TreeSet
     */
    public static ArrayList<Font> getSuitableFontsforUniCode(Integer UnsignedDecimalValue) {
        ArrayList<Font> t = new ArrayList<>();
        GraphicsEnvironment graphicsEnvironment
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] s1 = graphicsEnvironment.getAvailableFontFamilyNames();

        for (String s11 : s1) {
            Font f = Font.decode(s11);
            if (f.canDisplay(UnsignedDecimalValue)) {
                t.add(f);
            }
        }
        return t;
    }

}
