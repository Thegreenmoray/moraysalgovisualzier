package animations;

import graph_theory.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import runner.User_safe_interface_api;
import tests.data_structure.Graph_tools;

import java.util.*;




public class Visual_part {
  private static Pane edgeLayer = new Pane();
   private static Pane nodeLayer = new Pane();
   private static Pane textLayer = new Pane();

    private static Map<Node,Group> corrlate=new HashMap<>();
   private static Map<Edge, javafx.scene.Node> edgeToLine =new HashMap<>();
   private static Map<Edge, Text> edgeToText =new HashMap<>();
    private Pane root;

   private static ArrayList<Point2D> mina_distance=new ArrayList<>();
  private static double minDist = 40;

    public Visual_part(Pane root) {
        this.root = root;
    }



    public Graph randomgraph_establish(int size, double edgechance, boolean isweighted, boolean canbenegative, boolean isdirected) {
    Graph graph =isdirected? generate_graph_directed(size,edgechance,isweighted,canbenegative):generate_graph_undirected(size,edgechance,isweighted,canbenegative);
     if (!root.getChildren().contains(edgeLayer)) {
        root.getChildren().addAll(edgeLayer, nodeLayer,textLayer);}
        edgeLayer.setMouseTransparent(true);
        nodeLayer.setMouseTransparent(true);
        textLayer.setMouseTransparent(true);
        for (int i=0;i<graph.getVertices().size();i++) {
          Node node= graph.getVertices().get(i);
         Circle circle = new Circle(10);
           circle.setFill(Color.RED);

           Text text=new Text(""+node.getNumber());
         Group group = new Group(circle,text);
           Random random=new Random();
           int distance_X;
           int distance_Y;
           boolean good;
          int max_attempts=100;
         int current_attempts=0;
          do{
          good=true;
            distance_X=random.nextInt(1000)+100;
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
           group.relocate(distance_X,distance_Y);
          corrlate.put(node, group);
         nodeLayer.getChildren().add(group);
       }



       for (int i=0;i<graph.getEdges().size();i++) {
           Edge edge= graph.getEdges().get(i);


           Line line = null;

           Arrow arrow=null;
           if(isdirected){
             arrow=new Arrow();
           }else {
               line=new Line();
           }



         Node n1=edge.getV1();
         Node n2=edge.getV2();

           Group f =corrlate.get(n1.getNumber());
         Group k =corrlate.get(n2.getNumber());

           Bounds b = f.localToScene(f.getBoundsInLocal());
           Bounds c = k.localToScene(k.getBoundsInLocal());

if (isdirected) {
           edgeToLine.put(edge,arrow);}else {
    edgeToLine.put(edge,line);
}

           if (arrow==null){
           line.setStartX(b.getMinX()+b.getWidth()/2);
           line.setStartY(b.getMinY()+b.getHeight()/2);
           line.setEndX(c.getMinX() +c.getWidth()/2);
           line.setEndY(c.getMinY() +c.getHeight()/2);}
           else {
           arrow.start((float) (b.getMinX()+b.getWidth()/2), (float) (b.getMinY()+b.getHeight()/2));
arrow.end((float) (c.getMinX() +c.getWidth()/2), (float) (c.getMinY() +c.getHeight()/2));

           }
            if(isweighted){
             Text text=new Text(""+edge.getWeight());
             text.setX((b.getMinX()+c.getMinX())/2);
             text.setY((b.getMinY()+c.getMinY())/2);
             textLayer.getChildren().add(text);
             edgeToText.put(edge,text);
            }
          if (isdirected) {
           edgeLayer.getChildren().add(arrow);}else{
           edgeLayer.getChildren().add(line);
          }


       }
       return graph;
    }

    public <E> List<E> randomset_establish(int size,E itemused){



        return List.of();
    };


    private Graph randomDAG(int node_number, double probability, boolean isweighted, boolean canbenegative){
       Graph graph= Graph_tools.empty_graph();
       for (int i=0;i<node_number;i++) {
           graph.addVertex(new Node(i));
       }

       probability=Math.clamp(probability,0,1);

       Random rand=new Random();
        for(int i=0;i<node_number;i++) {
            for (int j = i + 1; j < node_number; j++) {
                if (rand.nextFloat() < probability) {
                    int c = rand.nextInt(20) + 2;
                    if (isweighted) {
                        int v = canbenegative && rand.nextBoolean() ? -1 : 1;

                  graph.addarc(new Edge(graph.getVertices().get(i), graph.getVertices().get(j), v * c));

                    } else {
                      graph.addarc(new Edge(graph.getVertices().get(i), graph.getVertices().get(j)));

                    }

                }
            }
        }
        return graph;
    }

    public Graph complement_graph(Graph graph) {
        Graph Complement_graph=new Graph(graph.getVertices(),new ArrayList<>());
        for (int i=0;i<graph.getVertices().size();i++) {
            for(int j=i+1;j<graph.getVertices().size();j++) {
                Node n=graph.getVertices().get(i);
                Node n2=graph.getVertices().get(j);

                if(graph.getEdge(n,n2)==null) {
                    Complement_graph.addEdge(new Edge(n,n2));
                }
            }
        }

        return Complement_graph;
    }



    public Graph establish() {


            if (!root.getChildren().contains(edgeLayer)) {
                root.getChildren().addAll(edgeLayer, nodeLayer, textLayer);
            }

            edgeLayer.setMouseTransparent(true);
            nodeLayer.setMouseTransparent(true);
            textLayer.setMouseTransparent(true);

        return new Graph(new ArrayList<>(),new ArrayList<>());
    }

    public void remove_edge(Edge edge) {

       Line line = (Line) edgeToLine.get(edge);
         edgeLayer.getChildren().remove(line);
       edgeToLine.remove(edge);



    }

    public void removenode( Node n) {

       Group f =corrlate.get(n);
       nodeLayer.getChildren().remove(f);
       corrlate.remove(n);


    }

    public void addedge( Edge e) {
        Line line = new Line();



        Node n1=e.getV1();
        Node n2=e.getV2();

        Group f =corrlate.get(n1);
        Group k =corrlate.get(n2);
        Bounds b = f.getBoundsInParent();
        Bounds c = k.getBoundsInParent();


        edgeToLine.put(e,line);


        line.setStartX(b.getMinX()+b.getWidth()/2);
        line.setStartY(b.getMinY()+b.getHeight()/2);
        line.setEndX(c.getMinX() +c.getWidth()/2);
        line.setEndY(c.getMinY() +c.getHeight()/2);
        edgeLayer.getChildren().add(line);




    }

    public void addedge( Edge e, float weight) {
        Line line = new Line();

        Node n1=e.getV1();
        Node n2=e.getV2();


        Group f =corrlate.get(n1);
        Group k =corrlate.get(n2);

        Bounds b = f.getBoundsInParent();
        Bounds c = k.getBoundsInParent();


        edgeToLine.put(e,line);


        line.setStartX(b.getMinX()+b.getWidth()/2);
        line.setStartY(b.getMinY()+b.getHeight()/2);
        line.setEndX(c.getMinX() +c.getWidth()/2);
        line.setEndY(c.getMinY() +c.getHeight()/2);
        Text text=new Text(""+weight);
        text.setX((b.getMinX()+c.getMinX())/2);
        text.setY((b.getMinY()+c.getMinY())/2);
        edgeLayer.getChildren().add(line);
       textLayer.getChildren().add(text);






    }

    public void addnode( Node n) {

        Circle circle = new Circle(10);
        circle.setFill(Color.BLACK);

        Text text=new Text(""+n.getNumber());
        Group pane = new Group(circle,text);
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
        corrlate.put(n, pane);
        nodeLayer.getChildren().add(pane);


    }

    public static EdgeAnimation highlightNode(Node u) {

        Group f = Visual_part.corrlate.get(u);
      Circle circle = (Circle) f.getChildren().getFirst();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> circle.setFill(Color.web("#00ff00"))
                ),
                new KeyFrame(Duration.millis(200),
                        e -> circle.setFill(Color.web( "#ff0000"))



                ));
        return new EdgeAnimation(timeline);
    }

    public static EdgeAnimation animate_edge(Edge edge, Pane root){

        Circle circle = new Circle(5);
        Node n1=edge.getV1();
        Node n2=edge.getV2();
        Group f =corrlate.get(n1);
        Group k =corrlate.get(n2);


    float targetx= (float) (f.getLayoutX()+f.minWidth(1) /2);
    float targety= (float) (f.getLayoutY()+f.minHeight(1)/2);
    float startx= (float) (k.getLayoutX()+k.maxWidth(1)/2);
    float starty= (float) (k.getLayoutY()+k.maxHeight(1)/2);

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

    public void makeallofgraphinvisible(Graph graph) {
    for (Node node:graph.getVertices()){
        make_node_invisible(node);
    }
    for (Edge e:graph.getEdges()){
        make_edge_invisible(e);
    }

/// do these v^ on their own move to the safe api

    }

    public void makeallofgraphvisible(Graph graph) {
        for (Node node:graph.getVertices()){
            make_node_visible(node);
        }
        for (Edge e:graph.getEdges()){
            make_edge_visible(e);
        }
    }

    public EdgeAnimation make_node_visible( Node node) {

        Group stackPane =corrlate.get(node);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{stackPane.setVisible(true);}));

    return new EdgeAnimation(timeline);
    }

    public EdgeAnimation make_node_invisible( Node node) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{corrlate.get(node).setVisible(false);}));


        return new EdgeAnimation(timeline);
    }


    public EdgeAnimation make_edge_visible( Edge edge) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{edgeToLine.get(edge).setVisible(true);}));

        return new EdgeAnimation(timeline);


    }

    public EdgeAnimation make_edge_invisible(Edge edge) {


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{
        if (edgeToLine.containsKey(edge)) {
       edgeToLine.get(edge).setVisible(false);}
       if (edgeToText.containsKey(edge)) {
           edgeToText.get(edge).setVisible(false);
       }


    }));


        return new EdgeAnimation(timeline);
    }

    public void addarc( Edge e) {
        Arrow arrow=new Arrow();
        Node n1=e.getV1();
        Node n2=e.getV2();

        Group f =corrlate.get(n1);
        Group k =corrlate.get(n2);

        Bounds b = f.getBoundsInParent();
        Bounds c = k.getBoundsInParent();
        edgeToLine.put(e,arrow);

        arrow.start((float) (b.getMinX()+b.getWidth()/2), (float) (b.getMinY()+b.getHeight()/2));
        arrow.end((float) (c.getMinX() +c.getWidth()/2), (float) (c.getMinY() +c.getHeight()/2));
        Text text=new Text(""+e.getWeight());
        text.setX((b.getMinX()+c.getMinX())/2);
        text.setY((b.getMinY()+c.getMinY())/2);
        textLayer.getChildren().add(text);
        edgeLayer.getChildren().add(arrow);

    }

    public void addarc( Edge e,float weight) {
        Arrow arrow=new Arrow();
        Node n1=e.getV1();
        Node n2=e.getV2();

        Group f =corrlate.get(n1);
        Group k =corrlate.get(n2);
        e.setWeight(weight);
        if (f != null&&k != null){
        Bounds b = f.getBoundsInParent();
        Bounds c = k.getBoundsInParent();
        edgeToLine.put(e,arrow);

        arrow.start((float) (b.getMinX()+b.getWidth()/2), (float) (b.getMinY()+b.getHeight()/2));
        arrow.end((float) (c.getMinX() +c.getWidth()/2), (float) (c.getMinY() +c.getHeight()/2));
        Text text=new Text(""+weight);
        text.setX((b.getMinX()+c.getMinX())/2);
        text.setY((b.getMinY()+c.getMinY())/2);
        textLayer.getChildren().add(text);
        edgeLayer.getChildren().add(arrow);}

    }

    public void removearc( Edge e) {
   if (!edgeToText.containsKey(e)){
       edgeToLine.remove(e);

   }
  Text text= edgeToText.get(e);
   textLayer.getChildren().remove(text);
   edgeToLine.remove(e);

    }

    //dont ask me how long this took me to figure out
    public  void playNext(Queue<Animations> time) {
     //will later expand this to sets when ready

       Animations polled =time.poll();

       if (polled==null){
           return;
       }
       if (polled instanceof EdgeAnimation next) {


           next.timeline.setOnFinished(e -> {
               if (next.circle != null) {
                   root.getChildren().remove(next.circle);
               }
               playNext(time);
           });

           next.timeline.play();

       }
    }

    private static Graph generate_graph_undirected(int size,double edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 0;
        }


            edge_chance = Math.clamp(edge_chance,0,1);


        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(rand.nextFloat()<edge_chance){
                    int c=rand.nextInt(20)+2;
                    if (isweighted)
                    {
                        int v=can_be_negative_weight&&rand.nextBoolean()?-1:1;
                        edges.add(new Edge(nodes.get(i), nodes.get(j), v*c));
                        edges.add(new Edge(nodes.get(j), nodes.get(i), v*c));
                    }
                    else
                    {
                        edges.add(new Edge(nodes.get(i), nodes.get(j)));
                        edges.add(new Edge(nodes.get(j), nodes.get(i)));
                    }

                }
            }
        }

        return new Graph(nodes,edges);
    }

    private static Graph generate_graph_directed(int size,double edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();


      edge_chance =Math.clamp(edge_chance,0,1);

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (i==j) {continue;}

                if (rand.nextFloat()<edge_chance) {
                    int c = rand.nextInt(20) + 2;
                    if (isweighted) {
                        int v = can_be_negative_weight && rand.nextBoolean() ? -1 : 1;
                        edges.add(new Edge(nodes.get(i), nodes.get(j), v * c));
                    } else {
                        edges.add(new Edge(nodes.get(i), nodes.get(j)));
                    }

                }

            }
        }
        return new Graph(nodes,edges);
    }

    public void clearboard() {
   edgeLayer.getChildren().clear();
   nodeLayer.getChildren().clear();
   corrlate.clear();
   edgeToLine.clear();
   edgeToText.clear();
   textLayer.getChildren().clear();
      // User_safe_interface_api.clearGraph();

    }

    public EdgeAnimation makearcinvisible(Edge edge) {
    if (!edgeToLine.containsKey(edge)) {
        return null;
    }
    if (edgeToText.containsKey(edge)) {
        edgeToText.get(edge).setVisible(false);
    }

    edgeToLine.get(edge).setVisible(false);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{if (edgeToText.containsKey(edge)) {
                            edgeToText.get(edge).setVisible(false);
                        }
                            }));



        return new EdgeAnimation(timeline);
    }

    public EdgeAnimation makearcvisible(Edge edge) {
        if (!edgeToLine.containsKey(edge)) {
            return null;
        }
        if (edgeToText.containsKey(edge)) {
            edgeToText.get(edge).setVisible(true);
        }
        javafx.scene.Node node = edgeToLine.get(edge);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {}
                ),
                new KeyFrame(Duration.millis(200),
                        e ->{if (edgeToText.containsKey(edge)) {
            edgeToText.get(edge).setVisible(true);
        }
        node.setVisible(true);}));



        return new EdgeAnimation(timeline);
    }

    public EdgeAnimation highlightnode( Node node) {
        if (!corrlate.containsKey(node)) {
            return null;
        }
        Group group = corrlate.get(node);
        Circle circle = (Circle) group.getChildren().getFirst();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {
                            circle.setFill(Color.web( "#0000FF"));
                        }
                ),
                new KeyFrame(Duration.millis(100),
                        e -> {
                     circle.setFill(Color.web("#0000FF"));
                        }));



        return new EdgeAnimation(timeline);
    }

    public EdgeAnimation disablenodes( Node node) {
        if (!corrlate.containsKey(node)) {
            return null;
        }
        Group group  = corrlate.get(node);
        Circle circle = (Circle) group.getChildren().getFirst();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> circle.setFill(Color.web("#ff0000"))
                ),
                new KeyFrame(Duration.millis(30),
                        e -> circle.setFill(Color.web("#ff0000"))));




        return new EdgeAnimation(timeline);
    }

    public EdgeAnimation highlightedge(Edge e) {
        if (!edgeToLine.containsKey(e)) {
            return null;
        }
        Line line = (Line) edgeToLine.get(e);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        k -> {line.setStroke(Color.web("#ff0000"));
                            line.setStrokeWidth(3);
                        }
                ),
                new KeyFrame(Duration.millis(30),
                        k -> {line.setStroke(Color.web("#ff0000"));
                line.setStrokeWidth(3);
                }



                ));




        return new EdgeAnimation(timeline);

    }

    public EdgeAnimation disableedge(Edge e) {
        if (!edgeToLine.containsKey(e)) {
            return null;
        }
        Line line = (Line) edgeToLine.get(e);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        k -> {
                            line.setStroke(Color.web("#000000"));
                            line.setStrokeWidth(1);
                        }
                ),
                new KeyFrame(Duration.millis(30),
                        k -> {
                            line.setStroke(Color.web("#000000"));
                            line.setStrokeWidth(1);
                        }));




        return new EdgeAnimation(timeline);
    }

    public EdgeAnimation colornode(Node node,String hexcode_color) {
        if (!corrlate.containsKey(node)) {
            return null;
        }
        Group group = corrlate.get(node);
        Circle circle = (Circle) group.getChildren().getFirst();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {
                            circle.setFill(Color.web(hexcode_color));
                        }
                ),
                new KeyFrame(Duration.millis(50),
                        e -> {
                            circle.setFill(Color.web(hexcode_color));
                        }));
        return new EdgeAnimation(timeline);
    }


}
