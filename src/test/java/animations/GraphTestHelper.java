package animations;

import graph_theory.Edge;
import graph_theory.Node;

import java.util.LinkedList;
import java.util.Random;

public class GraphTestHelper {
    public static Graph generate_graph_undirected(int size, int edge_chance, boolean isweighted, boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 0;
        }

        if(edge_chance <= 0){
            edge_chance = 1;
        }

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(rand.nextInt(edge_chance)==0){
                    int c=rand.nextInt(20)+2;
                    if (isweighted)
                    {
                        int v=can_be_negative_weight&&rand.nextBoolean()?-1:1;
                        edges.add(new Edge(nodes.get(i), nodes.get(j), v*c));
                        edges.add(new Edge(nodes.get(j), nodes.get(i), v*c));
                    }
                    else
                    {
                        edges.add(new Edge(nodes.get(i), nodes.get(j)));
                        edges.add(new Edge(nodes.get(j), nodes.get(i)));
                    }

                }
            }
        }

        return new Graph(nodes,edges);
    }


    public static Graph generate_graph_directed(int size,int edge_chance,boolean isweighted,boolean can_be_negative_weight){
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<Edge> edges = new LinkedList<>();

        if (size <=0){
            size = 0;
        }

        if(edge_chance <= 0){
            edge_chance = 1;
        }

        Random rand = new Random();

        for(int i=0;i<size;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (i==j) {continue;}

                if (rand.nextInt(edge_chance) == 0) {
                    int c = rand.nextInt(20) + 2;
                    if (isweighted) {
                        int v = can_be_negative_weight && rand.nextBoolean() ? -1 : 1;
                        edges.add(new Edge(nodes.get(i), nodes.get(j), v * c));
                    } else {
                        edges.add(new Edge(nodes.get(i), nodes.get(j)));
                    }

                }

            }
        }
        return new Graph(nodes,edges);
    }


}
