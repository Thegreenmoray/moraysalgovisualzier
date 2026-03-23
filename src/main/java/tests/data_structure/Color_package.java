package tests.data_structure;


//to allows you to store list of colors good for bipartite,greedy, and chromatic color
public class Color_package {
    boolean bool;
    boolean[] lists;




    public Color_package(boolean bool, boolean[] lists) {
        this.bool = bool;
        this.lists = lists;
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


}
