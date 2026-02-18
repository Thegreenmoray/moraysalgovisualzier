package tests.data_structure;

import graph_theory.Edge;
import animations.Graph;
import graph_theory.Min_heap;
import graph_theory.Node;
import animations.*;

import java.util.*;


/*The comments are predominantly meant for newbies, for more experienced
 developers these are just here to show how to visualize your code
  with the compiler. */

public class Example_problems {


//-----------------Sorting--------------------------------------------

    public static List<Integer> bubble_sort(List<Integer> e){


        for (int i=0;i<e.size();i++){
            for (int j=0;j<e.size()-1;j++){
                if (e.get(j)>=e.get(j+1)){
                    int hold = e.get(j);
                    e.set(j,e.get(j+1));
                    e.set(j+1,hold);


                }

            }
        }

        return e;
    }


    //round two
    private static  List<Integer> quicksort(List<Integer> e,int low,int high){

        int n=e.size();

        if (n<2) return e;


        int partition = partition(e, low, high);

        quicksort(e, low, partition - 1);
        quicksort(e, partition + 1, high);


        return e;
    }



    private static int partition(List<Integer> e,int low,int high){

        int pivot=e.get(high-1);
        int pivotpoint=-1;

        for (int i=low;i<high;i++){
            if (e.get(i)<=pivot){
                pivotpoint++;
                int temp= e.get(pivotpoint);
                e.set(pivotpoint,e.get(i));
                e.set(i,temp);
            }
        }


        return pivotpoint;
    };


    private static <K> List<List<K>> radixsort(List<List<K>> e){

        return e;
    }



//--------------------------Graph probelms----------------------------

    private static boolean[] bfs(Graph graph, int start, boolean[] visted, Edge_interface graphTheoryInterface, Visual_part visualPart) {
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

                    if (graphTheoryInterface !=null&&visualPart!=null){Edge edge = graph.getEdge(polled.getNumber(), n.getNumber());
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                    timelineQueue.add(graphTheoryInterface.highlightNode(n));
                }
                    q.add(n);
                    visted[n.getNumber()]=true;
                }

            }

        }
        if (graphTheoryInterface !=null&&visualPart!=null) {
            visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);
        }
        return visted;
    }



    public static Graph Prim(Graph graph, Edge_interface graphTheoryInterface, Visual_part visualPart, boolean arcs_or_edges){
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        ArrayList<Edge> heap=new ArrayList<>();
        Graph mst=Graph_tools.empty_graph();
      //only relevant for Christofides
        int pointer=-1;
        for(Edge e:graph.indenctedges(graph.getVertices().getFirst())){
            Min_heap.add_to_heap(e,heap);
        }
        mst.addVertex(graph.getVertices().getFirst());
       while(!heap.isEmpty()&&mst.getVertices().size()<graph.getVertices().size()){
            Edge other_edges=Min_heap.extract_from_heap(heap);

            if (Graph_tools.Willcreateacycle(mst,other_edges.getV1(),other_edges.getV2())) {
                continue;
            }
              if (!mst.containsnode(other_edges.getV1())&&!mst.containsnode(other_edges.getV2())) {
                  continue;
              }

              if (!mst.containsnode(other_edges.getV1())){
                  mst.addVertex(other_edges.getV1());
                  if (arcs_or_edges){
                  mst.addarc(other_edges,other_edges.getWeight());}
                  else {
                      //determine the highest pointer at this point
                      pointer=pointer+1;
                   mst.addEdge(other_edges,other_edges.getWeight(),false,pointer);
                  }//this is for Christofides

              for (Edge e:graph.indenctedges(other_edges.getV1())) {
                  Min_heap.add_to_heap(e,heap);}
              }
              if (!mst.containsnode(other_edges.getV2())) {
                  mst.addVertex(other_edges.getV2());
                  if (arcs_or_edges){
                      mst.addarc(other_edges,other_edges.getWeight());}
                  else {
                      //determine the highest pointer at this point
                      pointer=pointer+1;
                      mst.addEdge(other_edges,other_edges.getWeight(),false,pointer);
                  }//this is for Christofides

                  for (Edge e:graph.indenctedges(other_edges.getV2())) {
                      Min_heap.add_to_heap(e,heap);

                  }
              }



        }


        return mst;
    }

    private static boolean[] dfs(Graph graph, int node_number, boolean[] visted, Edge_interface graphTheoryInterface,Queue<EdgeAnimation> timelineQueue) {
       visted[node_number]=true;

        for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
            if(!visted[n.getNumber()]){
                if (graphTheoryInterface !=null){
                Edge edge=graph.getEdge(graph.getVertices().get(node_number).getNumber(),n.getNumber());

                timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                timelineQueue.add(graphTheoryInterface.highlightNode(n));
                graphTheoryInterface.onEdgesearched(edge);}

                dfs(graph,n.getNumber(),visted,graphTheoryInterface,timelineQueue);
            }
        }


        return visted;
    }


    public static float[][] Floyd_Warshall(float[][] adjacency_matrix, Graph graph, Edge_interface graphTheoryInterface, Visual_part visualPart){

        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                  if (visualPart !=null||graphTheoryInterface!=null){
                    Edge edgeij=graph.getEdge(i,j);
                    Edge edgeik=graph.getEdge(i,k);
                    Edge edgekj=graph.getEdge(k,j);


                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeij));
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeik));
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgekj));}

                    adjacency_matrix[i][j] = Math.min(adjacency_matrix[i][j], adjacency_matrix[i][k]+adjacency_matrix[k][j]);
                }
            }
        }
        if (visualPart !=null||graphTheoryInterface!=null){
        visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);}


        return adjacency_matrix;
    }


    public static boolean[] component_analysis(Graph graph, Edge_interface graphTheoryInterface, boolean[] visted, Visual_part visualPart,boolean b_or_d)  {
        ArrayList<Node> node= new ArrayList<>( graph.getVertices());
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();//only if dfs

        for(int i=0;i<node.size();i++){
            if(!visted[node.get(i).getNumber()]){

              visted= b_or_d ? bfs(graph,i,visted,graphTheoryInterface,visualPart):dfs(graph,i,visted,graphTheoryInterface,timelineQueue);
            }
        }
       if(!b_or_d){ //dfs
           if (graphTheoryInterface !=null&&visualPart!=null) {
               visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);
           }
       }
        return visted;
    }


    //-------------miscellaneous----------------------------------------------------------



    private static boolean binarysearch(List<Integer> list, int e){
       Arrays.sort(new List[]{list});

        return actualbinarysearch(list,e);
    }

    private static boolean actualbinarysearch(List<Integer> list, int e) {
        int start = 0;
        int end=list.size();
        while(start<end){
            int mid=(start+end)/2;
            int element=list.get(mid);
            if(element==e){
                return true;
            }
            else if(element>e){
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }


        return false;
    }

    private static int gcd(int a,int b){
         if(b==0) return a;
         if (a==0) return b;


        return gcd(b,a%b);
    }

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




        return product_matrix;
    }

//------------NP-Approximation algorithms (heuristics)----------------------------
//need to test this
    private static Graph Christofides_approximation(Graph graph, Visual_part visualPart, Edge_interface graphTheoryInterface){

      if (!Graph_tools.is_complete(graph)) {
          return null; //not doable
      }
        //start by building a MST, Prim or Kruskal is fine, ill just use a modified Prim for this
        Graph near_neighbor = Prim(graph, graphTheoryInterface, visualPart,false);

        //find all nodes of odd degree
     List<Node> odd_degree=new ArrayList<>();
       for (Node n : near_neighbor.getVertices()) {
           if(near_neighbor.degree(n)%2!=0){
                odd_degree.add(n);
           }
       }
 //get all edges relating to the odd degrees from the original graph
List<Edge> edges=new ArrayList<>();
for (int i=0;i<odd_degree.size();i++){
    for (int j=i+1;j<odd_degree.size();j++){
        Node u = odd_degree.get(i);
        Node v = odd_degree.get(j);
        Edge e1 = graph.getEdge(u.getNumber(), v.getNumber());
        Edge e2=graph.getEdge(v.getNumber(),u.getNumber());
        if(e1!=null||e2!=null){
        Edge edge = e1 ==null? e2:e1;
        Min_heap.add_to_heap(edge,edges);
        }
    }
}
//add the cheapest edges, even if they are duplicates, we want to make the graph
 //eulerian. the pointers are for the eulerian tour we will construct, and continue
 //from our variant of the MST.
int countining_the_pointer=near_neighbor.getEdges().getLast().getPointer();
    while (!odd_degree.isEmpty()&& !edges.isEmpty()){
        // this is the super greedy approach (n^2 log n), slightly less accurate than the optimal n^3 that  will be done later
        Edge edge=Min_heap.extract_from_heap(edges);
        if (odd_degree.contains(edge.getV1())&&odd_degree.contains(edge.getV2())){

        //by the handshake lemma if there exists nodes with odd degrees in an undirected graph they must come in pairs
        //that is the number of odd degree nodes is even for any graph
        //this is why for prim we had it add edges rather than arcs
        if(near_neighbor.degree(edge.getV1())%2!=0&&near_neighbor.degree(edge.getV2())%2!=0){
           countining_the_pointer=countining_the_pointer+1;
            near_neighbor.addEdge(edge,edge.getWeight(),true,countining_the_pointer);//TRUE IS for allowing dulipcates this techinally becomes a multigraph
           odd_degree.remove(edge.getV1());
           odd_degree.remove(edge.getV2());
        }}}
List<Node> nodeorder=new ArrayList<>();
    //return the node order of the eulerian tour
nodeorder=euleriantour(near_neighbor,edges.getFirst(),edges.getFirst().getPointer(),new boolean[near_neighbor.getEdges().size()],nodeorder,null);
//finally construct a Ham tour with our node list
        return build_Hamiltonian_tour(graph,nodeorder);

    }
    private static Graph build_Hamiltonian_tour(Graph graph, List<Node> nodeorder) {
      Graph tour=Graph_tools.empty_graph();
      Node start=nodeorder.getFirst();
      //extract the last unqine node by trimming the rest of the tail
        Node end_unique = null;
        int size=nodeorder.size()-1;
        for (int i = size; i >= 0; i--) {
            Node current = nodeorder.get(i);
            if (Collections.frequency(nodeorder, current) == 1) {
                end_unique = current;
                break;
            }else{
                nodeorder.remove(i);
            }
        }

      Node prev=null;
      Node next;
      tour.addVertex(nodeorder.getFirst());
      for(Node n:nodeorder){
          next=n;

 // now to deal with the duplicate nodes go
 //down the path set out by the node if there is a previous connection (likely from a
 //multigraph edge, ignore it and jump to the next one if it isn't a duplicate connect with
 //an arc (just pretend it's an edge.)
          if (prev!=null) {
             if (!tour.containsnode(next)) {
                 tour.addVertex(next);
                 Edge e=graph.getEdge(prev.getNumber(),next.getNumber());
                 tour.addarc(e,e.getWeight());
             }
          }
          prev=n;
//now with our trimmed tail this has to be unique, this also doesn't
//conflict with our tour since all of those nodes that were trimmed of would have been visited before
          if (prev.equals(end_unique)) {
              Edge e=graph.getEdge(end_unique.getNumber(),start.getNumber());
              tour.addarc(e,e.getWeight());
          }
      }




        return tour;
    }

    private static List<Node> euleriantour(Graph graph, Edge start, int pointer,boolean[] visted,List<Node> nodes,Edge parent) {

        visted[pointer]=true;
       nodes.add(start.getV1());
//traverse through the graph, since the graph is eulerian and connected
 //we will be able to make it back to the beginning, dont worry about duplicate nodes
 //we will deal with them in the next step
       for(Edge edge:graph.indenctedges(start.getV2())){


           if (parent.equals(edge)&&parent.getPointer()==edge.getPointer()){

               continue;}


            if(!visted[pointer]){
                  nodes.add(edge.getV2());
                nodes=euleriantour(graph,edge,pointer,visted,nodes,start);
            }
        }
        return nodes;
    }





    private static Graph Greedy_coloring(Graph graph,String[] color_list){

    return graph;
}


private static <G> ArrayList<List<G>> bin_packing_best_fit_offline_approximation(ArrayList<List<G>> bins
,int capcaity_of_bins){ //need a way to return bin cost that isn't O(n)
    return null;
}


private static List<Integer>  greedy_coin_change(List<Integer> coins,int intinal_amount){
       //assume coins are sorted
        boolean didnt_count=false;
        List<Integer> coin_list=new ArrayList<>();
        while (!didnt_count&&intinal_amount>0){
          didnt_count=true;
            for(int i=coins.size()-1;i>=0;i--){
                if(coins.get(i)<=intinal_amount){
                didnt_count=false;
                intinal_amount=intinal_amount-coins.get(i);
                coin_list.add(coins.get(i));
                 break;
                }
            }
        }
//you can do better then O(amount*coins) but this is fine for example purposes
if(didnt_count){
    coin_list.clear();
}



    return coin_list;
}
   //----------------------DP---------------------------------

private static int largest_increasing_subsequence(int[] squence_array){
    int[] ints=new int[squence_array.length];

    int n=squence_array.length;
    Arrays.fill(ints, 1);

    for(int i=n-1;0<=i;i--){
        for(int j=i;j<=n-1;j++){
        if(squence_array[i]<squence_array[j]){
            ints[i]=Math.max(ints[i],ints[j]+1);
        }
        }
    }

int largest=0;
    for (int anInt : ints) {
        largest = Math.max(largest, anInt);
    }

        return largest;
}



    private static int Kadane(List<Integer> e){
        int highest=e.getFirst();
        int current=e.getFirst();
        for (int i=1;i<e.size();i++){
            current = Math.max(e.get(i), current + e.get(i));
            highest=Math.max(highest,current);
        }
        return highest;
    }







    //--------------Some Interview problems--------------------------------


    private static boolean isapalindrome(String s){

       if(s==null){
           return false;
       }

        if(s.length()<2){
            return true;
        }
        int n=s.length();

        int half=n/2;
        int pointer1,pointer2;


        if(n%2!=0){
            pointer1=half;
            pointer2=half;
        }else {
            pointer2=half+1;
            pointer1=half-1;
        }


        for(int i=0;i<half;i++){
              if(s.charAt(pointer1)!=s.charAt(pointer2)){
                  return false;
              }
               pointer1--;
              pointer2++;
        }


        return true;
    }


private static int number_of_ways_to_return_change(){


    return 0;
}


private static List<Integer> two_partition(){
    return List.of();
}



    //--------------------NP-Hard---------------------------
    // (1.5)
    public static List<Node> independent_set(Graph graph,List<Node> best_set,List<Node> current_set,List<Node> candidates){

        if(current_set.isEmpty()){
            candidates=  graph.getVertices();
        }else {
            candidates=  node_candidates(graph,current_set,candidates);
        }
        // if our current size and our candidate size is smaller than
        //the best we've found so far, don't even bother searching further.
        if(current_set.size() + candidates.size() <= best_set.size()){
           return best_set;
        }

        for(Node n:candidates){
           current_set.add(n);
            if (current_set.size()>best_set.size()) {
                //prevents contamination
                best_set= new ArrayList<>(current_set);
            }
            best_set=independent_set(graph,best_set,current_set,candidates);
           current_set.remove(n);
        }


        return best_set;
    }

    private static List<Node> node_candidates( Graph graph,List<Node> currentSet,List<Node> candidates) {
    boolean isvaild;
        List<Node> vaild_candidates=new ArrayList<>();
        for(Node candidate :candidates){
        isvaild=true;
            for (Node node : currentSet) {
                if (graph.isadjacent(node, candidate)) {
                    isvaild = false;
                    break;
                }

            }

        if(isvaild){
            vaild_candidates.add(candidate);
        }

        }


        return vaild_candidates;
    }

    //round two
    public static void unbounded_knapsack(int[] values,int[] weights,int weight){
int[] unbounded_knapsack_array=new int[weight+1];

for(int i=1;i<=weight;i++){
    for(int j=0;j<values.length;j++){

       if(weights[j]<=i){
        unbounded_knapsack_array[i]=Math.max(unbounded_knapsack_array[i],
                unbounded_knapsack_array[i-weights[j]]+values[j]);
       }

    }
}



    }

    //round two
    public  static void binary_knapsack(int[] values,int[] weights,int weight){
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


    }

private static void chromomatic_color(){

}
    //round two
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


}
