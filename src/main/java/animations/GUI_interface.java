package animations;

import graph_theory.Edge;
import graph_theory.Node;

import java.util.List;

public interface GUI_interface {

     EdgeAnimation onEdgesearched(Edge e);
     EdgeAnimation highlightNode(Node u);
     EdgeAnimation pause(int ms);
     void addNode(Graph graph,Node n, Visual_part part);
     void removeNode(Graph graph,Node n,Visual_part part);
     void addEdge(Graph graph,Edge e,Visual_part part);
     void removeEdge(Graph graph,Edge e,Visual_part part);
     void setallinvisible(Graph graph,Visual_part part);
     void setallvisible(Graph graph,Visual_part part);
    EdgeAnimation makenodevisible(Graph graph,Node node,Visual_part part);
     void makeedgevisible(Graph graph,Edge edge,Visual_part part);
     void makeedgeinvisible(Graph graph,Edge edge,Visual_part part);
     void makenodeinvisible(Graph graph,Node node,Visual_part part);
     void addarc(Graph graph,Edge e,Visual_part part);
     void removearc(Graph graph,Edge e,Visual_part part);
     void makarcinvisible(Graph graph,Edge edge,Visual_part part);
     EdgeAnimation makearcvisible(Graph graph, Edge edge, Visual_part part);
    void addEdge(Graph graph,Edge e,Visual_part part,float weight);
    void addarc(Graph graph,Edge e,Visual_part part,float weight);
    EdgeAnimation highlight_semi_permant(Node node,Visual_part part);
    EdgeAnimation disable_highlights(Node node,Visual_part part);
    EdgeAnimation highlight_edge(Edge e, Visual_part part);
    EdgeAnimation disable_edge(Edge e,Visual_part part);
    EdgeAnimation color_nodes(Node node,Visual_part part,String Hexcode_color);
    <E> SetAnimation updatetile(List<E> list,Visual_part part,int index);
}



