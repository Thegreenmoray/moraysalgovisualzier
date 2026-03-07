package animations;

import graph_theory.Edge;
import graph_theory.Node;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Visual_partTest {
    Pane root = new Pane();
    Visual_part visualPart=new Visual_part(root);

    @Test
    void remove_edge() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
      visualPart.remove_edge(graph.getEdges().getFirst(),graph);
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

    @Test
    void removenode() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        visualPart.removenode(graph,graph.getVertices().getLast());
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<3;i++){
            graph.addVertex(new Node(i));
        }

        for (int j=0;j<graph2.getVertices().size();j++){
            assertEquals(graph.getVertices().get(j),graph2.getVertices().get(j));}
    }

    @Test
    void addedge() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addedge(graph, new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
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
    void testAddedge() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        visualPart.addedge(graph, new Edge(graph.getVertices().get(0),graph.getVertices().get(3)),5);
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        graph2.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        graph2.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(3),5));


        for (int j=0;j<graph2.getEdges().size();j++){
            assertEquals(graph.getEdges().get(j),graph2.getEdges().get(j));
        assertEquals(graph2.getEdges().get(j).getWeight(),graph.getEdges().get(j).getWeight());}
    }

    @Test
    void addnode() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        visualPart.addnode(graph,new Node(4));
        Graph graph2=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<5;i++){
            graph.addVertex(new Node(i));
        }

        for (int j=0;j<graph2.getVertices().size();j++){
            assertEquals(graph.getVertices().get(j),graph2.getVertices().get(j));}
    }

    @Test
    void addarc() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.addarc(graph, new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
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
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),5));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),5));
        visualPart.addarc(graph, new Edge(graph.getVertices().get(0),graph.getVertices().get(3)),5);
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
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        visualPart.removearc(graph,graph.getEdges().getFirst());
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
/* uncertain if I can unit test these

@Test
    void highlightNode() {
    }

    @Test
    void makeallofgraphinvisible() {
    }

    @Test
    void makeallofgraphvisible() {
    }

    @Test
    void make_node_visible() {
    }

    @Test
    void make_node_invisible() {
    }

    @Test
    void make_edge_visible() {
    }

    @Test
    void make_edge_invisible() {
    }

  @Test
    void makearcinvisible() {
    }

    @Test
    void makearcvisible() {
    }

*/





}