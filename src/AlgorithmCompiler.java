
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public class AlgorithmCompiler {

    //code should compile here.
    public Class<?> compile(String code) throws IOException, ClassNotFoundException {
        Path tempDir = Files.createTempDirectory("algovis");
        Path sourceFile = tempDir.resolve("UserAlgorithm.java");
        Files.writeString(sourceFile, code);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        int result = compiler.run(
                null, null, null,
                sourceFile.toString(),
                "-classpath", System.getProperty("java.class.path"),
                "-d", tempDir.toString()
        );



        if (result==0) {
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ tempDir.toUri().toURL() });
            return loader.loadClass("UserAlgorithm");}
        return null;


    }
}


