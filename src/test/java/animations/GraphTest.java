package animations;

import graph_theory.Edge;
import graph_theory.Node;
import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

        @Test
        void getEdge() {
    Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

           Edge edge =graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
            assertEquals(edge,graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1)));

        }

        @Test
        void getEdges() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

            Graph graph2= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph2.addVertex(new Node(i));
            }
            graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph2.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph2.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph2.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

            for (int i=0;i<graph.getEdges().size();i++){
                assertEquals(graph.getEdges().get(i),graph2.getEdges().get(i));
            }

        }

        @Test
        void getVertices() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            Graph graph2= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph2.addVertex(new Node(i));
            }
            for (int i=0;i<graph.getVertices().size();i++){
                assertEquals(graph.getVertices().get(i),graph2.getVertices().get(i));
            }


        }

        @Test
        void addarc() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
           graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
            Edge arc=new Edge(graph.getVertices().get(2),graph.getVertices().get(1));
              graph.addarc(arc);
                assertTrue(graph.containsedge(arc));

        }

        @Test
        void removearc() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
            Edge arc=new Edge(graph.getVertices().get(2),graph.getVertices().get(1));
            graph.addarc(arc);
            graph.removearc(arc);
            assertFalse(graph.containsedge(arc));

        }

        @Test
        void testAddarc() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
            graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),2));
            graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),8));
            graph.addarc(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),-100));
            Edge arc=new Edge(graph.getVertices().get(2),graph.getVertices().get(1),6);
            graph.addarc(arc);
            assertTrue(graph.containsedge(arc));

        }

        @Test
       void removeEdge() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

            Edge edge =graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
           graph.addEdge(edge);
           graph.removeEdge(edge);
            assertFalse(graph.containsedge(edge));

        }

       @Test
      void removeVertex() {
           Graph graph= Graph_tools.empty_graph();
           for (int i=0;i<4;i++){
               graph.addVertex(new Node(i));
           }
           graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
           graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
           graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
           graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

          Node node =new Node(4);
           graph.addVertex(node);
           graph.removeVertex(node);
           assertFalse(graph.containsnode(node));

       }

        @Test
        void neighbors() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            //  graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
        for (Node n:graph.neighbors(graph.getVertices().get(0))){
            assertEquals(n,graph.getVertices().get(1));
        }
        }

        @Test
        void indenctedges() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            //  graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));
            for (Edge edge:graph.indenctedges(graph.getVertices().get(0))){
                assertEquals(edge,graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1)));
            }
        }

    @Test
    void addEdge() {
            Graph graph= Graph_tools.empty_graph();
            for (int i=0;i<4;i++){
                graph.addVertex(new Node(i));
            }
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
            graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
            graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

            Edge edge=new Edge(graph.getVertices().get(0),graph.getVertices().get(3));
           graph.addEdge(edge);
            assertTrue(graph.containsedge(edge));

    }

    @Test
    void testAddEdge() {
            //weighted
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),2));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2),5));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2),-4));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3),1));

        Edge edge=new Edge(graph.getVertices().get(0),graph.getVertices().get(3),2);
        graph.addEdge(edge);
        assertTrue(graph.containsedge(edge));

    }


    @Test
    void addVertex() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
     Node node  = new Node(4);
       graph.addVertex(node);
        assertTrue(graph.containsnode(node));
    }


    @Test
    void degree() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        assertEquals(2,graph.degree(graph.getVertices().get(0)));
    }

    @Test
    void isadjacent() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        assertTrue(graph.isadjacent(graph.getVertices().get(0),graph.getVertices().get(1)));
    }

    @Test
    void isincident() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        assertTrue(graph.isincident(graph.getVertices().get(0),graph.getVertices().get(1)));

    }

    @Test
    void containsnode() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        assertTrue(graph.containsnode(graph.getVertices().get(1)));
    }

    @Test
    void containsedge() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        assertTrue(graph.containsedge(graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1))));
    }

}
