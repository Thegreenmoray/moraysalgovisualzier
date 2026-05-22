package graph_theory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Min_heapTest {
//ok these work, good.
    @Test
    void add_to_heap() {
 ArrayList<Node> nodes= new ArrayList<>();
  ArrayList<Edge> edges= new ArrayList<>();
  for(int i=0;i<4;i++){
      nodes.add(new Node(i));
  }
    edges.add(new Edge(nodes.get(0),nodes.get(1),2));
  edges.add(new Edge(nodes.get(0),nodes.get(2),1));
  edges.add(new Edge(nodes.get(0),nodes.get(3),5));
  edges.add(new Edge(nodes.get(1),nodes.get(2),8));
  edges.add(new Edge(nodes.get(1),nodes.get(3),3));
  edges.add(new Edge(nodes.get(2),nodes.get(3),10));
  ArrayList<Edge> actual=new ArrayList<>();
    for (Edge e:edges){
        Min_heap.add_to_heap(e,actual);
    }
    ArrayList<Edge> expected=new ArrayList<>();
    expected.add(new Edge(nodes.get(0),nodes.get(2),1));
    expected.add(new Edge(nodes.get(0),nodes.get(1),2));
    expected.add(new Edge(nodes.get(0),nodes.get(3),5));
    expected.add(new Edge(nodes.get(1),nodes.get(2),8));
    expected.add(new Edge(nodes.get(1),nodes.get(3),3));
    expected.add(new Edge(nodes.get(2),nodes.get(3),10));
    assertArrayEquals(actual.toArray(),expected.toArray());


    }

    @Test
    void extract_from_heap() {
        ArrayList<Node> nodes= new ArrayList<>();
        ArrayList<Edge> edges= new ArrayList<>();
        for(int i=0;i<4;i++){
            nodes.add(new Node(i));
        }
        edges.add(new Edge(nodes.get(0),nodes.get(1),2));
        edges.add(new Edge(nodes.get(0),nodes.get(2),1));
        edges.add(new Edge(nodes.get(0),nodes.get(3),5));
        edges.add(new Edge(nodes.get(1),nodes.get(2),8));
        edges.add(new Edge(nodes.get(1),nodes.get(3),3));
        edges.add(new Edge(nodes.get(2),nodes.get(3),10));
        ArrayList<Edge> actual=new ArrayList<>();
        for (Edge e:edges){
            Min_heap.add_to_heap(e,actual);
        }
           Edge edge= Min_heap.extract_from_heap(actual);
        assertEquals(edge,new Edge(nodes.get(0),nodes.get(2),1));
        ArrayList<Edge> expected=new ArrayList<>();
        expected.add(new Edge(nodes.get(0),nodes.get(1),2));
        expected.add(new Edge(nodes.get(1),nodes.get(3),3));
        expected.add(new Edge(nodes.get(0),nodes.get(3),5));
        expected.add(new Edge(nodes.get(1),nodes.get(2),8));
        expected.add(new Edge(nodes.get(2),nodes.get(3),10));

        assertArrayEquals(actual.toArray(),expected.toArray());

    }
}