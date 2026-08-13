package gui;

import controller.Controller;
import gui.profile.ProfilePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Profile;

public class DashboardPane extends GridPane {
    private Gui gui;
    private Controller controller;

    public DashboardPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(25));
        this.setHgap(15);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        Profile currentProfile = controller.getCurrentProfile();

        Label labelUser = new Label("Logged in as: " + currentProfile.getUsername());
        labelUser.setFont(Font.font("System", FontWeight.BOLD, 12));

        Button buttonProfile = new Button("My Profile");
        buttonProfile.setOnAction(event -> gui.changePane(new ProfilePane(gui, controller)));

        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.getChildren().addAll(labelUser, buttonProfile);

        // Overskrift
        Label labelWelcome = new Label("Dashboard");
        labelWelcome.setFont(Font.font("System", FontWeight.BOLD, 22));

        // Øverste række knapper
        Button buttonMainAction = new Button("Overview");
        Button buttonSettings = new Button("Settings");
        Button buttonHelp = new Button("Help");

        buttonMainAction.setPrefWidth(120);
        buttonSettings.setPrefWidth(120);
        buttonHelp.setPrefWidth(120);


        HBox mainButtonsBox = new HBox(15);
        mainButtonsBox.setAlignment(Pos.CENTER);
        mainButtonsBox.getChildren().addAll(buttonMainAction, buttonSettings, buttonHelp);

        Button buttonProfit = new Button("Compound Interest");
        Button buttonEkstra2 = new Button("Action 2");
        Button buttonEkstra3 = new Button("Action 3");

        buttonProfit.setPrefWidth(120);
        buttonEkstra2.setPrefWidth(120);
        buttonEkstra3.setPrefWidth(120);

        buttonProfit.setOnAction(event -> gui.changePane(new CompoundInterestPane(gui, controller)));

        HBox extraButtonsBox = new HBox(15);
        extraButtonsBox.setAlignment(Pos.CENTER);
        extraButtonsBox.getChildren().addAll(buttonProfit, buttonEkstra2, buttonEkstra3);

        this.add(topBar, 0, 0, 2, 1);
        this.add(labelWelcome, 0, 1, 2, 1);
        this.add(mainButtonsBox, 0, 2, 2, 1);
        this.add(extraButtonsBox,0,3,2,1);
    }
}