package graph_theory;

import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class Node {
int number;
int weight;
VertexState vertexState;

public Node(int number) {
    this.number = number;
    vertexState=VertexState.DEFAULT;
}

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
        vertexState=VertexState.DEFAULT;
    }


    public VertexState getVertexState() {
        return vertexState;
    }
public void setVertexState(VertexState vertexState) {
    this.vertexState = vertexState;
}
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }





    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Node)) return  false;

        return ((Node) obj).number == this.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
