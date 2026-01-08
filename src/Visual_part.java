import graph_theory.Edge;
import graph_theory.Node;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Visual_part extends Application implements Graph_theory_interface {


    @Override
    public void start(Stage stage) throws Exception {
      Pane root = new Pane();
      root.setPrefSize(800,600);
      root.setStyle("-fx-background-color: #ff0000");
      root.setStyle("-fx-border-color: #ff0000");
      root.setStyle("-fx-border-width: 1px;");
      
    }

    @Override
    public void onVertexVisited(Node v) {

    }

    @Override
    public void onEdgeRelaxed(Edge e) {

    }
}
