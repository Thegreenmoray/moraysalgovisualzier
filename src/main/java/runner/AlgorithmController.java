package runner;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import jakarta.servlet.http.HttpServletResponse;
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

    //just for future reference
    //@PostMapping: adds data to server
    //@GetMapping:  retrieving data from server.
    //@PutMapping:  for updating existing resources.
   // @DeleteMapping: removing resources.
   // @PatchMapping:  partial updates to a resource (such as a specific field).


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/run", method = RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
    }



    //since we are creating new visuals each time we need to add data
    @PostMapping("/run")
        public VisualizationResponse runAlgorithm(@RequestBody AlgothrimRequest req, HttpServletResponse response) {

            if(response!=null) {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Headers", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
           }


        System.out.println("Lists: " + req.lists);
        System.out.println("Matrices: " + req.matrices);
        System.out.println("Algorithm: " + req.algorithm);
        System.out.println("Nodes: " + req.nodes);
        System.out.println("Edges: " + req.edges);


            Graph graph = buildGraph(req);
        System.out.println("Graph Nodes: " + graph.getVertices());
        System.out.println("Graph Edges: " + graph.getEdges());
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
            System.out.println(n.id);
            System.out.println("Graph Nodes user: " + graph.getVertices());
            graph.addVertex(new Node(n.id));
        }

        // add edges
        for (DTOS.EdgeDTO e : req.edges) {
            System.out.println(e.from);
            System.out.println(e.to);

            Node from = graph.getVertexnum(e.from);
            Node to = graph.getVertexnum(e.to);
            //add a thing to check if edges are directed or not
            System.out.println("Graph Edges user: " + graph.getEdges());
            if(from == null || to==null){
                continue;
            }

            if (!e.directed) {
                graph.addEdge(new Edge(from, to, e.weight));
            } else {
                graph.addarc(new Edge(from, to, e.weight));
            }
        }



        return graph;
    }
}

