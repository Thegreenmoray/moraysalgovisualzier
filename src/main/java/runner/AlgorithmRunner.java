package runner;

//import animations.*;

//import javafx.application.Platform;
import graph_theory.Graph;
import graph_theory.Node;
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.ProxyExecutable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AlgorithmRunner {


    private Context newSandbox() {
   return   Context.newBuilder("js")
    .allowHostAccess(HostAccess.newBuilder(HostAccess.EXPLICIT)
            .allowPublicAccess(true)
        .allowAccessInheritance(true)
        .allowListAccess(true)
        .allowMapAccess(true)
        .build())
      .allowHostClassLookup(AlgorithmRunner::isSafeClass)
      .allowIO(false)
    .allowCreateThread(false)
    .allowNativeAccess(false)
   .allowAllAccess(false)
    .option("engine.WarnInterpreterOnly", "false").build();}


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


    public String run(String currentAlgorithm) {

        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm provided.");
        }

        // Wrap user code in a function
        String wrapped =
                "function run() {\n" +
                        currentAlgorithm +
                        "\n}";
        Context sandbox = newSandbox();
        // Evaluate the code
        sandbox.eval("js", wrapped);

        // Call the function and get the result
        Value result = sandbox.eval("js", "run()");

        return result.toString();
    }


    public List<AnimationInstruction> runUserCode(String algorithm, Graph graph) {
        List<AnimationInstruction> animations = new ArrayList<>();

        Context sandbox = newSandbox();

        // 1. Expose animation functions to JS
        exposeAnimationAPI(sandbox, animations,graph);



        // 3. Wrap user code in a function
        String wrapped = "function run() {\n" + algorithm + "\n}";

        // 4. Evaluate and execute
        sandbox.eval("js", wrapped);
        sandbox.eval("js", "run()");

        return animations;
    }
    private void exposeAnimationAPI(Context sandbox, List<AnimationInstruction> animations,Graph graph) {

        sandbox.getBindings("js").putMember("highlightNode", (ProxyExecutable) args -> {
            AnimationInstruction instr = new AnimationInstruction();
            instr.type = "highlightNode";
            instr.node = args[0].asInt();
            animations.add(instr);
            return null;
        });

        sandbox.getBindings("js").putMember("pause", (ProxyExecutable) args -> {
            AnimationInstruction instr = new AnimationInstruction();
            instr.type = "pause";
            instr.ms = args[0].asInt();
            animations.add(instr);
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


        // Add more animation functions here...
    }







}

