package runner;

import graph_theory.Graph;

import java.util.List;

public class VisualizationResponse {
        Graph graph;
         List<AnimationInstruction> animations;
   public VisualizationResponse(Graph graph, List<AnimationInstruction> animations) {
       this.graph = graph;
       this.animations = animations;
   }
    public Graph getGraph() {
        return graph;
    }

    public List<AnimationInstruction> getAnimations() {
        return animations;
    }

    }



