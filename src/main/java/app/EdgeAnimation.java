package app;

import javafx.animation.Timeline;
import javafx.scene.shape.Circle;

    public class EdgeAnimation {
        public Timeline timeline;
        public Circle circle;

     public    EdgeAnimation(Timeline t, Circle c) {
            this.timeline = t;
            this.circle = c;
        }

   public EdgeAnimation(Timeline timeline) {
        this.timeline = timeline;
   }



    }



