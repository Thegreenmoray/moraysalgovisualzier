package runner;

import animations.Graph;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AlgorithmCompiler {
    public static String modulePath;
//to ensure this works on any computer
    static {
        try {
            //Any of your own classes will work fine I just used Graph
            modulePath = String.valueOf(Paths.get(Graph.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    //code should compile here.
    public static Class<?> compile(String code) throws IOException, ClassNotFoundException {
        Path tempDir = Files.createTempDirectory("algovis");
        Path sourceFile = tempDir.resolve("UserAlgorithm.java");
        Files.writeString(sourceFile, code);
//Apparently this is how you get the compiler up
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();


        /*These comments are mostly here for myself and my fellow
         programmers, just to save you a few hours*/
        int result = compiler.run(
                null, null, null,
                //the -classpath lets the compiler know where to look
                // for classes and jar files
                "-classpath",
                //the System.getProperty("java.class.path") gives the running programs info
                //like ~\out\production\insertclassname so that it can actually
                //access the classes stored here (graph, etc.)
                System.getProperty("java.class.path")+
                        //pathseparator is just here to act as the separator
                        //for the operating sytems i.e C;/users/.....
                        File.pathSeparator + modulePath,
                //see comments above for modulepath
                "-d", tempDir.toString(),sourceFile.toString()
                //"-d" means put these files in this directory
                //last two are just normal temp directories nothing special

        ); //yes order does matter, it causes weird things to happen otherwise


        //Note: Use JDK not JDE for this, it will crash otherwise


//if it's not 0 something went wrong
        if (result==0) {
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ tempDir.toUri().toURL() });
            return loader.loadClass("UserAlgorithm");}
        return null;


    }





}


