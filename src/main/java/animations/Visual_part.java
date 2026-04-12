package animations;

import graph_theory.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;

import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Visual_part {
  private static Pane edgeLayer = new Pane();
   private static Pane nodeLayer = new Pane();
   private static Pane textLayer = new Pane();
   private static GridPane Squarevisuallayer=new GridPane();
    private static Map<Node,Group> corrlate=new HashMap<>();
   private static Map<Edge, javafx.scene.Node> edgeToLine =new HashMap<>();
   private static Map<Edge, Text> edgeToText =new HashMap<>();
    private int setnextID = 0;
    private int matrixnextID=0;
    private Map<Integer, List_setup> list_visuals = new HashMap<>();
    private Map<Integer,Matrix_setup> matrix_visuals = new HashMap<>();
    private VBox listStorage = new VBox(20);
    private VBox matrixStorage = new VBox(20);
    private Pane root;
   private static ArrayList<Point2D> mina_distance=new ArrayList<>();
  private static double minDist = 40;

    public Visual_part(Pane root) {
        this.root = root;

        Squarevisuallayer.add(listStorage, 0, 0);   // column 0
        Squarevisuallayer.add(matrixStorage, 1, 0); // column 1

        if (!root.getChildren().contains(Squarevisuallayer)){
            root.getChildren().add(Squarevisuallayer);}

        if (!root.getChildren().contains(edgeLayer))
            root.getChildren().addAll(edgeLayer, nodeLayer, textLayer);



    }

    public Graph visualizegraph( Graph graph,boolean isweighted, boolean isdirected) {
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

           Group f =corrlate.get(n1);
         Group k =corrlate.get(n2);

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


   //int size,E itemused
    public <E> int establishset(List<E> list) {
        if (!Squarevisuallayer.getChildren().contains(listStorage)&&!Squarevisuallayer.getChildren().contains(matrixStorage)){
            Squarevisuallayer.add(listStorage, 0, 0);   // column 0
            Squarevisuallayer.add(matrixStorage, 1, 0); // column 1
            }
        if (!root.getChildren().contains(Squarevisuallayer)){
            root.getChildren().add(Squarevisuallayer);
        }

        List_setup<E> listSetup=new List_setup<>(list,setnextID);


        int pointer=0;
        for (E e : list) {
            Rectangle rectangle=new Rectangle(25,25);

            Text text=new Text();
            text.setText(e.toString());
            rectangle.setFill(Color.GREEN);
            StackPane cell = new StackPane(rectangle, text);

            listSetup.getRoot().getChildren().add(cell);

listSetup.getCells().put(pointer,cell);
pointer++;

        }

        listStorage.getChildren().add(listSetup.getRoot());

        list_visuals.put(setnextID,listSetup);
      int to_be_returned=setnextID;
        setnextID++;

        return to_be_returned;
    };


    public <E> int establishmatrix(E[][] matrix) {
        if (!Squarevisuallayer.getChildren().contains(matrixStorage)&&!Squarevisuallayer.getChildren().contains(listStorage)){
            Squarevisuallayer.add(listStorage, 0, 0);   // column 0
            Squarevisuallayer.add(matrixStorage, 1, 0); // column 1
            }
        if (!root.getChildren().contains(Squarevisuallayer)){
            root.getChildren().add(Squarevisuallayer);
        }


        Matrix_setup<E> martixSetup=new Matrix_setup<>(matrix,setnextID);
        martixSetup.getRoot().setHgap(1);
        martixSetup.getRoot().setVgap(1);
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                Rectangle rectangle=new Rectangle(25,25);

                Text text=new Text();
                text.setText(matrix[i][j].toString());
                rectangle.setFill(Color.GREEN);
                StackPane cell = new StackPane(rectangle, text);

                martixSetup.getCells().put(new Point(i,j),cell);

                martixSetup.getRoot().add(cell,j,i);

            }

        }

        matrixStorage.getChildren().add(martixSetup.getRoot());



     int save_id=matrixnextID;
        matrix_visuals.put(matrixnextID, martixSetup);
        matrixnextID++;


        return save_id;
    };

/*
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
    }*/

    public Graph establish() {

            edgeLayer.setMouseTransparent(true);
            nodeLayer.setMouseTransparent(true);
            textLayer.setMouseTransparent(true);
            matrixStorage.setMouseTransparent(true);
            listStorage.setMouseTransparent(true);

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

    public EdgeAnimation highlightNode(Node u) {

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

    public EdgeAnimation animate_edge(Edge edge){

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
                        e -> {}),
                new KeyFrame(Duration.millis(200),
                        e ->{if (edgeToLine.containsKey(edge))
                        {edgeToLine.get(edge).setVisible(false);}
                            if (edgeToText.containsKey(edge))
                            {edgeToText.get(edge).setVisible(false);}}));


        return new EdgeAnimation(timeline);
    }

    public boolean edge_is_visible(Edge edge) {
        return edgeToLine.get(edge).isVisible();
    }

    public boolean node_is_visible(Node node) {
        return corrlate.get(node).isVisible();
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

        Bounds b = f.getBoundsInParent();
        Bounds c = k.getBoundsInParent();
        edgeToLine.put(e,arrow);

        arrow.start((float) (b.getMinX()+b.getWidth()/2), (float) (b.getMinY()+b.getHeight()/2));
        arrow.end((float) (c.getMinX() +c.getWidth()/2), (float) (c.getMinY() +c.getHeight()/2));
        Text text=new Text(""+weight);
        text.setX((b.getMinX()+c.getMinX())/2);
        text.setY((b.getMinY()+c.getMinY())/2);
        textLayer.getChildren().add(text);
        edgeLayer.getChildren().add(arrow);

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

        Animations polled = null;
       while (!time.isEmpty()&&polled==null)
       {polled =time.poll();}



           if (polled instanceof EdgeAnimation next) {


               next.timeline.setOnFinished(e -> {
                   if (next.circle != null) {
                       root.getChildren().remove(next.circle);
                   }
                   playNext(time);
               });

               next.timeline.play();

           }
           if (polled instanceof SetAnimation next){
               next.t.setOnFinished(e -> {
                   playNext(time);
               });

               next.t.play();

           }



    }

    public void clearboard() {
   edgeLayer.getChildren().clear();
   nodeLayer.getChildren().clear();
   corrlate.clear();
   edgeToLine.clear();
   edgeToText.clear();
   textLayer.getChildren().clear();
   Squarevisuallayer.getChildren().clear();
   matrix_visuals.clear();
   list_visuals.clear();
  matrixStorage.getChildren().clear();
  listStorage.getChildren().clear();
  setnextID=0;
  matrixnextID=0;
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,
                        e -> {circle.setFill(Color.web( "#0000FF"));}
                ),
                new KeyFrame(Duration.millis(100),
                        e -> {circle.setFill(Color.web("#0000FF"));}));



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

    public SetAnimation listsquarehighlight(List_setup<?> list, int index) {

       Rectangle cell = (Rectangle) list.getCells().get(index).getChildren().getFirst();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {
                            cell.setFill(Color.BROWN);
                        }
                ),
                new KeyFrame(Duration.millis(50),
                        e -> {
                          cell.setFill(Color.GREEN);
                        }));



        return new SetAnimation(timeline);
    }

    public SetAnimation pause(int pause) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {

                        }
                ),
                new KeyFrame(Duration.millis(pause),
                        e -> {

                        }));
        return new SetAnimation(timeline);
    }

    public List_setup<?> getsetid(int key) {
        return list_visuals.get(key);
    }

    public Matrix_setup<?> getmatrix(int key) {
        return matrix_visuals.get(key);
    }

    public <E>SetAnimation edit_square_value(List_setup<?> list, int index,E value) {


        Text cell = (Text) list.getCells().get(index).getChildren().get(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {

                        }
                ),
                new KeyFrame(Duration.millis(50),
                        e -> {
                            cell.setText(value.toString());
                        }));



        return new SetAnimation(timeline);
    }


    public SetAnimation highlightmatrixsquare(Matrix_setup<?> matrix,Point point) {
    Rectangle rectangle = (Rectangle) matrix.getCells().get(point).getChildren().getFirst();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {
                            rectangle.setFill(Color.BROWN);
                        }
                ),
                new KeyFrame(Duration.millis(200),
                        e -> {
                            rectangle.setFill(Color.GREEN);
                        }));

        return new SetAnimation(timeline);
    }


    public <E> SetAnimation edit_matrix_square_value(Matrix_setup<?> matrixSetup,Point point, E value) {
        Text box = (Text) matrixSetup.getCells().get(point).getChildren().get(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> {

                        }
                ),
                new KeyFrame(Duration.millis(50),
                        e -> {
                            box.setText(value.toString());
                        }));

        return new SetAnimation(timeline);
    }

}
