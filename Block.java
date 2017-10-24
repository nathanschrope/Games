/**
 * Block represents one square of a sudoku board
 */
public class Block {

    /**
     * number in the block
     */
    private int num;
    /**
     * represents the state of the block
     * Black = final
     * Blue = no errors, user inputted
     * Red = error detected
     * None = empty block
     */
    private Color color;

    /**
     * Produces color of the block
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * produces number of the block
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * default block
     */
    public Block(){
        num = 0;
        color = Color.NONE;
    }

    /**
     * Changes the color of the block
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Changes the number of the block
     * @param num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Produces whether two blocks are equal or not
     * @param block
     * @return
     */
    public boolean equals(Block block) {
        return this.num == block.num;
    }

    /**
     * Changes number and color of a block
     * @param num
     * @param color
     */
    public void setNumColor(int num, Color color){
        setColor(color);
        setNum(num);
    }

}
