package graph_theory;

import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {



    @Test
    void getV1() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

    Edge e=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));

            assertEquals(e.getV1(),graph.getVertices().get(0));

    }

    @Test
    void getV2() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(2)));
        graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        Edge e=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));

        assertEquals(e.getV2(),graph.getVertices().get(1));


    }

    @Test
    void getWeight() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
       graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),3));
        Edge edge=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));

        assertEquals(3, edge.getWeight());
    }

    @Test
    void getWeight_float() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),3));
        Edge edge=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));

        assertEquals(3.0, edge.getWeight());
    }

    @Test
    void setWeight() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),3));
        Edge edge=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
         edge.setWeight(4);
        assertEquals(4, edge.getWeight());
    }

    @Test
    void contains() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),3));
        Edge edge=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
        edge.setWeight(4);
        assertFalse(edge.contains(graph.getVertices().get(3)));
    }

    @Test
    void testEquals() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1),3));
        Edge edge=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
        edge.setWeight(4);
        Edge edge2=graph.getEdge(graph.getVertices().get(0),graph.getVertices().get(1));
        assertTrue(edge2.equals(edge));
    }


}