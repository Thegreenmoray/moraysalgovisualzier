package runner;

import org.junit.jupiter.api.Test;

public class animationTest {


    @Test
    void tests() {
        AnimationInstruction.listHighlight(0,0);
        AnimationInstruction.colorNode(0,"#000000");
        AnimationInstruction.listUpdate(0,0,"cat");
        AnimationInstruction.matrixHighlight(0,0,0);
        AnimationInstruction.disableEdge(0);
        AnimationInstruction.disableNode(0);
        AnimationInstruction.edgeVisible(0);
        AnimationInstruction.edgeInvisible(0);
        AnimationInstruction.matrixUpdate(0,0,0,"9");
        AnimationInstruction.nodeInvisible(0);
        AnimationInstruction.nodeVisible(0);
        AnimationInstruction.lightNode(0);
        AnimationInstruction.highlightNode(0);
        AnimationInstruction.highlightEdge(0);
        AnimationInstruction.pause(0);

    }

}
