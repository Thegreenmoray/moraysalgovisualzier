package tests.data_structure;

import graph_theory.Node;

import java.util.List;

public class Path_Checker {
    private List<Node> current_path;
    private boolean path_or_cycle_found;
    public Path_Checker(List<Node> current_path,boolean path_or_cycle_found) {
        this.current_path = current_path;
        this.path_or_cycle_found = path_or_cycle_found;
    }

    public List<Node> getCurrent_path() {
        return current_path;
    }

    public void setCurrent_path(List<Node> current_path) {
        this.current_path = current_path;
    }

    public boolean isPath_or_cycle_found() {
        return path_or_cycle_found;
    }

    public void setPath_or_cycle_found(boolean path_or_cycle_found) {
        this.path_or_cycle_found = path_or_cycle_found;
    }
}
