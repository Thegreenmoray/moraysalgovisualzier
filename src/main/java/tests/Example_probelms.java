package tests;

import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import animations.*;
import java.util.*;

public class Example_probelms {

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



    public static  List<Integer> quicksort(List<Integer> e){

        int n=e.size();

        if (n<2) return e;




        return e;
    }


    private static List<Integer> partition(List<Integer> e,int low,int high){

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


        return e;
    };



    public static int Kadane(List<Integer> e){
        int highest=e.getFirst();
        int current=e.getFirst();
        for (int i=1;i<e.size();i++){
            current = Math.max(e.get(i), current + e.get(i));
            highest=Math.max(highest,current);
        }
        return highest;
    }


    private static boolean[] bfs(Graph graph, int start, boolean[] visted, Edge_interface graphTheoryInterface, Visual_part visualPart) {
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        Queue<Node> q=new LinkedList<>();

        Node node=graph.getVertices().get(start);
        visted[node.getNumber()]=true;
        q.add(node);
        timelineQueue.add(graphTheoryInterface.highlightNode(node));
        while(!q.isEmpty()){
            Node polled=q.poll();
            List<Node> neighbors = graph.neighbors(polled);

            for (Node n:neighbors) {

                if(!visted[n.getNumber()]){
                    Edge edge=graph.getEdge(polled.getNumber(),n.getNumber());
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edge));
                    timelineQueue.add(graphTheoryInterface.highlightNode(n));
                    q.add(n);
                    visted[n.getNumber()]=true;
                }

            }

        }
        visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);

        return visted;
    }



    public void dijkstra(Graph graph, int start, boolean[] visted, int[] distance, Visual_part visualPart, ArrayList<Edge> heap) {

        distance[start]=0;
        visted[start]=true;







    }




    public static boolean[] dfs(Graph graph, int node_number, boolean[] visted, Edge_interface graphTheoryInterface, Visual_part visualPart) {
        visted[node_number]=true;

        for(Node n:graph.neighbors(graph.getVertices().get(node_number))){
            if(!visted[n.getNumber()]){
                Edge edge=graph.getEdge(graph.getVertices().get(node_number).getNumber(),n.getNumber());
                graphTheoryInterface.onEdgesearched(edge);
                dfs(graph,n.getNumber(),visted,graphTheoryInterface,visualPart);
            }
        }


        return visted;
    }


    public float[][] floyd_warshall(float[][] adjacency_matrix, Graph graph, Edge_interface graphTheoryInterface, Visual_part visualPart){
        Queue<EdgeAnimation> timelineQueue = new LinkedList<>();
        for (int k = 0; k < graph.getVertices().size(); k++) {
            for (int i = 0; i < graph.getVertices().size(); i++) {
                for (int j = 0; j < graph.getVertices().size(); j++) {
                    Edge edgeij=graph.getEdge(i,j);
                    Edge edgeik=graph.getEdge(i,k);
                    Edge edgekj=graph.getEdge(k,j);


                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeij));
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgeik));
                    timelineQueue.add(graphTheoryInterface.onEdgesearched(edgekj));

                    adjacency_matrix[i][j] = Math.min(adjacency_matrix[i][j], adjacency_matrix[i][k]+adjacency_matrix[k][j]);
                }
            }
        }
        visualPart.playNext((LinkedList<EdgeAnimation>) timelineQueue);
        return adjacency_matrix;
    }




    public static void component_analysis(Graph graph, Edge_interface graphTheoryInterface, boolean[] visted, Visual_part visualPart,boolean b_or_d)  {
        ArrayList<Node> node= new ArrayList<>( graph.getVertices());

        for(int i=0;i<node.size();i++){
            if(!visted[node.get(i).getNumber()]){

              visted= b_or_d ? bfs(graph,i,visted,graphTheoryInterface,visualPart):dfs(graph,i,visted,graphTheoryInterface,visualPart);
            }
        }

    }









    public static void independent_set(){

    }




    public static void unbounded_knapsack(int[] values,int[] weights,int weight){
int[] unbounded_knapsack_array=new int[weight+1];

for(int i=1;i<=weight;i++){
    for(int j=0;j<values.length;j++){

       if(weights[j]<=i){
        unbounded_knapsack_array[i]=Math.max(unbounded_knapsack_array[i],unbounded_knapsack_array[i-weights[j]]+values[j]);
       }

    }
}



    }







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
//wip

public static void generalized_sudoku(char[][] sudoku, int n,int k,char[] total_chars,char[] vaild_char_list){
if (issolved_or_is_vaild_sudoku_board(sudoku)){
    return;
}

for (int i = n; i <sudoku.length ; i++) {
    for (int j = k; j < sudoku[i].length; j++) {
        if (sudoku[i][j] != ' ') {
            continue;
        }

    }

}





    }

    private static boolean issolved_or_is_vaild_sudoku_board(char[][] sudoku) {

        for(int i=0;i<sudoku.length;i++){
            if (!valid_row(sudoku,i)){
                    return false;
                }

        }
        for(int j=0;j<sudoku[0].length;j++) {
            if (!vaild_column(sudoku,j)){
                return false;
            }
        }

        int n_sqrt= (int) Math.sqrt(sudoku.length);


        for(int i=0;i<n_sqrt;i++){
            for(int j=0;j<n_sqrt;j++){
                if (!vaild_sqrtn_by_sqrtn(sudoku,i*n_sqrt,j*n_sqrt,n_sqrt)){
                    return false;
                }
            }}




        return true;
    }

    private static boolean vaild_column(char[][] sudoku, int col) {
        HashSet<Character> set = new HashSet<>();
        for(int i=0;i<sudoku[col].length;i++){
          char c = sudoku[i][col];
          if(c==' '){
              return false;
          }
          if (set.contains(c)){
              return false;
          }else {set.add(c);}

      }


        return true;
    }

    private static boolean vaild_sqrtn_by_sqrtn(char[][] sudoku,int row,int col,int n_sqrt) {
        HashSet<Character> set = new HashSet<>();


        int intdivision_row=(row/n_sqrt)* n_sqrt;
        int intdivision_col=(col/n_sqrt)* n_sqrt;

        for(int i=0;i<n_sqrt;i++){
            for(int j=0;j<n_sqrt;j++){
              char c =sudoku[i+intdivision_row][j+intdivision_col];
                if(c==' '){
                    return false;
                }
            if (set.contains(c)){
         return false;
            }else {set.add(c);}

            }

}


        return true;
    }

    private static boolean valid_row(char[][] sudoku,int row) {

        HashSet<Character> set = new HashSet<>();
        for (int i=0;i<sudoku.length;i++){
            char c =sudoku[row][i];
            if (c==' '){
           return false;
       }

            if (set.contains(c)){
                return false;
            }else {set.add(c);}

        }


        return true;
    }


}
