package runner;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import org.springframework.web.bind.annotation.*;
import tests.data_structure.Graph_tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
    public class AlgorithmController {

        private final AlgorithmRunner runner;

        public AlgorithmController(AlgorithmRunner runner) {
            this.runner = runner;
        }

   //     @PutMapping("/lk")
     //   public VisualizationResponse cleargraph(@RequestBody AlgothrimRequest){}


    //just for future reference
    //@PostMapping: adds data to server
    //@GetMapping:  retrieving data from server.
    //@PutMapping:  for updating existing resources.
   // @DeleteMapping: removing resources.
   // @PatchMapping:  partial updates to a resource (such as a specific field).

    //since we are creating new visuals each time we need to add data
    @PostMapping("/run")
        public VisualizationResponse runAlgorithm(@RequestBody AlgothrimRequest req) {

        System.out.println("Lists: " + req.lists);
        System.out.println("Matrices: " + req.matrices);
        System.out.println("Algorithm: " + req.algorithm);
        System.out.println("Nodes: " + req.nodes);
        System.out.println("Edges: " + req.edges);


            Graph graph = buildGraph(req);

            // run user JS and collect animations
            List<AnimationInstruction> animations = runner.runUserCode(req.algorithm, graph,req.lists,req.matrices);

            return new VisualizationResponse(graph, animations);
        }




    @PostMapping("/generategraph")
    public VisualizationResponse generategraph(@RequestBody GraphRequest req) {
     Graph graph=Graph_tools.randomgraph(req.number_of_nodes, req.edge_chance, req.isweighted,req.canbenegative,req.isdirected);
        return new VisualizationResponse(graph,null);
    }


    private Graph buildGraph(AlgothrimRequest req) {
       Graph graph= Graph_tools.empty_graph();

        // add nodes
        for (DTOS.NodeDTO n : req.nodes) {
            graph.addVertex(new Node(n.label));
        }

        // add edges
        for (DTOS.EdgeDTO e : req.edges) {
            Node from = graph.getVertices().get(e.fromlabel);
            Node to  = graph.getVertices().get(e.tolabel);
           //add a thing to check if edges are directed or not
          if (!e.directed){
            graph.addEdge(new Edge(from,to,e.weight));}else {
            graph.addarc(new Edge(from,to,e.weight));}
        }



        return graph;
    }
}

