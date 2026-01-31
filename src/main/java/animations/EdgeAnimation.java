package animations;

import javafx.animation.Timeline;
import javafx.scene.shape.Circle;

    public class EdgeAnimation {
        Timeline timeline;
        Circle circle;

        EdgeAnimation(Timeline t, Circle c) {
            this.timeline = t;
            this.circle = c;
        }

   public EdgeAnimation(Timeline timeline) {
        this.timeline = timeline;
   }



    }



