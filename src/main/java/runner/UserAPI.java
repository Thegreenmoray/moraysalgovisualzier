package runner;

import org.graalvm.polyglot.HostAccess;

import java.util.List;
import java.util.Map;

public class UserAPI {

    private final Map<Integer, List<Object>> lists;
    private final Map<Integer, Object[][]> matrices;
    private final List<AnimationInstruction> animations;

    public UserAPI(Map<Integer, List<Object>> lists,
                   Map<Integer, Object[][]> matrices,
                   List<AnimationInstruction> animations) {
        this.lists = lists;
        this.matrices = matrices;
        this.animations = animations;
    }

    // LIST API
    @HostAccess.Export
    public Object get(int listName, int index) {

        if(lists.get(listName) == null|| matrices.get(listName).length>=index) {
            return null;
        }

        return lists.get(listName).get(index);
    }

    @HostAccess.Export
    public void set(int listName, int index, Object value) {
        if(lists.get(listName) == null|| matrices.get(listName).length>=index) {
            return;
        }
        lists.get(listName).set(index, value);
        animations.add(AnimationInstruction.listUpdate(listName, index, value));
    }
    @HostAccess.Export
    public void highlight(int listName, int index) {
        if(lists.get(listName) == null|| matrices.get(listName).length>=index) {
           return;
        }
        animations.add(AnimationInstruction.listHighlight(listName, index));
    }

    // MATRIX API
    @HostAccess.Export
    public Object getMatrix(int name, int r, int c) {
       if(matrices.get(name) == null|| r>=matrices.get(name).length||
               c>=matrices.get(name)[0].length||0>r||0>c) {
           return null;
       }


        return matrices.get(name)[r][c];
    }
    @HostAccess.Export
    public void setMatrix(int name, int r, int c, Object value) {
        if(matrices.get(name) == null|| r>=matrices.get(name).length||
                c>=matrices.get(name)[0].length||0>r||0>c) {
            return;
        }

        matrices.get(name)[r][c] = value;
        animations.add(AnimationInstruction.matrixUpdate(name, r, c, value));
    }
    @HostAccess.Export
    public void highlightMatrix(int name, int r, int c) {
        if(matrices.get(name) == null|| r>=matrices.get(name).length||
                c>=matrices.get(name)[0].length||0>r||0>c) {
            return;
        }

        animations.add(AnimationInstruction.matrixHighlight(name, r, c));
    }











}