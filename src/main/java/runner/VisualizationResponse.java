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


    }



