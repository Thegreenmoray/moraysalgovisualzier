package set_theory;

import graph_theory.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Set_theory_itemsTest {
//done
    @Test
    void isSubset() {
    List<Integer> e = new ArrayList<>();
    List<Integer> a = new ArrayList<>();
    a.add(1);
    boolean r=Set_theory_items.isSubset(e,a);
    assertTrue(r);
    }

    @Test
    void isProperSubset() {
        List<Integer> e = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(1);
        boolean r=Set_theory_items.isProperSubset(e,a);
        assertTrue(r);
        assertFalse(Set_theory_items.isProperSubset(b,e));
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
        List<Character> U = new ArrayList<>();
        U.add('a');
        U.add('b');
        U.add('r');
        U.add('j');
        U.add('c');
        U.add('d');
        List<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');

        List<Character> Actual=Set_theory_items.complement(U,A);
        List<Character> expected = new ArrayList<>();
        expected.add('r');
        expected.add('c');
        expected.add('d');
        expected.add('j');
      assertEquals(expected,Actual);

    }

    @Test
    void cardinality() {
        ArrayList<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');
        A.add('r');
        A.add('j');
        int actaul=Set_theory_items.cardinality(A);
        assertEquals(4,actaul);

    }

    @Test
    void symmetric_difference() {
        ArrayList<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');
        A.add('r');
        ArrayList<Character> B = new ArrayList<>();
        B.add('a');
        B.add('r');
        B.add('j');
        B.add('c');
        List<Character> actual= Set_theory_items.symmetric_difference(B,A);
        List<Character> expected = new ArrayList<>();
        expected.add('b');
        expected.add('c');
        expected.add('j');
        assertEquals(expected,actual);
    }

    @Test
    void difference() {
        List<Character> U = new ArrayList<>();
        U.add('a');
        U.add('b');
        U.add('r');
        U.add('j');
        U.add('c');
        U.add('d');
        List<Character> A = new ArrayList<>();
        A.add('a');
        A.add('b');

        List<Character> Actual=Set_theory_items.difference(U,A);
        List<Character> expected = new ArrayList<>();
        expected.add('r');
        expected.add('c');
        expected.add('d');
        expected.add('j');
        assertEquals(expected,Actual);
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