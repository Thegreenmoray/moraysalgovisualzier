package graph_theory;

public class Node {
private final int number;
private boolean isVisible;

private String hexcode_color;

    public String getHexcode_color() {
        return hexcode_color;
    }

    public void setHexcode_color(String hexcode_color) {
        this.hexcode_color = hexcode_color;
    }

    public Node(int number) {
    this.number = number;
this.isVisible = true;
    hexcode_color=null;

}

    public Node(int number,String hexcode_color) {
        this.number = number;
        this.hexcode_color = hexcode_color;
        this.isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    //wip, if it is invalid default to black and alert user
   // private boolean isvaildhexcode(String hexcode_color) {
    //    return false;}

  public int getNumber() {
        return number;}




   public boolean issamecolor(Node node) {

        if ((node.getHexcode_color()==null||this.hexcode_color ==null)){
            return false;
        }
       return node.getHexcode_color().equals(hexcode_color);
   }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Node)) return  false;

        return ((Node) obj).getNumber() == this.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
