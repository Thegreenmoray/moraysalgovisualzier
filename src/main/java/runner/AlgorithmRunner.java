package runner;

import animations.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Permission;

public class AlgorithmRunner {

private GraphAlgorithm currentAlgorithm;


    public void setup(String code) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    //this is just from the AlgothrimCompiler
    Class<?> clazz = AlgorithmCompiler.compile(code);
    if (clazz == null) {
        throw new RuntimeException("Compilation failed.");
    }

    if (!GraphAlgorithm.class.isAssignableFrom(clazz)) {
        throw new RuntimeException("UserAlgorithm must implement GraphAlgorithm.");
    }
//just some type casting
    Object instance = clazz.getDeclaredConstructor().newInstance();
    GraphAlgorithm algo = (GraphAlgorithm) instance;

    this.currentAlgorithm = algo;

}

    public void run( Edge_interface api, Visual_part part) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm loaded. Call setup() first.");
        }
        SecurityManager Returntonormal = System.getSecurityManager();

        //more secure measures will be added later this will do for now
try {
System.setSecurityManager(new SandboxSecurityManager());
    currentAlgorithm.run(api, part);
}catch (SecurityException se) {

    System.out.println("Algorithm attempted to call unathorized operations. ACCESS DENIED.");
} finally {
    // Restore original security manager
    System.setSecurityManager(Returntonormal);
}




    }}

