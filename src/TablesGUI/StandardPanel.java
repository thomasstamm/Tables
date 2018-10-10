/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TablesGUI;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Thomas
 */
public class StandardPanel extends JPanel {

    private static Border createStandardBorder() {
        Border darkgreyline = BorderFactory.createLineBorder(Color.DARK_GRAY,5);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border empty = BorderFactory.createEmptyBorder(5,5,5,5);
        Border compound = BorderFactory.createCompoundBorder(
			  raisedbevel, loweredbevel);
        Border compound1 = BorderFactory.createCompoundBorder(compound,empty);
        Border compound2 = BorderFactory.createCompoundBorder(darkgreyline,compound1);
        return compound2;
    }

    StandardPanel (int i, int j) {
        super();
        Construct(i,j);
    }
    
    private void Construct(int i, int j) {
        setBorder(createStandardBorder());
        setPreferredSize(new Dimension(i,j));
        setBackground(Color.LIGHT_GRAY);
    }
}