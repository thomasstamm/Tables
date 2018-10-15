package TablesLaunch;

//test commiting changes to GitHub

import javax.swing.SwingUtilities;
import TablesGUI.Screen;

public class Main {

    public static Screen MainScreen;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainScreen = new Screen();
            MainScreen.Activate();
        });

    }

}
