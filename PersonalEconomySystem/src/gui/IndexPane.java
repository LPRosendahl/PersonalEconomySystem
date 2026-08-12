package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;



public class IndexPane extends GridPane {
    private Gui gui;
    private Controller controller;

    public IndexPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        Button buttonLogin = new Button("Login");
        Button buttonCreateProfile = new Button("Create profile");

        buttonLogin.setPrefWidth(150);
        buttonCreateProfile.setPrefWidth(150);

        this.add(buttonLogin,0,0);
        this.add(buttonCreateProfile,0,1);

        buttonLogin.setOnAction(event -> gui.changePane(new LoginPane(gui, controller)));
        buttonCreateProfile.setOnAction(event -> gui.changePane(new CreateProfilePane(gui, controller)));

    }
}
