package runner;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import org.graalvm.polyglot.HostAccess;
import tests.data_structure.Graph_tools;

import java.util.ArrayList;
import java.util.List;

public class GraphAPI {
    private final List<AnimationInstruction> animations;
    private final Graph graph;

    public GraphAPI(List<AnimationInstruction> animations, Graph graph) {
        this.animations = animations;
        this.graph = graph;
    }

    @HostAccess.Export
    public void highlightNode(int id) {
        animations.add(AnimationInstruction.highlightNode(id));
    }
    @HostAccess.Export
    public void lightNode(int id) {
        animations.add(AnimationInstruction.lightNode(id));
    }

    @HostAccess.Export
    public void travelEdge(int from, int to) {
        animations.add(AnimationInstruction.travelEdge(from, to, false));
    }

    @HostAccess.Export
    public void pause(int ms) {
        animations.add(AnimationInstruction.pause(ms));
    }


    @HostAccess.Export
    public void makegraphinvisible() {

        for(Node n : graph.getVertices()) {
            n.setIsVisible(false);
        }
        for(Edge e : graph.getEdges()) {
            e.setIsVisible(false);
        }


        animations.add(AnimationInstruction.makeinvis());
    }

    @HostAccess.Export
    public boolean isnodevisible(int id) {
     return graph.getVertices().get(id).isVisible();
    }

    @HostAccess.Export
    public boolean isedgevisible(int id) {
        return graph.getEdges().get(id).isVisible();
    }



    @HostAccess.Export
    public void makegraphvisible() {

        for(Node n : graph.getVertices()) {
            n.setIsVisible(true);
        }
        for(Edge e : graph.getEdges()) {
            e.setIsVisible(true);
        }


        animations.add(AnimationInstruction.makevis());
    }

    @HostAccess.Export
    public void delightNode(int id) {
        animations.add(AnimationInstruction.disableNode(id));
    }
    @HostAccess.Export
    public void colorNode(int id, String color) {
        animations.add(AnimationInstruction.colorNode(id, color));
    }

    @HostAccess.Export
    public void highlightEdge(int id) {
        animations.add(AnimationInstruction.highlightEdge(id));
    }
    @HostAccess.Export
    public void disableEdge(int id) {
        animations.add(AnimationInstruction.disableEdge(id));
    }
    @HostAccess.Export
    public void makeNodeVisible(int id) {

        graph.getVertices().get(id).setIsVisible(true);
        animations.add(AnimationInstruction.nodeVisible(id));
    }
    @HostAccess.Export
    public void makeNodeInvisible(int id) {

        graph.getVertices().get(id).setIsVisible(false);

        animations.add(AnimationInstruction.nodeInvisible(id));
    }
    @HostAccess.Export
    public void makeEdgeInvisible(int id) {

        graph.getEdges().get(id).setIsVisible(false);

        animations.add(AnimationInstruction.edgeInvisible(id));
    }
    @HostAccess.Export
    public void makeEdgeVisible(int id) {

        graph.getEdges().get(id).setIsVisible(true);

        animations.add(AnimationInstruction.edgeVisible(id));
    }

    @HostAccess.Export
    public List<Integer> incidentEdges(int id) {
        List<Integer> result = new ArrayList<>();
        Node node = graph.getVertices().get(id);

        for (Edge e : graph.indenctedges(node)) {
            result.add(e.getId());   // <-- return primitive
        }

        return result;
    }

    @HostAccess.Export
    public List<Integer> getnodes() {
        List<Integer> result = new ArrayList<>();
        for (Node n : graph.getVertices()) {
            result.add(n.getNumber());
        }
        return result;
    }

    @HostAccess.Export
    public List<Integer> getedges() {
        List<Integer> result = new ArrayList<>();
        for (Edge n : graph.getEdges()) {
            result.add(n.getId());
        }
        return result;
    }

    @HostAccess.Export
    public float edgeweight(int i) { return graph.getEdges().get(i).getWeight(); }

    @HostAccess.Export
    public boolean isAdjacent(int a, int b) {
        if (!graph.containsnode(graph.getVertices().get(a)) ||
                !graph.containsnode(graph.getVertices().get(b))) {
            return false;
        }
        return graph.isadjacent(graph.getVertices().get(a), graph.getVertices().get(b));
    }
    @HostAccess.Export
    public boolean isIncident(int a, int b) {
        if (!graph.containsnode(graph.getVertices().get(a)) ||
                !graph.containsedge(graph.getEdge(graph.getVertices().get(a), graph.getVertices().get(b)))) {
            return false;
        }
        return graph.isincident(
                graph.getVertices().get(a),
                graph.getEdge(graph.getVertices().get(a), graph.getVertices().get(b))
        );
    }

    // --- Graph property checks ---
    @HostAccess.Export
    public boolean isBipartite() { return Graph_tools.isbipartite(graph); }
    @HostAccess.Export
    public boolean isTree() { return Graph_tools.is_tree(graph); }
    @HostAccess.Export
    public boolean isComplete() { return Graph_tools.is_complete(graph); }
    @HostAccess.Export
    public boolean isConnected() { return Graph_tools.is_connected(graph); }
    @HostAccess.Export
    public List<Integer> neighbors(int id) {
        List<Integer> neigh = new ArrayList<>();
        for (Node node : graph.neighbors(graph.getVertices().get(id))) {
            neigh.add(node.getNumber());
        }
        return neigh;
    }


    @HostAccess.Export
    public int degree(int id) {
        return graph.degree(graph.getVertices().get(id));
    }
    // --- Matrices ---
    @HostAccess.Export
    public float[][] adjMatrix() { return Graph_tools.adjacency_matrix(graph); }
    @HostAccess.Export
    public float[][] incidentMatrix() { return Graph_tools.arc_incident_matrix(graph); }

    // --- Misc ---
    @HostAccess.Export
    public List<String> generateColors() { return Graph_tools.random_unique_colors(graph); }

}
