import graph_theory.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.paint.*;


public class Visual_part extends Application implements Edge_interface,Vertex_interface {
public static Graph_algothrims graphAlgothrims=new Graph_algothrims();
   static Pane edgeLayer = new Pane();
    static Pane nodeLayer = new Pane();
    static Map<Node,StackPane> corrlate=new HashMap<>();
    static Map<Edge,Line> edgeToStackPane=new HashMap<>();
    private Pane root;
   static ArrayList<Point2D> mina_distance=new ArrayList<>();
   static double minDist = 40;

    public Visual_part(Pane root) {
        this.root = root;
    }



    @Override
    public void start(Stage stage) throws Exception {

        root.setPrefSize(500,500);
        Graph graph =establish(root);

        graphAlgothrims.bfs(graph, this);


    }




    public static Graph establish(Pane root) {
    Graph graph =graphAlgothrims.generate_graph_undirected(10,1,false,false);
        root.getChildren().addAll(edgeLayer, nodeLayer);





       for (int i=0;i<graph.getVertices().size();i++) {
          Node node= graph.getVertices().get(i);
         Circle circle = new Circle(10);
           circle.setFill(Color.GREEN);

           Text text=new Text(""+node.getNumber());
         StackPane pane = new StackPane(circle,text);
           Random random=new Random();
           int distance_X;
           int distance_Y;
           boolean good;
          int max_attempts=100;
         int current_attempts=0;
          do{
          good=true;
            distance_X=random.nextInt(500)+100;
            distance_Y=random.nextInt(500)+100;

              for (Point2D p : mina_distance) {
                  if (p.distance(distance_X, distance_Y) < minDist) {
                      current_attempts++;
                      good = false;
                      break;
                  }
              }

          }while (!good&&current_attempts<max_attempts); /*we do not want this looping forever!*/
          mina_distance.add(new Point2D(distance_X,distance_Y));
           pane.relocate(distance_X,distance_Y);
          corrlate.put(node, pane);
         nodeLayer.getChildren().add(pane);
       }


       for (int i=0;i<graph.getEdges().size();i++) {
           Edge edge= graph.getEdges().get(i);
           Line line = new Line();

         Node n1=edge.getV1();
         Node n2=edge.getV2();

           root.applyCss();
           root.layout();

           StackPane f =corrlate.get(n1);
         StackPane k =corrlate.get(n2);

           Bounds b = f.getBoundsInParent();
           Bounds c = k.getBoundsInParent();
         //add text for weighted edges

           edgeToStackPane.put(edge,line);


           line.setStartX(b.getMinX()+b.getWidth()/2);
           line.setStartY(b.getMinY()+b.getHeight()/2);
           line.setEndX(c.getMinX() +c.getWidth()/2);
           line.setEndY(c.getMinY() +c.getHeight()/2);

           edgeLayer.getChildren().add(line);

       }


        return graph;
    }




    public static void animate_edge(Edge edge, Pane root){
        edge.setEdgeState(EdgeState.ACTIVE);
        Circle circle = new Circle(1);
        //find start node later, for now just start at v1
        Node n1=edge.getV1();
        Node n2=edge.getV2();
        StackPane f =corrlate.get(n1);
        StackPane k =corrlate.get(n2);
    float targetx= (float) (k.getLayoutX()+k.getWidth() /2);
    float targety= (float) (k.getLayoutY()+k.getHeight()/2);
    float startx= (float) (f.getLayoutX()+f.getWidth()/2);
    float starty= (float) (f.getLayoutY()+f.getHeight()/2);
        float deltax= targetx - startx;
        float deltay= targety - starty;

       root.getChildren().add(circle);
       double startTime=System.currentTimeMillis();
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.millis(10), ev -> {

                    double progress = (System.currentTimeMillis() - startTime) / 200;

                   double xchange=startx+(deltax*progress);
                   double ychange=starty+(deltay*progress);
                   circle.setTranslateX(xchange);
                   circle.setTranslateY(ychange);


                }));
   timeline.setCycleCount(1);
   timeline.play();



    }


    @Override
    public void onVertexVisited(Node v) {
        v.setVertexState(VertexState.VISITED);
        StackPane f =corrlate.get(v);
        f.setStyle("-fx-background-color: #ff0000");
    }

    @Override
    public void onEdgeRelaxed(Edge edge) {
        animate_edge(edge, root);
    }



}
