import animations.Edge_interface;
import animations.GraphAlgorithm;
import animations.Visual_part;
import graph_theory.Graph;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AlgorithmRunner {

private GraphAlgorithm currentAlgorithm;


    public void setup(String code) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    Class<?> clazz = AlgorithmCompiler.compile(code);
    if (clazz == null) {
        throw new RuntimeException("Compilation failed.");
    }

    if (!GraphAlgorithm.class.isAssignableFrom(clazz)) {
        throw new RuntimeException("UserAlgorithm must implement GraphAlgorithm.");
    }

    Object instance = clazz.getDeclaredConstructor().newInstance();
    GraphAlgorithm algo = (GraphAlgorithm) instance;

    this.currentAlgorithm = algo;

}

    public void run(Graph g, Edge_interface api, Visual_part part) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm loaded. Call setup() first.");
        }
        currentAlgorithm.run(g, api,part);




    }}



