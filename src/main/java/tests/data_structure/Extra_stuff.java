package tests.data_structure;

import java.util.List;
import java.util.Random;

public class Extra_stuff {

    private static final char[] Amino_Acids ={'A',
    'C','D','E','F','G','H','I','K','L',
    'M','N','P','Q','R','S','T','V','W','Y'};


    private static <E> List<List<E>> randomized_sets_for_set_cover(List<E> universalset,List<List<E>> elements){

        return elements;
    }


    private static void SAT_builder(int literals,int conjuctions,boolean exact){

    }

    private static char[] sudoku_chars(int size){

        return new char[size];
    }

    private static char[][] vaild_sudoku_board(int root,char[] chars){

        return new char[0][];
    }

    private static String randomized_protein(int length){
        StringBuilder sb = new StringBuilder();

        Random rand = new Random();


        for (int i = 0; i < length; i++) {
            int chars=rand.nextInt(Amino_Acids.length);
            sb.append(Amino_Acids[chars]);
        }
    //theoretically any amino can connect with one another
    //though the laws of physics complicate this.
    //fortunately, strings aren't subject to the laws of physics
    //so they will go in any order

        return sb.toString();
    }
}
