import graph_theory.Edge;
import graph_theory.Node;
import javafx.animation.Timeline;

public interface Edge_interface {

     EdgeAnimation onEdgesearched(Edge e);
     EdgeAnimation highlightNode(Node u);
     EdgeAnimation pause(int ms);


}



