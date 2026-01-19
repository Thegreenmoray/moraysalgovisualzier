import graph_theory.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;




public class Visual_part {
public static Graph_algothrims graphAlgothrims=new Graph_algothrims();
   static Pane edgeLayer = new Pane();
    static Pane nodeLayer = new Pane();
    static Map<Node,StackPane> corrlate=new HashMap<>();
    static Map<Edge,Line> edgeToStackPane=new HashMap<>();
    public Pane root;
   static ArrayList<Point2D> mina_distance=new ArrayList<>();
   static double minDist = 40;

    public Visual_part(Pane root) {
        this.root = root;
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public  Graph establish(Pane root) {
    Graph graph =graphAlgothrims.generate_graph_undirected(10,2,false,false);
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




    public static EdgeAnimation animate_edge(Edge edge, Pane root){
        edge.setEdgeState(EdgeState.ACTIVE); //will work on this later
        Circle circle = new Circle(5);
        Node n1=edge.getV1();
        Node n2=edge.getV2();
        StackPane f =corrlate.get(n1);
        StackPane k =corrlate.get(n2);
    float targetx= (float) (k.getLayoutX()+k.getWidth() /2);
    float targety= (float) (k.getLayoutY()+k.getHeight()/2);
    float startx= (float) (f.getLayoutX()+f.getWidth()/2);
    float starty= (float) (f.getLayoutY()+f.getHeight()/2);

       root.getChildren().add(circle);
//dont ask me how long this took me to figure out
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(circle.translateXProperty(), startx),
                        new KeyValue(circle.translateYProperty(), starty)
                ),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(circle.translateXProperty(), targetx),
                        new KeyValue(circle.translateYProperty(), targety)
                )

        );





        timeline.setCycleCount(1);



        return new EdgeAnimation(timeline,circle);
    }
    //dont ask me how long this took me to figure out
    public  void playNext(LinkedList<EdgeAnimation> time) {
        EdgeAnimation next = time.poll();
        if (next == null) return;
        next.timeline.setOnFinished(e -> {
            root.getChildren().remove(next.circle);
            playNext(time);
        });

        next.timeline.play();


    }




}
