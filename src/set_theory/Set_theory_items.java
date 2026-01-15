package set_theory;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Set_theory_items {


    public static <T> boolean isSubset(List<T> A, List<T> B) {
        return B.containsAll(A);
    }

    public static <T> boolean isProperSubset(List<T> A, List<T> B) {
        return B.containsAll(A) && A.size() < B.size();
    }




    public static <T,K> List<Pair<T,K>> cartesian_product(List<T> tList,List<K> kList){
        List<Pair<T,K>> result = new ArrayList<>();
       
       for (T t:tList){
            for(K k:kList){
                result.add(new Pair<>(t,k));
            }
       }

        return result;
    }

    public static <T> List<T> union(List<T> list1,List<T> list2){

        Set<T> list = new HashSet<>(list1);
        list.addAll(list2);

        return new ArrayList<>(list);
    }


    public static <T> List<T> intersection(List<T> list1,List<T> list2) {
        Set<T> final_set = new HashSet<>();

         for(T i:list1){
             if(list2.contains(i)){
                 final_set.add(i);
             }
         }



        return new ArrayList<>(final_set);
    }




    public static <T> List<T> complement(List<T> U,List<T> A) {
      //can also be used as difference
        Set<T> list = new HashSet<>(U);

         list.removeAll(A);

        return new ArrayList<>(list);
    }




    public static <T> int cardinality(List<T> A){

        return A.size();
    }


    public static <T> List<T> symmetric_difference(List<T> A, List<T> subset){


        return union(difference(A,subset),difference(subset,A));
    }


    public static <T> List<T> difference(List<T> A, List<T> B) {
        //just to be more explicit
        return complement(A, B);
    }




    public static <T> List<List<T>> powerset(int i,List<T> base_set,List<List<T>> current_subsets,List<T> subset){
        if(i==cardinality(base_set)){
            current_subsets.add(new ArrayList<>(subset));
            return current_subsets;
        }
        T t=  base_set.get(i);
        subset.add(t);

       //take it
        powerset(i+1,base_set,current_subsets,subset);

        subset.remove(subset.size() - 1);

        //leave it
        return  powerset(i+1,base_set,current_subsets,subset);
    }



    }
