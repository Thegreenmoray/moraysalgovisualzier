package graph_theory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
List<Edge> edges;
List<Node> vertices;
List<List<Node>> adjacenty_list=createadjacentylist();







public Graph(List<Node> vertex, List<Edge> edge){
    this.edges = edge;
    this.vertices = vertex;
}




public int weight(Edge e){
    return e.weight;
}

public  List<Edge> getEdges(){
    return this.edges;
}
public List<Node> getVertices(){
    return this.vertices;
}

public void setEdges(List<Edge> edges) {
    this.edges = edges;
}

public void setVertices(List<Node> vertices) {
    this.vertices = vertices;
}

public void addarc(Edge e){
    this.edges.add(e);
}


public void removearc(Edge e){
    this.edges.remove(e);
}


public List<List<Node>> createadjacentylist(){
List<List<Node>> adjacenylist=new ArrayList<>();
   if(this.vertices==null){
       return new ArrayList<>();
   }

    for (Node v : this.vertices){
       adjacenylist.add(neighbors(v));
   }
    return adjacenylist;
}





public List<Node> neighbors(Node v){
Set<Node> result = new HashSet<Node>();
   for(Edge e:this.edges){
      if (e.contains(v)){
      Node vertex = v.equals(e.v1)? e.v2:e.v1;
         result.add(vertex);

      }
   }
    return new ArrayList<>(result);
}



public void addEdge(Edge e){
   Node e1 = e.v1;
   Node e2 = e.v2;
    this.edges.add(e);
    this.edges.add(new Edge(e2,e1));
}
public void addVertex(Node v){
    this.vertices.add(v);
}
public void removeEdge(Edge e){
    Node e1 = e.v1;
    Node e2 = e.v2;
    this.edges.remove(e);
    this.edges.remove(new Edge(e2,e1));
}
public void removeVertex(Node v){
   this.vertices.remove(v);
}

 public int degree(Node v){
    int degree=0;

    for(Edge e:edges){
       if(e.containsonly(v)){
           degree++;
       }
    }
     return degree;
 }

public boolean isadjacent(Node v1, Node v2){
    for (Edge e:edges){
        if(e.contains(v1)&&e.contains(v2)){
            return true;
        }
    }
    return false;
}

 public boolean isincident(Node v, Edge e){

     return e.contains(v);
 }








}
