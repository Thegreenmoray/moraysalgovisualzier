package animations;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_setup<E> extends Square_visuals{
   private int id;
   private List<E> list;
    private final Map<Integer, StackPane> cells = new HashMap<>();


    public List_setup( List<E> list, int id) {
        super(new HBox());
        this.list=list;
        this.id = id;
    }

    @Override
    public HBox getRoot() {
        return (HBox) super.getRoot();
    }

    public List<E> getList(){
        return list;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, StackPane> getCells() {
        return cells;
    }
}
