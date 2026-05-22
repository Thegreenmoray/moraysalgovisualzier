package runner;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmCompilerTest {

    @Test
    void compile() throws IOException, ClassNotFoundException {
   AlgorithmCompiler.compile("""
let n = User_safe_interface_api.safely_add_a_node();
""");
    }
}