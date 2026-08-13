package gui.profile;

import controller.Controller;
import gui.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Profile;

public class ChangeUsernamePane extends GridPane {
    private Gui gui;
    private Controller controller;

    TextField textFieldOld = new TextField();
    TextField textFieldNew = new TextField();

    public ChangeUsernamePane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        // ----- Style -----
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        // ----- Content -----
        Label labelOldUsername = new Label("Old Username: ");
        Label labelNewUsername = new Label("New Username: ");
        Button buttonChange = new Button("Change username");
        Button buttonCancel = new Button("Cancel");

        HBox changeUsernameBox = new HBox(15);
        changeUsernameBox.setAlignment(Pos.CENTER);
        changeUsernameBox.getChildren().addAll(labelOldUsername, textFieldOld, labelNewUsername, textFieldNew, buttonChange, buttonCancel);

        this.add(changeUsernameBox, 0,0,6,1);

        buttonChange.setOnAction(event -> changeUsernameMethod());

    }

    private void changeUsernameMethod() {
        String oldUsername = textFieldOld.getText().trim();
        String newUsername = textFieldNew.getText().trim();

        Profile profile = controller.getCurrentProfile();

        controller.changeUsername(profile, newUsername);


    }
}
