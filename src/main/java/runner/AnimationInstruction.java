package runner;

public class AnimationInstruction {
    public String type;   // e.g. "highlightNode", "travelEdge", "pause"

    // optional fields depending on type
    public Integer node;
    public Integer from;
    public Integer to;

    public Integer list;
    public Integer index;
    public Object value;

    public Integer matrix;
    public Integer row;
    public Integer col;

    public Integer ms; // for pause

    public Boolean directed; // optional, for edges

    public AnimationInstruction() {}


}
