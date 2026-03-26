package runner;

import animations.Visual_partTest;
import org.junit.jupiter.api.Test;

class AlgorithmRunnerTest {



    @Test
    void setup() {
   AlgorithmRunner alg = new AlgorithmRunner();
   alg.setup("""
let n = User_safe_interface_api.safely_add_a_node();
""");
        alg.run(Visual_partTest.visualPart);
        AlgorithmRunner.isSafeClass("");
    }

}