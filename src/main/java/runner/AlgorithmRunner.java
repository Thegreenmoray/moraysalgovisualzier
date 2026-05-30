package runner;

//import animations.*;

//import javafx.application.Platform;
import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.ProxyExecutable;
import org.springframework.stereotype.Component;
import tests.data_structure.Graph_tools;

import java.util.*;
import java.util.concurrent.*;

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
                                                  Graph graph,Map<Integer, List<Object>> lists,
                                                  Map<Integer, Object[][]> matrices) {
        List<AnimationInstruction> animations = new ArrayList<>();

        Context sandbox = newSandbox();

        // 1. Expose animation functions to JS
        GraphAPI graphAPI=new GraphAPI(animations,graph);
        UlitiltyAPI Api=new UlitiltyAPI(graph);
       sandbox.getBindings("js").putMember("Graph", graphAPI);
        sandbox.getBindings("js").putMember("Uility", Api);
        UserAPI api = new UserAPI(lists, matrices, animations);
        sandbox.getBindings("js").putMember("ListAPI", api);
        // 3. Wrap user code in a function
        String wrapped = "function run() {\n" + algorithm + "\n}";
        ExecutorService executor = Executors.newSingleThreadExecutor();



        Future<?> future = executor.submit(() -> {
            try {
                sandbox.eval("js", wrapped);
                sandbox.eval("js", "run()");
            } catch (Exception e) {
                throw new RuntimeException("User code error: " + e.getMessage(), e);
            }
        });

        try {
            future.get(1000, TimeUnit.MILLISECONDS); // 200ms timeout
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            future.cancel(true); // interrupt the thread
            throw new RuntimeException("User code timed out");
        }finally {
            executor.shutdownNow();
        }
        // 4. Evaluate and execute


        return animations;
    }


}