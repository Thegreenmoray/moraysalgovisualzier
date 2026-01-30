//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import animations.EdgeAnimation;
import animations.Edge_interface;
import animations.Visual_part;
import graph_theory.Edge;
import graph_theory.Graph;
import graph_theory.Node;
import graph_theory.VertexState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import tests.Example_probelms;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Main extends Application  {
    Pane root = new Pane();
    ArrayList<Edge> Heap;
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
                    "    // e.g. private void bfs(Graph g, Edge_interface api) { ... }\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void run( Edge_interface api,Visual_part part) {\n" +
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
                    StackPane f = Visual_part.corrlate.get(u);
                    f.setStyle("-fx-background-color: #ff0000");

                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.ZERO,
                                    e -> f.setStyle("-fx-background-color: #00ff00")
                            ),
                            new KeyFrame(Duration.millis(200),
                                    e -> f.setStyle("-fx-background-color: #ff0000")



                            ));
                    return new EdgeAnimation(timeline);
                }

                @Override
                public EdgeAnimation pause(int ms) {
                    Timeline t = new Timeline(new KeyFrame(Duration.millis(ms)));
                    return new EdgeAnimation(t);


                }
            };
            AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        Button compileButton = new Button("Compile");
        Button runButton = new Button("Run");
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




        //as an example
        //Example_probelms.component_analysis(graph,edgeInterface ,
        //        new boolean[graph.getVertices().size()],visualPart,true);


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }





}


