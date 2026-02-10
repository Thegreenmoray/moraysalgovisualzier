package tests.data_structure;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;




 class Graph_toolsTest {

    @Test
    void generate_graph_undirected() {
                                 //one in two
   Graph graph=Graph_tools.generate_graph_undirected(4,2,false,false);

    for (Edge e:graph.getEdges()){
        assertEquals(1, e.getWeight());
    }

   assertEquals(4,graph.getVertices().size());


    }

    @Test
    void generate_graph_directed() {
        Graph graph=Graph_tools.generate_graph_directed(4,2,false,false);

        for (Edge e:graph.getEdges()){
            assertEquals(1, e.getWeight());
        }

        assertEquals(4,graph.getVertices().size());
    }
    @Test
    void arc_incident_matrix() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)));
        graph.addarc(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)));
        //no edge for 1,2, infinty
        graph.addarc(new Edge(graph.getVertices().get(1),graph.getVertices().get(3)));
        graph.addarc( new Edge(graph.getVertices().get(2),graph.getVertices().get(3)));

        float[][] testmatrix= {
                {1,1,1,0,0,0,0,0,0,0,0},
                {-1,0,0,1,0,0,0,0,0,0,0},
                {0,-1,0,0,1,0,0,0,0,0,0},
                {0,0,-1,-1,-1,0,0,0,0,0,0,0},



        };


        float[][] indence = Graph_tools.arc_incident_matrix(graph);
        for (int i=0;i<indence.length;i++){
            for (int j=0;j<indence[i].length;j++){
                assertEquals(indence[i][j], testmatrix[i][j]);
            }
        }
    }


    @Test
    void adjacency_matrix_correct_undirected() {
        Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
        for (int i=0;i<4;i++){
            graph.addVertex(new Node(i));
        }

        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)),2);
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)),-3);
        graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(3)),5);
        //no edge for 1,2, infinty
        graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(3)),1);
        graph.addEdge( new Edge(graph.getVertices().get(2),graph.getVertices().get(3)),2);

float[][] testmatrix= {{0,2,-3,5},
        {2,0,Float.POSITIVE_INFINITY,1},
        {-3,Float.POSITIVE_INFINITY,0,2},
        {5,1,2,0}};


        float[][] adjency = Graph_tools.adjacency_matrix(graph);
        for (int i=0;i<adjency.length;i++){
            for (int j=0;j<adjency[i].length;j++){
                assertEquals(adjency[i][j], testmatrix[i][j]);
            }
        }
        //caught an error with the graph class works perfect now.
    }








}