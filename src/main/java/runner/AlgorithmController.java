package runner;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import org.springframework.web.bind.annotation.*;
import tests.data_structure.Graph_tools;

import java.util.ArrayList;
import java.util.List;

@RestController
    public class AlgorithmController {

        private final AlgorithmRunner runner;

        public AlgorithmController(AlgorithmRunner runner) {
            this.runner = runner;
        }

   //     @DeleteMapping("/lk")
     //   public VisualizationResponse cleargraph(@RequestBody AlgothrimRequest){}


    //just for future reference
    //@PostMapping: adds data to server
    //@GetMapping:  retrieving data from server.
    //@PutMapping:  for updating existing resources.
   // @DeleteMapping: removing resources.
   // @PatchMapping:  partial updates to a resource (such as a spefiec field).

    //since we are creating new visuals each time we need to add data
    @PostMapping("/run")
        public VisualizationResponse runAlgorithm(@RequestBody AlgothrimRequest req) {

            Graph graph = buildGraph(req);

            // run user JS and collect animations
            List<AnimationInstruction> animations = runner.runUserCode(req.algorithm, graph);

            return new VisualizationResponse(graph, animations);
        }

    private Graph buildGraph(AlgothrimRequest req) {
       Graph graph= Graph_tools.empty_graph();

        // add nodes
        for (DTOS.NodeDTO n : req.nodes) {
            graph.addVertex(new Node(n.id));
        }

        // add edges
        for (DTOS.EdgeDTO e : req.edges) {
            Node from = graph.getVertices().get(e.from);
            Node to   = graph.getVertices().get(e.to);
           //add a thing to check if edges are directed or not
            graph.addEdge(new Edge(from,to,e.weight));
        }
        return graph;
    }
}

