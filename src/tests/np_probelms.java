package tests;

public class np_probelms {




    public static void clique(){

    }


    public static void vertex_cover(){

    }

    public static void unbounded_knapsack(){

    }


    public  static void binary_knapsack(int[] values,int[] weights,int weight){
  int[][] binary_knapsack_matrix=new int[weight+1][weights.length+1];

   for(int i=0;i<=weight;i++){
       for (int j=0;j<=weights.length;j++){
           binary_knapsack_matrix[i][j]=0;
       }
   }

for(int i=0;i<weights.length;i++){
    for (int w=1;w<=weight;w++){
        if(weights[i]>w){
           binary_knapsack_matrix[i][w]=values[i];
        }




    }
}


    }
//wip
//for now set it to max of 25 X25 until an iterative version can be made
public static void generalized_sudoku(char[][] sudoku, int n,int k, int n_sqrt,char[] total_chars,char[] vaild_char_list){
  if(issolved(sudoku,n,k)){
      return;
  }






    }

    private static boolean issolved(char[][] sudoku,int row,int col) {

        return valid_row(sudoku,row)&&vaild_column(sudoku,col)&&vaild_sqrtn_by_sqrtn(sudoku,row,col);
    }

    private static boolean vaild_column(char[][] sudoku, int col) {
      for(int i=0;i<sudoku[col].length;i++){
          if(sudoku[i][col]=='X'){
              return false;
          }
      }


        return true;
    }

    private static boolean vaild_sqrtn_by_sqrtn(char[][] sudoku,int row,int col) {
        int n_sqrt= (int) Math.sqrt(sudoku.length);

        int intdivision_row=row/n_sqrt;
        int intdivision_col=col/n_sqrt;

        for(int i=0;i<n_sqrt;i++){
            for(int j=0;j<n_sqrt;j++){
                if(sudoku[i+intdivision_row][j+intdivision_col]=='X'){
                    return false;
                }
            }

}


        return true;
    }

    private static boolean valid_row(char[][] sudoku,int row) {
   for (int i=0;i<sudoku.length;i++){
       if (sudoku[row][i]=='X'){
           return false;
       }
   }
        return true;
    }


}
