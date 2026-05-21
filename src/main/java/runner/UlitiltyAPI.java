package runner;

import graph_theory.Edge;
import graph_theory.Graph;
import org.graalvm.polyglot.HostAccess;
import set_theory.Set_theory_items;

import java.util.ArrayList;
import java.util.List;

public class UlitiltyAPI {
    Graph graph;

    public UlitiltyAPI(Graph graph) {
        this.graph = graph;
    }

    @HostAccess.Export
    public <E> List<E> complement(List<E> univerisal_set, List<E> list){

        return Set_theory_items.complement(univerisal_set, list);
    }
    @HostAccess.Export
    public <E> List<E> union(List<E> list,List<E> list1){

        return Set_theory_items.union(list,list1);
    }
    @HostAccess.Export
    public <E> List<E> intersection(List<E> list,List<E> list1){


        return Set_theory_items.intersection(list, list1);
    }
    @HostAccess.Export
    public <E> List<E> difference(List<E> list,List<E> list1){

        return Set_theory_items.difference(list, list1);
    }
    @HostAccess.Export
    public <E> List<E> symmetric_difference(List<E> list,List<E> list1){

        return Set_theory_items.symmetric_difference(list,list1);
    }

    @HostAccess.Export
    public <E> List<List<E>> Powerset(List<E> list){
        List<List<E>> powerset=new ArrayList<>();

        return Set_theory_items.powerset(0,list,powerset,new ArrayList<>());
    }
    @HostAccess.Export
    public <E> boolean issubset(List<E> list,List<E> list1){
        ;
        return Set_theory_items.isSubset(list,list1);
    }
    @HostAccess.Export
    public <E> boolean ispropersubset(List<E> list,List<E> list1){

        return Set_theory_items.isProperSubset(list,list1);
    }



}

