package MinesweeperFinal;

public class Piece {
    private boolean hidden;
    private boolean flagged;
    private int numMines;
    private int[] coordinates;
    private boolean isMine;

    public Piece(int x, int y, boolean isMine) {
        hidden = true;
        flagged = false;
        coordinates = new int[]{x, y};
        this.isMine = isMine;
        numMines = 0;
    }

    public void setMines(int numMines) { this.numMines = numMines; }

    public int getMines() { return numMines; }

    public boolean isHidden() { return hidden; }

    public boolean isFlagged() { return flagged; }

    public void reveal() { hidden = false; }

    public void toggleFlag() { flagged = !flagged; }

    public int[] returnCoordinates() { return coordinates; }

    public boolean isMine() { return isMine; }

}

