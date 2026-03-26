package animations;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Matrix_setup<E> extends Square_visuals{
  private   E[][] matrix;
  private   int pointer;
    private final Map<Point, StackPane> cells = new HashMap<>();

    public Matrix_setup( E[][] matrix,int pointer) {
        super(new GridPane());
        this.matrix=matrix;
        this.pointer=pointer;
    }

    public E[][]  getmatrix(){
        return matrix;
    }

    public Map<Point, StackPane> getCells() {
        return cells;
    }

    public int getPointer() {
        return pointer;
    }

    @Override
    public GridPane getRoot() {
        return (GridPane) super.getRoot();
    }
}
