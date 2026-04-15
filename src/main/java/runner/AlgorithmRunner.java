package runner;

//import animations.*;

//import javafx.application.Platform;
import org.graalvm.polyglot.*;
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
            //.option("sandbox.MaxHeapMemory", "128MB")     // Prevents memory bombs
           // .option("sandbox.MaxCPUTime", "30s")           // Prevents infinite loops
           // .option("sandbox.MaxStatements", "50000")     // Prevents algorithmic attack
            //50k otherwise,100,000 for debugging
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


}

