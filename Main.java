import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the main program to make and play a sudoku game
 */
public class Main extends Application{

    /**
     * GUI representation of a sudoku board
     */
    public static GridPane grid;
    /**
     * Backend representation of a sudoku board
     */
    public static Board board;
    /**
     * Buttons used to change the number being placed on the board
     */
    public static GridPane buttons;
    /**
     * Current number that blocks are being replaced to
     */
    public static int curNum = 1;

    /**
     * building gui
     * @param stage
     */
    public void start(Stage stage){
        board = new Board();


        grid = new GridPane();
        buttons = new GridPane();
        buttons.setVgap(1);
        buttons.setHgap(1);
        HBox h = new HBox(new Label(" "),grid,buttons);
        h.setSpacing(50);
        h.setStyle("-fx-background-color: gray;");
        VBox v = new VBox(new Label(" "),h, new Label(""));
        v.setStyle("-fx-background-color: gray;");

        for(int x = 1; x < 10; x++){
            buttons.add(makeButtonLabel(x),0,x);
        }
        buttons.add(makeButtonLabel(0),0,10);



        for(int blocki = 0; blocki < 3; blocki++){
            for(int blockj = 0; blockj < 3; blockj++) {
                GridPane tempGrid = new GridPane();
                String num = " ";
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(board.getNum(blocki,blockj,i,j) != 0){
                            tempGrid.add(makeLabel("" + board.getNum(blocki,blockj,i,j)),i,j);
                        }else{
                            tempGrid.add(makeEmptyLabel(blocki * 3 + i,blockj * 3 + j),i,j);
                        }
                    }
                }
                tempGrid.setStyle("-fx-border-color: black");
                grid.add(tempGrid, blocki, blockj );
            }
        }

        Scene scene = new Scene(v,600,575);
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCharacter()){
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                        resetButtons();
                        int num = Integer.parseInt(event.getCharacter()) - 1;
                        if(num == -1){
                            num = 9;
                        }
                        buttons.getChildren().get(num).setStyle("-fx-border-color: black;-fx-background-color: yellow;");
                        if(buttons.getChildren().get(num).getId() == " "){
                            curNum = 0;
                        }else {
                            curNum = Integer.parseInt(buttons.getChildren().get(num).getId());
                        }
                        break;
                    default:
                        //do nothing
                        break;

                }
            }
        });
        scene.setFill(Paint.valueOf("Gray"));
        stage.setScene(scene);
        stage.setMinWidth(675);
        stage.setMinHeight(550);
        stage.centerOnScreen();
        stage.setTitle("Sudoku");
        stage.setFullScreen(true);
        stage.show();

    }

    /**
     * The main method of the program. Builds gui
     * @param args
     */
    public static void main(String[] args){

        launch(args);


    }

    /**
     * Produces a Label with a white background and black border. label shows the string given
     * used for starting values of the sudoku board, ones that can't be clicked on
     * @param num
     * @return
     */
    public static Label makeLabel(String num) {
        Label label = new Label(num);
        label.setMinSize(50, 50);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(30));
        label.setStyle("-fx-border-color: black; -fx-background-color: white");
        return label;
    }

    /**
     * Produces an empty and changeable label with a black border and white backgorund
     * @param col
     * @param row
     * @return
     */
    public static Label makeEmptyLabel(int col, int row){
        Label label = new Label();
        label.setId("" + col + row);
        label.setMinSize(50,50);
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Paint.valueOf("Blue"));
        label.setFont(Font.font(30));
        label.setStyle("-fx-border-color: black;-fx-background-color: white");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String temp;
                if(curNum == 0){
                    temp = " ";
                }else{
                    temp = "" + curNum;
                }
                int id = Integer.parseInt(label.getId());
                int row = id % 10;
                int col = id / 10;
                board.set(col,row,curNum);
                if(board.check(col,row)){
                    label.setTextFill(Paint.valueOf("Blue"));
                }else{
                    label.setTextFill(Paint.valueOf("Red"));
                }
                label.setText(temp);
                if(board.checkDone()){
                    grid.getChildren().remove(0,9);
                    buttons.getChildren().remove(0,10);
                    Label label = new Label("You solved the puzzle!");
                    label.setStyle("-fx-font-size: 50;");
                    grid.add(label,1,1);
                }
            }
        });
        return label;
    }

    /**
     * Produces Labels that can be pressed with either white background or yellow background and black border
     * @param x
     * @return
     */
    public static Label makeButtonLabel(int x){
        String temp;
        if(x == 0){
            temp = " ";
        }else{
            temp = "" + x;
        }
        Label label = new Label(temp);
        label.setId(temp);
        label.setMinSize(50,50);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(35));
        if(x == 1){
            label.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
        }else {
            label.setStyle("-fx-background-color: white;");
        }
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetButtons();
                label.setStyle("-fx-border-color: black;-fx-background-color: yellow;");
                if(label.getText() == " "){
                    curNum = 0;
                }else {
                    curNum = Integer.parseInt(label.getText());
                }
            }
        });
        return label;
    }

    /**
     * Turns all of the buttons that control curNum to white background
     */
    public static void resetButtons(){
        for(int x = 1; x < 11; x++){
            buttons.getChildren().get(x-1).setStyle("-fx-background-color: white;");
        }
    }

}
