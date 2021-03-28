package game2048;

import java.lang.reflect.Array;
import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Abdullah Al-S
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        ///////////////////////////////////////////////////////////////////
        // the state of the board
        // the side to check is north so use column to check for empty spaces or merges
        // create a method that take a col find the empty spaces
        // store them on an array emptySpaceArray as int starting from col #3 down to col #0
        // then recursively starting from col #3 down to col #0

        //////////////////////////////////////////////////////////////////
        // loop over the columns
//        int emptyRow;
//
//        for (int col = 0; col < board.size(); col++) {
//            for (int row = board.size() - 1; row >= 0; row--) {
//                // iterate over all the tiles
//                Tile tile = board.tile(col, row);
//                // check if the tile value = 0
//                if (tile == null) {
//                    emptyRow = row;
//                    int nonEmptyTileRowNumber = findTile(board, col, row-1);
//                    if (nonEmptyTileRowNumber != -1) {
//                        Tile movableTile = board.tile(col, nonEmptyTileRowNumber);
//                        board.move(col, emptyRow, movableTile);
//                        changed = true;
//                    }
//                }
//            }
//        }

        // testUpNoMerge() PASS
//        for (int c = 0; c < board.size(); c++) {
//            // for each columns loop over the rows
//            for (int r = 0; r < board.size(); r++) {
//                // for each cell take the tile
//                Tile t = board.tile(c, r);
//                // if that tile not null (it has a value)
//                if (t != null) {
//                    // set emptyRow to -1
//                    int emptyRow = -1;
//                    // loop over the row from the top till above the current row
//                    for (int row = board.size() - 1; row > r; row--) {
//                        // if you find an empty row set emptyRow to it
//                        if (board.tile(c, row) == null) {
//                            emptyRow = row;
//                        }
//                    }
//                    // move the tile that you find to the empty row above it
//                    board.move(c, emptyRow != -1 ? emptyRow: r, t);
//                    // set the changed value to update the GUI
//                    changed = true;
//
//                }
//
//            }
//        }



//        // testUpBasicMerge()
//        // loop over the columns
//        for (int c = 0; c < board.size(); c++) {
//            // for each columns loop over the rows
//            for (int r = 0; r < board.size(); r++) {
//                // for each cell take the tile
//                Tile t = board.tile(c, r);
//                // if that tile not null (it has a value)
//                if (t != null) {
//                    // set emptyRow to -1
//                    int emptyRow = -1;
//                    // loop over the row from the top till above the current row
//                    for (int row = board.size() - 1; row > r; row--) {
//                        // if you find an empty row set emptyRow to it
//                        if (board.tile(c, row) == null) {
//                            emptyRow = row;
//                        }
//                        //////////////////////////////////////////////////////////////////////
//
//                        // check the row above if it has a similar value to merge
//                        if (row <= board.size() - 2) {
//                            int tileVal = (board.tile(c, row + 1) != null) ? board.tile(c, row + 1).value(): -1;
//                            if (tileVal == t.value()) {
//                                emptyRow = row + 1;
//                            }
//                        }
//                    }
//
//                    // move the tile that you find to the empty row above it
//                    board.move(c, emptyRow != -1 ? emptyRow : r, t);
//                    // set the changed value to update the GUI
//                    changed = true;
//
//                }
//            }
//        }

//        ///////////////////////////////////////////////////////////////////
//         // use String to store each column
//        String[] columns = new String[4];
//
//        // loop over the columns and assign them to the array
//        for (int c = 0; c < board.size(); c ++) {
//            String column = "";
//            for (int r = board.size() -1; r >= 0; r--) {
//                Tile t = board.tile(c, r);
//                int value = (t != null) ? t.value() : 0;
//                column += String.valueOf(value);
//            }
//            columns[c] = column;
//            System.out.println(column);
//
//        }
//        System.out.println("--------------");
//        play(columns);
//
//


        boolean[] isMerged = {false, false, false, false};
        for (int c = 0; c < board.size(); c++) {
            for (int r = board.size()-1; r >= 0 ; r--) {
                // check the most upper tile if its empty
                Tile t = board.tile(c, r);

                if (t == null) {
                    // if the whole col is empty then break
                    int value = tiltASingleTile(board, c, r, true, isMerged);
                    if (value > 0) {
                        changed = true;
                        score += value;
                        isMerged[r+1] = true;
                        r++;
                    }

                } else {
                    if(r < 3) {
                        Tile tileAbove = board.tile(c, r+1);
                        int valueAbove = tileAbove.value();
                        if(t.value() == valueAbove) {
                            if (!isMerged[r+1] && !isMerged[r]) {
                                board.move(c, r+1, t);
                                changed = true;
                                score += valueAbove * 2;
                                r++;
                            }
                        }
                    }
                }
            }
        }



//        ///////////////////////////////////////////////////////////////////
        checkGameOver();    //
        if (changed) {      //
            setChanged();   //
        }                   //
        return changed;     //
    }                       //
//    /////////////////////////////////////////////////////////////////

    private static int tiltASingleTile(Board b, int callerColumn, int callerRow, boolean isCallerEmpty, boolean[] isMerged) {
        if (isCallerEmpty) {
            for (int i = callerRow-1; i >= 0; i--) {
                Tile t = b.tile(callerColumn, i);
                if (t != null) {
                    int value = t.value();
                    if (callerRow == 3) {
                        b.move(callerColumn, callerRow, t);
                        return 0;
                    } else {
                        Tile tileAbove = b.tile(callerColumn, callerRow+1);
                        int tileAboveValue = (tileAbove != null) ? tileAbove.value() : -1;
                        if ((t.value() == tileAboveValue) && (!isMerged[callerRow+1]) && (!isMerged[callerRow])) {
                            b.move(callerColumn, callerRow + 1, t);
                            isMerged[callerRow+1] = true;
                            return value * 2;
                        } else {
                            b.move(callerColumn, callerRow , t);
                            return 0;
                        }
                    }

                }
            }
            return -1;
        }
        return -1;
    }

//    private static void play(String[] arr) {
//        int[] emptyArr = {-1, -1, -1, -1};
//        int[] nonEmptyArr = {-1, -1, -1, -1};
//        int[] tileValues = {-1, -1, -1, -1};
//        int emptyTiles = 0;
//        int nonEmptyTiles = 0;
//
//        int[] allTileValues = new int[4];
//
//        for (int c = 0; c < arr.length; c++) {
//            // find the first non-zero value and switch its value with the first zero value
//            // find how many non-zero tiles
//            for (int i=0; i < arr[c].length(); i++) {
//                int num = Integer.valueOf(arr[c].charAt(i));
//                allTileValues[i] = num;
//                if (num != 0) {
//                    nonEmptyTiles += 1;
//                    nonEmptyArr[nonEmptyTiles - 1] = i;
//                    tileValues[nonEmptyTiles - 1] = num;
//                } else {
//                    emptyTiles += 1;
//                    emptyArr[emptyTiles - 1] = i;
//                }
//            }
//
//            // Merge
//            for (int i = 0; i < allTileValues.length - 1; i++) {
//                if ((allTileValues[i] != 0) && (allTileValues[i] == allTileValues[i+1])) {
//                    // merge
//                    allTileValues[i] += allTileValues[i+1];
//                    allTileValues[i+1] = 0;
//                }
//            }
//
//            if ((nonEmptyTiles > 0) && (emptyTiles > 0)) {
//                // loop over tileValues array and if there are two adjacent identical values merge them
//                // do not merge an already merged cell
//                for (int i = 0; i < tileValues.length; i++) {
//                    if ((tileValues[i] != -1) && (i < tileValues.length - 1)) {
//                        if (tileValues[i] == tileValues[i+1]) {
//                            // merge them
//                            tileValues[i] += tileValues[i+1];
//                            tileValues[i+1] = -1;
//                            // delete the i+1 index and add it to the empty cells
//                            nonEmptyArr[i+1] = -1;
//                        }
//                    }
//                }
//
//            }
//        }
//    }
//

//    private static int calculatePosition(int stringIndex) {
//        return 3 - stringIndex;
//    }


    /////////////////////////////////////////////////////////////////

//    private static int findTile(Board b, int col, int r) {
//        for (int row = r; row >= 0; row--) {
//            // iterate over all the tiles
//            Tile tile = b.tile(col, row);
//            // check if the tile value = 0
//            if (tile != null) {
//                return row;
//            }
//        }
//        return -1;
//    }
    ////////////////////////////////////////////////////////////////

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // Loop over the columns
        for (int col = 0; col < b.size(); col += 1) {
            // Loop over the rows
            for (int row = 0; row < b.size(); row += 1) {
                // iterate over all the tiles
                Tile tile = b.tile(col, row);
                // check if the tile value = 0
                if (tile == null) {
                    // if so then return true
                    return true;
                }
            }
        }
        // after the loop finished return false
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // Loop over the columns
        for (int col = 0; col < b.size(); col += 1) {
            // Loop over the rows
            for (int row = 0; row < b.size(); row += 1) {
                // iterate over all the tiles
                Tile tile = b.tile(col, row);
                // check if the tile value = MAX_PIECE
                if (tile != null) {
                    if (tile.value() == MAX_PIECE) {
                        // if so then return true
                        return true;
                    }
                }
            }
        }
        // after the loop finished return false
        return false;
    }


    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // Loop over the columns
        for (int col = 0; col < b.size(); col += 1) {
            // Loop over the rows
            for (int row = 0; row < b.size(); row += 1) {
                // iterate over all the tiles
                Tile tile = b.tile(col, row);
                // check if the tile is empty which means empty space
                if (tile == null) {
                    return true;
                } else {
                    // we should check if there are two adjacent tiles with the same value
                    int value = tile.value();
                    int upCol = ((col - 1) >= 0) ? col - 1 : 0;
                    int downCol = ((col + 1) < 4) ? col + 1 : 3;
                    int upRow = ((row - 1) >= 0) ? row - 1 : 0;
                    int downRow = ((row + 1) < 4) ? row + 1 : 3;
                    // we have a max of 4 possibilities
                    if (value == b.tile(upCol, row).value()) {
                        if (col != 0) {
                            return true;
                        }
                    }
                    if (value == b.tile(downCol, row).value()) {
                        if (col != 3) {
                            return true;
                        }
                    }
                    if (value == b.tile(col, upRow).value()) {
                        if (row != 0) {
                            return true;
                        }
                    }
                    if (value == b.tile(col, downRow).value()) {
                        if (row != 3) {
                            return true;
                        }
                    }
                }
            }
        }
        // after the loop finished return false
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
