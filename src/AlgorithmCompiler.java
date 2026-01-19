
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class AlgorithmCompiler {

    //code should compile here.
    public Class<?> compile(String code) {
        // TODO: implement tomorrow
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "-d", code, "-classpath", System.getProperty("java.class.path"));



        return null;
    }
}


