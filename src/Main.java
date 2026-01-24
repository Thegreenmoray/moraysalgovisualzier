//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import graph_theory.VertexState;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;


public class Main extends Application  {
    Pane root = new Pane();
    ArrayList<Edge> Heap;
    @Override
    public void start(Stage stage) throws InterruptedException {
        stage.setTitle("Moray's Algo visualizer");

        root.setPrefSize(2000,2000);
       // root.setStyle("-fx-background-color: #ff0000");
        //root.setStyle("-fx-border-color: #ff0000");
        //root.setStyle("-fx-border-width: 1px;");

      //  Button button = new Button("Click Me To Start Viewing Algorithms");
      //  button.relocate(200,200);
      //  button.setMinSize(500,500);
    // root.getChildren().addAll(button);
Scene scene = new Scene(root,800,600);
stage.setScene(scene);
        Text codeInput = new Text();
        codeInput.setText("Paste your algorithm here...");


        Visual_part visualPart=new Visual_part(root);
        Graph graph =visualPart.establish(root,10,2,false,false);
Edge_interface edgeInterface=new Edge_interface() {
    @Override
    public EdgeAnimation onEdgesearched(Edge e) {
        return Visual_part.animate_edge(e, root);
    }

    @Override
    public EdgeAnimation highlightNode(Node u) {
        u.setVertexState(VertexState.VISITED);
              StackPane f = Visual_part.corrlate.get(u);
             f.setStyle("-fx-background-color: #ff0000");

             Timeline timeline = new Timeline(
                     new KeyFrame(Duration.ZERO,
                             e -> f.setStyle("-fx-background-color: #00ff00")
                     ),
                     new KeyFrame(Duration.millis(200),
                             e -> f.setStyle("-fx-background-color: #ff0000")



                     ));
        return new EdgeAnimation(timeline);
    }

    @Override
    public EdgeAnimation pause(int ms) {
        Timeline t = new Timeline(new KeyFrame(Duration.millis(ms)));
        return new EdgeAnimation(t);


    }
};
        Visual_part.graphAlgothrims.component_analysis_bfs(graph,edgeInterface ,
                new boolean[graph.getVertices().size()],visualPart);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    //@Override
   // public void onVertexVisited(Node v) {
   //     v.setVertexState(VertexState.VISITED);
  //      StackPane f = Visual_part.corrlate.get(v);
   //     f.setStyle("-fx-background-color: #ff0000");
   // }




}


