/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesLaunch;

/**
 *
 * @author Thomas
 */

/*
    Runnable interface is part of the java.lang package.
    The Runnable interface should be implemented by any class whose instances
    are intended to be executed by a thread. The class must define a method of 
    no arguments called run.
    Remember: interface methods must not be static
 */
import TablesGUI.Screen;
import static TablesLaunch.Main.MainScreen;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        MainScreen = new Screen();
        MainScreen.Activate();
    }
}
