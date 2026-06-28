How to interact with api with your code


Graph class functions for user-
Call with Graph.(method name)
 highlightNode(int id)
  lightNode(int id)

  travelEdge(int from, int to)

   pause(int ms)


  makegraphinvisible() 

   isnodevisible(int id)

   isedgevisible(int id)



  makegraphvisible()
delightNode(int id) 
  colorNode(int id, String color) //needs a hexcode

  highlightEdge(int id) 

 disableEdge(int id) 

makeNodeVisible(int id) 

makeNodeInvisible(int id) 

makeEdgeInvisible(int id)
    
  makeEdgeVisible(int id) 

   incidentEdges(int id)

    getnodes()

     getedges()

   edgeweight(int i) 

  isAdjacent(int a, int b) 
   isIncident(int a, int b) 

    // --- Graph property checks ---
   isBipartite()
    isTree() 
    isComplete()
    isConnected()
    neighbors(int id)
     degree(int id) 
    // --- Matrices ---
    adjMatrix() 
    incidentMatrix()

    // --- Misc ---
    generateColors()


ListAPI.(method)

get(int listName, int index)
 set(int listName, int index, Object value)
    highlight(int listName, int index) 

   getMatrix(int name, int r, int c) 
    setMatrix(int name, int r, int c, Object value)  
highlightMatrix(int name, int r, int c)


Uility.(method)

complement(List<E> univerisal_set, List<E> list)
    union(List<E> list,List<E> list1)
    intersection(List<E> list,List<E> list1)
    difference(List<E> list,List<E> list1)
    
   symmetric_difference(List<E> list,List<E> list1)

    Powerset(List<E> list)
    issubset(List<E> list,List<E> list1)
   ispropersubset(List<E> list,List<E> list1)
   creatematrix(Object[][] f)

Other notes:
It is strongly recommend to know at least basic set and graph theory for this:
https://www.mathsisfun.com/sets/sets-introduction.html
https://www.mathsisfun.com/sets/graph-theory.html

Edges are always "weighted" unweighted edges just have a weight of 1.

Example code will be added to help show the user how to visualize your code

Keep to this version other versions are unsecure!

Although this is coded in Java, You need to code in javascript

Press right click the square below on node mode to generate a node, switch to edge mode and select two different nodes to form an edge, you may specify weight and if its directed or not. Delete a node/ edge on left click with the respective modes.
