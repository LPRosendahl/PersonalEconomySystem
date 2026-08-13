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

public class CreateProfilePane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldUsername = new TextField();
    private PasswordField passwordFieldPassword = new PasswordField();
    private PasswordField passwordFieldConfirm = new PasswordField();

    public CreateProfilePane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        // Overskrift
        Label labelTitel = new Label("Create profile");
        labelTitel.setFont(Font.font("System", FontWeight.BOLD, 20));

        Label labelCreateUsername = new Label("Username:");
        Label labelCreatePassword = new Label("Password:");
        Label labelConfirm = new Label("Confirm password:");

        //Hjælpetekst i felterne
        textFieldUsername.setPromptText("Choose username");
        passwordFieldPassword.setPromptText("Choose password");
        passwordFieldConfirm.setPromptText("Repeat password");

        Button buttonCreateProfile = new Button("Create");
        Button buttonCancel = new Button("Cancel");

        buttonCreateProfile.setPrefWidth(90);
        buttonCancel.setPrefWidth(90);

        //Samler knapperne i bunden højrejusteret
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(buttonCancel, buttonCreateProfile);

        this.add(labelTitel, 0, 0, 2, 1);
        this.add(labelCreateUsername,0,1);
        this.add(textFieldUsername,1,1);
        this.add(labelCreatePassword,0,2);
        this.add(passwordFieldPassword,1,2);
        this.add(labelConfirm,0,3);
        this.add(passwordFieldConfirm,1,3);
        this.add(buttonBox,1,4);

        buttonCreateProfile.setOnAction(event -> createProfileButton());
        buttonCancel.setOnAction(event -> gui.changePane(new IndexPane(gui, controller)));

    }

    private void createProfileButton() {
        String username = textFieldUsername.getText().trim();
        String password = passwordFieldPassword.getText().trim();
        String repeatedPassword = passwordFieldConfirm.getText().trim();

        // Tjek om felterne er tomme
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Create Profile");
            alert.setHeaderText("Missing input");
            alert.setContentText("Please fill out both username and password");
            alert.showAndWait();
            return;
        }

        // Tjek om brugernavnet er optaget (case-insensitive)

        boolean occupiedUsername = false;

        for (Profile profile : controller.getStorage().getProfiles()) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                occupiedUsername = true;
            }
        }

        if (!password.equals(repeatedPassword)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Create Profile");
            alert.setHeaderText("Passwords do not match");
            alert.setContentText("Please make sure your passwords match.");
            alert.showAndWait();
            return;
        }

        if (occupiedUsername) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Create profile");
            alert.setHeaderText("Username already taken");
            alert.setContentText("Choose a different username");
            alert.showAndWait();
        } else {
            controller.createProfile(username, password);
            gui.changePane(new DashboardPane(gui, controller));
        }

    }
}
