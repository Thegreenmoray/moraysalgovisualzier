package runner;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class AlgorithmController {

        private final AlgorithmRunner runner;

        public AlgorithmController(AlgorithmRunner runner) {
            this.runner = runner;
        }

        @PostMapping("/run")
        public String run(@RequestBody String code) {
            return runner.run(code);
        }
    }

