package eecs1022.lab6;

public class Game {
    // Game board
    private char[][] board;
    // to know if game is over 0 normal 1 o wins 2 x wins 3 draws
    public enum Status {
        NORMAL(0),
        O_WIN(1),
        X_WIN(2),
        DRAW(3);

        public int status;

        Status(int i) {
            status = i;
        }
    }
    private Status status = Status.NORMAL;
    // keep track of turn of player, if false, place O, if true place X
    private boolean isTurn = false;

    private String nameO;
    private String nameX;

    public Game(String nameO, String nameX) {
        this.nameO = nameO;
        this.nameX = nameX;

        this.initBoard();
    }

    public Game(String nameO, String nameX, boolean isTurn) {
        this.nameO = nameO;
        this.nameX = nameX;
        this.isTurn = isTurn;

        this.initBoard();
    }

    private void initBoard() {
        this.board = new char[3][3];

        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board.length; c++) {
                this.board[r][c] = '.';
            }
        }
    }

    public String place(int row, int col) {
        if (status.status > 0) return "Error: game is already over.\n\n" + this.toString();
        if (this.board[row][col] != '.') return String.format("Error: slot @(%d,%d) already occupied\n\n%s", row+1, col+1, this.toString());

        char place;

        if (isTurn) {
            place = 'X';
            isTurn = false;
        } else {
            place = 'O';
            isTurn = true;
        }
        this.board[row][col] = place;

        // check if wins
        if (this.hasWon(row, col, place)) {
            if (place == 'X')
                status = Status.X_WIN;
            else
                status = Status.O_WIN;
        }

        if (this.isDraw()) {
            status = Status.DRAW;
        }

        return this.toString();
    }

    // check placed if win
    public boolean hasWon(int row, int col, char placed) {
        // check row
        if (board[row][0] == placed &&
                board[row][1] == placed &&
                board[row][2] == placed ||
                // check column
                board[0][col] == placed &&
                board[1][col] == placed &&
                board[2][col] == placed ||
                // check diagonal
                row == col &&
                board[0][0] == placed &&
                board[1][1] == placed &&
                board[2][2] == placed ||
                // check oppsive diagonal
                row + col == 2 &&
                board[0][2] == placed &&
                board[1][1] == placed &&
                board[2][0] == placed)
            return true;
        return false;
    }

    public boolean isDraw() {
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board.length; c++) {
                // found empty, not end
                if (this.board[r][c] == '.')
                    return false;
            }
        }
        return true;
    }

    public String printBoard() {
        String s = "";
        for (int r = 0; r < this.board.length; r++) {
            s += this.board[r][0] + "\t|\t" + this.board[r][1] + "\t|\t" + this.board[r][2] + "\n";
            //s += "\n-------------------";
        }
        return s;
    }

    @Override
    public String toString() {
        String result;
        switch (status) {
            case O_WIN:
                result = String.format("Game is over with %s being the winner.", nameO);
                break;
            case X_WIN:
                result = String.format("Game is over with %s being the winner.", nameX);
                break;
            case DRAW:
                result = String.format("Game is over with a tie between %s and %s", nameO, nameX);
                break;
            default:
                result = String.format("Next player to play: %s", isTurn ? nameX : nameO);
        }
        return String.format("Current Game Status\n\n%s\n\n%s",
                printBoard(), result);
    }
}
