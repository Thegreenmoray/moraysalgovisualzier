package graph_theory;

import java.util.*;

public class Graph {
List<Edge> edges;
List<Node> vertices;
List<List<Edge>> indencent_list= createindcendcelist();
List<List<Node>> adjacencyList;


public Graph(List<Node> vertex, List<Edge> edge){
    this.edges = edge;
    this.vertices = vertex;
    indencent_list= createindcendcelist();
    adjacencyList=createadjencylist();
}

    private List<List<Node>> createadjencylist() {
        List<List<Node>> adjencylist=new ArrayList<>();
        if(this.vertices==null){
            return new ArrayList<>();
        }
        for (Node v : this.vertices) {
             adjencylist.add(adjenctnodes(v));
        }



        return adjencylist;


    }

    private List<Node> adjenctnodes(Node v) {
        Set<Node> result = new HashSet<>();
        for(Edge e:indencent_list.get(v.number)){
            Node vertex = v.equals(e.v1)? e.v2:e.v1;
            result.add(vertex);

         }
            return new ArrayList<>(result);

}

    private List<List<Edge>> createindcendcelist(){
        List<List<Edge>> incdencelist=new ArrayList<>();
        if(this.vertices==null){
            return new ArrayList<>();
        }
        for (Node v : this.vertices) {
            incdencelist.add(incidentEdges(v));
        }


        return incdencelist;
    }

    public Edge getEdge (int i, int j){

   for (Edge n:indencent_list.get(i)){
       if (n.v2.getNumber()==j){
           return n;
       }

   }
    return null;
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




    private List<Edge> incidentEdges(Node v) {
        List<Edge> result = new ArrayList<>();
        for (Edge e : this.edges) {
            if (e.v1.equals(v)) {
                result.add(e);
            }
        }
        return result;
    }




    public List<Node> neighbors(Node v){

        return adjacencyList.get(v.number);
    }



public void addEdge(Edge e){
   Node e1 = e.v1;
   Node e2 = e.v2;
    this.edges.add(e);
    this.edges.add(new Edge(e2,e1));
}
public void addEdge(Edge e,int weight){
    Node e1 = e.v1;
    Node e2 = e.v2;
    e.weight=weight;
    this.edges.add(e);
    this.edges.add(new Edge(e2,e1,weight));
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
     return indencent_list.get(v.number).size();
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











    public static Graph empty_graph(){

        return new Graph(new ArrayList<>(),new ArrayList<>());
    }





}
