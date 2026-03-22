package tests.data_structure;

import animations.Animations;
import animations.EdgeAnimation;
import animations.Visual_part;
import graph_theory.Edge;
import animations.Graph;
import graph_theory.Min_heap;
import graph_theory.Node;
import animations.*;
import runner.User_safe_interface_api;
import set_theory.Set_theory_items;

import java.util.*;


/*The comments for the other algorithms are predominantly meant for newbies,
for more experienced developers these are just
here to show how to visualize your code with the compiler.
Other notes: unless you are unit testing you can ignore the
if (part != null&&edgeInterface!=null) {} these only exist for unit tests
and aren't required for your code.
*/

public class Example_problems {

//------------------------------To start------------------------------


    //use the new one
public static void Adding_and_removing_nodes(Visual_part part, User_safe_interface_api api){
    Graph g=part.randomgraph_establish(4,3,true,false,true);
    Node node=new Node(6);
//api.addNode(g,node,part);
         //   api.removeNode(g,g.getVertices().get(2),part);
//api.removeNode(g,node,part);
 }

public static void Adding_and_removing_edges(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addEdge(g,edge,part);
        api.addEdge(g,edge2,part);
        api.removeEdge(g,edge2,part);}


    public static void Adding_and_removing_arcs(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addarc(g,edge,part);
        api.addarc(g,edge2,part);
        api.removearc(g,edge2,part);}


    public static void Make_graph_invisible_visible(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);

api.setallinvisible(g,part);
api.setallvisible(g,part);
}



    public static void making_edges_invisible_visible(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addEdge(g,edge,part);
        api.addEdge(g,edge2,part);
        api.makeedgeinvisible(g,edge,part);
        api.makeedgevisible(g,edge,part);
}

    public static void Making_arcs_visible_invisible(Visual_part part, GUI_interface api){
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        Graph g=part.randomgraph_establish(4,3,true,false,true);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addarc(g,edge,part);
        api.addarc(g,edge2,part);
        api.removearc(g,edge2,part);
        api.makarcinvisible(g,edge,part);
       timelineQueue.add( api.makearcvisible(g,edge,part));
       part.playNext((LinkedList<? extends Animations>) timelineQueue);
}


    public static void Making_nodes_visible_invisible(Visual_part part, GUI_interface api){
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        Graph g=part.randomgraph_establish(4,3,true,false,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        api.makenodeinvisible(g,node,part);
       timelineQueue.add( api.makenodevisible(g,node,part));
        part.playNext((LinkedList<? extends Animations>) timelineQueue);
    }


    public static void making_edges_weighted(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,true,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addEdge(g,edge,part,9);
        api.addEdge(g,edge2,part,-1);
    }

    public static void Making_arcs_weighted(Visual_part part, GUI_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addarc(g,edge,part,4);
        api.addarc(g,edge2,part,-8);
    }




//------------------------set probelms-----------------------

    public static List<Integer> bubble_sort(List<Integer> e){

//how to deal with sets
        for (int i=0;i<e.size();i++){
            for (int j=0;j<e.size()-1;j++){
                if (e.get(j)>=e.get(j+1)){
                    int hold = e.get(j);
                    e.set(j,e.get(j+1));
                    e.set(j+1,hold);}}}

        return e;
    }


    //round two
    public static  List<Integer> quicksort(List<Integer> e,int low,int high){

//how to deal with sets with recursion
        if (low > high) return e;


        int partition = partition(e, low, high);

        quicksort(e, low, partition-1);
        quicksort(e, partition+1, high);


        return e;
    }


/*
for showing how to introduce multiple matrices
    private static float[][] naive_matrix_multiplication(float[][] matrix1, float[][] matrix2){
        int rows1=matrix1.length;
        int cols1=matrix1[0].length;
        int rows2=matrix2.length;
        int cols2=matrix2[0].length;
        if(cols1!=rows2){
            return null;//send note to user that that was not vaild combination
        }
        float[][] product_matrix=new float[rows1][cols2];
        int l=product_matrix.length;
        for(int i=0;i<rows1;i++){
            for(int j=0;j<cols2;j++){
                for(int k=0;k<l;k++){
                    product_matrix[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }
        }


//this might good

        return product_matrix;
    }
*/


    private static int partition(List<Integer> e,int low,int high){

        int pivot=e.get(high);
        int pivotpoint=low-1;

        for (int i=low;i<=high;i++){
            if (e.get(i)<=pivot){
                pivotpoint++;
                int temp= e.get(pivotpoint);
                e.set(pivotpoint,e.get(i));
                e.set(i,temp);
            }
        }


        return pivotpoint;
    };


    public static int unbounded_knapsack(int[] values,int[] weights,int weight){
    //mainpate arrays beyond swapping
        int[] unbounded_knapsack_array=new int[weight+1];
        int n=values.length;
        for(int i=1;i<=weight;i++){
            for(int j=0;j<n;j++){

                if(weights[j]<=i){
                    unbounded_knapsack_array[i]=Math.max(unbounded_knapsack_array[i],
                            unbounded_knapsack_array[i-weights[j]]+values[j]);
                }

            }
        }


        return unbounded_knapsack_array[weight];
    }


    public  static int binary_knapsack(int[] values,int[] weights,int weight){
      //manipulate matrices
        int n = weights.length;
        int[][] binary_knapsack_matrix=new int[n+1][weight+1];

        for(int i=1;i<=n;i++){
            // n is the number of items in weights
            for (int w=1;w<=weight;w++){

                if(weights[i-1]>w){ //if an item is too heavy, revert to previous.
                    binary_knapsack_matrix[i][w]=binary_knapsack_matrix[i-1][w];
                }else { //take it or leave it
                    binary_knapsack_matrix[i][w]=Math.max(binary_knapsack_matrix[i-1][w],binary_knapsack_matrix[i-1][w-weights[i-1]]+values[i-1]);
                }
            }
        }


        return binary_knapsack_matrix[n][weight];
    }



/*
    //and deal with matrices with recursion (the queue otfen has issues with recursion)
 private static void generalized_sudoku(char[][] sudoku,char[] total_chars,char[] vaild_char_list){
        if (!isboardvaild(sudoku)){
            return;
            //invaild board!
        }
   generalized_sudoku_slover(sudoku,0,0,total_chars,vaild_char_list, sudoku.length);
    }

 private static boolean isboardvaild(char[][] sudoku){
        return issolved_or_is_vaild_sudoku_board(sudoku, true);
    }

//work on this
private static void generalized_sudoku_slover(char[][] sudoku, int n,int k,char[] total_chars,char[] vaild_char_list,int sudoku_size){
if (issolved_or_is_vaild_sudoku_board(sudoku,false)){
    return;
}

    if (k ==sudoku_size&&!(n==sudoku_size)) {
        k=0;
        n++;
    }


        if (sudoku[n][k] == ' ') {
          //candidate selector
        }


        k++;
if (!(n==sudoku_size)) {
        generalized_sudoku_slover(sudoku,n,k,total_chars,vaild_char_list,sudoku_size);
    }}

 private static boolean issolved_or_is_vaild_sudoku_board(char[][] sudoku,boolean accepts_blank_characters) {

        for(int i=0;i<sudoku.length;i++){
            if (!valid_row(sudoku,i,accepts_blank_characters)){
                    return false;
                }

        }
        for(int j=0;j<sudoku[0].length;j++) {
            if (!vaild_column(sudoku,j,accepts_blank_characters)){
                return false;
            }
        }

        int n_sqrt= (int) Math.sqrt(sudoku.length);


        for(int i=0;i<n_sqrt;i++){
            for(int j=0;j<n_sqrt;j++){
                if (!vaild_sqrtn_by_sqrtn(sudoku,i*n_sqrt,j*n_sqrt,n_sqrt,accepts_blank_characters)){
                    return false;
                }
            }}




        return true;
    }

 private static boolean vaild_column(char[][] sudoku, int col,boolean accepts_blank_characters) {
        HashSet<Character> set = new HashSet<>();
        for(int i=0;i<sudoku[col].length;i++){
          char c = sudoku[i][col];
          if(c==' '&&!accepts_blank_characters){
              return false;
          }
          if (set.contains(c)){
              return false;
          }else {set.add(c);}

      }


        return true;
    }

 private static boolean vaild_sqrtn_by_sqrtn(char[][] sudoku,int row,int col,int n_sqrt,boolean accepts_blank_characters) {
        HashSet<Character> set = new HashSet<>();


        int intdivision_row=(row/n_sqrt)* n_sqrt;
        int intdivision_col=(col/n_sqrt)* n_sqrt;

        for(int i=0;i<n_sqrt;i++){
            for(int j=0;j<n_sqrt;j++){
              char c =sudoku[i+intdivision_row][j+intdivision_col];
                if(c==' '&&!accepts_blank_characters){
                    return false;
                }
            if (set.contains(c)){
         return false;
            }else {set.add(c);}

            }

}


        return true;
    }

 private static boolean valid_row(char[][] sudoku,int row,boolean accepts_blank_characters) {

        HashSet<Character> set = new HashSet<>();
        for (int i=0;i<sudoku.length;i++){
            char c =sudoku[row][i];
            if (c==' '&&!accepts_blank_characters){
           return false;
       }

            if (set.contains(c)){
                return false;
            }else {set.add(c);}

        }


        return true;
    }
*/

//--------------------------Graph problems----------------------------

    private static boolean[] bfs(Graph graph, int start, boolean[] visted, GUI_interface graphTheoryInterface, Visual_part visualPart) {
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        Queue<Node> q=new LinkedList<>();

        Node node=graph.getVertices().get(start);
        visted[node.getNumber()]=true;
        q.add(node);
        if (graphTheoryInterface !=null&&visualPart!=null){
        timelineQueue.add(graphTheoryInterface.highlightNode(node));}
        while(!q.isEmpty()){
            Node polled=q.poll();
            List<Node> neighbors = graph.neighbors(polled);

            for (Node n:neighbors) {

                if(!visted[n.getNumber()]) {

                    if (graphTheoryInterface !=null&&visualPart!=null){Edge edge = graph.getEdge(polled, n);
                    timelineQueue.add(graphTheoryInterface.pause(200));
                        timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                    timelineQueue.add(graphTheoryInterface.highlightNode(n));
                }
                    q.add(n);
                    visted[n.getNumber()]=true;
                }

            }

        }
        if (graphTheoryInterface !=null&&visualPart!=null) {
            visualPart.playNext((LinkedList<? extends Animations>) timelineQueue);
        }
        return visted;
    }

    public static Graph Prim(Graph graph, GUI_interface graphTheoryInterface, Visual_part visualPart){
       //shows how to use other functions
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        ArrayList<Edge> heap=new ArrayList<>();
        Graph mst=Graph_tools.empty_graph();
        for(Edge e:graph.indenctedges(graph.getVertices().getFirst())){
            Min_heap.add_to_heap(e,heap);
        }
     if (graphTheoryInterface !=null&&visualPart!=null){
       graphTheoryInterface.setallinvisible(graph,visualPart);
        timelineQueue.add(graphTheoryInterface.pause(200));}

        mst.addVertex(graph.getVertices().getFirst());
        if (graphTheoryInterface !=null&&visualPart!=null) {
            timelineQueue.add(graphTheoryInterface.pause(200));
            timelineQueue.add(graphTheoryInterface.makenodevisible(graph, graph.getVertices().getFirst(), visualPart));
            timelineQueue.add(graphTheoryInterface.highlightNode(graph.getVertices().getFirst()));
        }
        while(!heap.isEmpty()&&mst.getVertices().size()<graph.getVertices().size()){
            Edge other_edges=Min_heap.extract_from_heap(heap);

            if (Graph_tools.Willcreateacycle(mst,other_edges.getV1(),other_edges.getV2())) {
                continue;
            }


            if (mst.containsnode(other_edges.getV1())&&mst.containsnode(other_edges.getV2())) {
                continue;
            }

              if (!mst.containsnode(other_edges.getV2())) {
                  mst.addVertex(other_edges.getV2());
                  if (graphTheoryInterface !=null&&visualPart!=null) {
                      timelineQueue.add(graphTheoryInterface.makenodevisible(graph, other_edges.getV2(), visualPart));
                      timelineQueue.add(graphTheoryInterface.pause(200));
                      timelineQueue.add(graphTheoryInterface.highlightNode(other_edges.getV2()));
                  }
                      mst.addarc(other_edges,other_edges.getWeight());
                      if (graphTheoryInterface !=null&&visualPart!=null) {
                          timelineQueue.add(graphTheoryInterface.pause(200));
                          timelineQueue.add(graphTheoryInterface.makearcvisible(graph, other_edges, visualPart));
                      }


                  for (Edge e:graph.indenctedges(other_edges.getV2())) {
                      Min_heap.add_to_heap(e,heap);

                  }
              }



        }

        if (graphTheoryInterface !=null&&visualPart!=null) {
            visualPart.playNext((LinkedList<? extends Animations>) timelineQueue);
        }



        return mst;
    }

    private static boolean[] dfs(Graph graph, int node_number, boolean[] visted, GUI_interface graphTheoryInterface, Queue<EdgeAnimation> timelineQueue) {
       visted[node_number]=true;
//how to deal with recursion on graphs
        for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
            if(!visted[n.getNumber()]){
                if (graphTheoryInterface !=null){
                Edge edge=graph.getEdge(graph.getVertices().get(node_number),n);
                    timelineQueue.add(graphTheoryInterface.pause(200));
                timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                timelineQueue.add(graphTheoryInterface.highlightNode(n));
                graphTheoryInterface.onEdgesearched(edge);}

                dfs(graph,n.getNumber(),visted,graphTheoryInterface,timelineQueue);
            }
        }


        return visted;
    }

    public static float[][] Floyd_Warshall(float[][] adjacency_matrix, Graph graph, GUI_interface graphTheoryInterface, Visual_part visualPart){
// searching the all pairs of shortest paths,
// good for finding the diameter on smaller graphs
        //how to calculate distances
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                //these parts only exist for unit tests ignore these
                  if (visualPart !=null&&graphTheoryInterface!=null){
                    Node node1=graph.getVertices().get(i);
                    Node node2=graph.getVertices().get(j);
                    Node node3=graph.getVertices().get(k);

                      Edge edgeij=graph.getEdge(node1,node2);
                    Edge edgeik=graph.getEdge(node1,node3);
                    Edge edgekj=graph.getEdge(node3,node2);

                    if (edgeij!=null){
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeij));
                      timelineQueue.add(graphTheoryInterface.pause(200));}
                    if (edgeik!=null){
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeik));
                      timelineQueue.add(graphTheoryInterface.pause(200));}
                    if (edgekj!=null){
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgekj));
                      timelineQueue.add(graphTheoryInterface.pause(200));}
                  }

                    adjacency_matrix[i][j] = Math.min(adjacency_matrix[i][j], adjacency_matrix[i][k]+adjacency_matrix[k][j]);
                }
            }
        }//these parts only exist for unit tests ignore these
        if (visualPart !=null&&graphTheoryInterface!=null){
        visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);}


        return adjacency_matrix;
    }

    public static boolean[] component_analysis(Graph graph, GUI_interface graphTheoryInterface, boolean[] visted, Visual_part visualPart, boolean b_or_d)  {
        ArrayList<Node> node= new ArrayList<>( graph.getVertices());
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();//only if dfs

        for(int i=0;i<node.size();i++){
            if(!visted[node.get(i).getNumber()]){

              visted= b_or_d ? bfs(graph,i,visted,graphTheoryInterface,visualPart):
                      dfs(graph,i,visted,graphTheoryInterface,timelineQueue);
            }
        }
       if(!b_or_d){ //dfs
           if (graphTheoryInterface !=null&&visualPart!=null) {
               visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);
           }
       }
        return visted;
    }


 public static List<Node> independent_set(Graph graph,Visual_part  part,
                                    GUI_interface edgeInterface) {
     Queue<EdgeAnimation> timelineQueue = new LinkedList<>();

   List<Node> list= independent_set_calculation(graph,new ArrayList<>(),
             new ArrayList<>(),new ArrayList<>(),part,edgeInterface,timelineQueue);

     if (edgeInterface !=null&&part!=null) {
         for (Node node : list) {

             timelineQueue.add(edgeInterface.highlight_semi_permant(node, part));
         }
     }
     if (edgeInterface !=null&&part!=null) {
         part.playNext((LinkedList<? extends Animations>) timelineQueue);
     }

     return list;
 }



    private static List<Node> independent_set_calculation(Graph graph, List<Node> best_set,
                                                          List<Node> current_set, List<Node> candidates, Visual_part  part,
                                                          GUI_interface edgeInterface, Queue<EdgeAnimation> timelineQueue){

//other features such as permantly lighting nodes


        if(current_set.isEmpty()){
            candidates=  graph.getVertices();
        }
        // if our current size and our candidate size is smaller than
        //the best we've found so far, don't even bother searching further.

        if(current_set.size() + candidates.size() <= best_set.size()){
            if (part != null&&edgeInterface!=null) {
                    timelineQueue.add(edgeInterface.pause(1));
            }

            return best_set;
        }





        for(int i=0;i<candidates.size();i++){
        Node candidate=candidates.get(i);

  current_set.add(candidate);
            if (part != null&&edgeInterface!=null) {
            timelineQueue.add(edgeInterface.highlight_semi_permant(candidate,part));}


           if (current_set.size()>best_set.size()) {
                //prevents contamination
                best_set= new ArrayList<>(current_set);
            }
            List<Node> nextCandidates = new ArrayList<>();

 /*in clique its much easier since a node cannot be adjacent to itself
 or at least you cannot form an edge between itself
  however thats a problem for independent set since
 this is examining nodes not adjacent to each other
 and a node can never be adjacent to itself so unless
 you do something like this it will never stop.*/


         for (int j=i+1;j<candidates.size();j++){
             //^ this forces you to look at other nodes
             //and prevent an infinite loop
             //this also stops you from looking at other
             //permutations, which would waste more time

                 Node c = candidates.get(j);
               //now you conduct the check
                 boolean vaild_candidate = true;
                 for (Node u : current_set) {
                     if (graph.isadjacent(u, c)) {
                         vaild_candidate = false;
                         break;
                     }
                 }

                 if (vaild_candidate) nextCandidates.add(c);

         }


            best_set=independent_set_calculation(graph,best_set,current_set,nextCandidates,part,edgeInterface,timelineQueue);
            current_set.remove(candidate);
         if (part != null&&edgeInterface!=null) {
            timelineQueue.add( edgeInterface.disable_highlights(candidate,part));}

        }
/* honestly you are better off taking the G^c of the intinal graph
then running clique, no need to worry about ordering.
this may not be great for sparse graphs though because the complement will be dense
But that's debatable*/

        return best_set;
    }

public static int chromatic_number(Graph graph,GUI_interface gui_interface,Visual_part  part
){
    Queue<EdgeAnimation> timelineQueue = new LinkedList<>();


List<String> colors =Graph_tools.random_unique_colors(graph);
if (colors==null|| colors.isEmpty()){ //either graph is too big or graph has no nodes
    return 0;
}
    ArrayList<String> colors_list=new ArrayList<>();
    colors_list.add(colors.get(0));
ArrayList<Node> uncolored=new ArrayList<>(graph.getVertices());
     if (graph.getEdges().isEmpty()){
         chromatic_number_check(graph,
                 colors_list,0, new ArrayList<>(),
                gui_interface,part,timelineQueue,uncolored);
         if (gui_interface !=null&&part!=null) {
             part.playNext((LinkedList<? extends Animations>) timelineQueue);
         }
        return 1;
}

    colors_list.add(colors.get(1));
     if (Graph_tools.isbipartite(graph)){
       for (Node node:graph.getVertices()){
           node.setHexcode_color(null);
       }
         chromatic_number_check(graph,
                 colors_list,0, new ArrayList<>(),
                gui_interface,part,timelineQueue,uncolored);
         if (gui_interface !=null&&part!=null) {
             part.playNext((LinkedList<? extends Animations>) timelineQueue);
         }
         return 2;
     }
    for (Node node:graph.getVertices()){
        node.setHexcode_color(null);
    }

     if (Graph_tools.is_complete(graph)){
         for (Node node:graph.getVertices()){
             node.setHexcode_color(null);
         }
         chromatic_number_check(graph,
                 colors,0, new ArrayList<>(),
                gui_interface,part,timelineQueue,uncolored);
         if (gui_interface !=null&&part!=null) {
             part.playNext((LinkedList<? extends Animations>) timelineQueue);
         }
         return graph.getVertices().size();
     }

graph.getVertices().sort(Comparator.comparingInt(graph::degree).reversed());


     for (int i=3;i<graph.getVertices().size();i++){
        colors_list.add(colors.get(i-1));


         chromatic_number_check(graph,
     colors_list,0, new ArrayList<>(),
 gui_interface,part,timelineQueue,uncolored);


     if (isvaildcoloring(graph)){
         if (gui_interface !=null&&part!=null) {
             part.playNext((LinkedList<? extends Animations>) timelineQueue);
         }


     return i;}
     for (Node  node : graph.getVertices()){
         node.setHexcode_color(null);
         if (part!=null&&gui_interface!=null){
             gui_interface.disable_highlights(node,part);
         }
     }
     }



    return 0;
}

    private static boolean isvaildcoloring(Graph graph) {
    for (Node node : graph.getVertices()) {
       if (node.getHexcode_color()==null) {
           return false;
       }
    }

        return true;
    }


    //round 2.5
private static ArrayList<String> chromatic_number_check(Graph graph,
  List<String> color_map,int currentsize,
  ArrayList<String> colorpalette,GUI_interface  gui_interface,Visual_part  part,
  Queue<EdgeAnimation> timelineQueue,ArrayList<Node> uncolored){
//how to color nodes
     if (graph.getVertices().size()==currentsize){
         if(vaild_color_checking(graph)){
        colorpalette = (ArrayList<String>) color_map;
         }
         return colorpalette;
     }

    Node node=graph.getVertices().get(currentsize);
     uncolored.remove(node);

boolean willconflict=false;
         for (String color : color_map) {

          if (!colorpalette.isEmpty()) {
              break;
          }


             for (Node neighbor:graph.neighbors(node)) {
                 if (neighbor.getHexcode_color()!=null&&color.equals(neighbor.getHexcode_color())) {
                    willconflict=true;
                 break;  //try another
                 }
             }

             if (!willconflict&& neighbors_neighbors_are_good(graph, node, (ArrayList<String>) color_map, uncolored, timelineQueue,part,gui_interface)) {
               node.setHexcode_color(color);

                 if (part!=null&&gui_interface!=null) {
                     timelineQueue.add(gui_interface.color_nodes(node.getNumber(), part, color));
                 }
                 //now proceed
                 colorpalette= chromatic_number_check(graph,
                                          color_map,
                         currentsize+1, colorpalette,gui_interface,part,timelineQueue,uncolored);
         }
willconflict=false;
     }






    return colorpalette;
}


private static boolean neighbors_neighbors_are_good(Graph graph, Node node,  ArrayList<String> currentcolorlist, ArrayList<Node> uncolored,Queue<EdgeAnimation> animations,Visual_part  part,GUI_interface gui_interface) {
//^ridiculous name, but more descriptive
     for (Node neighbor:graph.neighbors(node)) {
        if (!uncolored.contains(neighbor)) continue;

        int available = 0;
        for (String color : currentcolorlist) {
         // animations.add(gui_interface.color_nodes(node, part, color));
            boolean conflict = false;
            for (Node neighbor_of_neighbor : graph.neighbors(neighbor)) {
                if (neighbor_of_neighbor.getHexcode_color() != null &&color.equals(neighbor_of_neighbor.getHexcode_color())) {

                    conflict = true;
                    break;
                }
            }
            if (!conflict) available++;
        }

        // If neighbor has no colors left, this assignment fails
        if (available == 0) {
            return false;}

    }


    return true;


}



    private static boolean vaild_color_checking(Graph graph) {
      for (Node node : graph.getVertices()) {
          for (Node neighbor:graph.neighbors(node)){
              if (node.issamecolor(neighbor)) {
                 return false;
              }
          }
      }
        return true;
    }
/*
//maybe?
    private static <G> List<List<G>> set_cover(G[] universalset,List<List<G>> elements,List<List<G>> current_cover){

    return current_cover;
}*/


    public static boolean ishamilition(Graph graph,Visual_part part,
 GUI_interface edgeInterface){
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
//highlighting edges
     if (!Graph_tools.is_connected(graph)){
         return false;
     }
     if (Graph_tools.is_tree(graph)){
         return false;
     }

    if (graph.getVertices().size()<=2){
        return false;
    }

    for (Node v : graph.getVertices()){
        if (graph.degree(v)==1){
            return false;
        }
    }

    if (Dirac_check(graph)){
        for (Node node : graph.getVertices()){
            if (edgeInterface !=null&&part!=null) {
                timelineQueue.add(edgeInterface.highlight_semi_permant(node, part));
            }
        }

        return true;
    }


ArrayList<Node> allnodesdesending = new ArrayList<>(graph.getVertices());
allnodesdesending.sort(Comparator.comparingInt(graph::degree));
ArrayList<Node> one_node = new ArrayList<>();
one_node.add(allnodesdesending.getFirst());

        if (edgeInterface !=null&&part!=null) {
            timelineQueue.add(edgeInterface.highlight_semi_permant(one_node.getFirst(),part));
        }
        Path_Checker path_checker = check_if_hamilition(graph,
            Graph_tools.adjacency_matrix(graph),allnodesdesending,
                new Path_Checker(one_node,false),
                part,edgeInterface,timelineQueue);

        if (!path_checker.isPath_or_cycle_found()){
            for (Node node : allnodesdesending){
                if (edgeInterface !=null&&part!=null) {
                    timelineQueue.add(edgeInterface.disable_highlights(node, part));
                }
            }
        for (Edge edge : graph.getEdges()){
            if (edgeInterface !=null&&part!=null) {
                timelineQueue.add(edgeInterface.disable_edge(edge, part));
            }
        }

        }

        if (edgeInterface !=null&&part!=null) {
            part.playNext((LinkedList<? extends Animations>) timelineQueue);
        }



        return path_checker.isPath_or_cycle_found();
    }

    private static boolean Dirac_check(Graph graph) {
      int nodes = graph.getVertices().size();
      float condition= (float) nodes /2;
      boolean check=true;
     for (Node node : graph.getVertices()){
         if (graph.degree(node) < condition){
             //just because this fails this does not guarantee non-hamilition
             //you need to look
             check=false;
             break;
         }}

     return check;
    }


    private static Path_Checker check_if_hamilition(Graph graph,
                                                   float[][] adjacency_matrix, List<Node> total_list_of_nodes, Path_Checker path_checker
   , Visual_part  part, GUI_interface edgeInterface, Queue<EdgeAnimation> timelineQueue) {

     if (total_list_of_nodes.size()==path_checker.getCurrent_path().size()&&isacycle(path_checker.getCurrent_path(),
             adjacency_matrix,timelineQueue,edgeInterface,graph,part)) {
         //if we find a ham cycle dont bother couniting
         return new Path_Checker(path_checker.getCurrent_path(),true);
     }

     List<Node> remaining_node=Set_theory_items.complement(total_list_of_nodes, path_checker.getCurrent_path());

     for (Node node : remaining_node) {
         if (path_checker.isPath_or_cycle_found()) {
           //declare victory and stop
             break;
         }
         if (adjacency_matrix[path_checker.getCurrent_path().getLast().getNumber()][node.getNumber()]!=Float.POSITIVE_INFINITY) {

                 if (edgeInterface!=null&&part!=null) {
                     timelineQueue.add(edgeInterface.highlight_edge(graph.getEdge(path_checker.getCurrent_path().getLast(),node),part));
                     timelineQueue.add( edgeInterface.highlight_semi_permant(node,part));
               }
                 path_checker.getCurrent_path().add(node);
                path_checker=check_if_hamilition(graph,adjacency_matrix,total_list_of_nodes,path_checker,part,edgeInterface,timelineQueue);
                if (!path_checker.isPath_or_cycle_found()) {
                    path_checker.getCurrent_path().remove(node);
                if (edgeInterface!=null&&part!=null) {
                    timelineQueue.add(edgeInterface.disable_edge(graph.getEdge(path_checker.getCurrent_path().getLast(),node), part));
                 timelineQueue.add(edgeInterface.disable_highlights(node,part));}
                }
             }
         }


     return path_checker;
   }

    private static boolean isacycle(List<Node> nodes, float[][] adjacencyMatrix, Queue<EdgeAnimation> timelineQueue,
                                    GUI_interface edgeInterface, Graph graph,Visual_part part) {
       Node prev=null;
       Node curr;
       Node last=nodes.getLast();
       Node first=nodes.getFirst();
       boolean isgood=false;

       for (Node node : nodes) {
           curr=node;

         if (node.equals(last)) {
             if (adjacencyMatrix[last.getNumber()][first.getNumber()]!=Float.POSITIVE_INFINITY) {
               isgood=true;
                 if (edgeInterface !=null&&part!=null) {
                     timelineQueue.add(edgeInterface.highlight_edge(graph.getEdge(last, first), part));
                     timelineQueue.add(edgeInterface.onEdgesearched(graph.getEdge(last, first)));
                 }
               break;
             }else {
                 return false;
             }
         }
           if (prev != null) {
               if (adjacencyMatrix[prev.getNumber()][curr.getNumber()]==Float.POSITIVE_INFINITY) {
                break;
               }
               if (edgeInterface !=null&&part!=null) {
                   timelineQueue.add(edgeInterface.highlight_edge(graph.getEdge(prev, curr), part));
               }
           }
           prev=curr;
       }
        return isgood;
    }




}
