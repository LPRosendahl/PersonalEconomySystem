package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import storage.Storage;

public class ProfitPane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldIncome = new TextField();
    private TextField textFieldExpenses = new TextField();
    private TextField textFieldProfit = new TextField();

    public ProfitPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        Label labelIncome = new Label("Income");
        Label labelExpenses = new Label("Expenses");
        Label labelProfit = new Label("Profit");

        Button buttonCalculate = new Button("Calculate");
        Button buttonBack = new Button("Back to Dashboard");

        textFieldProfit.setEditable(false);

        this.add(labelIncome,0,0);
        this.add(textFieldIncome,1,0);
        this.add(labelExpenses,0,1);
        this.add(textFieldExpenses,1,1);
        this.add(buttonCalculate,0,2);
        this.add(labelProfit,0,3);
        this.add(textFieldProfit,1,3);
        this.add(buttonBack,0,4);

        buttonCalculate.setOnAction(event -> calculateProfit());
        buttonBack.setOnAction(event -> gui.changePane(new DashboardPane(gui, controller)));


    }

    private void calculateProfit() {
        try {
            double income = Double.parseDouble(textFieldIncome.getText());
            double expenses = Double.parseDouble(textFieldExpenses.getText());
            double profit = controller.calculateYearlyProfit(income, expenses);
            textFieldProfit.setText(String.valueOf(profit));
        } catch (NumberFormatException e) {
            textFieldProfit.setText("Invalid input");
        }
    }
}
