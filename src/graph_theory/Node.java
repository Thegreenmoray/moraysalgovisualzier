package graph_theory;

public class Node {
int number;
int weight;
public Node(int number) {
    this.number = number;
}

    public Node(int number, int weight) {
        this.number = number;
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
