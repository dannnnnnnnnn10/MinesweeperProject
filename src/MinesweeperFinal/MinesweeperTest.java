package MinesweeperFinal;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MinesweeperTest {

    @Test
    public void testPiece() {
        Piece mine = new Piece(0, 0, true);
        assertTrue(mine.isHidden());
        assertFalse(mine.isFlagged());
        mine.toggleFlag();
        assertTrue(mine.isFlagged());
        mine.toggleFlag();
        mine.reveal();
        assertFalse(mine.isHidden());
        assertEquals(mine.returnCoordinates()[0], 0);
        assertEquals(mine.returnCoordinates()[1], 0);
        assertTrue(mine.isMine());
        assertEquals(mine.getMines(), 0);

        Piece space = new Piece(0, 0, false);
        assertFalse(space.isMine());
        space.setMines(1);
        assertEquals(space.getMines(), 1);
    }

    @Test
    public void testModelStartPosition() {
        MineModel model = new MineModel(0, 10, 5, 5);
        assertFalse(model.pieceAt(4, 4).isMine());
        assertFalse(model.pieceAt(4, 5).isMine());
        assertFalse(model.pieceAt(4, 6).isMine());
        assertFalse(model.pieceAt(5, 4).isMine());
        assertFalse(model.pieceAt(5, 5).isMine());
        assertFalse(model.pieceAt(5, 6).isMine());
        assertFalse(model.pieceAt(6, 4).isMine());
        assertFalse(model.pieceAt(6, 5).isMine());
        assertFalse(model.pieceAt(6, 6).isMine());
    }

    @Test
    public void testModelGetSet() {
        MineModel model = new MineModel(10, 10, 5, 5);
        assertEquals(model.getNumMines(), 10);
        assertEquals(model.getSideLength(), 10);
        assertEquals(model.getFlagsPlaced(), 0);
        model.flagSpace(model.pieceAt(5, 5));
        assertEquals(model.getFlagsPlaced(), 1);
        model.flagSpace(model.pieceAt(5, 5));
        assertEquals(model.getFlagsPlaced(), 0);
        assertFalse(model.isGameEnd());
    }

    @Test
    public void testModelGenerateBoard() {
        MineModel model = new MineModel(10, 10, 5, 5);
        int mineCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 10);

        model = new MineModel(10, 10, 0, 5);
        mineCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 10);

        model = new MineModel(10, 10, 3, 0);
        mineCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 10);

        model = new MineModel(10, 10, 0, 0);
        mineCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 10);

        model = new MineModel(90, 10, 5, 5);
        mineCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 90);

        model = new MineModel(99, 30, 5, 5);
        mineCount = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (model.pieceAt(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineCount, 99);

    }

    @Test
    public void testFindAdjacent() {
        MineModel model = new MineModel(10, 10, 5, 5);
        ArrayList<Piece> adjacent = model.findAdjacent(model.pieceAt(5, 5));
        assertEquals(adjacent.size(), 8);
        boolean there = false;
        for (int x = 4; x <= 6; x++) {
            for (int y = 4; y <= 6; y++) {
                if (x != 5 && y != 5) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(0, 0));
        assertEquals(adjacent.size(), 3);
        there = false;
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                if (x != 0 && y != 0) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(0, 1));
        assertEquals(adjacent.size(), 5);
        there = false;
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                if (x != 0 && y != 1) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(1, 0));
        assertEquals(adjacent.size(), 5);
        there = false;
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 1; y++) {
                if (x != 0 && y != 0) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(9, 9));
        assertEquals(adjacent.size(), 3);
        there = false;
        for (int x = 8; x <= 9; x++) {
            for (int y = 8; y <= 9; y++) {
                if (x != 9 && y != 9) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(9, 8));
        assertEquals(adjacent.size(), 5);
        there = false;
        for (int x = 8; x <= 9; x++) {
            for (int y = 7; y <= 9; y++) {
                if (x != 9 && y != 8) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(8, 9));
        assertEquals(adjacent.size(), 5);
        there = false;
        for (int x = 7; x <= 9; x++) {
            for (int y = 8; y <= 9; y++) {
                if (x != 8 && y != 9) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(0, 9));
        assertEquals(adjacent.size(), 3);
        there = false;
        for (int x = 0; x <= 1; x++) {
            for (int y = 8; y <= 9; y++) {
                if (x != 0 && y != 9) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

        adjacent = model.findAdjacent(model.pieceAt(9, 0));
        assertEquals(adjacent.size(), 3);
        there = false;
        for (int x = 8; x <= 9; x++) {
            for (int y = 0; y <= 1; y++) {
                if (x != 9 && y != 0) {
                    for (int i = 0; i < adjacent.size(); i++) {
                        if (adjacent.get(i).equals(model.pieceAt(x, y))) {
                            there = true;
                        }
                    }
                    assertTrue(there);
                    there = false;
                }
            }
        }

    }

    @Test
    public void testCalculateMines() {
        MineModel model = new MineModel(10, 10, 5, 5);
        model.createBlankBoard(10);
        assertEquals(model.getNumMines(), 0);
        assertEquals(model.getSideLength(), 10);
        model.setPiece(4, 5, true);
        assertEquals(model.getNumMines(), 1);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 1);
        model.setPiece(4, 6, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 2);
        model.setPiece(4, 4, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 3);
        model.setPiece(5, 4, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 4);
        model.setPiece(5, 6, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 5);
        model.setPiece(6, 4, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 6);
        model.setPiece(6, 5, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 7);
        model.setPiece(6, 6, true);
        assertEquals(model.calculateMines(model.pieceAt(5, 5)), 8);
    }

    @Test
    public void testIsWin() {
        MineModel model = new MineModel(10, 10, 5, 5);
        assertFalse(model.isWin());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!model.pieceAt(i, j).isMine()) {
                    model.pieceAt(i, j).reveal();
                }
            }
        }
        assertTrue(model.isWin());
    }

    @Test
    public void testRevealSpacesAndClearAllAdjacent() {
        MineModel model = new MineModel(10, 10, 5, 5);
        model.createBlankBoard(10);
        model.setPiece(0, 0, true);
        model.setPiece(1, 3, true);
        model.setPiece(5, 0, true);
        model.setPiece(5, 1, true);
        model.setPiece(4, 6, true);
        model.setPiece(8, 8, true);
        model.setPiece(8, 9, true);
        model.setPiece(9, 8, true);
        model.setPiece(3, 1, true);
        model.setPiece(0, 8, true);
        model.setPiece(1, 9, true);
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (!model.pieceAt(i, j).isMine()) {
                    model.pieceAt(i, j).setMines(model.calculateMines(model.pieceAt(i, j)));
                }
            }
        }
        model.revealSpaces(model.pieceAt(7,4));

        assertTrue(model.pieceAt(0, 0).isHidden());
        assertTrue(model.pieceAt(0, 1).isHidden());
        assertTrue(model.pieceAt(0, 2).isHidden());
        assertTrue(model.pieceAt(0, 3).isHidden());
        assertFalse(model.pieceAt(0, 4).isHidden());
        assertFalse(model.pieceAt(0, 5).isHidden());
        assertFalse(model.pieceAt(0, 6).isHidden());
        assertFalse(model.pieceAt(0, 7).isHidden());
        assertTrue(model.pieceAt(0, 8).isHidden());
        assertTrue(model.pieceAt(0, 9).isHidden());

        assertTrue(model.pieceAt(1, 0).isHidden());
        assertTrue(model.pieceAt(1, 1).isHidden());
        assertTrue(model.pieceAt(1, 2).isHidden());
        assertTrue(model.pieceAt(1, 3).isHidden());
        assertFalse(model.pieceAt(1, 4).isHidden());
        assertFalse(model.pieceAt(1, 5).isHidden());
        assertFalse(model.pieceAt(1, 6).isHidden());
        assertFalse(model.pieceAt(1, 7).isHidden());
        assertFalse(model.pieceAt(1, 8).isHidden());
        assertTrue(model.pieceAt(1, 9).isHidden());

        assertTrue(model.pieceAt(2, 0).isHidden());
        assertTrue(model.pieceAt(2, 1).isHidden());
        assertFalse(model.pieceAt(2, 2).isHidden());
        assertFalse(model.pieceAt(2, 3).isHidden());
        assertFalse(model.pieceAt(2, 4).isHidden());
        assertFalse(model.pieceAt(2, 5).isHidden());
        assertFalse(model.pieceAt(2, 6).isHidden());
        assertFalse(model.pieceAt(2, 7).isHidden());
        assertFalse(model.pieceAt(2, 8).isHidden());
        assertFalse(model.pieceAt(2, 9).isHidden());

        assertTrue(model.pieceAt(3, 0).isHidden());
        assertTrue(model.pieceAt(3, 1).isHidden());
        assertFalse(model.pieceAt(3, 2).isHidden());
        assertFalse(model.pieceAt(3, 3).isHidden());
        assertFalse(model.pieceAt(3, 4).isHidden());
        assertFalse(model.pieceAt(3, 5).isHidden());
        assertFalse(model.pieceAt(3, 6).isHidden());
        assertFalse(model.pieceAt(3, 7).isHidden());
        assertFalse(model.pieceAt(3, 8).isHidden());
        assertFalse(model.pieceAt(3, 9).isHidden());

        assertTrue(model.pieceAt(4, 0).isHidden());
        assertTrue(model.pieceAt(4, 1).isHidden());
        assertFalse(model.pieceAt(4, 2).isHidden());
        assertFalse(model.pieceAt(4, 3).isHidden());
        assertFalse(model.pieceAt(4, 4).isHidden());
        assertFalse(model.pieceAt(4, 5).isHidden());
        assertTrue(model.pieceAt(4, 6).isHidden());
        assertFalse(model.pieceAt(4, 7).isHidden());
        assertFalse(model.pieceAt(4, 8).isHidden());
        assertFalse(model.pieceAt(4, 9).isHidden());

        assertTrue(model.pieceAt(5, 0).isHidden());
        assertTrue(model.pieceAt(5, 1).isHidden());
        assertFalse(model.pieceAt(5, 2).isHidden());
        assertFalse(model.pieceAt(5, 3).isHidden());
        assertFalse(model.pieceAt(5, 4).isHidden());
        assertFalse(model.pieceAt(5, 5).isHidden());
        assertFalse(model.pieceAt(5, 6).isHidden());
        assertFalse(model.pieceAt(5, 7).isHidden());
        assertFalse(model.pieceAt(5, 8).isHidden());
        assertFalse(model.pieceAt(5, 9).isHidden());

        assertFalse(model.pieceAt(6, 0).isHidden());
        assertFalse(model.pieceAt(6, 1).isHidden());
        assertFalse(model.pieceAt(6, 2).isHidden());
        assertFalse(model.pieceAt(6, 3).isHidden());
        assertFalse(model.pieceAt(6, 4).isHidden());
        assertFalse(model.pieceAt(6, 5).isHidden());
        assertFalse(model.pieceAt(6, 6).isHidden());
        assertFalse(model.pieceAt(6, 7).isHidden());
        assertFalse(model.pieceAt(6, 8).isHidden());
        assertFalse(model.pieceAt(6, 9).isHidden());

        assertFalse(model.pieceAt(7, 0).isHidden());
        assertFalse(model.pieceAt(7, 1).isHidden());
        assertFalse(model.pieceAt(7, 2).isHidden());
        assertFalse(model.pieceAt(7, 3).isHidden());
        assertFalse(model.pieceAt(7, 4).isHidden());
        assertFalse(model.pieceAt(7, 5).isHidden());
        assertFalse(model.pieceAt(7, 6).isHidden());
        assertFalse(model.pieceAt(7, 7).isHidden());
        assertFalse(model.pieceAt(7, 8).isHidden());
        assertFalse(model.pieceAt(7, 9).isHidden());

        assertFalse(model.pieceAt(8, 0).isHidden());
        assertFalse(model.pieceAt(8, 1).isHidden());
        assertFalse(model.pieceAt(8, 2).isHidden());
        assertFalse(model.pieceAt(8, 3).isHidden());
        assertFalse(model.pieceAt(8, 4).isHidden());
        assertFalse(model.pieceAt(8, 5).isHidden());
        assertFalse(model.pieceAt(8, 6).isHidden());
        assertFalse(model.pieceAt(8, 7).isHidden());
        assertTrue(model.pieceAt(8, 8).isHidden());
        assertTrue(model.pieceAt(8, 9).isHidden());

        assertFalse(model.pieceAt(9, 0).isHidden());
        assertFalse(model.pieceAt(9, 1).isHidden());
        assertFalse(model.pieceAt(9, 2).isHidden());
        assertFalse(model.pieceAt(9, 3).isHidden());
        assertFalse(model.pieceAt(9, 4).isHidden());
        assertFalse(model.pieceAt(9, 5).isHidden());
        assertFalse(model.pieceAt(9, 6).isHidden());
        assertFalse(model.pieceAt(9, 7).isHidden());
        assertTrue(model.pieceAt(9, 8).isHidden());
        assertTrue(model.pieceAt(9, 9).isHidden());

        model.flagSpace(model.pieceAt(5, 1));
        model.clearAllAdjacent(model.pieceAt(5, 2));

        assertFalse(model.pieceAt(4, 1).isHidden());
        assertTrue(model.pieceAt(4, 0).isHidden());

        model.flagSpace(model.pieceAt(3, 1));
        model.flagSpace(model.pieceAt(1, 3));
        model.clearAllAdjacent(model.pieceAt(2, 2));

        assertFalse(model.pieceAt(2, 1).isHidden());
        assertFalse(model.pieceAt(1, 2).isHidden());
        assertFalse(model.pieceAt(1, 1).isHidden());
        assertTrue(model.pieceAt(2, 0).isHidden());
        assertTrue(model.pieceAt(1, 0).isHidden());
        assertTrue(model.pieceAt(0, 1).isHidden());
        assertTrue(model.pieceAt(0, 2).isHidden());
        assertTrue(model.pieceAt(0, 3).isHidden());
        assertTrue(model.pieceAt(3, 0).isHidden());

        model.flagSpace(model.pieceAt(1, 0));
        model.clearAllAdjacent(model.pieceAt(1, 1));
        assertFalse(model.pieceAt(0, 0).isHidden());
        assertFalse(model.pieceAt(2, 0).isHidden());
        assertTrue(model.isGameEnd());
    }

}
