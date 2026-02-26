package graph_theory;

public class Node {
int number;
int weight;
VertexState vertexState;
String hexcode_color;

    public String getHexcode_color() {
        return hexcode_color;
    }

    public void setHexcode_color(String hexcode_color) {
        this.hexcode_color = hexcode_color;
    }

    public Node(int number) {
    this.number = number;
    vertexState=VertexState.DEFAULT;
    hexcode_color="#000000";

}

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
        vertexState=VertexState.DEFAULT;
        hexcode_color="#000000";
    }
    public Node(int number, int weight,String hexcode_color) {
        this.number = number;
        this.weight = weight;
        vertexState=VertexState.DEFAULT;
        this.hexcode_color = hexcode_color;
    }
    public Node(int number,String hexcode_color) {
        this.number = number;
        vertexState=VertexState.DEFAULT;
        this.hexcode_color = hexcode_color;
    }

    //wip, if it is invalid default to black and alert user
    private boolean isvaildhexcode(String hexcode_color) {
        return false;
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



   public boolean issamecolor(Node node) {

       return node.getHexcode_color().equals(hexcode_color);
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
