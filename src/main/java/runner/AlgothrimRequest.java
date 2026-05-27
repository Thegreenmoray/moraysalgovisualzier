package runner;

import java.util.List;
import java.util.Map;

public class AlgothrimRequest {

        public List<DTOS.NodeDTO> nodes;
        public List<DTOS.EdgeDTO> edges;
        public String algorithm;
        public Map<Integer, List<Object>> lists;
        public Map<Integer, Object[][]> matrices;

}
