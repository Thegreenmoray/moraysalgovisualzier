package animations;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;

public interface Edge_interface {

     EdgeAnimation onEdgesearched(Edge e);
     EdgeAnimation highlightNode(Node u);
     EdgeAnimation pause(int ms);
     EdgeAnimation addNode(Graph graph,Node n, Visual_part part);
     EdgeAnimation removeNode(Graph graph,Node n,Visual_part part);
     EdgeAnimation addEdge(Graph graph,Edge e,Visual_part part);
     void removeEdge(Graph graph,Edge e,Visual_part part);
     EdgeAnimation setallinvisible(Graph graph,Visual_part part);
     EdgeAnimation makenodevisible(Graph graph,Visual_part part);
     EdgeAnimation makeedgevisible(Graph graph,Visual_part part);
     EdgeAnimation makeedgeinvisible(Graph graph,Visual_part part);
     EdgeAnimation makenodeinvisible(Graph graph,Visual_part part);
}



