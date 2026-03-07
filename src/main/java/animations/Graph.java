package animations;

import graph_theory.Edge;
import graph_theory.Node;

import java.util.*;

public class Graph {
List<Edge> edges;
List<Node> vertices;
HashMap<Node,List<Edge>> indencent_list;
 HashMap<Node,List<Node>> adjacencyList;


public Graph(List<Node> vertex, List<Edge> edge){
    this.edges = edge;
    this.vertices = vertex;
    indencent_list= createindcendcelist();
    adjacencyList=createadjencylist();
}

    private HashMap<Node,List<Node>> createadjencylist() {
        HashMap<Node,List<Node>> adjencylist=new HashMap<>();
        if(this.vertices==null){
            return new HashMap<>();
        }
        for (Node v : this.vertices) {
             adjencylist.put(v,adjenctnodes(v));
        }



        return adjencylist;


    }

    private List<Node> adjenctnodes(Node v) {
        Set<Node> result = new HashSet<>();
        for(Edge e:indenctedges(v)){
            Node vertex = v.equals(e.getV1())? e.getV2() : e.getV1();
            result.add(vertex);

         }
            return new ArrayList<>(result);

}

    private HashMap<Node,List<Edge>> createindcendcelist(){
        HashMap<Node,List<Edge>> incdencelist=new HashMap<>();
        if(this.vertices==null){
            return new HashMap<>();
        }
        for (Node v : this.vertices) {
            incdencelist.put(v,incidentEdges(v));
        }


        return incdencelist;
    }

    public Edge getEdge (Node v1, Node v2){
if (indenctedges(v1).isEmpty()){
    return null;
}

   for (Edge n:indenctedges(v1)){
       if (n.getV2().equals(v2)){
           return n;}}

    return null;
}

public    List<Edge> getEdges(){
    return this.edges;
}
public List<Node> getVertices(){
    return this.vertices;
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
            if (e.getV1().equals(v)) {
                result.add(e);
            }
        }
        return result;
    }




    public List<Node> neighbors(Node v){

        return adjacencyList.getOrDefault(v,Collections.emptyList());
    }

public List<Edge> indenctedges(Node v){

    return indencent_list.getOrDefault(v,Collections.emptyList());
}

public void addEdge(Edge e){

    Node v1 = e.getV1();
    Node v2 = e.getV2();

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
    for(Edge e:indenctedges(v)){
        edges.remove(e);
    }

}


    private void updateadjencylist() {
   adjacencyList=createadjencylist();
    }

    private void updateincidentedges() {
    indencent_list= createindcendcelist();
    }

    public void addEdge(Edge e,float weight,boolean allowdulipcates,int pointer){

        Node v1 = e.getV1();
        Node v2 = e.getV2();
        e.setWeight(weight);

        // Ensure vertices exist
        if (!vertices.contains(v1)) addVertex(v1);
        if (!vertices.contains(v2)) addVertex(v2);


       if (allowdulipcates){
        //should you need a multigraph here it is
           for (Edge existing : edges) {
        if (existing.connects(v1, v2)){

            edges.add(new Edge(v1, v2,weight,pointer));
            edges.add(new Edge(v2, v1, weight,pointer));
            updateincidentedges();
            updateadjencylist();
            return;
        }}}
        // Prevent duplicates without indexing
        for (Edge existing : edges) {
            if (existing.connects(v1, v2)) {
                return;
            }
        }

        edges.add(new Edge(v1, v2, weight,pointer));
        edges.add(new Edge(v2, v1, weight,pointer));

        updateincidentedges();
        updateadjencylist();


    }

    public void addEdge(Edge e,float weight,boolean allowdulipcates){

        Node v1 = e.getV1();
        Node v2 = e.getV2();
        e.setWeight(weight);

        // Ensure vertices exist
        if (!vertices.contains(v1)) addVertex(v1);
        if (!vertices.contains(v2)) addVertex(v2);


        if (allowdulipcates){
            //should you need a multigraph here it is
            for (Edge existing : edges) {
                if (existing.connects(v1, v2)){

                    edges.add(new Edge(v1, v2,weight));
                    edges.add(new Edge(v2, v1, weight));
                    updateincidentedges();
                    updateadjencylist();
                    return;
                }}}
        // Prevent duplicates without indexing
        for (Edge existing : edges) {
            if (existing.connects(v1, v2)) {
                return;
            }
        }

        edges.add(new Edge(v1, v2, weight));
        edges.add(new Edge(v2, v1, weight));

        updateincidentedges();
        updateadjencylist();


    }

    public void addVertex(Node v){
    if (!this.containsnode(v)){
    this.vertices.add(v);}

}
public void removeEdge(Edge e){
    Node e1 = e.getV1();
    Node e2 = e.getV2();
    this.edges.remove(e);
    this.edges.remove(getEdge(e2, e1));
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
     return indenctedges(v).size();
 }

public boolean isadjacent(Node v1, Node v2){
    for (Node node:neighbors(v1)){
      if (node.equals(v2)){
          return true;
      }
    }
    return false;
}

 public boolean isincident(Node v1, Node v2){

    for(Edge e1:indenctedges(v1)){
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
        return getEdge(e.getV1(), e.getV2())!=null;
    }


}
