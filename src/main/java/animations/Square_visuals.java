package animations;

import javafx.scene.Node;

abstract class Square_visuals {
    final private Node root;
    public Square_visuals(Node root){
        this.root = root;
    }


    public Node getRoot() {
        return root;
    }
}
