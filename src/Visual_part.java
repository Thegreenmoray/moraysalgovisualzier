import graph_theory.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Visual_part extends Application implements Graph_theory_interface {
public Graph_algothrims graphAlgothrims=new Graph_algothrims();
    ArrayList<StackPane> stackPanes=new ArrayList<>();

    //Map<Node,StackPane> corrlate=new HashMap<>();
    Map<Edge,StackPane> edgeToStackPane=new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        root.setPrefSize(500,500);
        Graph graph =establish(root);

        graphAlgothrims.bfs(graph);

    }


    public Graph establish(Pane root) {
    Graph graph =graphAlgothrims.generate_graph_undirected(10,1,false,false);

       for (int i=0;i<graph.getVertices().size();i++) {
          Node node= graph.getVertices().get(i);
         Circle circle = new Circle(15);
           Text text=new Text(""+node.getNumber());
         StackPane pane = new StackPane(circle,text);
         pane.relocate(500+i,500+i);
         stackPanes.add(pane);
         root.getChildren().add(pane);
       }


       for (int i=0;i<graph.getEdges().size();i++) {
           Edge edge= graph.getEdges().get(i);
           Line line = new Line();

         Node n1=edge.getV1();
         Node n2=edge.getV2();


           StackPane f =stackPanes.get(n1.getNumber());
         StackPane k =stackPanes.get(n2.getNumber());
       line.setStartX(f.getLayoutX()+f.getWidth() /2);
       line.setStartY(f.getLayoutY()+f.getHeight() /2);
       line.setEndX(k.getLayoutX() +k.getWidth()/2);
       line.setEndY(k.getLayoutY() +k.getHeight()/2);
           line.toBack();
         //add text for weighted edges
         StackPane f1 =new StackPane(line);
           edgeToStackPane.put(edge,f1);
           root.getChildren().add(f1);
       }


        return graph;
    }




    public void animate_edge(Edge edge){
        edge.setEdgeState(EdgeState.ACTIVE);
      StackPane stackPane =edgeToStackPane.get(edge);

        Timeline timeline=new Timeline(
                new KeyFrame(Duration.millis(1), ev -> {
                stackPane.setStyle("-fx-background-color: #ff0001");

                }));
   timeline.setCycleCount(10);
   timeline.play();



    }





    @Override
    public void onVertexVisited(Node v) {
        v.setVertexState(VertexState.VISITED);
        StackPane f =stackPanes.get(v.getNumber());
        f.setStyle("-fx-background-color: #ff0000");
    }

    @Override
    public void onEdgeRelaxed(Edge e) {
        e.setEdgeState(EdgeState.RELAXED);
    }

}
