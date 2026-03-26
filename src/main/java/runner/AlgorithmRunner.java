package runner;

import animations.*;

import javafx.application.Platform;
import org.graalvm.polyglot.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

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
    .option("engine.WarnInterpreterOnly", "false")
    .build();


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

    public void run(Visual_part part) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm loaded. Call setup() first.");
        }
        final Graph[] graphHolder = new Graph[1];

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            graphHolder[0] = part.establish();
            latch.countDown();
        });

        try {
            latch.await(); // Wait until graph is created
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Graph graph = graphHolder[0];


        User_safe_interface_api userSafeInterfaceApi =
                new User_safe_interface_api(graph,new LinkedList<Animations>(),part);




        sandbox.getBindings("js")
                .putMember("User_safe_interface_api", userSafeInterfaceApi);

        // Now you can safely evaluate the user code
        sandbox.eval("js", currentAlgorithm);
    }


}

