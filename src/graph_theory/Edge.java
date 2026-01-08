package graph_theory;

public class Edge {
    Node v1;
    Node v2;
    int weight;
    public Edge(Node v1, Node v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = 1;
    }

    public Edge(Node v1, Node v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }


public boolean contains(Node v){
    return v1.equals(v) || v2.equals(v);
}


public boolean containsonly(Node v){
        return v1.number==v.number;
}

}
