package runner;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.junit.jupiter.api.Test;
import tests.data_structure.Graph_tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

class AlgorithmRunnerTest {



    @Test
    void setup() {
   AlgorithmRunner algorithmRunner = new AlgorithmRunner();
   algorithmRunner.runUserCode("",Graph_tools.empty_graph(),new HashMap<>(),new HashMap<>());
   AlgorithmController algorithmCompiler = new AlgorithmController(algorithmRunner);
  algorithmCompiler.runAlgorithm(new AlgothrimRequest(), null);

}}