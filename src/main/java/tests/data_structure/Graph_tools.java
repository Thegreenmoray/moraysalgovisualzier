package tests.data_structure;

import graph_theory.*;

import java.util.*;



public class Graph_tools {
    


    public static Graph generate_graph_undirected(int size,int edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 1;
        }

        if(edge_chance <= 0){
            edge_chance = 1;
        }

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(rand.nextInt(edge_chance)==0){
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


    public static Graph generate_graph_directed(int size,int edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 1;
        }

        if(edge_chance <= 0){
            edge_chance = 1;
        }

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (i==j) {continue;}

                    if (rand.nextInt(edge_chance) == 0) {
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



public static Graph empty_graph(){

    return new Graph(new ArrayList<>(),new ArrayList<>());
}


public static float[][] arc_incident_matrix(Graph graph){
    return arc_incident_matrix_creation(new float[graph.getVertices().size()][graph.getEdges().size()],graph);
}

private static float[][] arc_incident_matrix_creation(float[][] arc_incident_matrix,Graph graph){
    int arcs=graph.getEdges().size();
        for (int i = 0; i < arcs; i++) {
    Edge edge=graph.getEdges().get(i);
    int v1=edge.getV1().getNumber();
    int v2=edge.getV2().getNumber();

    arc_incident_matrix[v1][i]=edge.getWeight();
    arc_incident_matrix[v2][i]=-edge.getWeight();

} //still arcs just not stated as such


    return arc_incident_matrix;
}


    public static float[][] adjacency_matrix(Graph graph){
        return adjacency_matrix_creation(new float[graph.getVertices().size()][graph.getVertices().size()],graph);
    }

private static float[][] adjacency_matrix_creation(float[][] adjacency_matrix,Graph graph){
     int nodes=graph.getVertices().size();
      for(int i=0;i<nodes;i++){
          for(int j=0;j<nodes;j++){
              if (i==j){
                  adjacency_matrix[i][j]=0;
                  continue;
              }
             Edge edge=graph.getEdge(i,j);
         if (edge!=null){
             adjacency_matrix[i][j]=edge.getWeight();
             continue;
         }
          adjacency_matrix[i][j]=Float.POSITIVE_INFINITY;


          }
      }
        return adjacency_matrix;
    }



}
