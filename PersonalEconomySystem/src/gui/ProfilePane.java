package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import controller.Controller;

public class ProfilePane extends GridPane {
    private Gui gui;
    private Controller controller;

    public ProfilePane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        Button buttonBack = new Button("Back");
        Button buttonLogout = new Button("Log out");

        buttonBack.setPrefWidth(120);
        buttonLogout.setPrefWidth(120);

        this.add(buttonBack, 0, 0);
        this.add(buttonLogout,0,1);

        buttonLogout.setOnAction(event -> {
            controller.setCurrentProfile(null);
            gui.changePane(new IndexPane(gui, controller));
        });

        buttonBack.setOnAction(event -> gui.changePane(new DashboardPane(gui, controller)));


    }
}
