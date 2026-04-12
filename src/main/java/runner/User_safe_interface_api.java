package runner;

import animations.*;
import graph_theory.Edge;
import graph_theory.Min_heap;
import graph_theory.Node;
import javafx.application.Platform;
import javafx.util.Pair;
import set_theory.Set_theory_items;
import tests.data_structure.Graph_tools;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class User_safe_interface_api {
    private  final Visual_part part;
   private Graph graph;
    private  Queue<Animations> queue;
    private final Map<Integer, List_setup<?>> lists = new HashMap<>();
    private final Map<Integer, Matrix_setup<?>> matrices = new HashMap<>();

    public User_safe_interface_api(Graph graph,Queue<Animations> queue,Visual_part part) {
   this.queue = queue;
   this.graph = graph;
   this.part = part;
    }

    public List<Node> getnodes(){

        return graph.getVertices();
    }

    public List<Edge> getedges(){
        return graph.getEdges();
    }

    public Node safely_add_a_node(){
        int num = graph.nextAvailableNodeNumber();
        Node n = graph.addVertexWithNumber(num);

        Platform.runLater(() -> {


            add_node(n); // your animation command

        });
        return n;


    }

    public Node obtain_existing_node(int num){
         if (num <=-1||num>=graph.getVertices().size()){
             return null;
         }

        return graph.getVertices().get(num);
    }

    public void establishgraph() {
        Platform.runLater(() -> {
            if (graph == null) {
                graph = part.establish();
                return;
            }

            //let person know that graph has alreadly been established

        }); }

    public void clearboard() {
        if (graph == null) return;
        graph = null;          // model cleared
        // visuals cleared
        lists.clear();
        matrices.clear();
    }

    public void arc_visiblility(Edge edge){
       if (graph.containsedge(edge)){
           return;
       }
       if (part.edge_is_visible(edge)){
           return;
       }
        Platform.runLater(() -> {
        EdgeAnimation animation=part.makearcvisible(edge);
queue.add(animation);});
    }

    public void highlight_node(Node  node) {
        Platform.runLater(() -> {
        EdgeAnimation animation = part.highlightNode(node);
    queue.add(animation);});
    }

    public void begin_animations(){
        Platform.runLater(() -> {
        part.playNext( queue);});
    }

    public void traceline(Edge e) {
        Platform.runLater(() -> {
        EdgeAnimation animation = part.animate_edge(e);
        queue.add(animation);});
    }

    public void pause(int pause){
        Platform.runLater(() -> {
        SetAnimation animation = part.pause(pause);
        queue.add(animation);});
    }

public void make_node_visible(Node node){

    if(!graph.containsnode(node)){
        return;

    }
    if (part.node_is_visible(node)){
        return;
    }
    Platform.runLater(() -> {
        EdgeAnimation animation=part.make_node_visible(node);
    queue.add(animation);});
    }

public void light_node(Node node){

    if(!graph.containsnode(node)){
        return;
    }
    Platform.runLater(() -> {
    EdgeAnimation animation=part.highlightnode(node);
queue.add(animation);});
    }

public int obtain_node_number(Node node){
    if(!graph.containsnode(node)){
        return -1;
    }

        return node.getNumber();
}



public void delight_node(Node node){

    if(!graph.containsnode(node)){
        return;
    }
    Platform.runLater(() -> {
    EdgeAnimation animation=part.disablenodes(node);
queue.add(animation);});
    }

 public void color_node(Node node,String color){

    if(!graph.containsnode(node)){
        return;
    }
     Platform.runLater(() -> {
    EdgeAnimation animation=part.colornode(node,color);
    queue.add(animation);});
 }

 public void highlightedge(Edge e){

     if(!graph.containsedge(e)){
         return;
     }
     Platform.runLater(() -> {
     EdgeAnimation animation=part.highlightedge(e);
     queue.add(animation);});
 }

 public void disable_edge(Edge e){

     if(!graph.containsedge(e)){
         return;
     }
     Platform.runLater(() -> {
     EdgeAnimation animation=part.disableedge(e);
     queue.add(animation);});
 }

 public void make_arc_invisible(Edge edge){

     if (!graph.containsedge(edge)){
         return;
     }
     Platform.runLater(() -> {
     if (!part.edge_is_visible(edge)){
       return;
     }
     EdgeAnimation animation=part.makearcinvisible(edge);
     queue.add(animation);});
 }

 public void makeedge_visible(Edge edge){
     if (!graph.containsedge(edge)){
         return;
     }
     Platform.runLater(() -> {
     if (part.edge_is_visible(edge)){
         return;
     }
     EdgeAnimation animation=part.make_edge_visible(edge);
     queue.add(animation);});
 }

 public void makeedge_invisible(Edge edge){

     if (!graph.containsedge(edge)){
         return;
     }
     if (!part.edge_is_visible(edge)) {
         return;
     }Platform.runLater(() -> {
     EdgeAnimation animation=part.make_edge_invisible(edge);
     queue.add(animation);});
 }

 public void makenode_invisible(Node node){

     if (!graph.containsnode(node)){
         return;
     }

     if (!part.node_is_visible(node)){
         return;
     }Platform.runLater(() -> {
     EdgeAnimation animation=part.make_node_invisible(node);
     queue.add(animation);
     for (Edge e:graph.indenctedges(node)){
         EdgeAnimation edgeAnimation=part.make_edge_invisible(e);
         queue.add(edgeAnimation);
     }});
 }

 private void add_node(Node node){
     part.addnode(node);
 }

 public void remove_node(Node node){

        if (!graph.containsnode(node)){
            return;
        }
    List<Edge> edges= graph.indenctedges(node);

        for (Edge e:edges){
            graph.removeEdge(e);
        }

     graph.removeVertex(node);

     Platform.runLater(() -> {
        part.removenode(node);
      for (Edge edge:edges){
    part.remove_edge(edge); }

     }
        );
 }

 public void remove_edge(Edge e){

     if (!graph.containsedge(e)){
         return;
     }
     graph.removeEdge(e);
     Platform.runLater(() -> {
        part.remove_edge(e);
        });
 }

 public Edge safely_add_edge(Node node,Node node2){
     if (node == null || node2 == null) return null;
     if (node == node2) return null;


     if (!graph.containsnode(node) || !graph.containsnode(node2)) return null;


     if (graph.isadjacent(node, node2)||graph.isadjacent(node2,node)) return null;

Edge edge=new Edge(node,node2);

     graph.addEdge(edge);
     Platform.runLater(() -> {
         add_edge(edge);
     });
     return edge;
 }
//note undirected should call this twice since all edges are directed
public Edge get_speficedge(Node node1,Node node2){
    Edge e = graph.getEdge(node1, node2);
    if (e != null) return e;

    return graph.getEdge(node2, node1);

}

 private void add_edge(Edge e){
     part.addedge(e);
 }

public Edge safely_add_edge(Node node,Node node2,float weight){
        if (node == null || node2 == null) return null;
        if (node == node2) return null;


        if (!graph.containsnode(node) || !graph.containsnode(node2)) return null;


        if (graph.isadjacent(node, node2)||graph.isadjacent(node2,node)) return null;

        Edge edge=new Edge(node,node2,weight);

        graph.addEdge(edge);
        Platform.runLater(() -> {
            add_edge(edge,weight);
        });
        return edge;
    }

 private void add_edge(Edge edge,float weight){
        part.addedge(edge,weight);
 }

 private void add_arc(Edge e,float weight){
        part.addarc(e,weight);
 }

 public void remove_arc(Edge e){
     Platform.runLater(() -> {
        if (!graph.containsedge(e)){
            return;
        }
        if (e.getV1()==null||e.getV2()==null){
            return;
        }
        part.removearc(e);
        graph.removearc(e);});
 }

 public Edge safely_add_arc(Node node,Node node2){
     if (node == null || node2 == null) return null;
     if (node == node2) return null;


     if (!graph.containsnode(node) || !graph.containsnode(node2)) return null;


     if (graph.isadjacent(node, node2)) return null;

     Edge edge=new Edge(node,node2);

     graph.addarc(edge);
     Platform.runLater(() -> {
         add_arc(edge);
     });
     return edge;
 }

public Edge safely_add_arc(Node node,Node node2,float weight){
        if (node == null || node2 == null) return null;
        if (node == node2) return null;


        if (!graph.containsnode(node) || !graph.containsnode(node2)) return null;


        if (graph.isadjacent(node, node2)) return null;

        Edge edge=new Edge(node,node2,weight);

        graph.addarc(edge,weight);
        Platform.runLater(() -> {
            add_arc(edge,weight);
        });
        return edge;
    }

 private void add_arc(Edge edge){
        part.addarc(edge);

 }

 public Edge getspeficarc(Node node,Node node2){

     return graph.getEdge(node, node2);
 }

 public List<Node> neighbors(Node node){
if (!graph.containsnode(node)){
    return new ArrayList<>();
}
     return new ArrayList<>(graph.neighbors(node));
 }

 public List<Edge> indence_edges(Node node1){
        if (!graph.containsnode(node1)){
            return new ArrayList<>();
        }
     return new ArrayList<>(graph.indenctedges(node1));
 }

 public int degree(Node node1){
     if (!graph.containsnode(node1)){
         return 0;
     }
     return graph.degree(node1);
 }

 public boolean isadjenct(Node node1,Node node2){
    if (!graph.containsnode(node1)||!graph.containsnode(node2)){
        return false;
    }
    return graph.isadjacent(node1,node2);
}

public boolean isincident(Node node1,Edge edge){
 if (!graph.containsnode(node1)||!graph.containsedge(edge)){
        return false;
    }
    return graph.isincident(node1,edge);
}

public boolean is_bipartite(){
    return Graph_tools.isbipartite(graph);
}

public boolean is_tree(){
        return Graph_tools.is_tree(graph);
}

public boolean is_compelte(){
        return Graph_tools.is_complete(graph);
}

public boolean is_connected(){
        return Graph_tools.is_connected(graph);
}

public float[][] adjacency_matrix(){

    return Graph_tools.adjacency_matrix(graph);
}

public float[][] incident_matrix(){
        return Graph_tools.arc_incident_matrix(graph);
}

public List<String> generate_colors(){

    return Graph_tools.random_unique_colors(graph);
}

public List<Edge> createheap(){

    return new ArrayList<>(graph.getEdges());
}

public List<Edge> add_to_heap(Edge e, List<Edge> edges){
    List<Edge> sanisatized = new ArrayList<>(edges);

        if (!graph.containsedge(e)){
            return edges;
        }
    return Min_heap.add_to_heap(e,sanisatized);
}

public Edge extract_from_heap(List<Edge> edges){
    List<Edge> sanisatized = new ArrayList<>(edges);
        return Min_heap.extract_from_heap(sanisatized);
}

public <E> List<E> complement(List<E> univerisal_set,List<E> list){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized_uni = new ArrayList<>(univerisal_set);
    return Set_theory_items.complement(sanisatized_uni,sanisatized);
}

public <E> List<E> union(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.union(sanisatized,sanisatized1);
}

public <E> List<E> intersection(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);

        return Set_theory_items.intersection(sanisatized,sanisatized1);
}

public <E> List<E> difference(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.difference(sanisatized,sanisatized1);
}

public <E> List<E> symmetric_difference(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.symmetric_difference(sanisatized,sanisatized1);
}

public <T,K> List<Pair<T,K>> cartiesan_product(List<T> list,List<K> list1){
    List<T> sanisatized = new ArrayList<>(list);
    List<K> sanisatized1 = new ArrayList<>(list1);
    return Set_theory_items.cartesian_product(sanisatized,sanisatized1);
}

public <E> List<List<E>> Powerset(List<E> list){
    List<List<E>> powerset=new ArrayList<>();
    List<E> sanisatized = new ArrayList<>(list);
    return Set_theory_items.powerset(0,sanisatized,powerset,new ArrayList<>());
}

public <E> boolean issubset(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.isSubset(sanisatized,sanisatized1);
}

public <E> boolean ispropersubset(List<E> list,List<E> list1){
    List<E> sanisatized = new ArrayList<>(list);
    List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.isProperSubset(sanisatized,sanisatized1);
}

public void graph_random(int size, double edgechance, boolean isweighted, boolean canbenegative, boolean isdirected){
   clearboard();

    this.graph = Graph_tools.randomgraph(size, edgechance, isweighted, canbenegative, isdirected);


    Platform.runLater(() -> {
        part.visualizegraph(graph,isweighted,isdirected);
    });


}

public void makegraphinvisible(){

    Platform.runLater(() -> {
        part.makeallofgraphinvisible(graph);
    });
}

public void makegraphvisible(){

        Platform.runLater(() -> {
            part.makeallofgraphvisible(graph);
        });
    }

public <E> int establishlist(List<E> list) {
    CountDownLatch latch = new CountDownLatch(1);
    final int[] result = new int[1];
    List<E> safeCopy = new ArrayList<>(list);


    Platform.runLater(() -> {

        int key =part.establishset(safeCopy);
   lists.put(key,part.getsetid(key));
        result[0] = key;
        latch.countDown();
    });

    try {
        latch.await();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
    }



    return result[0];
}
public <E> int establishmatrix(E[][] matrix){
    CountDownLatch latch = new CountDownLatch(1);
    final int[] result = new int[1];
E[][] safematrix=deepCopyMatrix(matrix);
    Platform.runLater(() -> {


        int key =part.establishmatrix(safematrix);
        matrices.put(key,part.getmatrix(key));
        result[0] = key;
        latch.countDown();
    });

    try {
        latch.await();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
    }

    return result[0];
}

private <E> E[][] deepCopyMatrix(E[][] original) {
        return Arrays.stream(original)
                .map(row -> Arrays.copyOf(row, row.length))
                .toArray(size -> Arrays.copyOf(original, size));
    }

public void highlight_list_square(int index,int id){
    if (index <=-1||index>=lists.size()){
        return;
    }
        List_setup<?> list =  lists.get(id);


    if (list.getList().size() <= index) {
        return;
    }

    Platform.runLater(() -> {
     SetAnimation animation =part.listsquarehighlight(list,index);
     queue.add(animation);
    });
}

public <E>void edit_list_square_value(int index,int id,E value) {
   if (index <=-1||index>=lists.size()){
       return;
   }


    List_setup<?> list = lists.get(id);
    if (list == null) {
        return;
    }

@SuppressWarnings("unchecked")
  List_setup<E> getittowork=(List_setup<E>)list;

    getittowork.getList().set(index,value);

    Platform.runLater(() -> {
        SetAnimation animation = part.edit_square_value(list, index, value);
        queue.add(animation);
    });
}

public void highlight_matrix_square(int row,int col,int id){
     if (id>=matrices.size()||id<-1){
         return;
     }
   Matrix_setup<?> matrixSetup=matrices.get(id);

    int rows=matrixSetup.getmatrix().length;
   int cols=matrixSetup.getmatrix()[0].length;
   if (rows < row || cols < col|| row < 0 || col < 0){
       return ;
   }
    Platform.runLater(() -> {
        SetAnimation animation = part.highlightmatrixsquare(matrixSetup,new Point(row,col));
        queue.add(animation);
    });
}

public <E>void edit_matrix_square_value(int row,int col,int id,E value) {
        if (id>=matrices.size()||id<-1){
            return;
        }
        Matrix_setup<?> matrixSetup=matrices.get(id);


        int rows=matrixSetup.getmatrix().length;
        int cols=matrixSetup.getmatrix()[0].length;
        if (rows < row || cols < col|| row < 0 || col < 0){
            return ;
        }

        @SuppressWarnings("unchecked")
        Matrix_setup<E> typed = (Matrix_setup<E>) matrixSetup;
        typed.getmatrix()[row][col] = value;



        Platform.runLater(() -> {
            SetAnimation animation = part.edit_matrix_square_value(matrixSetup,new Point(row,col), value);
            queue.add(animation);
        });
    }

}
