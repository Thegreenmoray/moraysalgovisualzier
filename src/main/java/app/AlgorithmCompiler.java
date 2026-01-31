package app;

import graph_theory.*;

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

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        int result = compiler.run(
                null, null, null,
                "-classpath", System.getProperty("java.class.path")+
                        File.pathSeparator + modulePath,
                "-d", tempDir.toString(),sourceFile.toString()

        );



        if (result==0) {
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ tempDir.toUri().toURL() });
            return loader.loadClass("UserAlgorithm");}
        return null;


    }





}


