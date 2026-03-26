package tests.data_structure;

import animations.Graph;
import graph_theory.*;

import java.util.*;



public class Graph_tools {

    
private Graph_tools(){};

public static Graph empty_graph(){

    return new Graph(new ArrayList<>(),new ArrayList<>());
}


public static float[][] arc_incident_matrix(Graph graph){
   if (graph.getVertices().isEmpty()||graph.getEdges().isEmpty()){
      //not valid let user know
       return null;
   }
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
    if (graph.getVertices().isEmpty()){
        //not valid let user know
        return null;
    }
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
              Node node =graph.getVertices().get(i);
              Node ne=graph.getVertices().get(j);
             Edge edge=graph.getEdge(node,ne);
         if (edge!=null){
             adjacency_matrix[i][j]=edge.getWeight();
             continue;
         }
          adjacency_matrix[i][j]=Float.POSITIVE_INFINITY;


          }
      }
        return adjacency_matrix;
    }

public static boolean is_tree(Graph graph){
    //run a modifed dfs
   boolean istree=true;
   //assume graph is connected
       istree= dfs_treechecker(graph,graph.getVertices().getFirst(),null,new boolean[graph.getVertices().size()],istree);



    return istree;
}

private static boolean dfs_treechecker(Graph graph, Node start,Node parent, boolean[] visted,boolean istree) {
        visted[start.getNumber()]=true;



        for(Node n:graph.neighbors(graph.getVertices().get(start.getNumber()))){

            if (!istree){
                break;
            }

            if ((parent!=null&&!parent.equals(n)&&visted[n.getNumber()])){

                return false;
                //stop searching its not a tree
            }


            if(!visted[n.getNumber()]){

               istree= dfs_treechecker(graph,n,start,visted,istree);
            }






        }


        return istree;

    }

public static boolean is_complete(Graph graph){
   int amount_of_nodes=graph.getVertices().size();

   for (Node n:graph.getVertices()) {
       //by defintion all nodes in a compelte graph have n-1 edges
       if (graph.degree(n)!=amount_of_nodes-1){
         return false;
       }

   }

    return true;
}


public static List<String> random_unique_colors(Graph graph){
    HashSet<String> color_packages=new HashSet<>();
        Random rand=new Random();
        for (int i=0;i<graph.getVertices().size();i++) {

            if (color_packages.size() >= 0xFFFFFF) {
                return null; //too full.
            }
            String colors;

            do {
                int index = rand.nextInt(0xFFFFFF);
                colors = String.format("#%06X", index);
            } while (color_packages.contains(colors));
            color_packages.add(colors);
        }

    return new ArrayList<>(color_packages);
}


 public static boolean isbipartite(Graph graph){
    String cyan="#00FFFF";
    String magenta="#FF00FF";
    String yellow="#FF0000"; //defualt

        if (graph.getVertices().isEmpty()||graph.getEdges().isEmpty()||graph.getVertices().size()==1){
            return true;
        }
      int nodes=graph.getVertices().size();
//^here to prevent "contamination" of graph
for(Node n: graph.getVertices()){
    n.setHexcode_color(yellow);
}
   boolean[] lists =new boolean[nodes];
    Color_package booleanPackage = new Color_package(true, lists);
for(int i=0;i<nodes;i++) {


    if (!lists[i]&&booleanPackage.isBool()) {
        graph.getVertices().get(i).setHexcode_color(cyan);
        booleanPackage = bfs_bipartite(graph, i, lists, yellow, magenta, cyan);
        lists = booleanPackage.getLists();
    }


}

     for(Node n: graph.getVertices()){
         n.setHexcode_color(null);
     }


    return booleanPackage.isBool();
}


private static Color_package bfs_bipartite(Graph graph, int start, boolean[] visted, String defualt_color, String magenta, String cyan) {
        Queue<Node> q=new LinkedList<>();

        Node node=graph.getVertices().get(start);
        visted[node.getNumber()]=true;
        q.add(node);
        boolean isbipartite=true;
        while(!q.isEmpty()&&isbipartite){
            Node polled=q.poll();
            List<Node> neighbors = graph.neighbors(polled);

            for (Node n:neighbors) {

              if (isbipartite) {
                  isbipartite=coloring(polled,n,magenta,cyan,defualt_color);
              }else {
             break;
              }


                if(!visted[n.getNumber()]) {
                    q.add(n);
                    visted[n.getNumber()]=true;
                }

            }

        }

        return new Color_package(isbipartite,visted);
    }

private static boolean coloring(Node start,Node neighbor,String color1,String color2,String defualt_color) {



    if (start.getHexcode_color().equals(neighbor.getHexcode_color())
            &&!neighbor.getHexcode_color().equals(defualt_color)&&!start.getHexcode_color().equals(defualt_color)) {
        return false;
    }
    neighbor.setHexcode_color(start.getHexcode_color().equals(color1) ? color2:color1);
    return true;
}


public static boolean is_connected(Graph graph){
boolean isconnected=true;

boolean[] isvisited=new boolean[graph.getVertices().size()];

 isvisited=dfs(isvisited,graph,graph.getVertices().getFirst().getNumber());

 for (boolean b:isvisited) {
     if(!b){
         isconnected=false;
         break;
     }
 }

    return isconnected;
}

private static boolean[] dfs(boolean[] visted,Graph graph,int node_number) {
        visted[node_number]=true;

        for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
            if(!visted[n.getNumber()]){
                dfs(visted,graph,n.getNumber());
            }}


        return visted;
    }


    public static Graph randomgraph(int size, double edgechance, boolean isweighted, boolean canbenegative, boolean isdirected) {
        return isdirected? generate_graph_directed(size,edgechance,isweighted,canbenegative):generate_graph_undirected(size,edgechance,isweighted,canbenegative);
    }

    private static Graph generate_graph_undirected(int size,double edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 0;
        }


        edge_chance = Math.clamp(edge_chance,0,1);


        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(rand.nextFloat()<edge_chance){
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

    private static Graph generate_graph_directed(int size,double edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();


        edge_chance =Math.clamp(edge_chance,0,1);

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (i==j) {continue;}

                if (rand.nextFloat()<edge_chance) {
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


}