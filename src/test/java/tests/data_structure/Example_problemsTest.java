package tests.data_structure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


 class Example_problemsTest {
    @Test
     void bubble_sort_test() {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(9);
        list.add(2);


        ArrayList<Integer> bubble_sorted = (ArrayList<Integer>) Example_problems.bubble_sort(list);
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(9);


        for (int i=0;i<test.size();i++){
            assertEquals(test.get(i),bubble_sorted.get(i));
        }

    }
    @Test
    void quicksort() {
    }

    @Test
    void floyd_warshall() {
    }

    @Test
    void component_analysis() {
    }

    @Test
    void unbounded_knapsack() {
    }

    @Test
    void binary_knapsack() {
    }
}