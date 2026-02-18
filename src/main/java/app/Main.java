package app;

import animations.*;
import graph_theory.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main extends Application {
    Pane root = new Pane();
    @Override
    public void start(Stage stage) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            stage.setTitle("Moray's Algo visualizer");

            root.setPrefSize(2000,2000);
            Visual_part part =new Visual_part(root);
            Scene scene = new Scene(root,800,600);
            stage.setScene(scene);
            TextArea codeInput = new TextArea();
            codeInput.setText("import graph_theory.*;\n" +
                    "import animations.*;\n" +
                    "import java.util.*;\n" +
                    "import tests.*;\n" +
                    "import set_theory.*;\n" +
                    "public class UserAlgorithm implements GraphAlgorithm {\n" +
                    "\n" +
                    "    // You can add helper methods here\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void run(Edge_interface api,Visual_part part) {\n" +
                    "        // Your algorithm goes here\n" +
                    "    }\n" +
                    "}\n");

              codeInput.setLayoutX(300);
              codeInput.setLayoutY(300);
        codeInput.setFont(Font.font("Consolas", 16)); // readable monospaced font
        codeInput.setWrapText(true);


        Edge_interface edgeInterface=new Edge_interface() {
                @Override
                public EdgeAnimation onEdgesearched(Edge e) {
                    return Visual_part.animate_edge(e, root);
                }

                @Override
                public EdgeAnimation highlightNode(Node u) {
                    u.setVertexState(VertexState.VISITED);

                return Visual_part.highlightNode(u);}

                @Override
                public EdgeAnimation pause(int ms) {
                    Timeline t = new Timeline(new KeyFrame(Duration.millis(ms)));
                    return new EdgeAnimation(t);


                }
                @Override
            public void addNode(Graph graph, Node n, Visual_part part) {
                part.addnode(graph,n);
            }

            @Override
            public void removeNode(Graph graph, Node n, Visual_part part) {
                part.removenode(graph,n);
            }

            @Override
            public void addEdge(Graph graph, Edge e, Visual_part part) {
                 part.addedge(graph,e);
            }

            @Override
            public void removeEdge(Graph graph, Edge e, Visual_part part) {
               part.remove_edge(e, graph);
            }

            @Override
            public void setallinvisible( Visual_part part) {
              part.makeallofgraphinvisible();
            }

            @Override
            public void setallvisible(Visual_part part) {
                part.makeallofgraphvisible();
            }

            @Override
            public void makenodevisible(Graph graph,Node node, Visual_part part) {
             part.make_node_visible(graph,node);
            }

            @Override
            public void makeedgevisible(Graph graph,Edge edge, Visual_part part) {
           part.make_edge_visible(graph,edge);
            }

            @Override
            public void makeedgeinvisible(Graph graph,Edge e, Visual_part part) {
           part.make_edge_invisible(graph,e);
            }

            @Override
            public void makenodeinvisible(Graph graph,Node node, Visual_part part) {
          part.make_node_invisible(graph,node);
            }


        };
        //eventally add arcs


            AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        Button compileButton = new Button("Compile");
        Button runButton = new Button("Run");
        compileButton.setLayoutX(200);
        runButton.setLayoutX(200);
        compileButton.setLayoutY(200);
        runButton.setLayoutY(250);
        root.getChildren().addAll(codeInput,compileButton,runButton);

        compileButton.setOnAction(e -> {
            try {
                algorithmRunner.setup(codeInput.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        runButton.setOnAction(e -> {
            try {
                algorithmRunner.run( edgeInterface,part);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }





}


