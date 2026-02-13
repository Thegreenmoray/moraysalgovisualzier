package tests.data_structure;

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




   public static class statistics{

        








    }



//might be useful later, maybe add this for statistics testing for neral network or whatever?
//and other similar things?
}
