package runner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class AlgorithmController {

        private final AlgorithmRunner runner;

        public AlgorithmController(AlgorithmRunner runner) {
            this.runner = runner;
        }


        @GetMapping("/")
        public String home() {
            return "This does indeed work.";
        }

        @PostMapping("/run")
        public String run(@RequestBody String code) {
            try {
                return runner.run(code);
            } catch (Exception e) {
                e.printStackTrace(); // TEMP: print to console
                return "Error: " + e.getMessage();
            }
        }
    }

