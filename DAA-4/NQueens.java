import java.util.Scanner;

public class NQueens {
    private int size;
    private boolean[][] board;
    private int count;

    public NQueens(int size) {
        this.size = size;
        this.board = new boolean[size][size];
        this.count = 0;
    }

    public void printBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col]) {
                    System.out.print("Q ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isSafe(int row, int col) {
        // Check Column (above and below of the (row, col))
        for (int i = 0; i < size; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Check backward slash (\) diagonal only in above direction
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j--;
        }

        // Check backward slash (\) diagonal only in below direction
        i = row;
        j = col;
        while (i < size && j < size) {
            if (board[i][j]) {
                return false;
            }
            i++;
            j++;
        }

        // Check forward slash diagonal (/) only in above direction
        i = row;
        j = col;
        while (i >= 0 && j < size) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j++;
        }

        // Check forward slash diagonal (/) only in below direction
        i = row;
        j = col;
        while (i < size && j >= 0) {
            if (board[i][j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public void setFirstQueenPosition(int row, int col) {
        board[row - 1][col - 1] = true;
        printBoard();
    }

    public void solve(int row) {
        if (row == size) {
            count++;
            printBoard();
            return;
        }

        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row][col] = true;
                solve(row + 1);
                board[row][col] = false;
            }
        }
    }

    public void displayMessage() {
        if (count > 0) {
            System.out.println("Solution exists for the given position of the queen.");
        } else {
            System.out.println("Solution doesn't exist for the given position of the queen.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of chessboard: ");
        int size = scanner.nextInt();
        NQueens solver = new NQueens(size);
        System.out.print("Enter row (1-" + size + "): ");
        int row = scanner.nextInt();
        System.out.print("Enter column (1-" + size + "): ");
        int col = scanner.nextInt();
        solver.setFirstQueenPosition(row, col);
        solver.solve(0);
        solver.displayMessage();
    }
}
