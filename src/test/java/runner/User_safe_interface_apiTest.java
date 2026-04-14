package runner;

import graph_theory.Graph;
import graph_theory.Edge;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static animations.Visual_partTest.visualPart;

class User_safe_interface_apiTest {

    User_safe_interface_api user_safe_interface_api=new User_safe_interface_api(new Graph(new ArrayList<>(),new ArrayList<>()),new LinkedList<>(),visualPart);






    @Test
    void establishgraph() {
    }

    @Test
    void clearGraph() {
    }

    @Test
    void arc_visiblility() {
    }

    @Test
    void highlight_node() {
    }

    @Test
    void begin_animations() {
    }

    @Test
    void onEdgesearched() {
    }

    @Test
    void pause() {
    }

    @Test
    void make_node_visible() {
    }

    @Test
    void light_node() {
    }

    @Test
    void delight_node() {
    }

    @Test
    void color_node() {
    }

    @Test
    void highlightedge() {
    }

    @Test
    void disable_edge() {
    }

    @Test
    void make_arc_invisible() {
    }

    @Test
    void makeedge_visible() {
    }

    @Test
    void makeedge_invisible() {
    }

    @Test
    void makenode_invisible() {
    }

    @Test
    void remove_node() {
    }

    @Test
    void remove_edge() {
    }

    @Test
    void safelyadd_edge() {
    }

    @Test
    void get_speficedge() {
    }

    @Test
    void testSafelyadd_edge() {
    }

    @Test
    void add_arc() {
    }

    @Test
    void remove_arc() {
    }

    @Test
    void safely_add_arc() {
        for (int i=0;i<4;i++){
            user_safe_interface_api.safely_add_a_node();}

        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                user_safe_interface_api.
                        safely_add_arc(user_safe_interface_api.obtain_existing_node(i),user_safe_interface_api.obtain_existing_node(j));
            }
        }

    }

    @Test
    void testSafely_add_arc() {
    }

    @Test
    void getspeficarc() {
    }

    @Test
    void graphthings() {
    for (int i=0;i<4;i++){
        user_safe_interface_api.safely_add_a_node();}

    for (int i=0;i<4;i++){
        for(int j=i+1;j<4;j++){
            user_safe_interface_api.
     safely_add_edge(user_safe_interface_api.obtain_existing_node(i),user_safe_interface_api.obtain_existing_node(j));
        }
    }

    user_safe_interface_api.isadjenct(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.obtain_existing_node(1));
    user_safe_interface_api.isincident(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.get_speficedge(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.obtain_existing_node(1)));
    user_safe_interface_api.degree(user_safe_interface_api.obtain_existing_node(0));
    user_safe_interface_api.neighbors(user_safe_interface_api.obtain_existing_node(0));
    user_safe_interface_api.indence_edges(user_safe_interface_api.obtain_existing_node(0));
     user_safe_interface_api.get_speficedge(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.obtain_existing_node(1));
     user_safe_interface_api.getspeficarc(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.obtain_existing_node(1));


    }



    @Test
    void graphtools() {
        for (int i=0;i<4;i++){
            user_safe_interface_api.safely_add_a_node();}

        for (int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                user_safe_interface_api.
                        safely_add_edge(user_safe_interface_api.obtain_existing_node(i),user_safe_interface_api.obtain_existing_node(j));
            }
        }
    user_safe_interface_api.is_bipartite();
    user_safe_interface_api.is_tree();
    user_safe_interface_api.adjacency_matrix();
    user_safe_interface_api.incident_matrix();
    user_safe_interface_api.is_compelte();
    user_safe_interface_api.is_connected();
    user_safe_interface_api.generate_colors();
    }


    @Test
    void heapstuff(){
        for (int i=0;i<4;i++){
            user_safe_interface_api.safely_add_a_node();}

        for (int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                user_safe_interface_api.
                        safely_add_edge(user_safe_interface_api.obtain_existing_node(i),user_safe_interface_api.obtain_existing_node(j));
            }
        }

       List<Edge> edges =user_safe_interface_api.createheap();
        user_safe_interface_api.add_to_heap(user_safe_interface_api.get_speficedge(user_safe_interface_api.obtain_existing_node(0),user_safe_interface_api.obtain_existing_node(1)),edges);
        user_safe_interface_api.extract_from_heap(edges);

    }

    @Test
    void setthoery() {
    List<Integer> list=new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    List<Integer> list1=new ArrayList<>();
    list1.add(1);
    list1.add(5);
    list1.add(9);

    List<Integer> universial=new ArrayList<>();
    universial.add(1);
    universial.add(2);
    universial.add(3);
    universial.add(5);
    universial.add(9);

    user_safe_interface_api.intersection(list1,list);
    user_safe_interface_api.union(list1,list);
    user_safe_interface_api.difference(list1,list);
    user_safe_interface_api.complement(universial,list);
    user_safe_interface_api.cartiesan_product(universial,list);
    user_safe_interface_api.symmetric_difference(list1,list);
    user_safe_interface_api.Powerset(list);
    user_safe_interface_api.issubset(universial,list);
    user_safe_interface_api.ispropersubset(list1,list);

    }



    @Test
    void graph_random() {
    }

    @Test
    void makegraphinvisible() {
    }

    @Test
    void makegraphvisible() {
    }

   @Test
   void squarestuff(){

   }


}