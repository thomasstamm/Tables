/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesLaunch;

import javax.swing.SwingUtilities;
import TablesGUI.Screen;

/**
 *
 * @author Thomas
 */
public class Main {

    public static Screen MainScreen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainLambda(args);
//        mainInnerclass(args);
//        maintrad(args);

    }

    public static void mainInnerclass(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainScreen = new Screen();
                MainScreen.Activate();
            }
        });
    }

    public static void mainLambda(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainScreen = new Screen();
            MainScreen.Activate();
        });
    }

    public static void maintrad(String[] args) {
        MyRunnable doRun = new MyRunnable();
        SwingUtilities.invokeLater(doRun);
    }

}
