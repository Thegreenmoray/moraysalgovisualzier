package graph_theory;

import set_theory.Set_theory_items;

import java.util.*;

public class Graph {
List<Edge> edges;
List<Node> vertices;
List<List<Edge>> indencent_list;
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
        int idx = vertices.indexOf(v);
        Set<Node> result = new HashSet<>();
        for(Edge e:indencent_list.get(idx)){
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
if (indencent_list.isEmpty()){
    return null;
}

   for (Edge n:indencent_list.get(i)){
       if (n.v2.getNumber()==j){
           return n;}}

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
    if (!edges.contains(e)){
    this.edges.add(e);
    updateincidentedges();
    updateadjencylist();}

}


public void removearc(Edge e){

    this.edges.remove(e);
    updateincidentedges();
    updateadjencylist();
}

    public void addarc(Edge e,int weight){
    if (!edges.contains(e)){
       e.setWeight(weight);
        this.edges.add(e);
        updateincidentedges();
        updateadjencylist();}

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

public List<Edge> indenctedges(Node v){

    return indencent_list.get(v.number);
}

public void addEdge(Edge e){

    Node v1 = e.v1;
    Node v2 = e.v2;

    // Ensure vertices exist
    if (!vertices.contains(v1)) addVertex(v1);
    if (!vertices.contains(v2)) addVertex(v2);

    // Prevent duplicates without indexing
    for (Edge existing : edges) {
        if (existing.connects(v1, v2)) {
            return;
        }
    }

    edges.add(e);
    edges.add(new Edge(v2, v1));

    updateincidentedges();
    updateadjencylist();

}

private void deletednodesedges(Node v){
    for(Edge e:indencent_list.get(v.number)){
        edges.remove(e);
    }

}


    private void updateadjencylist() {
   adjacencyList=createadjencylist();
    }

    private void updateincidentedges() {
    indencent_list= createindcendcelist();
    }

    public void addEdge(Edge e,float weight){

        Node v1 = e.v1;
        Node v2 = e.v2;
        e.weight = weight;

        // Ensure vertices exist
        if (!vertices.contains(v1)) addVertex(v1);
        if (!vertices.contains(v2)) addVertex(v2);

        // Prevent duplicates without indexing
        for (Edge existing : edges) {
            if (existing.connects(v1, v2)) {
                return;
            }
        }

        edges.add(e);
        edges.add(new Edge(v2, v1, weight));

        updateincidentedges();
        updateadjencylist();


    }







    public void addVertex(Node v){
    if (!this.containsnode(v)){
    this.vertices.add(v);}

}
public void removeEdge(Edge e){
    Node e1 = e.v1;
    Node e2 = e.v2;
    this.edges.remove(e);
    this.edges.remove(getEdge(e2.number,e1.number));
    updateincidentedges();
    updateadjencylist();

}
public void removeVertex(Node v){
  deletednodesedges(v);
   this.vertices.remove(v);
  updateadjencylist();
  updateincidentedges();
}

 public int degree(Node v){
     return indencent_list.get(v.number).size();
 }

public boolean isadjacent(Node v1, Node v2){
    for (Node node:adjacencyList.get(v1.number)){
      if (node.equals(v2)){
          return true;
      }
    }
    return false;
}

 public boolean isincident(Node v1, Node v2){

    for(Edge e1:indencent_list.get(v1.number)){
        if (e1.getV2().getNumber()==v2.getNumber()){
          return true;
        }
    }

     return false;
 }


public boolean containsnode(Node v1){
for(Node node:this.vertices){
    if (node.equals(v1)){
        return true;
    }
}
    return false;
}


    public boolean containsedge(Edge e) {
        return getEdge(e.v1.number,e.v2.number)!=null;
    }
}
