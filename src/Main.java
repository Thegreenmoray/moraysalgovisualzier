//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import graph_theory.VertexState;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;


public class Main extends Application implements Edge_interface,Vertex_interface {
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

Visual_part visualPart=new Visual_part(root);
        Graph graph =visualPart.establish(root);

        Visual_part.graphAlgothrims.component_analysis_bfs(graph, edge-> onEdgesearched(edge),
                new boolean[graph.getVertices().size()],visualPart);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void onVertexVisited(Node v) {
        v.setVertexState(VertexState.VISITED);
        StackPane f = Visual_part.corrlate.get(v);
        f.setStyle("-fx-background-color: #ff0000");
    }

    @Override
    public EdgeAnimation onEdgesearched(Edge edge) {
       return Visual_part.animate_edge(edge, root);
    }




}


