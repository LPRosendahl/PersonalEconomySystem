package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import storage.Storage;


public class Gui extends Application {
    private Scene scene;
    private Storage storage = new Storage();
    private Controller controller = new Controller(storage);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Economy System");

        IndexPane index = new IndexPane(this, controller);
        //DashboardPane dashboard = new DashboardPane(this, controller);
        //scene = new Scene(dashboard, 400, 300);
        scene = new Scene(index, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changePane(Pane newPane) {
        scene.setRoot(newPane);
    }
}
