public class Solver {

    private int[][] board;
    private int[][] boxes;
    private boolean crossF;

    public Solver(int[][] board, int[][] boxes, boolean crossF) {
        this.board = board;
        this.boxes = boxes;
        this.crossF = crossF;
    }
    public boolean solve(){
        int[][] copy = this.board;
        if(!(findWrong(copy, boxes, crossF))){
            return false;
        }
        if (solveSudoku(this.board, this.boxes, this.crossF)) {
            //System.out.println("���Ƃ̉���:");
            //printBoard(this.board);
            return true;
        } else {
            //System.out.println("�������݂��܂���B");
            return false;
        }
    }

    public static boolean solveSudoku(int[][] board, int[][] boxes, boolean crossF) {
        int N = board.length;

        // �����蓖�ẴZ��������
        int[] unassigned = findUnassignedLocation(board);
        int row = unassigned[0];
        int col = unassigned[1];

        // �S�ẴZ�������蓖�Ă��Ă���΁A���Ƃ͉����ς�
        if (row == -1 && col == -1) {
            return true;
        }

        // 1����9�܂ł̐���������
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num, crossF, boxes)) {
                // ���������S�ł���΃Z�b�g���čċA�I�ɉ���T��
                board[row][col] = num;

                if (solveSudoku(board, boxes, crossF)) {
                    return true;
                }

                // ����������Ȃ������ꍇ�̓o�b�N�g���b�N
                board[row][col] = 0;
            }
        }

        // ����������Ȃ������ꍇ
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

    private static boolean isSafe(int[][] board, int row, int col, int num, boolean crossF, int[][] boxes) {
        boolean i = !usedInRow(board, row, col, num) &&
               !usedInCol(board, row, col, num) &&
               !usedInBox(board, row, col, num, boxes);
        if(crossF){
            i = i && !usedInDiagonal(board, row, col, num); 
        }

        return i; 
    }

    private static boolean usedInRow(int[][] board, int row, int col, int num) {
        for (int c = 0; c < board.length; c++) {
            if(col != c){
                if (board[row][c] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] board, int row, int col, int num) {
        for (int r = 0; r < board.length; r++) {
            if(row != r){
                if (board[r][col] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] board, int row, int col, int num, int[][] boxes) {
        int N = board.length;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(boxes[row][col] == boxes[r][c] && num == board[r][c]){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean usedInDiagonal(int[][] board, int row, int col, int num) {
        int N = board.length;
        if(row == col){
            for (int i = 0; i < N; i++) {
                if (board[i][i] == num) {
                    return true;
                }
            }
        }
        if(row + col == N-1){
            for (int i = 0; i < N; i++) {
                if (board[i][N-1-i] == num) {
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

    public static boolean findWrong(int[][] board, int[][] boxes, boolean crossF) {
        int N = board.length;
    
        // �����s�ɓ������������邩���`�F�b�N
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] != 0) {
                    for (int d = col + 1; d < N; d++) {
                        if (board[row][col] == board[row][d]) {
                            return false;
                        }
                    }
                }
            }
        }
    
        // ������ɓ������������邩���`�F�b�N
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                if (board[row][col] != 0) {
                    for (int d = row + 1; d < N; d++) {
                        if (board[row][col] == board[d][col]) {
                            return false;
                        }
                    }
                }
            }
        }
    
        //�{�b�N�X��9�}�X�ɐݒ肳��Ă��邩
        for(int i = 0; i < 9; i++){
            int n = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if(boxes[row][col] == i){
                        n++;
                        
                    }
                    if(!NextTo(boxes, row, col, boxes[row][col])){
                        return false;
                    }
                }
            }
            if(n < 9){
                return false;
            }
        }
        // �����{�b�N�X�ɓ������������邩���`�F�b�N
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(board[row][col] != 0){
                    for(int bRow = row+1; bRow < N; bRow++){
                        for(int bCol = 0; bCol < N; bCol++){
                            if(boxes[row][col] == boxes[bRow][bCol] && board[row][col] == board[bRow][bCol]){
                                return false;
                            }
                        }
                    }
                    for(int bRow = 0; bRow < N; bRow++){
                        for(int bCol = col+1; bCol < N; bCol++){
                            if(boxes[row][col] == boxes[bRow][bCol] && board[row][col] == board[bRow][bCol]){
                                return false;
                            }
                        }
                    }
                }
            }
        }

        //�Ίp����ɓ������������邩�`�F�b�N
        if(crossF){
            for (int i = 0; i < N; i++) {
                if (board[i][i] != 0) {
                    for(int j = i + 1; j < N; j++){
                        if(board[j][j] == board[i][i]){
                            return false;
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                if (board[i][N-1-i] != 0) {
                    for(int j = i + 1; j < N; j++){
                        if(board[j][N-1-j] == board[i][N-1-i]){
                            return false;
                        }
                    }
                }
            }
        }
    
        return true;
    }

    public static boolean NextTo(int[][] boxes, int row, int col, int boxNum){
        //OK�Ȃ�true�ANG�Ȃ�false��Ԃ�
        boolean b = false;
        // if(row > 0 && row < 8 && col > 0 && col < 8){
        //     if(boxes[row][col] != boxes[row-1][col] && boxes[row][col] != boxes[row+1][col] &&
        //         boxes[row][col] != boxes[row][col-1] && boxes[row][col] != boxes[row][col+1]){
        //         b = b && false;
        //     }
        // }
        if(row < 8){
            b = b || boxes[row][col] == boxes[row+1][col];
        }
        if(row > 0){
            b = b || boxes[row][col] == boxes[row-1][col];
        }
        if(col < 8){
            b = b || boxes[row][col] == boxes[row][col+1];
        }
        if(col > 0){
            b = b || boxes[row][col] == boxes[row][col-1];
        }
        return b;
        //
    }
}
