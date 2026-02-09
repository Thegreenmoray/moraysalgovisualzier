package set_theory;

import graph_theory.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Set_theory_itemsTest {

    @Test
    void isSubset() {
    }

    @Test
    void isProperSubset() {
    }

    @Test
    void cartesian_product() {
    }

    @Test
    void union() {
        ArrayList<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');
        ArrayList<Character> B = new ArrayList<>();
        B.add('a');
        B.add('r');
        B.add('j');
        ArrayList<Character> unioned= (ArrayList<Character>) Set_theory_items.union(A,B);
        ArrayList<Character> expected = new ArrayList<>();
        expected.add('a');
        expected.add('b');
        expected.add('r');
        expected.add('j');
        assertEquals(expected,unioned);
    }

    @Test
    void intersection() {
        ArrayList<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');
        ArrayList<Character> B = new ArrayList<>();
        B.add('a');
        B.add('r');
        B.add('j');
        ArrayList<Character> interectioned= (ArrayList<Character>) Set_theory_items.intersection(A,B);
        ArrayList<Character> expected = new ArrayList<>();
        expected.add('a');
        assertEquals(expected,interectioned);




    }

    @Test
    void complement() {
    }

    @Test
    void cardinality() {
    }

    @Test
    void symmetric_difference() {
    }

    @Test
    void difference() {
    }

    @Test
    void powerset() {
   ArrayList<Integer> A = new ArrayList<>();
   A.add(1);
   A.add(2);
        List<List<Integer>> powerset=new ArrayList<>();

   List<List<Integer>> Powerset = Set_theory_items.
           powerset(0,
           A,
           powerset,
           new ArrayList<>());
   ArrayList<ArrayList<Integer>> expected = new ArrayList<>();

   ArrayList<Integer> empty_set=new ArrayList<>();
   ArrayList<Integer> one_point =new ArrayList<>();
   one_point.add(1);
   ArrayList<Integer> two_point=new ArrayList<>();
   two_point.add(2);
   expected.add(A);
   expected.add(one_point);
   expected.add(two_point);
   expected.add(empty_set);

   assertArrayEquals(expected.toArray(),Powerset.toArray());
    }
}