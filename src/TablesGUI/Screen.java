/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablesGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Thomas
 */
public class Screen {

    /**
     * Constants, given default access, visible in whole package
     */
    static final String NEWLINE = System.getProperty("line.separator");
    static final String NO_ATTACHED_FILE = "No_Attached_File";

    /**
     * screenSize.initialPanelWidth=1366 screenSize.initialPanelHeight=768 the
     * dimensions (400,100) of the largest components according to their initial
     * dimensions (northpanel, southpanel and centerpane) determine the initial
     * domensions of Frame en contentPane
     */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int initialPanelWidth = 500;
    int initialPanelHeight = 150;

    JFrame Frame = new JFrame(NO_ATTACHED_FILE);
    JPanel contentPane = new JPanel(new BorderLayout());

    JPanel myStandardNorthPanel;
    JPanel myStandardSouthPanel;
    JPanel myStandardCenterPanel;

    JButton MyLoadJButton = new JButton("Load");
    JButton MySaveJButton = new JButton("Save");
    JButton MySaveAsJButton = new JButton("Save as");
    JButton MyNewJButton = new JButton("New");
    /**
     * The default value us given at start-up time when the static mode variabele
     * is initialized to UTF16
     */
    JButton MyModeJButton = new JButton(IOManager_Modified_UTF8.Mode);
 
    MyJButtonLoadActionListener MyLoadJButtonActionListener
            = new MyJButtonLoadActionListener();

    MyJButtonSaveActionListener MySaveJButtonActionListener
            = new MyJButtonSaveActionListener();

    MyJButtonSaveAsActionListener MySaveAsJButtonActionListener
            = new MyJButtonSaveAsActionListener();

    MyJButtonNewActionListener MyNewJButtonActionListener
            = new MyJButtonNewActionListener();
    
    MyJButtonModeActionListener MyModeJButtonActionListener =
            new MyJButtonModeActionListener();

    Color initialJButtoncolor = MyLoadJButton.getBackground();

    JTextArea textAreaNorth = new JTextArea(3, 20);
    JTextArea textAreaSouth = new JTextArea(3, 20);

    MyDefaultTableModel MyDefaultTableModel = new MyDefaultTableModel();
    JTable myStandardTable = new JTable(MyDefaultTableModel);

    MyMouseListener Screen_MouseListener = new MyMouseListener();
    MyPopupMenu MyPopupMenu;

    public void Activate() {

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents();
        addTable();
        addListeners();
        activatePopUP();

        /**
         * Initial flags
         */
        FlowManager.setAttachedFile(FlowManager.NO_ATTACHED_FILE);

        Frame.pack();
        Frame.setVisible(true);
    }

    void addComponents() {

        Frame.add(contentPane);

        myStandardNorthPanel = new StandardPanel(initialPanelWidth,
                initialPanelHeight);
        contentPane.add(myStandardNorthPanel, BorderLayout.NORTH);
        myStandardNorthPanel.add(textAreaNorth);

        myStandardSouthPanel = new StandardPanel(initialPanelWidth,
                initialPanelHeight);
        contentPane.add(myStandardSouthPanel, BorderLayout.SOUTH);
        myStandardSouthPanel.add(textAreaSouth);

        myStandardCenterPanel = new StandardPanel(initialPanelWidth,
                initialPanelHeight);
        contentPane.add(myStandardCenterPanel, BorderLayout.CENTER);
        contentPane.add(new StandardPanel(100, 80), BorderLayout.WEST);
        contentPane.add(new StandardPanel(100, 80), BorderLayout.EAST);
        myStandardCenterPanel.setLayout(new BoxLayout(myStandardCenterPanel, BoxLayout.Y_AXIS));

        myStandardNorthPanel.add(MyLoadJButton);
        myStandardNorthPanel.add(MySaveJButton);
        myStandardNorthPanel.add(MySaveAsJButton);
        myStandardNorthPanel.add(MyNewJButton);
        myStandardNorthPanel.add(MyModeJButton);
    }

    void addTable() {
        myStandardCenterPanel.add(myStandardTable.getTableHeader());
        myStandardCenterPanel.add(myStandardTable);
        CustomTableCellRenderer renderer = new CustomTableCellRenderer();

        try {
            myStandardTable.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    void addListeners() {
        myStandardTable.addKeyListener(new MyKeyListener());
        Frame.addMouseListener(Screen_MouseListener);
        myStandardTable.addMouseListener(Screen_MouseListener);

        //MyTabelModelListener is added in constructor of MyDefaulTabelModel
        MyLoadJButton.addActionListener(MyLoadJButtonActionListener);
        MySaveJButton.addActionListener(MySaveJButtonActionListener);
        MySaveAsJButton.addActionListener(MySaveAsJButtonActionListener);
        MyNewJButton.addActionListener(MyNewJButtonActionListener);
        MyModeJButton.addActionListener(MyModeJButtonActionListener);
    }

    /**
     * All listeners for the menuitems are defined and added in the constructor
     * of MyPopupMenu(). Because a new instance of MyPopupMenu is created here,
     * and in the constructor of MyPopupMenu all MenuItemListeners are created,
     * all frames for the menuitems are instantiated (except menuitem01 and
     * menuitem03. Menuitem01 does not have a frame. The frame of Menuitme03 is
     * only created when there is a file attached. Somehow it is not shown in
     * the viewactiveframes in MyMouselistener). The MyPopupMenu menu is set
     * invisble, will be made visible by a right mouseclick.
     */
    void activatePopUP() {
        MyPopupMenu = new MyPopupMenu();
        MyPopupMenu.setVisible(false);
    }

    public void displayMessageSouth(String s) {
        textAreaSouth.insert(NEWLINE, 0);
        textAreaSouth.insert(s, 0);
    }

    public void displayMessageNorth(String s) {
        textAreaNorth.insert(NEWLINE, 0);
        textAreaNorth.insert(s, 0);
    }
}
