package animations;

import javafx.animation.Timeline;
import javafx.scene.shape.Circle;

    public class EdgeAnimation extends Animations {
        public Timeline timeline;
        public Circle circle;

     public EdgeAnimation(Timeline t, Circle c) {
         super(t);
         this.timeline = t;
            this.circle = c;
        }

   public EdgeAnimation(Timeline timeline) {
       super(timeline);
       this.timeline = timeline;
   }



    }



