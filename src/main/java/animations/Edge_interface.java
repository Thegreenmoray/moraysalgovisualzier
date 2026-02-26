package animations;

import app.EdgeAnimation;
import graph_theory.Edge;
import graph_theory.Node;

public interface Edge_interface {

     EdgeAnimation onEdgesearched(Edge e);
     EdgeAnimation highlightNode(Node u);
     EdgeAnimation pause(int ms);
     void addNode(Graph graph,Node n, Visual_part part);
     void removeNode(Graph graph,Node n,Visual_part part);
     void addEdge(Graph graph,Edge e,Visual_part part);
     void removeEdge(Graph graph,Edge e,Visual_part part);
     void setallinvisible(Visual_part part);
     void setallvisible(Visual_part part);
    void makenodevisible(Graph graph,Node node,Visual_part part);
     void makeedgevisible(Graph graph,Edge edge,Visual_part part);
     void makeedgeinvisible(Graph graph,Edge edge,Visual_part part);
     void makenodeinvisible(Graph graph,Node node,Visual_part part);
     void addarc(Graph graph,Edge e,Visual_part part);
     void removearc(Graph graph,Edge e,Visual_part part);
     void makarcinvisible(Graph graph,Edge edge,Visual_part part);
     void makearcvisible(Graph graph, Edge edge, Visual_part part);
    void addEdge(Graph graph,Edge e,Visual_part part,float weight);
    void addarc(Graph graph,Edge e,Visual_part part,float weight);
}



