/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fontmanager;

/**
 *
 * @author Thomas
 */
public class NoSuitableFontforCharacterException extends Exception {

    public NoSuitableFontforCharacterException(String message) {
        super(message);
    }
}
