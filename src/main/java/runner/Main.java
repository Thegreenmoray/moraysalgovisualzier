package runner;

import animations.*;
import graph_theory.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class Main //extends Application
{
  protected   static Pane root = new Pane();

    public void start(Stage stage) {
            stage.setTitle("Moray's Algo visualizer");

            root.setPrefSize(2000,2000);
            Visual_part part =new Visual_part(root);
            Scene scene = new Scene(root,800,600);
            stage.setScene(scene);
            TextArea codeInput = new TextArea();
            codeInput.setText( """
for (let i = 0; i < 5; i++) {
   User_safe_interface_api.safely_add_a_node();
}
for (var i = 0; i < 5; i++) {
for (var j = i+1; j < 5; j++) {
User_safe_interface_api.safely_add_edge(User_safe_interface_api.obtain_existing_node(i),
User_safe_interface_api.obtain_existing_node(j));
}
}

setkey=User_safe_interface_api.establishlist([10, 20, 30]);
 matrixkey=User_safe_interface_api.establishmatrix([[10, 20, 30],[1,4,46]]);
 matrixkey2=User_safe_interface_api.establishmatrix([[67, 30],[4,46],[5,8]]);
 nope=User_safe_interface_api.obtain_existing_node(0);
User_safe_interface_api.remove_node(nope);

"""
);

              codeInput.setLayoutX(700);
              codeInput.setLayoutY(300);
        codeInput.setFont(Font.font("Consolas", 16)); // readable monospaced font
        codeInput.setWrapText(true);




            AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        Button compileButton = new Button("Compile");
        Button runButton = new Button("Run");
        compileButton.setLayoutX(100);
        runButton.setLayoutX(100);
        compileButton.setLayoutY(200);
        runButton.setLayoutY(250);

        Button clearButton = new Button("Clear");

        clearButton.setLayoutX(100);
        clearButton.setLayoutY(300);

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(100);
        exitButton.setLayoutY(350);

        root.getChildren().addAll(codeInput,compileButton,runButton,clearButton,exitButton);

        compileButton.setOnAction(e -> {
            try {
                algorithmRunner.setup(codeInput.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        runButton.setOnAction(e -> {
            Thread t = new Thread(() -> {
                try {
                    algorithmRunner.run( part);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            t.start();
        });


        clearButton.setOnAction(e -> {
            part.clearboard();
        });


        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        stage.show();
    }

    static void notmain(String[] args) throws Exception {
        //launch(args);
        AlgorithmRunner runner = new AlgorithmRunner();
        Server.start(runner);

    }





}


