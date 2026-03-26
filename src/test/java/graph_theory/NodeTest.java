package graph_theory;

import animations.Graph;
import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void getHexcode_color() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.getVertices().getFirst().setHexcode_color("#0000FF");
        assertTrue( graph.getVertices().getFirst().getHexcode_color().equals("#0000FF"));
    }

    @Test
    void setHexcode_color() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.getVertices().getFirst().setHexcode_color("#0000FF");
        assertTrue( graph.getVertices().getFirst().getHexcode_color().equals("#0000FF"));
    }


    @Test
    void getNumber() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        assertEquals(0,graph.getVertices().getFirst().getNumber());
    }


    @Test
    void issamecolor() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        graph.getVertices().getFirst().setHexcode_color("#0000FF");
        Node node1= new Node(5);
        assertFalse(node1.issamecolor(graph.getVertices().getFirst()));
    }

    @Test
    void testEquals() {
        Graph graph= Graph_tools.empty_graph();
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }
        Node node1= new Node(0);
        assertTrue(node1.equals(graph.getVertices().getFirst()));
    }



}