public class SudokuSolver {

    public static boolean solveSudoku(int[][] board) {
        int N = board.length;

        // 未割り当てのセルを検索
        int[] unassigned = findUnassignedLocation(board);
        int row = unassigned[0];
        int col = unassigned[1];

        // 全てのセルが割り当てられていれば、数独は解決済み
        if (row == -1 && col == -1) {
            return true;
        }

        // 1から9までの数字を試す
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                // 数字が安全であればセットして再帰的に解を探す
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                // 解が見つからなかった場合はバックトラック
                board[row][col] = 0;
            }
        }

        // 解が見つからなかった場合
        return false;
    }

    private static int[] findUnassignedLocation(int[][] board) {
        int N = board.length;
        int[] result = new int[]{-1, -1};

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    result[0] = row;
                    result[1] = col;
                    return result;
                }
            }
        }

        return result;
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) &&
               !usedInCol(board, col, num) &&
               !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] board, int col, int num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] board, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        int N = board.length;
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % 3 == 0)
                System.out.print("");
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 8, 0, 0, 0, 9, 0, 0, 0},
            {6, 1, 0, 0, 4, 0, 0, 3, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 2, 1, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 3, 0, 0, 5},
            {0, 7, 9, 0, 0, 8, 0, 0, 0},
            {3, 0, 0, 0, 0, 0, 5, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 6, 0}
        };

        if (solveSudoku(board)) {
            System.out.println("数独の解決:");
            printBoard(board);
        } else {
            System.out.println("解が存在しません。");
        }
    }
}
