package runner;

import org.graalvm.polyglot.HostAccess;

import java.util.List;
import java.util.Map;

public class UserAPI {

    private final Map<Integer, List<Integer>> lists;
    private final Map<Integer, int[][]> matrices;
    private final List<AnimationInstruction> animations;

    public UserAPI(Map<Integer, List<Integer>> lists,
                   Map<Integer, int[][]> matrices,
                   List<AnimationInstruction> animations) {
        this.lists = lists;
        this.matrices = matrices;
        this.animations = animations;
    }

    // LIST API
    @HostAccess.Export
    public int get(int listName, int index) {
        return lists.get(listName).get(index);
    }

    @HostAccess.Export
    public void set(int listName, int index, Object value) {
        lists.get(listName).set(index, (Integer) value);
        animations.add(AnimationInstruction.listUpdate(listName, index, value));
    }
    @HostAccess.Export
    public void highlight(int listName, int index) {
        animations.add(AnimationInstruction.listHighlight(listName, index));
    }

    // MATRIX API
    @HostAccess.Export
    public int getMatrix(int name, int r, int c) {
        return matrices.get(name)[r][c];
    }
    @HostAccess.Export
    public void setMatrix(int name, int r, int c, Object value) {
        matrices.get(name)[r][c] = (int) value;
        animations.add(AnimationInstruction.matrixUpdate(name, r, c, value));
    }
    @HostAccess.Export
    public void highlightMatrix(int name, int r, int c) {
        animations.add(AnimationInstruction.matrixHighlight(name, r, c));
    }











}