package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class DashboardPane extends GridPane {
    private Gui gui;
    private Controller controller;

    public DashboardPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Button buttonProfit = new Button("Profit Calculator");
        Button buttonEkstra = new Button("Ekstra knap");
        Button buttonLogOut = new Button("Log out");

        this.add(buttonProfit,0,0);
        this.add(buttonEkstra,0,1);
        this.add(buttonLogOut,0,2);

        buttonProfit.setOnAction(event -> gui.changePane(new ProfitPane(gui, controller)));
        buttonLogOut.setOnAction(event -> gui.changePane(new LoginPane(gui, controller)));


    }
}
