package MinesweeperFinal;

import java.util.ArrayList;
import java.util.Random;

public class MineModel {
    private Piece[][] board;
    private int numMines;
    private int sideLength;
    private int flagsPlaced;
    private boolean gameEnd;


    public MineModel(int numMines, int sideLength, int x, int y) {
        this.numMines = numMines;
        this.sideLength = sideLength;
        gameEnd = false;
        flagsPlaced = 0;
        generateBoard(x, y);
    }

    public void revealSpaces(Piece piece) {
        if (piece.isMine()) {
            gameOver();
        }
        piece.reveal();
        if (piece.getMines() == 0) {
            ArrayList<Piece> adjacentPieces = findAdjacent(piece);
            for (int i = 0; i < adjacentPieces.size(); i++) {
                if (!adjacentPieces.get(i).isMine()) {
                    if (adjacentPieces.get(i).isHidden()) {
                        adjacentPieces.get(i).reveal();
                        if (adjacentPieces.get(i).getMines() == 0) {
                            revealSpaces(adjacentPieces.get(i));
                        }
                    }
                }
            }
        }
    }

    public void clearAllAdjacent(Piece piece) {
        int count = 0;
        ArrayList<Piece> adjacentPieces = findAdjacent(piece);
        for (int i = 0; i < adjacentPieces.size(); i++) {
            if (adjacentPieces.get(i).isFlagged()){
                count++;
            }
        }
        if (count == piece.getMines()) {
            for (int i = 0; i < adjacentPieces.size(); i++) {
                if (!adjacentPieces.get(i).isFlagged() && adjacentPieces.get(i).isHidden()){
                    revealSpaces(adjacentPieces.get(i));
                }
            }
        }
    }

    public int calculateMines(Piece piece) {
        int result = 0;
        ArrayList<Piece> adjacentPieces = findAdjacent(piece);
        for (int i = 0; i < adjacentPieces.size(); i++) {
            if (adjacentPieces.get(i).isMine()) {
                result++;
            }
        }
        return result;
    }

    public void flagSpace(Piece piece) {
        if (piece.isHidden()) {
            if (piece.isFlagged()) {
                flagsPlaced--;
            } else {
                flagsPlaced++;
            }
            piece.toggleFlag();
        }
    }

    public ArrayList<Piece> findAdjacent(Piece piece) {
        ArrayList<Piece> result = new ArrayList<>();
        int x = piece.returnCoordinates()[0];
        int y = piece.returnCoordinates()[1];

        if (x > 0) {
            result.add(board[x - 1][y]);
            if (y > 0) {
                result.add(board[x][y - 1]);
                result.add(board[x - 1][y - 1]);
            }
            if (y < sideLength - 1) {
                result.add(board[x][y + 1]);
                result.add(board[x - 1][y + 1]);
            }
        }
        else if (x == 0) {
            if (y > 0) {
                result.add(board[x][y - 1]);
            }
            if (y < sideLength - 1) {
                result.add(board[x][y + 1]);
            }
        }
        if (x < sideLength - 1) {
            result.add(board[x + 1][y]);
            if (y > 0) {
                result.add(board[x + 1][y - 1]);
            }
            if (y < sideLength - 1) {
                result.add(board[x + 1][y + 1]);
            }
        }

        return result;
    }

    private void generateBoard(int x, int y) {
        board = new Piece[sideLength][sideLength];
        int minesPlaced = 0;
        int numToSkip = 9;
        if (x == 0 || x == sideLength - 1) {
            numToSkip -= 3;
        }
        if (y == 0 || y == sideLength - 1) {
            numToSkip -= 3;
        }
        if (numToSkip == 3) {
            numToSkip += 1;
        }
        int numSpaces = sideLength * sideLength - numToSkip;
        Random rand = new Random();
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                if (x - 1 <= i && i <= x + 1 && y - 1 <= j && j <= y + 1) {
                    board[i][j] = new Piece(i, j, false);
                } else {
                    int random = rand.nextInt(numSpaces);
                    if (random >= numMines - minesPlaced) {
                        board[i][j] = new Piece(i, j, false);
                    } else {
                        board[i][j] = new Piece(i, j, true);
                        minesPlaced++;
                    }
                    --numSpaces;
                }
            }
        }
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                if (!board[i][j].isMine()) {
                    board[i][j].setMines(calculateMines(board[i][j]));
                }
            }
        }
    }

    private void gameOver() {
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                board[i][j].reveal();
            }
        }
        gameEnd = true;
    }

    public boolean isWin() {
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                if (board[i][j].isHidden() && !board[i][j].isMine()) {
                    return false;
                }
            }
        }
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                if (board[i][j].isHidden() && !board[i][j].isFlagged()) {
                    board[i][j].toggleFlag();
                }
            }
        }
        return true;
    }

    public int getFlagsPlaced() {
        return flagsPlaced;
    }

    public int getNumMines() {
        return numMines;
    }

    public int getSideLength() { return sideLength; }

    public Piece pieceAt(int x, int y) { return board[x][y]; }

    public boolean isGameEnd() { return gameEnd; }

    public void createBlankBoard(int sideLength) {
        this.sideLength = sideLength;
        numMines = 0;
        board = new Piece[sideLength][sideLength];
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                board[i][j] = new Piece(i, j, false);
            }
        }
    }

    public void setPiece(int x, int y, boolean isMine) {
        Piece prev = board[x][y];
        board[x][y] = new Piece(x, y, isMine);
        if (isMine && !prev.isMine()) {
            numMines++;
        }
        else if (!isMine && prev.isMine()) {
            numMines--;
        }
    }
}
