package animations;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;

public class Arrow extends Group {
Line line;
Polygon arrow;
double dx=0;
double dy=0;
public Arrow() {
        this.line=new Line();
        this.arrow=new Polygon(0, 0, -6, -12, 6, -12);
    getChildren().addAll(line,arrow);
    setMouseTransparent(true);
    line.setMouseTransparent(true);
    arrow.setMouseTransparent(true);

}

    public Line getLine() {
        return line;
    }


    public Polygon getArrow() {
        return arrow;
    }


    public void start(float x,float y) {
    setLayoutX(x);
    setLayoutY(y);
}

    private void updategeopointer() {

        line.setStartX(0);
        line.setStartY(0);

        line.setEndX(dx);
        line.setEndY(dy);

        arrow.setTranslateX(dx);
        arrow.setTranslateY(dy);
        double theta=atan2(dy, dx);
        arrow.setRotate(Math.toDegrees(theta));



    }

    public void end(float x,float y) {
        dx = x - getLayoutX();
       dy = y - getLayoutY();
        double radius = 10;

        double len = Math.sqrt(dx*dx + dy*dy);
        double ux = dx / len;
        double uy = dy / len;

// shorten the arrow so it touches the circle edge
        dx -= ux * radius;
        dy -= uy * radius;


        updategeopointer();
}


}
