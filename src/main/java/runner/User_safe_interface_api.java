package runner;

import animations.*;
import graph_theory.Edge;
import graph_theory.Node;
import javafx.application.Platform;

import java.util.Queue;

public class User_safe_interface_api {
    private  final Visual_part part;
   private Graph graph;
    private  Queue<Animations> queue;



    public User_safe_interface_api(Graph graph,Queue<Animations> queue,Visual_part part) {
   this.queue = queue;
   this.graph = graph;
   this.part = part;
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
    public void clearGraph() {
        if (graph == null) return;

        graph = null;          // model cleared
        part.clearboard();     // visuals cleared
    }



    public void arc_visiblility(Edge edge){
       if (graph.containsedge(edge)){
           return;
       }
        EdgeAnimation animation=Main.edgeInterface.makearcvisible(edge,part);
queue.add(animation);
    }

    public void highlight_node(Node  node) {
    EdgeAnimation animation = Main.edgeInterface.highlightNode(node);
     queue.add(animation);
    }

    public void begin_animations(){
        part.playNext( queue);
    }

    public void onEdgesearched(Edge e) {
        EdgeAnimation animation = Main.edgeInterface.onEdgesearched(e);
        queue.add(animation);
    }

    public void pause(int pause){
        EdgeAnimation animation = Main.edgeInterface.pause(pause);
        queue.add(animation);
    }

public void make_node_visible(Node node){
    if(!graph.containsnode(node)){
        return;

    }
        EdgeAnimation animation=Main.edgeInterface.makenodevisible(node,part);
    queue.add(animation);
    }

public void light_node(Node node){
    if(!graph.containsnode(node)){
        return;
    }
    EdgeAnimation animation=Main.edgeInterface.highlight_semi_permant(node,part);
queue.add(animation);
    }
public void delight_node(Node node){
    if(!graph.containsnode(node)){
        return;
    }
    EdgeAnimation animation=Main.edgeInterface.disable_highlights(node,part);
queue.add(animation);
    }
 public void color_node(Node node,String color){
    if(!graph.containsnode(node)){
        return;
    }
    EdgeAnimation animation=Main.edgeInterface.color_nodes(node,part,color);
    queue.add(animation);
 }

 public void highlightedge(Edge e){
     if(!graph.containsedge(e)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.highlight_edge(e,part);
     queue.add(animation);
 }

 public void disable_edge(Edge e){
     if(!graph.containsedge(e)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.disable_edge(e,part);
     queue.add(animation);
 }


 public void make_arc_invisible(Edge edge){
     if (!graph.containsedge(edge)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.makarcinvisible(edge,part);
     queue.add(animation);
 }


 public void makeedge_visible(Edge edge){
     if (!graph.containsedge(edge)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.makeedgevisible(edge,part);
     queue.add(animation);
 }

 public void makeedge_invisible(Edge edge){
     if (!graph.containsedge(edge)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.makeedgeinvisible(edge,part);
     queue.add(animation);
 }

 public void makenode_invisible(Node node){
     if (!graph.containsnode(node)){
         return;
     }
     EdgeAnimation animation=Main.edgeInterface.makenodeinvisible(node,part);
     queue.add(animation);
     for (Edge e:graph.indenctedges(node)){
         EdgeAnimation edgeAnimation=Main.edgeInterface.makeedgeinvisible(e,part);
         queue.add(edgeAnimation);
     }
 }

 private void add_node(Node node){
     part.addnode(node);
 }

 public void remove_node(Node node){
        if (!graph.containsnode(node)){
            return;
        }
        part.removenode(node);
     for (Edge edge:graph.indenctedges(node)){
         remove_edge(edge);

     }
        graph.removeVertex(node);
 }


 public void remove_edge(Edge e){
     if (!graph.containsedge(e)){
         return;
     }

        part.remove_edge(e);
        graph.removeEdge(e);
 }

 public void add_edge(Edge e){
     if (graph.containsedge(e)){
         return;
     }
     if (e.getV1()==null||e.getV2()==null){
         return;
     }
     part.addedge(e);
     graph.addEdge(e);
 }


 public void add_edge(Edge edge,float weight){
        if (graph.containsedge(edge)){
            return;
        }
     if (edge.getV1()==null||edge.getV2()==null){
         return;
     }
        part.addedge(edge,weight);
     edge.setWeight(weight);
     graph.addEdge(edge);
 }

 public void add_arc(Edge e,Visual_part part,float weight){
        if (graph.containsedge(e)){
            return;
        }
     if (e.getV1()==null||e.getV2()==null){
         return;
     }
        part.addarc(e,weight);
     e.setWeight(weight);
     graph.addarc(e);

 }

 public void remove_arc(Edge e){
        if (!graph.containsedge(e)){
            return;
        }
        if (e.getV1()==null||e.getV2()==null){
            return;
        }
        part.removearc(e);
        graph.removearc(e);
 }

 public void add_arc(Edge edge){
        if (graph.containsedge(edge)){
            return;
        }
        if (edge.getV1()==null||edge.getV2()==null){
            return;
        }
        part.addarc(edge);
        graph.addarc(edge);
 }


}
