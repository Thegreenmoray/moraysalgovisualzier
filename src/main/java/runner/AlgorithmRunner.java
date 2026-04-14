package runner;

//import animations.*;

//import javafx.application.Platform;
import graph_theory.Graph;
import org.graalvm.polyglot.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class AlgorithmRunner {



    Context sandbox =  Context.newBuilder("js")
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
            .option("sandbox.MaxHeapMemory", "128MB")     // Prevents memory bombs
            .option("sandbox.MaxCPUTime", "30s")           // Prevents infinite loops
            .option("sandbox.MaxStatements", "50000")     // Prevents algorithmic attack
            //50k otherwise,100,000 for debugging
    .option("engine.WarnInterpreterOnly", "false").build();


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



String currentAlgorithm;
    public void setup(String code) {
        // Here you should parse, compile, or store the user code
        this.currentAlgorithm = code;
    }

    public void run() {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm loaded. Call setup() first.");
        }
        final Graph[] graphHolder = new Graph[1];

        CountDownLatch latch = new CountDownLatch(1);

        //Platform.runLater(() -> {
       //     graphHolder[0] = part.establish();
            latch.countDown();
      //  });

        try {
            latch.await(); // Wait until graph is created
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Graph graph = graphHolder[0];


       // User_safe_interface_api userSafeInterfaceApi = new User_safe_interface_api(graph,new LinkedList<Animations>(),part);



        sandbox.getBindings("js");
                //.putMember("User_safe_interface_api", userSafeInterfaceApi);

        String user = currentAlgorithm;
        String instrumented = Arrays.stream(user.split("\n"))
                .map(line -> {
                    String trimmed = line.trim();
                    if (trimmed.endsWith(";")) {
                        return line + " yield;";
                    } else {
                        return line;
                    }
                })
                .collect(Collectors.joining("\n"));
        String wrapped =
                "function* run() {\n" +
                        instrumented +
                        "\n}";
        // Now you can safely evaluate the user code
        sandbox.eval("js", wrapped);


        Value generator = sandbox.eval("js", "run()");

// 3. Schedule execution slices
        new Thread(() -> {
            try {
                while (true) {
                    // Run one slice
                    generator.invokeMember("next");

                    // Allow UI to update
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                System.out.println("Execution finished or stopped.");
            }
        }).start();



    }
    public void runHeadless() throws Exception {
        Value generator = sandbox.eval("js", "run()");
        while (true) {
            Value result = generator.invokeMember("next");
            if (result.getMember("done").asBoolean()) break;
        }
    }

}

