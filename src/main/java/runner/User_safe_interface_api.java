package runner;

import animations.Animations;
import animations.GUI_interface;
import animations.Visual_part;
import graph_theory.Node;

import java.util.Queue;

public class User_safe_interface_api {
    private final Visual_part part;
    private final GUI_interface api;




    public User_safe_interface_api(Visual_part part, GUI_interface api) {
    this.part = part;
    this.api = api;
    }

    public void highlight_node(int node_id) {
      Node node=part.get_node_from_number(node_id);
      part.highlightnode(node);
    }









}
