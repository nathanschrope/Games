/**
 * backend representation of a sudoku board
 */
public class Board {

    /**
     * Sudoku boards is size by size
     */
    public static final int size = 9;

    /**
     * Array of blocks that represent the board
     */
    private Block[][] board;

    /**
     * default board
     */
    public Board(){
        //default board is all zeros
        board = new Block[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = new Block();
            }
        }
        board[0][0].setNumColor(5,Color.BLACK);
        board[1][0].setNumColor(3,Color.BLACK);
        board[0][1].setNumColor(6,Color.BLACK);
        board[0][3].setNumColor(8,Color.BLACK);
        board[0][4].setNumColor(4, Color.BLACK);
        board[0][5].setNumColor(7,Color.BLACK);
        board[1][2].setNumColor(9, Color.BLACK);
        board[1][6].setNumColor(6, Color.BLACK);
        board[2][2].setNumColor(8,Color.BLACK);
        board[3][1].setNumColor(1,Color.BLACK);
        board[3][4].setNumColor(8,Color.BLACK);
        board[3][7].setNumColor(4,Color.BLACK);
        board[4][0].setNumColor(7,Color.BLACK);
        board[4][1].setNumColor(9,Color.BLACK);
        board[4][3].setNumColor(6, Color.BLACK);
        board[4][5].setNumColor(2,Color.BLACK);
        board[4][7].setNumColor(1, Color.BLACK);
        board[4][8].setNumColor(8, Color.BLACK);
        board[5][1].setNumColor(5,Color.BLACK);
        board[5][4].setNumColor(3,Color.BLACK);
        board[5][7].setNumColor(9,Color.BLACK);
        board[6][6].setNumColor(2,Color.BLACK);
        board[7][2].setNumColor(6,Color.BLACK);
        board[7][6].setNumColor(8,Color.BLACK);
        board[7][8].setNumColor(7, Color.BLACK);
        board[8][3].setNumColor(3,Color.BLACK);
        board[8][4].setNumColor(1, Color.BLACK);
        board[8][5].setNumColor(6, Color.BLACK);
        board[8][7].setNumColor(5,Color.BLACK);
        board[8][8].setNumColor(9,Color.BLACK);
    }

    /**
     * Sets a block in the board with a given number
     * @param col
     * @param row
     * @param num
     */
    public void set(int col, int row, int num){
        board[col][row].setNum(num);
        check(col,row);
    }

    /**
     *  Sets a block in the board with a given number and given color
     * @param col
     * @param row
     * @param num
     * @param color
     */
    public void set(int col,int row, int num, Color color){
        board[col][row].setNum(num);
        board[col][row].setColor(color);
        check(col,row);
    }

    /**
     * Gets the number of a block in the board using column and row numbers
     * @param col
     * @param row
     * @return
     */
    public int getNum(int col,int row){
        return board[col][row].getNum();
    }

    /**
     * Gets the number of a block in the board using big block number(0,8), row and column (0,2)
     * @param blockCol
     * @param blockRow
     * @param col
     * @param row
     * @return
     */
    public int getNum(int blockCol, int blockRow,int col, int row){
        int block = 3*blockRow + blockCol;
        int actCol = col;
        int actRow = row;
        if(block < 3){
            // no needed action
        }else if(block < 6){
            actRow += 3;
        }else{
            actRow += 6;
        }
        if(block % 3 == 0 ){
            //no needed action
        }else if(block % 3 == 1){
            actCol += 3;
        }else{
            actCol += 6;
        }
        return board[actCol][actRow].getNum();
    }

    /**
     * Gets the color of a block in the board
     * @param col
     * @param row
     * @return
     */
    public Color getColor(int col, int row){
        return board[col][row].getColor();
    }

    /**
     * Checks the board for mistakes around a given position
     * @param col
     * @param row
     * @return
     */
    public boolean check(int col, int row){
        boolean check = true;
        int test1 = col/3;
        int test2 = row/3;
        for(int x = 0; x < size; x++) {
            if ((board[col][row].equals(board[col][x]) && x != row && board[col][x].getColor() != Color.RED) || (board[col][row].equals(board[x][row]) && x != col && board[x][row].getColor() != Color.RED)){
                board[col][row].setColor(Color.RED);
                check = false;
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int tempCol = 3*test1+i;
                int tempRow = 3*test2+j;
               if( board[col][row].equals(board[tempCol][tempRow]) && !(row == tempRow && col == tempCol) && board[tempCol][tempRow].getColor() != Color.RED){
                   board[col][row].setColor(Color.RED);
                   check = false;
               }
            }

        }

        if(board[col][row].getColor() == Color.NONE || check){
            board[col][row].setColor(Color.BLUE);
        }
        return check;
    }

    /**
     * Sets the color of a block in the board
     * @param col
     * @param row
     * @param color
     */
    public void setColor(int col, int row, Color color){
        board[col][row].setColor(color);
    }

    /**
     * Checks to see if the puzzle is solved
     * @return
     */
    public boolean checkDone(){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(board[col][row].getColor() != Color.BLACK && board[col][row].getColor() != Color.BLUE){
                    return false;
                }
            }
        }
        return true;
    }

}
