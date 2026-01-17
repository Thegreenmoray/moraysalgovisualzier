import graph_theory.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

import java.util.*;



public class Graph_algothrims {


  public boolean[] bfs(Graph graph, int start, boolean[] visted, Edge_interface graphTheoryInterface
                       // ,Vertex_interface vertex_interface
  , Visual_part visualPart) {
      Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
Queue<Node> q=new LinkedList<>();

Node node=graph.getVertices().get(start);
visted[node.getNumber()]=true;
q.add(node);
while(!q.isEmpty()){
   Node polled=q.poll();
 List<Node> neighbors = graph.neighbors(polled);

    for (Node n:neighbors) {

        if(!visted[n.getNumber()]){
            Edge edge=graph.getEdge(polled.getNumber(),n.getNumber());
           timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
          q.add(n);
           visted[n.getNumber()]=true;
        }

    }

}
visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);

      return visted;
  }



  public void dijkstra(Graph graph, int start,boolean[] visted, int[] distance, Visual_part visualPart,ArrayList<Edge> heap) {

      distance[start]=0;
      visted[start]=true;







  }




    public void dfs(Graph graph, boolean[] visted,int node_number,Edge_interface graphTheoryInterface){
visted[node_number]=true;

for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
    if(!visted[n.getNumber()]){
        Edge edge=graph.getEdge(graph.getVertices().get(node_number).getNumber(),n.getNumber());
        graphTheoryInterface.onEdgesearched(edge);
        dfs(graph,visted,n.getNumber(),graphTheoryInterface);
    }
}


  }


    public int[][] floyd_warshall(int[][] adjacency_matrix,Graph graph,Edge_interface graphTheoryInterface){
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                    Edge edgeij=graph.getEdge(i,j);
                    Edge edgeik=graph.getEdge(j,k);
                    Edge edgekj=graph.getEdge(k,j);

graphTheoryInterface.onEdgesearched(edgeij);
graphTheoryInterface.onEdgesearched(edgeik);
graphTheoryInterface.onEdgesearched(edgekj);

                    adjacency_matrix[i][j] = Math.min(adjacency_matrix[i][j], adjacency_matrix[i][k]+adjacency_matrix[k][j]);
                }
            }
        }

        return adjacency_matrix;
    }




    public void component_analysis_bfs(Graph graph, Edge_interface graphTheoryInterface,boolean[] visted,Visual_part visualPart)  {
      ArrayList<Node> node= new ArrayList<>( graph.getVertices());

      for(int i=0;i<node.size();i++){
          if(!visted[node.get(i).getNumber()]){

             visted=bfs(graph,i,visted,graphTheoryInterface,visualPart);
          }
      }

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
