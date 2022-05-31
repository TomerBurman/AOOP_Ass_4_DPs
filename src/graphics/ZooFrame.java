package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ZooFrame - main class - runs main zoo. has ZooPanel as delegator.
 * initiates ZooMenu and ZooPanel
 *
 * @author : Tomer Burman, Oran Bourak
 * @version : 1
 */
public class ZooFrame extends JFrame {
    private final ZooMenu menu = new ZooMenu();
    private static ZooPanel panel;

    /**
     * ZooMenu - extends JMenuBar - represents the JMenuBar in ZooFrame
     * File, BackGround and Help options are available.
     *
     * @author : Oran Bourak
     * @version : 1
     */
    private static class ZooMenu extends JMenuBar implements ActionListener{
        /**
         * file - opens Exit option
         * backGround - opens Image, Green or None background.
         * help - opens a JDialog.
         *
         */
        private final JMenu file;
        private final JMenu backGround;
        private final JMenu help;
        private final JMenuItem image = new JMenuItem("Image");
        private final JMenuItem green = new JMenuItem("Green");
        private final JMenuItem none = new JMenuItem("None");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem help_item = new JMenuItem("Help");
        /**
         * ZooMenu - ctor that initiates file, exit and backGround
         *
         */
        ZooMenu(){
            file = new JMenu("File");
            backGround = new JMenu("Background");
            help = new JMenu("Help");
            exit.addActionListener(this);
            none.addActionListener(this);
            green.addActionListener(this);
            image.addActionListener(this);
            file.add(exit);
            backGround.add(image);
            backGround.add(green);
            backGround.add(none);
            help_item.addActionListener(this);
            help.add(help_item);
            this.add(file);
            this.add(backGround);
            this.add(help);
        }

        /**
         * actionPerformed depends on the triggering event.
         * exit - exits the system
         * none - sets background to white
         * green -sets background to green
         * image - uploads an image and draws it as background
         * help_item - opens JDialog with information.
         * @param e triggering event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(exit)) {
                JOptionPane.showMessageDialog(new JFrame(), "Goodbye");
                System.exit(0);
            }
            else if(e.getSource().equals(none))
                ZooPanel.setBack(Color.WHITE);
            else if(e.getSource().equals(green))
                ZooPanel.setBack(Color.GREEN);
            else if(e.getSource().equals(image)){
                FileDialog fd = new FileDialog(new JFrame(),"Choose an img",FileDialog.LOAD);
                fd.setDirectory("C:\\");
                fd.setFile("*.png");
                fd.setVisible(true);
                String filename = fd.getFile();
                String filepath = fd.getDirectory();
                if(filename == null) {
                    JOptionPane.showMessageDialog(panel, "No changes were applied.", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    ZooPanel.setBack(Color.WHITE);
                    ZooPanel.setBack(filepath + filename); // full path including photo name.
                }

            }
            else if(e.getSource().equals(help_item))
                JOptionPane.showMessageDialog(new JFrame(),"Homework-2 GUI","Help",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     * ZooFrame ctor - sets layout to BorderLayout and adds ZooPanel to CENTER.
     */
    public ZooFrame(){
        this.setLayout(new BorderLayout());
        panel = ZooPanel.makeInstance();
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.getController().start();
    }

    /**
     * returns ZooPanel composed in ZooFrame.
     * @return
     */
    public static ZooPanel getPanel(){
        return panel;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        ZooFrame zoo = new ZooFrame();
        zoo.setJMenuBar(new ZooMenu());
    }

}
