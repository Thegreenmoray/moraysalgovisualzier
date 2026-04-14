package animations;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class Visual_partTest {
   public static Visual_part visualPart=new Visual_part(new Pane());
    static {
        Platform.startup(() -> {});
    }
    @Test
    void remove_edge() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
          visualPart.addnode(new Node(i));
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        visualPart.addedge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        visualPart.addedge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        visualPart.addedge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addedge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
       graph.removeEdge(graph.getEdges().getFirst());
      visualPart.remove_edge(graph.getEdges().getFirst());
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph2.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph2.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        for (int j=0;j<graph2.getEdges().size();j++){
        assertEquals(graph.getEdges().get(j),graph2.getEdges().get(j));}
    }
/*
    @Test
    void complement_graph() {

        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));


     Graph graph_c = visualPart.complement_graph(graph);

     Graph expected=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            expected.addVertex(new Node(i));
        }
        expected.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        expected.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(3)));

for (int i = 0; i < expected.getEdges().size(); i++) {
    assertEquals(graph_c.getEdges().get(i),expected.getEdges().get(i));
}




    }
*/
    @Test
    void removenode() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
           visualPart.addnode(new Node(i));
        }

        visualPart.removenode(graph.getVertices().getLast());
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<3;i++){
            graph.addVertex(new Node(i));
        }

        for (int j=0;j<graph2.getVertices().size();j++){
            assertEquals(graph.getVertices().get(j),graph2.getVertices().get(j));}
    }

    @Test
    void addedge() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
           visualPart.addnode(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        visualPart.addedge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        visualPart.addedge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        visualPart.addedge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addedge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        graph.addEdge( new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        visualPart.addedge( new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph2.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph2.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));


        for (int j=0;j<graph2.getEdges().size();j++){
            assertEquals(graph.getEdges().get(j),graph2.getEdges().get(j));}
    }



    @Test
    void addnode() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        visualPart.addnode(new Node(4));
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<5;i++){
            graph.addVertex(new Node(i));
        }

        for (int j=0;j<graph2.getVertices().size();j++){
            assertEquals(graph.getVertices().get(j),graph2.getVertices().get(j));}
    }

    @Test
    void addarc() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            visualPart.addnode(new Node(i));
            graph.addVertex(new Node(i));
        }
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        visualPart.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        visualPart.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addarc( new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph2.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph2.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));


        for (int j=0;j<graph2.getEdges().size();j++) {
            assertEquals(graph.getEdges().get(j), graph2.getEdges().get(j));
        }
    }

    @Test
    void testAddarc() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            visualPart.addnode(new Node(i));
            graph.addVertex(new Node(i));
        }
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        visualPart.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        visualPart.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)),5);
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)),5);
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        graph2.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        graph2.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3),5));


        for (int j=0;j<graph2.getEdges().size();j++){
            assertEquals(graph.getEdges().get(j),graph2.getEdges().get(j));
            assertEquals(graph2.getEdges().get(j).getWeight(),graph.getEdges().get(j).getWeight());}
    }

    @Test
    void removearc() {
        Graph graph=visualPart.establish();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
            visualPart.addnode(new Node(i));
        }
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        visualPart.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
       visualPart.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        graph.removearc(graph.getEdges().getFirst());
        visualPart.removearc(graph.getEdges().getFirst());
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph2.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph2.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        for (int j=0;j<graph2.getEdges().size();j++){
            assertEquals(graph.getEdges().get(j),graph2.getEdges().get(j));}
    }


   @Test
    void highlightNode() {
     Graph graph=visualPart.establish();

         graph.addVertex(new Node(0));
         visualPart.addnode(new Node(0));
         visualPart.highlightnode(graph.getVertices().getFirst());

    }

    @Test
    void makeallofgraphinvisible() {
    }

    @Test
    void makeallofgraphvisible() {
    }

    @Test
    void make_node_invisible() {
        Graph graph=visualPart.establish();

        graph.addVertex(new Node(0));
        visualPart.addnode(new Node(0));
        visualPart.make_node_invisible(graph.getVertices().getFirst());
        visualPart.make_node_visible(graph.getVertices().getFirst());

    }

    @Test
    void make_edge_invisible() {
        Graph graph=visualPart.establish();
        for (int i=0;i<2;i++){
            graph.addVertex(new Node(i));
            visualPart.addnode(new Node(i));
        }
    Edge edge =new Edge(graph.getVertices().get(0),graph.getVertices().get(1));
       graph.addEdge(edge);
        visualPart.addedge(edge);
        visualPart.make_edge_invisible(edge);
        visualPart.make_edge_visible(edge);

    }


    @Test
    void makearcvisible() {
        Graph graph=visualPart.establish();
        for (int i=0;i<2;i++){
            graph.addVertex(new Node(i));
            visualPart.addnode(new Node(i));
        }
        Edge edge =new Edge(graph.getVertices().get(0),graph.getVertices().get(1));
        graph.addarc(edge);
        visualPart.addedge(edge);
        visualPart.makearcinvisible(edge);
        visualPart.makearcvisible(edge);


    }


    @Test
    void addedgetwo() {
        Graph graph=visualPart.establish();
        for (int i=0;i<2;i++){
            graph.addVertex(new Node(i));
            visualPart.addnode(new Node(i));
        }
        Edge edge =new Edge(graph.getVertices().get(0),graph.getVertices().get(1),5);
        graph.addEdge(edge);
        visualPart.addedge(edge,5);
        visualPart.make_edge_invisible(edge);
        visualPart.make_edge_visible(edge);

    }
@Test
void animate_edge(){
        Graph graph=visualPart.establish();
    for (int i=0;i<2;i++){
        graph.addVertex(new Node(i));
        visualPart.addnode(new Node(i));
    }
    Edge edge =new Edge(graph.getVertices().get(0),graph.getVertices().get(1),5);
    graph.addEdge(edge);
    visualPart.addedge(edge,5);
    Queue<Animations> queue=new LinkedList<>();
    EdgeAnimation edgeAnimation =visualPart.animate_edge(edge);
   queue.add(edgeAnimation);
    visualPart.playNext(queue);
}
@Test
void visualizegraph(){
    Graph graph=visualPart.establish();
    for (int i=0;i<2;i++){
        graph.addVertex(new Node(i));
    }

    for (int i=0;i<2;i++){
        for (int j=i+1;j<2;j++){
            graph.addEdge(new Edge(graph.getVertices().get(i),graph.getVertices().get(j)));
        }
    }
    visualPart.visualizegraph(graph,true,false);

}
@Test
void disableedgeandnodes(){
    Graph graph=visualPart.establish();
    for (int i=0;i<2;i++){
        graph.addVertex(new Node(i));
        visualPart.addnode(new Node(i));
    }

    for (int i=0;i<2;i++){
        for (int j=i+1;j<2;j++){
            graph.addEdge(new Edge(graph.getVertices().get(i),graph.getVertices().get(j)));
        visualPart.addedge(new Edge(graph.getVertices().get(i),graph.getVertices().get(j)));
        }
    }
visualPart.colornode(graph.getVertices().getFirst(),"#06e02d");
    visualPart.disableedge(graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1)));
    visualPart.disablenodes(graph.getVertices().getFirst());

}
@Test
void set_and_matrices(){
     ArrayList<Integer> nums=new ArrayList<>();
     Boolean[][] booleans=new Boolean[2][2];
     for (int i=0;i<2;i++){
         for (int j=0;j<2;j++){
             booleans[i][j]=true;
         }
     }
    nums.add(0);
    nums.add(1);
    visualPart.establishset(nums);
    visualPart.establishmatrix(booleans);
}
@Test
void pointers(){
    Boolean[][] booleans=new Boolean[2][2];
    for (int i=0;i<2;i++){
        for (int j=0;j<2;j++){
            booleans[i][j]=true;
        }
    }
        Matrix_setup<Boolean> integerMatrixSetup=new Matrix_setup<>(booleans,0);
  int g = integerMatrixSetup.getPointer();

    ArrayList<Integer> nums=new ArrayList<>();
    nums.add(0);
    nums.add(1);
    List_setup<Integer> numies=new List_setup<>(nums,0);
    int f=numies.getId();
}


@Test
void extranode(){
    Graph graph=visualPart.establish();
    for (int i=0;i<2;i++){
        graph.addVertex(new Node(i));
        visualPart.addnode(new Node(i));
    }

    for (int i=0;i<2;i++){
        for (int j=i+1;j<2;j++){
            graph.addEdge(new Edge(graph.getVertices().get(i),graph.getVertices().get(j)));
       visualPart.addedge(new Edge(graph.getVertices().get(i),graph.getVertices().get(j)));
        }
    }

    visualPart.highlightedge(graph.getEdges().getFirst());
    visualPart.highlightnode(graph.getVertices().getFirst());
    visualPart.highlightNode(graph.getVertices().getFirst());
    visualPart.node_is_visible(graph.getVertices().getFirst());
    visualPart.edge_is_visible(graph.getEdges().getFirst());
    visualPart.makeallofgraphinvisible(graph);
    visualPart.makeallofgraphvisible(graph);



    }
@Test
    void setoperations(){
        ArrayList<Integer> nums=new ArrayList<>();
        Boolean[][] booleans=new Boolean[2][2];
        for (int i=0;i<2;i++){
            for (int j=0;j<2;j++){
                booleans[i][j]=true;
            }
        }
        nums.add(0);
        nums.add(1);
    int key=  visualPart.establishset(nums);
    int key2  = visualPart.establishmatrix(booleans);
      Queue<Animations> queue=new LinkedList<>();
   List_setup<?> numies=visualPart.getsetid(key);

    Matrix_setup<?> booleanMatrixSetup=visualPart.getmatrix(key2);
numies.getList();
booleanMatrixSetup.getmatrix();
      SetAnimation pause = visualPart.pause(10);
   SetAnimation setmodel=visualPart.listsquarehighlight(numies,key);
   SetAnimation setchange=visualPart.edit_square_value(numies,key,2);
    SetAnimation matrixmodel=visualPart.highlightmatrixsquare(booleanMatrixSetup,new Point(0,0));
    SetAnimation matrixchange=visualPart.edit_matrix_square_value(booleanMatrixSetup,new Point(0,0),false);
   queue.add(pause);
   visualPart.playNext(queue);
    }



}