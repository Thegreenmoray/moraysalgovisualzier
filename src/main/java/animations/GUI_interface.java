package animations;

import graph_theory.Edge;
import graph_theory.Node;

import java.util.List;

public interface GUI_interface {

     EdgeAnimation onEdgesearched(Edge e);
     EdgeAnimation highlightNode(Node u);
     EdgeAnimation pause(int ms);
    EdgeAnimation makenodevisible(Node node,Visual_part part);
    EdgeAnimation makeedgevisible(Edge edge,Visual_part part);
    EdgeAnimation makeedgeinvisible(Edge edge,Visual_part part);
    EdgeAnimation makenodeinvisible(Node node,Visual_part part);
    EdgeAnimation makarcinvisible(Edge edge,Visual_part part);
     EdgeAnimation makearcvisible( Edge edge, Visual_part part);
   EdgeAnimation highlight_semi_permant(Node node,Visual_part part);
    EdgeAnimation disable_highlights(Node node,Visual_part part);
    EdgeAnimation highlight_edge(Edge e, Visual_part part);
    EdgeAnimation disable_edge(Edge e,Visual_part part);
    EdgeAnimation color_nodes(Node node,Visual_part part,String Hexcode_color);
    <E> SetAnimation updatetile(List<E> list,Visual_part part,int index);
}



