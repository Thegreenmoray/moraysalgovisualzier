package animations;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Arrow extends Group {
Line line;
Polygon arrow;
public Arrow(Line line,Polygon arrow) {
        this.line=line;
        this.arrow=arrow;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Polygon getArrow() {
        return arrow;
    }

    public void setArrow(Polygon arrow) {
        this.arrow = arrow;
    }
}
