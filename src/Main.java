//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Moray's Algo visualizer");
        Pane root = new Pane();
        root.setPrefSize(800,600);
       // root.setStyle("-fx-background-color: #ff0000");
        //root.setStyle("-fx-border-color: #ff0000");
        //root.setStyle("-fx-border-width: 1px;");

        Button button = new Button("Click Me To Start Viewing Algorithms");
        button.relocate(200,200);
        button.setMinSize(500,500);
     root.getChildren().addAll(button);
Scene scene = new Scene(root,800,600);
stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }






}


//one idea for github