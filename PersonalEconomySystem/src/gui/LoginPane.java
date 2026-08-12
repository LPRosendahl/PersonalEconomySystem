package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Profile;

public class LoginPane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldUsername = new TextField();
    private TextField textFieldPassword = new TextField();
    private Button buttonLogintoProfile = new Button();

    public LoginPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");

        buttonLogintoProfile.setText("Login");

        this.add(labelUsername,0,0);
        this.add(textFieldUsername,1,0);
        this.add(labelPassword,0,1);
        this.add(textFieldPassword,1,1);
        this.add(buttonLogintoProfile,0,2);

        buttonLogintoProfile.setOnAction(event -> logIntoProfile());

    }

    private void logIntoProfile() {
        String userName = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        for (Profile profile : controller.getStorage().getProfiles()) {
            if (profile.getUsername().equals(userName) && profile.getPassword().equals(password)) {
                buttonLogintoProfile.setOnAction(event -> gui.changePane(new DashboardPane(gui, controller)));
            } else if (!profile.getUsername().equals(userName) || !profile.getPassword().equals(password)) {
                Alert alertWrongPassword = new Alert(Alert.AlertType.ERROR);
                alertWrongPassword.setTitle("Login");
                alertWrongPassword.setHeaderText("Wrong username or password!");
                alertWrongPassword.setContentText("Try again with the correct username and password");
                alertWrongPassword.show();
            }
        }

    }
}
