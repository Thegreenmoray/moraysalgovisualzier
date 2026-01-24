package tests.data_structure;

import graph_theory.Edge;

import java.util.List;

public class Min_heap {
    //do this for a global heap;

// come back tomorrow to see if this actually works
public  static void add_to_heap(Edge edge,List<Edge> edges) {
    edges.add(edge);
   form_heap(edges);
}



public static void form_heap(List<Edge> heap) {
    int actual_child=heap.size();

 while (actual_child>1) {
     int actual_parent=actual_child/2;

     if(heap.get(actual_child-1).getWeight()<heap.get(actual_parent-1).getWeight()){
           Edge temp=heap.get(actual_child-1);
           heap.set(actual_child-1,heap.get(actual_parent-1));
           heap.set(actual_parent-1,temp);
     }else {
        actual_child=actual_parent;
     }

 }


}



public static Edge extract_from_heap(List<Edge> edges) {


    Edge edge=edges.remove(0);
    edges.add(0,edges.get(edges.size()-1));
    edges.remove(edges.size()-1);
   fix_heap(edges,0);

    return edge;
}




    public static void fix_heap(List<Edge> edges,int index) {
     int child;
    while (2*index+1<edges.size()) {
        int left=2*index+1;
            child=left;
      //here to prevent crashes
      if (left+1 < edges.size()) {//left+1 otherwise know as the right
             int right=left+1;
             if(edges.get(right).getWeight()<edges.get(left).getWeight()) {
                child=right;
             }}
        if (edges.get(child).getWeight()<edges.get(index).getWeight()) {
            Edge temp=edges.get(index);
            edges.set(index,edges.get(child));
            edges.set(child,temp);
            index=child;
        }else {
            return;
        }





    }



}


}
