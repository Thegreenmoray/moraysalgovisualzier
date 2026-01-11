package graph_theory;

public class Edge {
    Node v1;
    Node v2;
    int weight;
    EdgeState edgeState;
    public Edge(Node v1, Node v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = 1;
        edgeState=EdgeState.DEFAULT;
    }

    public Edge(Node v1, Node v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
        edgeState=EdgeState.DEFAULT;
    }

    public EdgeState getEdgeState() {
        return edgeState;
    }
public void setEdgeState(EdgeState edgeState) {
        this.edgeState = edgeState;
}
    public Node getV1() {
        return v1;
    }

    public void setV1(Node v1) {
        this.v1 = v1;
    }

    public Node getV2() {
        return v2;
    }

    public void setV2(Node v2) {
        this.v2 = v2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean contains(Node v){
    return v1.equals(v) || v2.equals(v);
}


public boolean containsonly(Node v){
        return v1.number==v.number;
}

}
