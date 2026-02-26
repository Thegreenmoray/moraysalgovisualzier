package tests.data_structure;


//to allows you to store list of colors good for bipartite,greedy, and chromatic color
public class Color_package {
    boolean bool;
    boolean[] lists;
    String[]  colors;



    public Color_package(boolean bool, boolean[] lists) {
        this.bool = bool;
        this.lists = lists;
        this.colors = null;
    }
public Color_package(boolean bool, String[] colors) {
        this.bool = bool;
        this.colors = colors;
        this.lists = null;
}



    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public boolean[] getLists() {
        return lists;
    }

    public void setLists(boolean[] lists) {
        this.lists = lists;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }



}
