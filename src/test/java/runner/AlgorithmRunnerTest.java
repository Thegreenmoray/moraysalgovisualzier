package runner;


import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

class AlgorithmRunnerTest {



    @Test
    void setup() {
   AlgorithmRunner alg = new AlgorithmRunner();
        alg.runUserCode("", Graph_tools.empty_graph());
        AlgorithmRunner.isSafeClass("");
    }

}