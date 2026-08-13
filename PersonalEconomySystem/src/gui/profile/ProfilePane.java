package gui.profile;

import gui.DashboardPane;
import gui.Gui;
import gui.IndexPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import controller.Controller;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfilePane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldUsername = new TextField();
    private TextField textFieldEmail = new TextField();

    public ProfilePane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        // ----- Style -----
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        // ----- TOP -----
        Label labelTitel = new Label("Profil oplysninger");
        labelTitel.setFont(Font.font("System", FontWeight.BOLD, 22));

        // ----- Middle -----
        Label labelUsername = new Label("Username: ");
        Label labelEmail = new Label("E-mail: ");

        textFieldUsername.setText(controller.getCurrentProfile().getUsername());
        textFieldUsername.setEditable(false);

        textFieldEmail.setText(controller.getCurrentProfile().getEmail());
        textFieldEmail.setEditable(true);

        Button buttonChangeUsername = new Button("Change username");
        Button buttonChangeEmail = new Button("Change email");

        HBox usernameBox = new HBox(15);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.getChildren().addAll(labelUsername, textFieldUsername, buttonChangeUsername);

        HBox emailBox = new HBox(15);
        emailBox.setAlignment(Pos.CENTER);
        emailBox.getChildren().addAll(labelEmail, textFieldEmail, buttonChangeEmail);


        // ----- Bottom -----

        Button buttonBack = new Button("Back");
        Button buttonLogout = new Button("Log out");
        Button buttonDelete = new Button("Delete account");

        buttonBack.setPrefWidth(120);
        buttonLogout.setPrefWidth(120);
        buttonDelete.setPrefWidth(120);

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(buttonBack, buttonLogout, buttonDelete);



        this.add(labelTitel,0,0, 3, 1);
        this.add(usernameBox,0,1,3,1);

        //this.add(labelUsername,0,1,1,1);
        //this.add(textFieldUsername,1,1,1,1);
        this.add(emailBox, 0,2,3,1);
        //this.add(labelEmail,0,2,1,1);
        //this.add(textFieldEmail,1,2,1,1);
        this.add(buttonBox,0,3,3,1);

        buttonLogout.setOnAction(event -> {
            controller.setCurrentProfile(null);
            gui.changePane(new IndexPane(gui, controller));
        });

        buttonBack.setOnAction(event -> gui.changePane(new DashboardPane(gui, controller)));


    }
}
