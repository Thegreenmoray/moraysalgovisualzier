package graph_theory;

import java.util.Objects;

public class Edge {
  private final Node v1;
 private final Node v2;
private     float weight;





    int pointer;



    public Edge(Node v1, Node v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = 1;


    }

    public Edge(Node v1, Node v2, float weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;


    }

    public Edge(Node v1, Node v2, float weight, int pointer) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;

        this.pointer=pointer;
    }

    public int getPointer() {
        return pointer;
    }

    public Node getV1() {
        return v1;
    }



    public Node getV2() {
        return v2;
    }



    public int getWeight() {
        return (int)weight;
    }

    public float getWeight_float() {

        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean contains(Node v){
    return v1.equals(v) || v2.equals(v);
}



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge e = (Edge)obj;

        return v1.equals(e.v1) &&
                v2.equals(e.v2);
    }




    public boolean connects(Node a, Node b) {
        return (v1.equals(a) && v2.equals(b)) ||
                (v1.equals(b) && v2.equals(a));
    }


    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }

}
