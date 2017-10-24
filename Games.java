import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Games extends Application{

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){

        Label label = new Label("What Game Do You Want To Play?");
        Label sudoku = new Label("Sudoku");
        sudoku.setFont(new Font(50));
        sudoku.setStyle("-fx-background-color: White; -fx-border-color: Blue; -fx-border-style: double; -fx-border-width: 5px");
        sudoku.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new Main().start(stage);
            }
        });
        sudoku.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sudoku.setStyle("-fx-background-color: lightblue; -fx-border-color: Blue; -fx-border-style: double; -fx-border-width: 5px");
                sudoku.setText("Click Me");
            }
        });
        sudoku.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sudoku.setStyle("-fx-background-color: White; -fx-border-color: Blue; -fx-border-style: double; -fx-border-width: 5px");
                sudoku.setText("Click Me");
                sudoku.setText("Sudoku");
            }
        });
        label.setFont(new Font(72));
        label.setStyle("-fx-background-color: White");

        VBox v = new VBox(label,sudoku);
        label.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: Gray");
        v.setAlignment(Pos.CENTER);
        Scene scene = new Scene(v);
        stage.setScene(scene);
        stage.setTitle("Games");
        stage.setFullScreen(true);
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();
    }

}
