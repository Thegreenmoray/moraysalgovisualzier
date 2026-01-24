import javafx.animation.Timeline;
import javafx.scene.shape.Circle;

    class EdgeAnimation {
        Timeline timeline;
        Circle circle;

        EdgeAnimation(Timeline t, Circle c) {
            this.timeline = t;
            this.circle = c;
        }

   EdgeAnimation(Timeline timeline) {
        this.timeline = timeline;
   }



    }



