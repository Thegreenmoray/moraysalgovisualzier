package runner;

public class DTOS {

    public static class NodeDTO {
        public int id;
        public int label;
    }

    public static class EdgeDTO {
        public int fromlabel;
        public int tolabel;
        public int from;
        public int to;
        public boolean directed;
        public float weight;
    }




}
