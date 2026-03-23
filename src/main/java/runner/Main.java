package runner;

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
import java.util.List;


public class Main extends Application {
  protected   static Pane root = new Pane();


    protected static GUI_interface edgeInterface=new GUI_interface() {
        @Override
        public EdgeAnimation onEdgesearched(Edge e) {
            return Visual_part.animate_edge(e, root);
        }

        @Override
        public EdgeAnimation highlightNode(Node u) {

            return Visual_part.highlightNode(u);}

        @Override
        public EdgeAnimation pause(int ms) {
            Timeline t = new Timeline(new KeyFrame(Duration.millis(ms)));
            return new EdgeAnimation(t);


        }


        @Override
        public EdgeAnimation highlight_semi_permant( Node node, Visual_part part) {
            return part.highlightnode(node);
        }

        @Override
        public EdgeAnimation disable_highlights(Node node, Visual_part part) {
            return part.disablenodes(node);
        }

        @Override
        public EdgeAnimation highlight_edge(Edge e, Visual_part part) {
            return part.highlightedge(e);
        }

        @Override
        public EdgeAnimation disable_edge(Edge e, Visual_part part) {
            return part.disableedge(e);
        }

        @Override
        public EdgeAnimation color_nodes(Node node, Visual_part part, String Hexcode_color) {
            return part.colornode(node,Hexcode_color);
        }

        @Override
        public <E> SetAnimation updatetile(List<E> list, Visual_part part, int index) {
            return null;
        }


        @Override
        public EdgeAnimation makenodevisible(Node node, Visual_part part) {
            return part.make_node_visible(node);

        }

        @Override
        public EdgeAnimation makeedgevisible(Edge edge, Visual_part part) {
         return    part.make_edge_visible(edge);
        }

        @Override
        public EdgeAnimation makeedgeinvisible(Edge e, Visual_part part) {
          return   part.make_edge_invisible(e);
        }

        @Override
        public EdgeAnimation makenodeinvisible(Node node, Visual_part part) {
           return part.make_node_invisible(node);
        }



        @Override
        public EdgeAnimation makarcinvisible(Edge edge,Visual_part part) {
          return  part.makearcinvisible(edge);
        }

        @Override
        public EdgeAnimation makearcvisible( Edge edge, Visual_part part) {
            return part.makearcvisible(edge);
        }


    };
    @Override
    public void start(Stage stage) throws InterruptedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            stage.setTitle("Moray's Algo visualizer");

            root.setPrefSize(2000,2000);
            Visual_part part =new Visual_part(root);
            Scene scene = new Scene(root,800,600);
            stage.setScene(scene);
            TextArea codeInput = new TextArea();
            codeInput.setText( """
let n = User_safe_interface_api.safely_add_a_node();
"""

);

              codeInput.setLayoutX(300);
              codeInput.setLayoutY(300);
        codeInput.setFont(Font.font("Consolas", 16)); // readable monospaced font
        codeInput.setWrapText(true);




            AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        Button compileButton = new Button("Compile");
        Button runButton = new Button("Run");
        compileButton.setLayoutX(100);
        runButton.setLayoutX(100);
        compileButton.setLayoutY(200);
        runButton.setLayoutY(250);

        Button clearButton = new Button("Clear");

        clearButton.setLayoutX(100);
        clearButton.setLayoutY(300);
        root.getChildren().addAll(codeInput,compileButton,runButton,clearButton);

        compileButton.setOnAction(e -> {
            try {
                algorithmRunner.setup(codeInput.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        runButton.setOnAction(e -> {
            Thread t = new Thread(() -> {
                try {
                    algorithmRunner.run(edgeInterface, part);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            t.start();
        });


        clearButton.setOnAction(e -> {
          //User_safe_interface_api.clearGraph();
       });

        stage.show();
    }

    static void main(String[] args) {
        launch(args);
    }





}


