How to interact with api with your (yes yours) code


Call like this User_safe_interface_api.method();
ex: User_safe_interface_api.safelyaddnode();

Graph class functions for user-

safelyaddnode- add nodes to graph

getnodes - returns list of nodes
getedges - returns list of edges

remove_node- removes nodes along with any edges attached to it (needs node)

remove_edge- removes an edge (needs edge)

makeedge_visible and makeedge_invisible makes edge visible/invisible(needs edge)

safely_add_edge- add undirected edge, variant with weight (needs two nodes and float if weighted)

safely_add_arc- add directed edge, variant with weight (needs two nodes and float if weighted)

obtain_existing_node- get node from the graph (needs an int)

obtain_node_number -get number of the node from the graph(needs a node)

Getedge-extracts specific edge from graph (needs two nodes)

arc_visiblility and make_arc_invisible-Makes an arc visible/invisible if it isnt already (needs an edge)

highlight_node- temporarily highlights node of choice (needs node)

begin_animations- animates the algothrim, put at end.

traceline-trace the starting point of a node to another node(needs an edge)

make_node_visible and makenode_invisible-makes node visible/invisible if it is isnt already (needs node)

light_node and delight_node- semi-permanently highlights and dehighlights node (needs node)

degree-returns degree of node (needs node)

neighbors-returns the neighbors of a node (needs node)

indenctedges-returns edges indenct to a node (needs node)

IsAdjacent- Checks if the two nodes are adjacent (needs two nodes)

IsIncident- checks if an edge is incident between a node (needs a node and an edge)

makegraphvisible/makegraphinvisible- makes whole graph visible/invisible

Set/Matrix functions:

establishlist -establishs list in gui (needs a list), returns a key

establishmatrix -establishs matrix in gui (needs a matrix), returns a key

highlight_list_square- temporaily highlights a square of a list given an index and a key (requires int index and int id)

edit_list_square_value- edits square's value of a list an index, a key, and a value to replace it (requires int index,int id,and a value)

highlight_matrix_square- temporaily highlights a square of a matrix given a row, a column and a key (requires 3 ints respective of order)

edit_matrix_square_value- edits square's value of a matrix given a row, a column, a key, and a value to replace it (requires 3 ints respective of order and a value)

Extra:

pause- halts the animation for a specified time (need int)

clearboard- resets internal logic of api (use clear button to clear visuals)


Set_theory functions for user-

Union-Returns a list containing elements for either list A or list B (needs two lists)

Intersection- Returns a list containing elements only found in both list A and list B (needs two lists)

Complement -Compares two lists B and A with taking all elements not in A and returning them (needs two lists)

Difference - Compares list A and list B and takes all elements in A, but also not in B (needs two lists)

Powerset-Generates all sublists of a list (needs a list)

Isasubset- Checks if a set is a subset of another set (needs two lists)

ispropersubset- Checks if a set is a proper subset of another set (needs two lists)

Extra Functions:
Edge min-heap: evaluates edges by weight( useful for algorithms like Prim or Dijkstra)

consists of:

createheap- creates a empty set of edges

add_to_heap-adds an edge to the set given and returns the new one (requires an edge and List of edges)

extract_from_heap- extracts cheapest edge from edgelist given (requires List of edges)

Adjacency Matrix Generator

Arc-Incident Matrix Generator

Directed and Undirected Graph generators

 isBipartite- Checks if the graph is bipartite

 isatree - checks if the graph is a tree

 isconnected - checks if the graph is connected

 iscompelte -checks if the graph is complete

 randomunqiunecolorgenerator- generates a list of random uqunine colors

 randomgraph- generate random graph you can specifify if its weighted, allows for negative weights, or if it is directed (needs an int and 3 booleans)

Other notes:
It is strongly recommend to know at least basic set and graph theory for this:
https://www.mathsisfun.com/sets/sets-introduction.html
https://www.mathsisfun.com/sets/graph-theory.html

Edges are always "weighted" unweighted edges just have a weight of 1.

Example code will be added to help show the user how to visualize your code

For algothrims like prim use addarcs not addedges, otherwise it will count it twice. especially if using irrational numbers since you cant just divide by 2 cleanly

Keep to this version other versions are unsecure!

If using getspeficedge, you will have to call it twice becuase the edge structure assumes directed (should you need that)

Although this is coded in Java, You need to code in javascript

The clear button will only clear visuals, you will have to run clearboard() to clear the graph, similar for sets/matrices