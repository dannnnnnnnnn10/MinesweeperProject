package MinesweeperFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineGUI {

    public static void main(String[] args) {

        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        JMenuBar menus = new JMenuBar();
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem colorSelectItem = new JMenuItem("Change Theme");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem rulesItem = new JMenuItem("How to Play");
        JMenuItem aboutItem = new JMenuItem("About Minesweeper");
        final Color[] color = {new Color(35, 74, 177)};

        String[] choices = new String[5];
        choices[0] = "Easy";
        choices[1] = "Medium";
        choices[2] = "Hard";
        choices[3] = "Extreme";
        choices[4] = "Custom";

        String[] colors = new String[5];
        colors[0] = "Default Blue";
        colors[1] = "Royal Purple";
        colors[2] = "Forest Green";
        colors[3] = "Hot Pink";
        colors[4] = "Deep Black";

        fileMenu.add(newGameItem);
        fileMenu.add(quitItem);
        settingsMenu.add(colorSelectItem);
        helpMenu.add(rulesItem);
        helpMenu.add(aboutItem);
        menus.add(fileMenu);
        menus.add(settingsMenu);
        menus.add(helpMenu);

        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final MinePanel[] panel = {new MinePanel(99, 30, color[0])};

        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        colorSelectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = (String) JOptionPane.showInputDialog(null,
                            "Choose a color", null,
                            JOptionPane.QUESTION_MESSAGE, null, colors, colors[0]);
                    if (input.equals("Default Blue")) {
                        color[0] = new Color(35, 74, 177);
                        panel[0].setThemeColor(color[0]);
                    }
                    if (input.equals("Royal Purple")) {
                        color[0] = new Color(109, 27, 161);
                        panel[0].setThemeColor(color[0]);
                    }
                    if (input.equals("Forest Green")) {
                        color[0] = new Color(33, 108, 18);
                        panel[0].setThemeColor(color[0]);
                    }
                    if (input.equals("Hot Pink")) {
                        color[0] = new Color(225, 23, 253);
                        panel[0].setThemeColor(color[0]);
                    }
                    if (input.equals("Deep Black")) {
                        color[0] = new Color(0, 0, 0);
                        panel[0].setThemeColor(color[0]);
                    }
                }
                catch (Exception NullPointerException){

                }
            }
        });

        rulesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Minesweeper is a game in which you are trying to find all the mines in a field by"
                        + "\nrevealing all the empty spaces. An empty space will show you how many spaces adjacent"
                        + "\nto it are mines. If the space has no number in it, there are no mines adjacent to it."
                        + "\nYou can flag a space by right clicking on it, designating it as having a mine. If a"
                        + "\nspace has adjacent mines and you flag the same number of adjacent spaces as there are"
                        + "\nadjacent mines, you can click on the space to clear all adjacent non-flagged spaces."
                        + "\nBut be careful! If your flags are marking an incorrect space, you'll reveal a mine and"
                        + "\nlose! Try to find all the mines without setting one off!",
                        "How to Play",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "This version of Minesweeper was made by Daniel Dietsche for CIS 611/622 during GVSU's"
                        + "\nFall 2022 semester.",
                        "About",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel[0].newGame();
            }
        });



        frame.setJMenuBar(menus);
        panel[0].newGame();
        frame.getContentPane().add(panel[0]);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(1000, 875));
        frame.pack();
        frame.setVisible(true);
    }
}
