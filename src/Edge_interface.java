import graph_theory.Edge;
import graph_theory.Node;
import javafx.animation.Timeline;

public interface Edge_interface {

     EdgeAnimation onEdgesearched(Edge e);
     void highlightNode(Node u);
     void pause(int ms);


}



