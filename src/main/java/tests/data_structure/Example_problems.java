package tests.data_structure;

import animations.Animations;
import animations.EdgeAnimation;
import animations.Visual_part;
import graph_theory.Edge;
import animations.Graph;
import graph_theory.Min_heap;
import graph_theory.Node;
import animations.*;
import set_theory.Set_theory_items;

import java.util.*;


/*The comments for the other algorithms are predominantly meant for newbies,
for more experienced developers these are just
here to show how to visualize your code with the compiler. */

public class Example_problems {

//------------------------------To start------------------------------

public static void Adding_and_removing_nodes(Visual_part part,Edge_interface api){
    Graph g=part.randomgraph_establish(4,3,true,false,true);
    Node node=new Node(6);
api.addNode(g,node,part);
            api.removeNode(g,g.getVertices().get(2),part);
api.removeNode(g,node,part);}

public static void Adding_and_removing_edges(Visual_part part,Edge_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addEdge(g,edge,part);
        api.addEdge(g,edge2,part);
        api.removeEdge(g,edge2,part);}


    public static void Adding_and_removing_arcs(Visual_part part,Edge_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addarc(g,edge,part);
        api.addarc(g,edge2,part);
        api.removearc(g,edge2,part);}


    public static void Make_graph_invisible_visible(Visual_part part,Edge_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);

api.setallinvisible(g,part);
api.setallvisible(g,part);
}



    public static void making_edges_invisible_visible(Visual_part part,Edge_interface api){
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

    public static void Making_arcs_visible_invisible(Visual_part part,Edge_interface api){
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


    public static void Making_nodes_visible_invisible(Visual_part part,Edge_interface api){
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        Graph g=part.randomgraph_establish(4,3,true,false,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        api.makenodeinvisible(g,node,part);
       timelineQueue.add( api.makenodevisible(g,node,part));
        part.playNext((LinkedList<? extends Animations>) timelineQueue);
    }


    public static void making_edges_weighted(Visual_part part,Edge_interface api){
        Graph g=part.randomgraph_establish(4,3,true,true,false);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addEdge(g,edge,part,9);
        api.addEdge(g,edge2,part,-1);
    }

    public static void Making_arcs_weighted(Visual_part part,Edge_interface api){
        Graph g=part.randomgraph_establish(4,3,true,false,true);
        Node node=new Node(6);
        api.addNode(g,node,part);
        Edge edge=new Edge(g.getVertices().get(3),node);
        Edge edge2=new Edge(g.getVertices().get(2),node);
        api.addarc(g,edge,part,4);
        api.addarc(g,edge2,part,-8);
    }




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



//--------------------------Graph problems----------------------------

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



    public static Graph Prim(Graph graph, Edge_interface graphTheoryInterface, Visual_part visualPart, boolean arcs_or_edges){
        Queue<EdgeAnimation> timelineQueue=new LinkedList<>();
        ArrayList<Edge> heap=new ArrayList<>();
        Graph mst=Graph_tools.empty_graph();
      //only relevant for Christofides
        int pointer=-1;
        for(Edge e:graph.indenctedges(graph.getVertices().getFirst())){
            Min_heap.add_to_heap(e,heap);
        }
       graphTheoryInterface.setallinvisible(graph,visualPart);
        timelineQueue.add(graphTheoryInterface.pause(200));
        mst.addVertex(graph.getVertices().getFirst());
       timelineQueue.add(graphTheoryInterface.pause(200));
        timelineQueue.add(graphTheoryInterface.makenodevisible(graph,graph.getVertices().getFirst(),visualPart));
        timelineQueue.add(graphTheoryInterface.highlightNode(graph.getVertices().getFirst()));
        while(!heap.isEmpty()&&mst.getVertices().size()<graph.getVertices().size()){
            Edge other_edges=Min_heap.extract_from_heap(heap);

            if (Graph_tools.Willcreateacycle(mst,other_edges.getV1(),other_edges.getV2())) {
                continue;
            }


            if (mst.containsnode(other_edges.getV1())&&mst.containsnode(other_edges.getV2())) {
                continue;
            }

              if (!mst.containsnode(other_edges.getV1())){
                  mst.addVertex(other_edges.getV1());
                  timelineQueue.add(graphTheoryInterface.pause(200));
                  timelineQueue.add(graphTheoryInterface.makenodevisible(graph,other_edges.getV1(),visualPart));
                  timelineQueue.add(graphTheoryInterface.highlightNode(other_edges.getV1()));
                  if (arcs_or_edges){
                  mst.addarc(other_edges,other_edges.getWeight());
                      timelineQueue.add(graphTheoryInterface.pause(200));
                      timelineQueue.add( graphTheoryInterface.makearcvisible(graph,other_edges,visualPart));

                  }
                  else {
                      //determine the highest pointer at this point
                      pointer=pointer+1;
                   mst.addEdge(other_edges,other_edges.getWeight(),false,pointer);
                  }//only relevant for Christofides

              for (Edge e:graph.indenctedges(other_edges.getV1())) {
                  Min_heap.add_to_heap(e,heap);}
              }
              if (!mst.containsnode(other_edges.getV2())) {
                  mst.addVertex(other_edges.getV2());
                  timelineQueue.add( graphTheoryInterface.makenodevisible(graph,other_edges.getV2(),visualPart));
                  timelineQueue.add(graphTheoryInterface.pause(200));
                  timelineQueue.add(graphTheoryInterface.highlightNode(other_edges.getV2()));
                  if (arcs_or_edges){
                      mst.addarc(other_edges,other_edges.getWeight());
                      timelineQueue.add(graphTheoryInterface.pause(200));
                      timelineQueue.add( graphTheoryInterface.makearcvisible(graph,other_edges,visualPart));
                  }
                  else {
                      //determine the highest pointer at this point
                      pointer=pointer+1;
                      mst.addEdge(other_edges,other_edges.getWeight(),false,pointer);
                  }//only relevant for Christofides

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

    private static boolean[] dfs(Graph graph, int node_number, boolean[] visted, Edge_interface graphTheoryInterface,Queue<EdgeAnimation> timelineQueue) {
       visted[node_number]=true;

        for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
            if(!visted[n.getNumber()]){
                if (graphTheoryInterface !=null){
                Edge edge=graph.getEdge(graph.getVertices().get(node_number).getNumber(),n.getNumber());
                    timelineQueue.add(graphTheoryInterface.pause(200));
                timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                timelineQueue.add(graphTheoryInterface.highlightNode(n));
                graphTheoryInterface.onEdgesearched(edge);}

                dfs(graph,n.getNumber(),visted,graphTheoryInterface,timelineQueue);
            }
        }


        return visted;
    }


    public static float[][] Floyd_Warshall(float[][] adjacency_matrix, Graph graph, Edge_interface graphTheoryInterface, Visual_part visualPart){
// searching the all pairs of shortest paths,
// good for finding the diameter on smaller graphs
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                //these parts only exist for unit tests ignore these
                  if (visualPart !=null&&graphTheoryInterface!=null){
                    Edge edgeij=graph.getEdge(i,j);
                    Edge edgeik=graph.getEdge(i,k);
                    Edge edgekj=graph.getEdge(k,j);

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

    private static Graph maxium_blossom(Graph graph){
        float[][] adj=Graph_tools.adjacency_matrix(graph);
        float[][] minadj=min_adj(adj);



        return null;
    }

    private static float[][] min_adj(float[][] adj) {

        float max=adj[0][0];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                max=Math.max(max,adj[i][j]);
            }
        }

      for (int i = 0; i < adj.length; i++) {
          for (int j = 0; j < adj[i].length; j++) {
              adj[i][j]=max-adj[i][j];
          }
      }






        return adj;
    }
 //-----------------Actual normal greedy algorithms (greedy is always optimal)-----------

private static float Fractional_knapsack(List<Integer> items,List<Integer> weights,int W){

    return 0;
}






    //-------------miscellaneous----------------------------------------------------------

    private static boolean binarysearch(List<Integer> list, int e){
       list.sort(Comparator.naturalOrder());

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

    private static String shortest_superstring_approximation(){

        return "";
    }





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
        // this is the super greedy approach (n^2 log n), slightly less accurate than the optimal n^3 blossom that will be done later
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


    private static Graph Greedy_coloring(Graph graph){
    if (graph.getVertices().isEmpty()) {return graph;}
       ArrayList<String> list = (ArrayList<String>) Graph_tools.random_unique_colors(graph);
    if (list == null) {return graph;}

    for (Node n1:graph.getVertices()) {

        HashSet<String> no_no_colors=new HashSet<>();
            for (Node node : graph.neighbors(n1)){
                no_no_colors.add(node.getHexcode_color());
        }

       for (String color:list){
           if (!no_no_colors.contains(color)){
               n1.setHexcode_color(color);
               break;
           }

       }

    }

    return graph;
}



   private static List<Node> Greedy_vertex_cover(Graph graph){
        LinkedList<Node> cover=new LinkedList<>();
       graph.getVertices().sort(Comparator.comparingInt(graph::degree).reversed());
       HashSet<Edge> current_edge_collection=new HashSet<>();

       for (Node n:graph.getVertices()){

           if (Set_theory_items.complement(graph.getEdges(),current_edge_collection).isEmpty()){
               break;
           }

           cover.add(n);
           for (Edge e:graph.indenctedges(n)){
               current_edge_collection.add(e);
           }

       }

        return cover;
   }


    private static <G> ArrayList<List<G>> bin_packing_best_fit_offline_approximation(
int capcaity_of_bins,List<Integer> items){ //need a way to return bin cost that isn't O(n)
  ArrayList<List<G>> best_fit_offline_approximation=new ArrayList<>(items.size()); //theorically you would need as many bins as items so long as the items are less than the bin
    items.sort(Collections.reverseOrder());


        return best_fit_offline_approximation;
}

    private static List<Node> node_candidates( Graph graph,List<Node> currentSet,List<Node> candidates) {
        // this is perfectly okay for clique but not independent set

        boolean isvaild;
        List<Node> vaild_candidates=new ArrayList<>();

        for(Node candidate :candidates){
            isvaild=false;
            for (Node node : currentSet) {
                if (graph.isadjacent(node, candidate)) {
                    isvaild = true;
                    break;
                }

            }

            if(isvaild){
                vaild_candidates.add(candidate);
            }

        }


        return vaild_candidates;
    }

private static List<Integer> Greedy_coin_change(List<Integer> coins,int intinal_amount){
       //assume coins are sorted

        List<Integer> coin_list=new ArrayList<>();

            for(int i=coins.size()-1;i>=0;i--){
            while (coins.get(i)<=intinal_amount){
                intinal_amount=intinal_amount-coins.get(i);
                coin_list.add(coins.get(i));

            }
        }

if(intinal_amount>0){
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



private static int Chain_Matrix_multiplication(int[] dimesions){
//note does not multiply matrices but matrix dimensions
    //this is asking what is most efficient way to multiply matrices since
    //matrices are associative but not communicative


    return 0;
}

private static List<Integer> subsetsum(int sum,int[] listed){
int n=listed.length;
boolean[][] subset=new boolean[n+1][sum+1];
ArrayList<Integer> reconstructed=new ArrayList<>();

subset[0][0]=true;
for(int i=1;i<=sum;i++){
    subset[0][i]=false;
}


    for(int i=1;i<=n;i++){
        for(int j=1;j<=sum;j++){
        if(listed[i-1]==j){
            subset[i][j]=true;
        } else if (listed[i-1]>j) {
            subset[i][j]=subset[i-1][j];
        }else {

            subset[i][j] = subset[i - 1][j]
                    || subset[i - 1][j - listed[i - 1]];
        }

    }
}

    if(!subset[n][sum]){
       return reconstructed;  //only case if a subset sum to T does not exist
        //this is here to prevent an infinite loop
    }


    int i=n;
    int j=sum;
    while(j>0){
        if (!subset[i - 1][j]) {
            reconstructed.add(listed[i - 1]);
            j -= listed[i - 1];
        }
        i--;


    }


    return reconstructed;
}

    private static float[] general_minium_coin_change(int amount,List<Integer> coins){
    float[] coins_list=new float[amount+1];
    for(int i=1;i<=amount;i++){
        coins_list[i]=Float.POSITIVE_INFINITY;
    }

    for(int i=1;i<=amount;i++){
        for(int coin:coins){
            if (amount-coin>=0){
            coins_list[i]=Math.min(coins_list[i],coins_list[amount-coin]+1);
            }
        }
    }


        return coins_list;
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







private static List<Integer> two_partition(Integer[] listed){
  int sum=0;
   for (int list:listed){
       sum+=list;
   }
        if (sum%2!=0||sum==0){
            return new ArrayList<>();
        }
        int partition=sum/2;


    int n=listed.length;
    boolean[][] subset=new boolean[n+1][partition+1];
    ArrayList<Integer> reconstructed=new ArrayList<>();

    subset[0][0]=true;
    for(int i=1;i<=partition;i++){
        subset[0][i]=false;
    }


    for(int i=1;i<=n;i++){
        for(int j=1;j<=partition;j++){
            if(listed[i-1]==j){
                subset[i][j]=true;
            } else if (listed[i-1]>j) {
                subset[i][j]=subset[i-1][j];
            }else {

                subset[i][j] = subset[i - 1][j]
                        || subset[i - 1][j - listed[i - 1]];
            }

        }
    }

    if(!subset[n][partition]){
        return reconstructed;  //only case if a partition to P does not exist
        //this is here to prevent an infinite loop
    }


    int i=n;
    int j=partition;
    while(j>0){
        if (!subset[i - 1][j]) {
            reconstructed.add(listed[i - 1]);
            j -= listed[i - 1];
        }
        i--;


    }




    return reconstructed;
}






    //--------------------NP-Hard--------------------------------------------------
    // (1.5)
    public static List<Node> independent_set(Graph graph,List<Node> best_set,List<Node> current_set,List<Node> candidates){


        if(current_set.isEmpty()||candidates.isEmpty()){
            candidates=  graph.getVertices();
        }
        // if our current size and our candidate size is smaller than
        //the best we've found so far, don't even bother searching further.

        if(current_set.size() + candidates.size() <= best_set.size()){
            return best_set;
        }





        for(int i=0;i<candidates.size();i++){
        Node candidate=candidates.get(i);

  current_set.add(candidate);


           if (current_set.size()>best_set.size()) {
                //prevents contamination
                best_set= new ArrayList<>(current_set);
            }
            List<Node> nextCandidates = new ArrayList<>();

 /*in clique its much easier since a node cannot be adjenct to itself
 or at least you cannot form an edge between itself
  however thats a problem for independent set since
 this is examining nodes not adjenct to each other
 and a node can never be adjencent to itself so unless
 you do something like this it will never stop.*/


         for (int j=i+1;j<candidates.size();j++){
             //^ this forces you to look at other nodes
             //and prevent an infinite loop

                 Node c = candidates.get(j);

                 boolean vaild_candidate = true;
                 for (Node u : current_set) {
                     if (graph.isadjacent(u, c)) {
                         vaild_candidate = false;
                         break;
                     }
                 }

                 if (vaild_candidate) nextCandidates.add(c);

         }


            best_set=independent_set(graph,best_set,current_set,nextCandidates);
            current_set.remove(candidate);
        }
// honestly you are better off taking the G^c (coming soon) of the intinal graph
//then running clique, no need to worry about ordering.
// this may not be great for sparse graphs though becuase the complement will be dense
//But thats debatable

        return best_set;
    }


    //round two
    public static void unbounded_knapsack(int[] values,int[] weights,int weight){
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


private static void Matrix_Permanent_of_zero(){

}


private static void chromatic_number(Graph graph,String[] colorlist){

}



private static <G> List<List<G>> set_cover(){

    return List.of();
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
