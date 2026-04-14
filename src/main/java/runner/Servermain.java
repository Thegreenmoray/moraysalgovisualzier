package runner;

public class Servermain {


        public static void main(String[] args) throws Exception {
            AlgorithmRunner runner = new AlgorithmRunner();
            Server.start(runner);
        }

}
