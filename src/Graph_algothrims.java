import graph_theory.*;

import java.util.*;

public class Graph_algothrims {


  public void bfs(Graph graph){
boolean[] visted=new boolean[graph.getVertices().size()];
Queue<Node> q=new LinkedList<>();

Node node=graph.getVertices().getFirst();
visted[node.getNumber()]=true;
q.add(node);
while(!q.isEmpty()){
   Node polled=q.poll();
 List<Node> neighbors = graph.neighbors(polled);

    for (Node n:neighbors) {
        if(!visted[n.getNumber()]){
           q.add(n);
           visted[n.getNumber()]=true;
        }
    }

}

  }

  public void dfs(Graph graph, boolean[] visted,int node_number){
visted[node_number]=true;

for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
    if(!visted[n.getNumber()]){
        dfs(graph,visted,n.getNumber());
    }
}


  }


    public int[][] floyd_warshall(int[][] adjacency_matrix,Graph graph){
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                    adjacency_matrix[i][j] =
                            Math.min(adjacency_matrix[i][j],
                                    adjacency_matrix[i][k]+adjacency_matrix[k][j]);
                }
            }
        }

        return adjacency_matrix;
    }





    public Graph generate_graph_undirected(int size,int edge_chance,boolean isweighted,boolean can_be_negative_weight){
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




    public int[][] adjacency_matrix(int[][] adjacency_matrix,Graph graph){
      for(int i=0;i<graph.getVertices().size();i++){
          for(int j=0;j<graph.getVertices().size();j++){
              if (i==j){
                  adjacency_matrix[i][j]=0;
                  continue;
              }
             Edge edge=graph.getEdge(i,j);
         if (edge!=null){
             adjacency_matrix[i][j]=edge.getWeight();
             continue;
         }
          adjacency_matrix[i][j]=1000000;


          }
      }
        return adjacency_matrix;
    }




}
