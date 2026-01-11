import java.util.ArrayList;
import java.util.List;

public class Set_based_algothrims {


 public static List<Integer> bubble_sort(List<Integer> e){
     //not ideal but good for now

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



public static  List<Integer> mergesort(List<Integer> e){

     int n=e.size();

     if (n<2) return e;

     int mid=(n / 2);
     List<Integer> first_half=new ArrayList<>();
     List<Integer> second_half=new ArrayList<>();

     for (int i=0;i<mid;i++){
         first_half.add(e.get(i));
     }
     for (int i=mid;i<n;i++){
         second_half.add(e.get(i));
     }



    return merge(mergesort(first_half),mergesort(second_half));
}

    private static List<Integer> merge(List<Integer> list1,List<Integer> list2) {


        return list1;
    }








    public static int house_robber(List<Integer> e){
   ArrayList<Integer> money=new ArrayList<>(e.size());
     for (int i=0;i<e.size();i++){
         money.add(0);
     }
        money.set(0, e.get(0));
        money.set(1, Math.max(e.get(0), e.get(1)));

        for (int i=2;i<e.size();i++){
        money.set(i,Math.max(money.get(i-1), e.get(i)+money.get(i-2)));
     }

        return money.get(e.size()-1);
    }


public static int Kadane(List<Integer> e){
     int highest=e.getFirst();
     int current=e.getFirst();
     for (int i=1;i<e.size();i++){
         current = Math.max(e.get(i), current + e.get(i));
         highest=Math.max(highest,current);
     }
    return highest;
}

public static int Fibonacci(int n){
     int[] e=new int[n];
    e[0]=1;
    e[1]=1;

     for (int i=2;i<n;i++){
        e[i]=e[i-1]+e[i-2];
     }
    return e[n-1];
}




}
