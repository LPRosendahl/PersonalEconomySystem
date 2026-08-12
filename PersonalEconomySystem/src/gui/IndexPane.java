package gui;

import controller.Controller;
import javafx.geometry.Insets;
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
        this.setVgap(10);

        Button buttonLogin = new Button("Login");
        Button buttonCreateProfile = new Button("Create profile");

        this.add(buttonLogin,0,0);
        this.add(buttonCreateProfile,0,1);

        buttonLogin.setOnAction(event -> gui.changePane(new LoginPane(gui, controller)));

    }
}
