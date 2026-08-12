package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Profile;

public class LoginPane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldUsername = new TextField();
    private PasswordField passwordFieldPassword = new PasswordField();

    public LoginPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        // Overskrift
        Label labelTitel = new Label("Login");
        labelTitel.setFont(Font.font("System", FontWeight.BOLD, 20));

        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");

        // Hjælpetekst i fleterne
        textFieldUsername.setPromptText("Enter Username");
        passwordFieldPassword.setPromptText("Enter password");

        Button buttonLogintoProfile = new Button("Login");
        Button buttonCancel = new Button("Cancel");

        // Ensretter størrelsen på knapperne
        buttonLogintoProfile.setPrefWidth(90);
        buttonCancel.setPrefWidth(90);

        // Container til knapperne, så de står pænt ved siden af hinanden.
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(buttonCancel, buttonLogintoProfile);

        this.add(labelTitel, 0, 0, 2, 1);
        this.add(labelUsername,0,1);
        this.add(textFieldUsername,1,1);
        this.add(labelPassword,0,2);
        this.add(passwordFieldPassword,1,2);
        this.add(buttonBox,1,3);

        buttonLogintoProfile.setOnAction(event -> logIntoProfile());
        buttonCancel.setOnAction(event -> gui.changePane(new IndexPane(gui, controller)));

    }

    private void logIntoProfile() {
        String userName = textFieldUsername.getText().trim();
        String password = passwordFieldPassword.getText().trim();
        Profile foundProfile = null;
        boolean found = false;
        int i = 0;

        while (!found && i < controller.getStorage().getProfiles().size()) {
            Profile profile = controller.getStorage().getProfiles().get(i);
            if (profile.getUsername().equals(userName) && profile.getPassword().equals(password)) {
                foundProfile = profile;
                found = true;
            }
            i++;
        }

        if (found) {
            controller.setCurrentProfile(foundProfile);
            gui.changePane(new DashboardPane(gui, controller));
        } else {
            Alert alertWrongPassword = new Alert(Alert.AlertType.ERROR);
            alertWrongPassword.setTitle("Login");
            alertWrongPassword.setHeaderText("Wrong username or password!");
            alertWrongPassword.setContentText("Try again with the correct username and password");
            alertWrongPassword.showAndWait();
        }

    }
}
