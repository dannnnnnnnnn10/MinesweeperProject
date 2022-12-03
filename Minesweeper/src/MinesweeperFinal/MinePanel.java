package MinesweeperFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinePanel extends JPanel {

    private MineModel model;
    private JButton[][] field;
    private JLabel flagsLeft;
    private JLabel totalMines;
    private JLabel blank;
    private Color themeColor;
    private boolean firstClick;
    private int numMines;
    private int sideLength;
    private String[] choices;

    private ImageIcon mine;
    private ImageIcon flag;
    private ImageIcon one;
    private ImageIcon two;
    private ImageIcon three;
    private ImageIcon four;
    private ImageIcon five;
    private ImageIcon six;
    private ImageIcon seven;
    private ImageIcon eight;

    private listener listener;

    public MinePanel(int numMines, int sideLength, Color color) {
        this.numMines = numMines;
        this.sideLength = sideLength;
        flagsLeft = new JLabel();
        totalMines = new JLabel();
        blank = new JLabel();
        blank.setText("");
        flagsLeft.setText("Flags Left: " + numMines);
        totalMines.setText(("Mines: " + numMines));
        listener = new listener();
        themeColor = color;
        firstClick = true;
        choices = new String[5];
        choices[0] = "Easy";
        choices[1] = "Medium";
        choices[2] = "Hard";
        choices[3] = "Extreme";
        choices[4] = "Custom";
        setupIcons();

        setupPanel();
    }

    private void setupPanel() {
        removeAll();
        model = new MineModel(numMines, sideLength, 0, 0);
        field = new JButton[sideLength][sideLength];
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(sideLength, sideLength, 1, 1));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(15, 1, 1, 1));
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                field[i][j] = new JButton("", null);
                field[i][j].setBackground(new Color(35, 74, 177));
                field[i][j].addMouseListener(listener);
                fieldPanel.add(field[i][j]);
            }
        }

        infoPanel.add(totalMines);
        infoPanel.add(blank);
        infoPanel.add(flagsLeft);


        add(fieldPanel, BorderLayout.WEST);
        fieldPanel.setPreferredSize(new Dimension(800, 800));
        add(infoPanel, BorderLayout.EAST);
    }

    private void setupIcons() {
        java.net.URL imageURL = MinePanel.class.getResource("images/one.png");
        if (imageURL != null) {
            one = new ImageIcon(imageURL);
        }
        Image img = one.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        one = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/two.png");
        if (imageURL != null) {
            two = new ImageIcon(imageURL);
        }
        img = two.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        two = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/three.png");
        if (imageURL != null) {
            three = new ImageIcon(imageURL);
        }
        img = three.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        three = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/four.png");
        if (imageURL != null) {
            four = new ImageIcon(imageURL);
        }
        img = four.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        four = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/five.png");
        if (imageURL != null) {
            five = new ImageIcon(imageURL);
        }
        img = five.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        five = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/six.png");
        if (imageURL != null) {
            six = new ImageIcon(imageURL);
        }
        img = six.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        six = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/seven.png");
        if (imageURL != null) {
            seven = new ImageIcon(imageURL);
        }
        img = seven.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        seven = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/eight.png");
        if (imageURL != null) {
            eight = new ImageIcon(imageURL);
        }
        img = eight.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        eight = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/mine.png");
        if (imageURL != null) {
            mine = new ImageIcon(imageURL);
        }
        img = mine.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        mine = new ImageIcon(img);
        imageURL = MinePanel.class.getResource("images/flag.png");
        if (imageURL != null) {
            flag = new ImageIcon(imageURL);
        }
        img = flag.getImage() ;
        img = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        flag = new ImageIcon(img);
    }

    public void setThemeColor(Color color) {
        themeColor = color;
        updateField();
    }

    private void updateField(){
        for (int i = 0; i < model.getSideLength(); i++) {
            for (int j = 0; j < model.getSideLength(); j++) {
                if (!model.pieceAt(i, j).isHidden()) {
                    field[i][j].setEnabled(false);
                    if (!model.pieceAt(i, j).isMine()) {
                        field[i][j].setBackground(new Color(253, 253, 253));
                        if (model.pieceAt(i, j).getMines() == 1) {
                            field[i][j].setIcon(one);
                        }
                        else if (model.pieceAt(i, j).getMines() == 2) {
                            field[i][j].setIcon(two);
                        }
                        else if (model.pieceAt(i, j).getMines() == 3) {
                            field[i][j].setIcon(three);
                        }
                        else if (model.pieceAt(i, j).getMines() == 4) {
                            field[i][j].setIcon(four);
                        }
                        else if (model.pieceAt(i, j).getMines() == 5) {
                            field[i][j].setIcon(five);
                        }
                        else if (model.pieceAt(i, j).getMines() == 6) {
                            field[i][j].setIcon(six);
                        }
                        else if (model.pieceAt(i, j).getMines() == 7) {
                            field[i][j].setIcon(seven);
                        }
                        else if (model.pieceAt(i, j).getMines() == 8) {
                            field[i][j].setIcon(eight);
                        }
                    }
                    else {
                        field[i][j].setIcon(mine);
                    }
                }
                else if (model.pieceAt(i, j).isFlagged()) {
                    field[i][j].setIcon(flag);
                    field[i][j].setBackground(themeColor);
                }
                else {
                    field[i][j] .setIcon(null);
                    field[i][j].setBackground(themeColor);
                    field[i][j].setEnabled(true);
                }
            }
        }
        flagsLeft.setText("Mines Left: " + (model.getNumMines() - model.getFlagsPlaced()));
        totalMines.setText("Mines: " + numMines);
    }

    public void newGame() {
        boolean panelMade = false;
        while (!panelMade) {
            try {
                String input = (String) JOptionPane.showInputDialog(null,
                        "Choose your difficulty", null,
                        JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

                if (input.equals("Easy")) {
                    numMines = 10;
                    sideLength = 10;
                    panelMade = true;

                }

                if (input.equals("Medium")) {
                    numMines = 30;
                    sideLength = 15;
                    panelMade = true;
                }

                if (input.equals("Hard")) {
                    numMines = 50;
                    sideLength = 20;
                    panelMade = true;
                }

                if (input.equals("Extreme")) {
                    numMines = 99;
                    sideLength = 30;
                    panelMade = true;
                }

                if (input.equals("Custom")) {
                    try {
                        boolean validMineCount = false;
                        int mineCount = 50;
                        while (!validMineCount) {
                            String mines = (String) JOptionPane.showInputDialog(null,
                                    "Enter a number of mines between 1 and 90",
                                    "Enter Mine Count", JOptionPane.QUESTION_MESSAGE, null,
                                    null, "50");
                            validMineCount = true;
                            for (int k = 0; k < mines.length(); ++k) {
                                if (!Character.isDigit(mines.charAt(k))) {
                                    validMineCount = false;
                                }
                            }
                            if (validMineCount) {
                                mineCount = Integer.parseInt(mines);
                                if (mineCount < 0 || mineCount > 90) {
                                    validMineCount = false;
                                }
                            }
                            if (!validMineCount) {
                                JOptionPane.showMessageDialog(null,
                                        "Please enter a number between 1 and 90",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        boolean validSideSize = false;
                        int customSideLength = 20;
                        while (!validSideSize) {
                            String side = (String) JOptionPane.showInputDialog(null,
                                    "Enter the length of the grid sides between 10 and 30",
                                    "Enter Side Length", JOptionPane.QUESTION_MESSAGE, null,
                                    null, "20");
                            validSideSize = true;
                            for (int k = 0; k < side.length(); ++k) {
                                if (!Character.isDigit(side.charAt(k))) {
                                    validSideSize = false;
                                }
                            }
                            if (validSideSize) {
                                customSideLength = Integer.parseInt(side);
                                if (customSideLength < 10 || customSideLength > 30) {
                                    validSideSize = false;
                                }
                            }
                            if (!validSideSize) {
                                JOptionPane.showMessageDialog(null,
                                        "Please enter a number between 10 and 30",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        numMines = mineCount;
                        sideLength = customSideLength;
                        panelMade = true;
                    } catch (Exception NullPointerException) {

                    }
                }
            } catch (Exception NullPointerException) {
                JOptionPane.showMessageDialog(null,
                        "Please choose a difficulty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        setupPanel();
        firstClick = true;
        updateField();
    }

    private class listener implements MouseListener {
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < model.getSideLength(); i++) {
                for (int j = 0; j < model.getSideLength(); j++) {
                    if (e.getSource() == field[i][j]) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!firstClick) {
                                if (model.getFlagsPlaced() < model.getNumMines() || model.pieceAt(i, j).isFlagged()) {
                                    model.flagSpace(model.pieceAt(i, j));
                                    updateField();
                                }
                            }
                        }
                        else {
                            if (firstClick) {
                                model = new MineModel(numMines, sideLength, i, j);
                                firstClick = false;
                            }
                            if (model.pieceAt(i, j).isHidden()) {
                                if (!model.pieceAt(i, j).isFlagged()) {
                                    model.revealSpaces(model.pieceAt(i, j));
                                    updateField();
                                }
                            }
                            else {
                                model.clearAllAdjacent(model.pieceAt(i, j));
                                updateField();
                            }
                            if (model.isGameEnd()) {
                                int response4 = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                switch (response4) {
                                    case JOptionPane.YES_OPTION:
                                        newGame();
                                        break;
                                    case JOptionPane.NO_OPTION:
                                    case JOptionPane.CLOSED_OPTION:
                                        System.exit(0);
                                        break;
                                }
                            }
                            else if (model.isWin()) {
                                updateField();
                                int response4 = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "You Win!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                switch (response4) {
                                    case JOptionPane.YES_OPTION:
                                        newGame();
                                        break;
                                    case JOptionPane.NO_OPTION:
                                    case JOptionPane.CLOSED_OPTION:
                                        System.exit(0);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

