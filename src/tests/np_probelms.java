package tests;

import graph_theory.Node;
import set_theory.Set_theory_items;

import java.util.List;

public class np_probelms {


    public static void independent_set(List<Node> totalset, List<Node> current_set,
                                       List<Node> best_set){
        if (current_set.isEmpty()){
            current_set.addAll(totalset);
        } else {
          Set_theory_items.intersection(current_set,totalset);
        }






    }


    public static void clique(){

    }


    public static void vertex_cover(){

    }

    public static void unbounded_knapsack(){

    }


    public  static void binary_knapsack(){
    }

    public  static void coin_change(){

    }



}
