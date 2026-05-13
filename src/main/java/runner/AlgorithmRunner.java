package runner;

//import animations.*;

//import javafx.application.Platform;
import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Min_heap;
import graph_theory.Node;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.ProxyExecutable;
import org.springframework.stereotype.Component;
import tests.data_structure.Graph_tools;

import java.util.*;

@Component
public class AlgorithmRunner {


    private Context newSandbox() {
   return Context.newBuilder("js")
    .allowHostAccess(HostAccess.EXPLICIT)
      .allowHostClassLookup(AlgorithmRunner::isSafeClass)
      .allowIO(false)
    .allowCreateThread(false)
    .allowNativeAccess(false)
   .allowAllAccess(false)
    .option("engine.WarnInterpreterOnly", "false").build();

    }


    public static boolean isSafeClass(String className) {
        // Whitelist approach for maximum security
        Set<String> allowedPrefixes = Set.of(
                "java.lang.Object",
                "java.lang.String",
                "java.lang.Math",
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Boolean",
                "java.lang.Byte",
                "java.lang.Character",
                "java.lang.Short",
                "java.math.BigInteger",
                "java.math.BigDecimal",
                "java.util.ArrayList",
                "java.util.HashMap",
                "java.util.HashSet",
                "java.util.Arrays",
                "java.util.Collections"
        );

        // Check if class is in whitelist
        for (String allowed : allowedPrefixes) {
            if (className.equals(allowed) || className.startsWith(allowed + "$")) {
                return true;
            }
        }


        if (className.startsWith("com.oracle.truffle.")) return true;
        if (className.startsWith("org.graalvm.")) return true;


        return false;
    }



    public List<AnimationInstruction> runUserCode(String algorithm,
                                                  Graph graph,Map<Integer, List<Integer>> lists,
                                                  Map<Integer, int[][]> matrices) {
        List<AnimationInstruction> animations = new ArrayList<>();

        Context sandbox = newSandbox();

        // 1. Expose animation functions to JS
        exposeAnimationAPI(sandbox, animations,graph);

        UserAPI api = new UserAPI(lists, matrices, animations);
        sandbox.getBindings("js").putMember("API", api);
        // 3. Wrap user code in a function
        String wrapped = "function run() {\n" + algorithm + "\n}";

        // 4. Evaluate and execute
        sandbox.eval("js", wrapped);
        sandbox.eval("js", "run()");

        return animations;
    }
    private void exposeAnimationAPI(Context sandbox, List<AnimationInstruction> animations,Graph graph) {

        sandbox.getBindings("js").putMember("highlightNode", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            animations.add(AnimationInstruction.highlightNode(nodeId));
            return null;
        });

        sandbox.getBindings("js").putMember("travelEdge", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            boolean directed = args.length > 2 && args[2].asBoolean();
            animations.add(AnimationInstruction.travelEdge(from, to, directed));
            return null;
        });




        sandbox.getBindings("js").putMember("neighbors", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            List<Integer> neigh =  new ArrayList<>();

            for (Node node : graph.neighbors(graph.getVertices().get(nodeId))) {
                neigh.add(node.getNumber());
            }

            return neigh.toArray();
        });
        sandbox.getBindings("js").putMember("pause", (ProxyExecutable) args -> {
            int ms = args[0].asInt();
            animations.add(AnimationInstruction.pause(ms));
            return null;
        });

        sandbox.getBindings("js").putMember("indence_edges",
                (ProxyExecutable) args ->{
                    int nodeId = args[0].asInt();

                   return graph.indenctedges(graph.getVertices().get(nodeId)).toArray();
        });

        sandbox.getBindings("js").putMember("degree",
                (ProxyExecutable) args ->{
                    int nodeId = args[0].asInt();

                    return graph.degree(graph.getVertices().get(nodeId));
                });

        sandbox.getBindings("js").putMember("isadjenct", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            if (!graph.containsnode(graph.getVertices().get(to))||!graph.containsnode(graph.getVertices().get(from))){
                return false;
            }
            return graph.isadjacent(graph.getVertices().get(from),graph.getVertices().get(to));

        });

        sandbox.getBindings("js").putMember("isincident", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            if (!graph.containsnode(graph.getVertices().get(to))||!graph.containsedge(graph.getEdge(graph.getVertices().get(from),graph.getVertices().get(to)))){
                return false;
            }
            return graph.isincident(graph.getVertices().get(from),graph.getEdge(graph.getVertices().get(from),graph.getVertices().get(to)));

        });

        sandbox.getBindings("js").putMember("isbipartite", (ProxyExecutable) args -> Graph_tools.isbipartite(graph));

        sandbox.getBindings("js").putMember("istree", (ProxyExecutable) args -> Graph_tools.is_tree(graph));

        sandbox.getBindings("js").putMember("iscompelte", (ProxyExecutable) args -> Graph_tools.is_complete(graph));

        sandbox.getBindings("js").putMember("isconnected", (ProxyExecutable) args -> Graph_tools.is_connected(graph));

        sandbox.getBindings("js").putMember("adjacencymatrix", (ProxyExecutable) args -> Graph_tools.adjacency_matrix(graph));

        sandbox.getBindings("js").putMember("incidentmatrix", (ProxyExecutable) args -> Graph_tools.arc_incident_matrix(graph));

        sandbox.getBindings("js").putMember("generatecolors", (ProxyExecutable) args -> Graph_tools.random_unique_colors(graph));

        sandbox.getBindings("js").putMember("getnodes", (ProxyExecutable) args -> graph.getVertices());

        sandbox.getBindings("js").putMember("getedge", (ProxyExecutable) args -> graph.getEdges());

        sandbox.getBindings("js").putMember("light_node", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            animations.add(AnimationInstruction.lightNode(nodeId));
            return null;
        });

        sandbox.getBindings("js").putMember("delight_node", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            animations.add(AnimationInstruction.disableNode(nodeId));
            return null;
        });

        sandbox.getBindings("js").putMember("color_node", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            String color = args[1].asString();
            animations.add(AnimationInstruction.colorNode(nodeId, color));
            return null;
        });

        sandbox.getBindings("js").putMember("highlightedge", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            animations.add(AnimationInstruction.highlightEdge(from, to));
            return null;
        });

        sandbox.getBindings("js").putMember("disable_edge", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            animations.add(AnimationInstruction.disableEdge(from, to));
            return null;
        });

        sandbox.getBindings("js").putMember("make_node_visible", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            animations.add(AnimationInstruction.nodeVisible(nodeId));
            return null;
        });

        sandbox.getBindings("js").putMember("make_arc_invisible", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            animations.add(AnimationInstruction.arcInvisible(from, to));
            return null;
        });

        sandbox.getBindings("js").putMember("makeedge_invisible", (ProxyExecutable) args -> {
            int from = args[0].asInt();
            int to = args[1].asInt();
            animations.add(AnimationInstruction.edgeInvisible(from, to));
            return null;
        });

        sandbox.getBindings("js").putMember("makenode_invisible", (ProxyExecutable) args -> {
            int nodeId = args[0].asInt();
            animations.add(AnimationInstruction.nodeInvisible(nodeId));

            for (Edge e : graph.indenctedges(graph.getVertices().get(nodeId))) {
                animations.add(AnimationInstruction.edgeInvisible(
                        e.getV1().getNumber(),
                        e.getV2().getNumber()
                ));
            }

            return null;
        });


        // Add more animation functions here...
    }

/*
    public List<Edge> createheap(){

        return new ArrayList<>(graph.getEdges());
    }

    public List<Edge> add_to_heap(Edge e, List<Edge> edges){
        List<Edge> sanisatized = new ArrayList<>(edges);

        if (!graph.containsedge(e)){
            return edges;
        }
        return Min_heap.add_to_heap(e,sanisatized);
    }

    public Edge extract_from_heap(List<Edge> edges){
        List<Edge> sanisatized = new ArrayList<>(edges);
        return Min_heap.extract_from_heap(sanisatized);
    }



    public <E> List<E> complement(List<E> univerisal_set,List<E> list){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized_uni = new ArrayList<>(univerisal_set);
        return Set_theory_items.complement(sanisatized_uni,sanisatized);
    }

    public <E> List<E> union(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.union(sanisatized,sanisatized1);
    }

    public <E> List<E> intersection(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);

        return Set_theory_items.intersection(sanisatized,sanisatized1);
    }

    public <E> List<E> difference(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.difference(sanisatized,sanisatized1);
    }

    public <E> List<E> symmetric_difference(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.symmetric_difference(sanisatized,sanisatized1);
    }


    public <E> List<List<E>> Powerset(List<E> list){
        List<List<E>> powerset=new ArrayList<>();
        List<E> sanisatized = new ArrayList<>(list);
        return Set_theory_items.powerset(0,sanisatized,powerset,new ArrayList<>());
    }

    public <E> boolean issubset(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.isSubset(sanisatized,sanisatized1);
    }

    public <E> boolean ispropersubset(List<E> list,List<E> list1){
        List<E> sanisatized = new ArrayList<>(list);
        List<E> sanisatized1 = new ArrayList<>(list1);
        return Set_theory_items.isProperSubset(sanisatized,sanisatized1);
    }

 }
*/
}