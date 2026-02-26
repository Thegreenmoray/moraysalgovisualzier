package tests.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Math_stuff {

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }


 public static double factorial_function(int n) {
   if (n >=20) return approxfactorial(n);
return factorial(n); }

    private static double approxfactorial(int n) {
        return  (Math.sqrt(2*Math.PI*n)*Math.pow(n/Math.E,n));
    }



    public static class Linear_Algebra {

        public static float[][] generate_symmetric_matrix(int row_and_col) {
            float[][] matrix = new float[row_and_col][row_and_col];
            Random rand = new Random();

            for (int i=0; i<row_and_col; i++) {
                for (int j=0; j<row_and_col; j++) {

                    int int_random=(int)(rand.nextInt(900));
                    int_random= rand.nextBoolean()?-int_random:int_random;
                    matrix[i][j] = int_random;
                    matrix[j][i] = int_random;

                }
            }


            return matrix;
        }
    }



    public static float[][] transpose(float[][] matrix) {





        return matrix;
    }



   public static class statistics{

    public static float mean(float[] values) {
         if (values.length == 0) return 0;


        float sum = 0.0f;
        for (float value : values) {
            sum += value;
        }


        return sum/values.length;
    }

    public static int median(ArrayList<Integer> values) {

      values.sort(Integer::compareTo);
        if (values.isEmpty()) return 0;


        return values.size()%2!=0?values.get(values.size()/2):(values.get((values.size()/2)+1)+values.get(values.size()/2))/2;
    }








    }



//might be useful later, maybe add this for statistics testing for neral network or whatever?
//and other similar things?
}
