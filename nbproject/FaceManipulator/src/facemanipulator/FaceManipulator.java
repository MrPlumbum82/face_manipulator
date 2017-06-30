package facemanipulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * A frame with a FaceManipulatorComponent and various GUI components to aid the
 * user in changing the faces.
 * 
 * GUI components:
 * Menus
 * JSliders
 * JLabels
 * JCheckBoxs
 * JRadioButtons
 * JButtons
 * JColorChooser
 * TabbedPane
 * 
 * LayoutManagers:
 * Grid Bag Layout
 * Box Layout
 * 
 * Advanced Feature:
 * Added music in the extras tab.
 *
 * @author Sadan Mallhi
 * @vesion Apr 24, 2014
 * 
 * I certify that I wrote all of the code in this file myself.
 */
public class FaceManipulator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Face Manipulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 900);
        final FaceManipulatorComponent fmc = new FaceManipulatorComponent();
        frame.add(fmc);
        //frame.add(new FaceManipulatorComponent());

        // Head Panel
        JPanel HeadPanel = new JPanel();
        HeadPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        final JSlider hsize = new JSlider(1, 100, 10);
        hsize.setToolTipText("Change the Size");
        JLabel hlabel = new JLabel("Size");
        hlabel.setToolTipText("Change the Size");
        final JColorChooser headColor = new JColorChooser();
        JButton headColorButton = new JButton("Confirm Color");
        c.gridx = 2;
        c.gridy = 0;
        HeadPanel.add(hlabel, c);
        c.gridx = 3;
        c.gridy = 0;
        HeadPanel.add(hsize, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        HeadPanel.add(headColor, c);
        c.gridx = 1;
        c.gridy = 3;
        HeadPanel.add(headColorButton, c);

        //Mouth Panel
        JPanel MouthPanel = new JPanel();
        MouthPanel.setLayout(new BoxLayout(MouthPanel, 1));
        final JSlider msize = new JSlider(1, 100, 10);
        msize.setToolTipText("Change the Size");
        JLabel mlabel = new JLabel("Size");
        mlabel.setToolTipText("Change the Size");
        final JRadioButton mHappyType = new JRadioButton("Happy");
        final JRadioButton mNeutralType = new JRadioButton("Neutral");
        final JRadioButton mConfusedType = new JRadioButton("Confused");
        MouthPanel.add(mlabel);
        MouthPanel.add(msize);
        MouthPanel.add(mHappyType);
        MouthPanel.add(mNeutralType);
        MouthPanel.add(mConfusedType);
        ButtonGroup mGroup = new ButtonGroup();
        mGroup.add(mHappyType);
        mGroup.add(mNeutralType);
        mGroup.add(mConfusedType);

        // Eyes Panel
        JPanel EyesPanel = new JPanel();
        EyesPanel.setLayout(new GridBagLayout());
        GridBagConstraints ec = new GridBagConstraints();
        final JSlider esize = new JSlider(0, 100, fmc.getEYE_RADIUS());
        esize.setToolTipText("Change the Size");
        JLabel elabel = new JLabel("Eye Size");
        elabel.setToolTipText("Change the Size");
        final JColorChooser eyeColor = new JColorChooser();
        JButton eyeColorButton = new JButton("Confirm Eye Color");
        ec.gridx = 2;
        ec.gridy = 0;
        EyesPanel.add(elabel, ec);
        ec.gridx = 3;
        ec.gridy = 0;
        EyesPanel.add(esize, ec);
        ec.gridx = 1;
        ec.gridy = 1;
        ec.gridwidth = 3;
        EyesPanel.add(eyeColor, ec);
        ec.gridx = 1;
        ec.gridy = 3;
        EyesPanel.add(eyeColorButton, ec);

        // Brows Panel
        JPanel BrowsPanel = new JPanel();
        BrowsPanel.setLayout(new BoxLayout(BrowsPanel, 1));
        final JSlider bsize = new JSlider(1, 100, 10);
        bsize.setToolTipText("Change the Brow Size");
        JLabel blabel = new JLabel(" Brow Size");
        blabel.setToolTipText("Change the Brow Size");
        final JRadioButton bAngryType = new JRadioButton("Angry");
        final JRadioButton bNeutralType = new JRadioButton("Neutral");
        final JRadioButton bSadType = new JRadioButton("Sad");
        BrowsPanel.add(blabel);
        BrowsPanel.add(bsize);
        BrowsPanel.add(bAngryType);
        BrowsPanel.add(bNeutralType);
        BrowsPanel.add(bSadType);
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(bAngryType);
        bGroup.add(bNeutralType);
        bGroup.add(bSadType);

        // Extras Panel
        JPanel ExtrasPanel = new JPanel();
        final JCheckBox glasses = new JCheckBox("Glasses");
        final JCheckBox zit = new JCheckBox("Pimples");
        final JCheckBox funk = new JCheckBox("Get Funky? (Music)");
        ExtrasPanel.add(glasses);
        ExtrasPanel.add(zit);
        ExtrasPanel.add(funk);

        // East Panel
        JPanel SouthPanel = new JPanel();
        SouthPanel.setLayout(new GridLayout(3, 1));
        SouthPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        final JRadioButton human = new JRadioButton("Human");
        final JRadioButton nerd = new JRadioButton("Nerd");
        human.setSelected(true);
        SouthPanel.add(human);
        SouthPanel.add(nerd);
        ButtonGroup southGroup = new ButtonGroup();
        southGroup.add(human);
        southGroup.add(nerd);

        // Menus
        JMenuBar mBar = new JMenuBar();
        frame.setJMenuBar(mBar);
        JMenu menu = new JMenu("Pre Sets");
        JMenuItem[] menuItems = {
            new JMenuItem("Mad Nerd"),
            new JMenuItem("Sad Person"),
            new JMenuItem("Happy Person"),};

        menu.add(menuItems[0]);
        menu.add(menuItems[1]);
        menu.add(menuItems[2]);
        mBar.add(menu);

        // Event Handlers
        funk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "funkytown.wav";
                InputStream in = null;
                try {
                    in = new FileInputStream(filename);
                } catch (FileNotFoundException ex) {
                    System.err.println("Could not find " + filename);
                    System.exit(0);
                }
                try {
                    AudioPlayer.player.start(new AudioStream(in));
                } catch (IOException ex) {
                    Logger.getLogger(FaceManipulator.
                            class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        menuItems[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fmc.setHeadColor(Color.RED);
                fmc.setPimple(1);
                fmc.setGlass(1);
                fmc.setBrows(3);
                fmc.setFace(1);

            }
        });

        menuItems[1].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fmc.setHeadColor(Color.BLUE);
                fmc.setPimple(0);
                fmc.setGlass(0);
                fmc.setBrows(2);
                fmc.setFace(1);

            }
        });

        menuItems[2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fmc.setHeadColor(Color.YELLOW);
                fmc.setPimple(0);
                fmc.setGlass(0);
                fmc.setBrows(1);
                fmc.setFace(3);

            }
        });
        ItemListener zitliItemListener = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    fmc.setPimple(1);
                }
                if (e.getStateChange() == 2) {
                    fmc.setPimple(0);
                }
            }
        };

        ItemListener glass = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    fmc.setGlass(1);
                }
                if (e.getStateChange() == 2) {
                    fmc.setGlass(0);
                }
            }
        };

        ActionListener Type = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
                if (src == nerd) {
                    fmc.setPimple(1);
                    fmc.setGlass(1);
                }
                if (src == human) {
                    fmc.setPimple(0);
                    fmc.setGlass(0);
                }

            }
        };

        ActionListener mouthType = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
                if (src == mHappyType) {
                    fmc.setFace(3);
                }
                if (src == mNeutralType) {
                    fmc.setFace(1);
                }
                if (src == mConfusedType) {
                    fmc.setFace(2);
                }
            }
        };

        ActionListener browType = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
                if (src == bAngryType) {
                    fmc.setBrows(3);
                }
                if (src == bNeutralType) {
                    fmc.setBrows(1);
                }
                if (src == bSadType) {
                    fmc.setBrows(2);
                }
            }
        };

        ActionListener headColorChanger = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fmc.setHeadColor(headColor.getColor());
            }

        };

        ActionListener eyeColorChanger = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fmc.setEyeColor(eyeColor.getColor());
            }

        };

        ChangeListener headSizeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fmc.setSPACE(hsize.getValue());
            }

        };

        ChangeListener mouthSizeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fmc.setMOUTH_THICKNESS(msize.getValue());
            }

        };

        ChangeListener eyeSizeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fmc.setEYE_RADIUS(esize.getValue());
            }

        };

        ChangeListener browSizeListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                fmc.setBROW_THICKNESS(bsize.getValue());
            }

        };

        human.addActionListener(Type);
        nerd.addActionListener(Type);
        glasses.addItemListener(glass);
        zit.addItemListener(zitliItemListener);
        mHappyType.addActionListener(mouthType);
        mConfusedType.addActionListener(mouthType);
        mNeutralType.addActionListener(mouthType);
        bAngryType.addActionListener(browType);
        bSadType.addActionListener(browType);
        bNeutralType.addActionListener(browType);
        headColorButton.addActionListener(headColorChanger);
        eyeColorButton.addActionListener(eyeColorChanger);
        hsize.addChangeListener(headSizeListener);
        msize.addChangeListener(mouthSizeListener);
        esize.addChangeListener(eyeSizeListener);
        bsize.addChangeListener(browSizeListener);

        // Creating the Tabbed Panel
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Head", HeadPanel);
        tabs.addTab("Mouth", MouthPanel);
        tabs.addTab("Eyes", EyesPanel);
        tabs.addTab("Eye-Brows", BrowsPanel);
        tabs.addTab("Extras", ExtrasPanel);

        // Adding all the components to the frame
        frame.add(tabs, BorderLayout.SOUTH);
        frame.add(SouthPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setResizable(true);
    }

}
