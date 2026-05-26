package runner;

import graph_theory.Graph;
import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

import java.util.ArrayList;

public class VisualrepTest {
    @Test
    public void server() {
        Servermain b =new Servermain();
        VisualizationResponse d=new VisualizationResponse(Graph_tools.empty_graph(),new ArrayList<>());
    d.getAnimations();
    d.getGraph();

    CorsConfig bc=new CorsConfig();
    bc.corsConfigurer();
    }
}
