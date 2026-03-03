package tests.data_structure;

import graph_theory.Edge;
import animations.Graph;
import graph_theory.GraphTestHelper;
import graph_theory.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


 class Example_problemsTest {
    @Test
     void bubble_sort_test() {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(9);
        list.add(2);


        ArrayList<Integer> bubble_sorted = (ArrayList<Integer>) Example_problems.bubble_sort(list);
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(9);


        for (int i=0;i<test.size();i++){
            assertEquals(test.get(i),bubble_sorted.get(i));
        }

    }
    @Test
    void quicksort() {
    }

    @Test
    void prim() {
       Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
       for (int i=0;i<5;i++){
          graph.addVertex(new Node(i));
       }
       graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)),10,false);
       graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)),20,false);
       graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(4)),10,false);
       graph.addEdge(new Edge(graph.getVertices().get(1),graph.getVertices().get(3)),50,false);
       graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(4)),33,false);
       graph.addEdge(new Edge(graph.getVertices().get(2),graph.getVertices().get(3)),20,false);
       graph.addEdge(new Edge(graph.getVertices().get(3),graph.getVertices().get(4)),20,false);


      Graph prim=Example_problems.Prim(graph,null,null, true);
      int expected=60;
      int total=0;
      for(int i=0;i<prim.getEdges().size();i++){
         total+=prim.getEdges().get(i).getWeight();
      }
      assertEquals(expected,total);

    }


    @Test
    void floyd_warshall() {
       Graph graph=new Graph(new ArrayList<>(),new ArrayList<>());
       for (int i=0;i<3;i++){
          graph.addVertex(new Node(i));
       }

       graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(1)),2,false,0);
       graph.addEdge(new Edge(graph.getVertices().get(0),graph.getVertices().get(2)),3,false,1);
       //no edge for 1,2, infinty

       float[][] testmatrix= {{0,2,3},
               {2,0,5},
               {3,5,0}};


       float[][] adjency = Example_problems.Floyd_Warshall(Graph_tools.adjacency_matrix(graph),graph,null,null);
       for (int i=0;i<adjency.length;i++){
          for (int j=0;j<adjency[i].length;j++){
             assertEquals(adjency[i][j], testmatrix[i][j]);
          }
       }

    }

    @Test
    void component_analysis() {
   Graph graph= GraphTestHelper.generate_graph_undirected(4,1,false,false);
   boolean[] visited=new boolean[graph.getVertices().size()];
        Arrays.fill(visited, true);

    boolean[] bfs_test=Example_problems.component_analysis(graph,null,new boolean[graph.getVertices().size()],null,true);

    assertArrayEquals(bfs_test,visited);

       boolean[] dfs_test=Example_problems.component_analysis(graph,null,new boolean[graph.getVertices().size()],null,false);

assertArrayEquals(dfs_test,visited);
    }

    @Test
    void unbounded_knapsack() {
    }

    @Test
    void binary_knapsack() {
    }

    @Test
     void indepedent_set(){

        List<Edge> edges=new ArrayList<>();
        List<Node> nodes=new ArrayList<>();

        nodes.add(new Node(0));
        nodes.add(new Node(1));
        nodes.add(new Node(2));
        nodes.add(new Node(3));
        nodes.add(new Node(4));
        edges.add(new Edge(nodes.get(0),nodes.get(1)));
        edges.add(new Edge(nodes.get(0),nodes.get(3)));
        edges.add(new Edge(nodes.get(1),nodes.get(4)));
        edges.add(new Edge(nodes.get(2),nodes.get(3)));
        edges.add(new Edge(nodes.get(3),nodes.get(4)));


        Graph graph=new Graph(nodes,edges);


        ArrayList<Node> nodes1= (ArrayList<Node>) Example_problems.independent_set(graph,null,null);
      assertEquals(3,nodes1.size());


    }
}