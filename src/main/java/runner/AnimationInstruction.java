package runner;

public class AnimationInstruction {
    public String type;   // e.g. "highlightNode", "travelEdge", "pause"

    // optional fields depending on type
    public Integer node;
    public Integer from;
    public Integer to;
    public String color;

    public Integer list;
    public Integer index;
    public Object value;

    public Integer matrix;
    public Integer row;
    public Integer col;


    public Integer ms; // for pause

    public Boolean directed; // optional, for edges

    public AnimationInstruction() {}

    public static AnimationInstruction listHighlight(int listId, int index) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "listHighlight";
        a.list = listId;
        a.index = index;
        return a;
    }


    public static AnimationInstruction listUpdate(int listId, int index, Object value) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "listUpdate";
        a.list = listId;
        a.index = index;
        a.value = value;
        return a;
    }


    public static AnimationInstruction matrixHighlight(int matrixId, int row, int col) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "matrixHighlight";
        a.matrix = matrixId;
        a.row = row;
        a.col = col;
        return a;
    }

    public static AnimationInstruction matrixUpdate(int matrixId, int row, int col, Object value) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "matrixUpdate";
        a.matrix = matrixId;
        a.row = row;
        a.col = col;
        a.value = value;
        return a;
    }

    public static AnimationInstruction highlightNode(int nodeId) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "highlightNode";
        a.node = nodeId;
        return a;
    }

    public static AnimationInstruction travelEdge(int from, int to, boolean directed) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "travelEdge";
        a.from = from;
        a.to = to;
        a.directed = directed;
        return a;
    }


    public static AnimationInstruction pause(int ms) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "pause";
        a.ms = ms;
        return a;
    }

    public static AnimationInstruction disableNode(int nodeId) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "disableNode";
        a.node = nodeId;
        return a;
    }

    public static AnimationInstruction lightNode(int nodeId) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "lightNode";
        a.node = nodeId;
        return a;
    }

    public static AnimationInstruction colorNode(int nodeId, String color) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "colorNode";
        a.node = nodeId;
        a.value = color;
        return a;
    }

    public static AnimationInstruction highlightEdge(int from, int to) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "highlightEdge";
        a.from = from;
        a.to = to;
        return a;
    }

    public static AnimationInstruction disableEdge(int from, int to) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "disableEdge";
        a.from = from;
        a.to = to;
        return a;
    }

    public static AnimationInstruction nodeVisible(int nodeId) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "nodeVisible";
        a.node = nodeId;
        return a;
    }
    public static AnimationInstruction arcInvisible(int from, int to) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "arcInvisible";
        a.from = from;
        a.to = to;
        return a;
    }

    public static AnimationInstruction edgeVisible(int from, int to) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "edgeVisible";
        a.from = from;
        a.to = to;
        return a;
    }

    public static AnimationInstruction edgeInvisible(int from, int to) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "edgeInvisible";
        a.from = from;
        a.to = to;
        return a;
    }


    public static AnimationInstruction nodeInvisible(int nodeId) {
        AnimationInstruction a = new AnimationInstruction();
        a.type = "nodeInvisible";
        a.node = nodeId;
        return a;
    }
}
