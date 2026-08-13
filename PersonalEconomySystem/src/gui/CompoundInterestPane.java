package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CompoundInterestPane extends GridPane {
    private Gui gui;
    private Controller controller;

    private TextField textFieldStartkapital = new TextField();
    private TextField textFieldMaanedlig = new TextField();
    private TextField textFieldRente = new TextField();
    private TextField textFieldAar = new TextField();
    private TextField textFieldAfkast = new TextField();

    public CompoundInterestPane(Gui gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);
        // Farveovergang fra mørkeblå til lidt lysere mørkeblå
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #2c3e50, #4ca1af);");
        this.setAlignment(Pos.CENTER);

        Label labelStartkapital = new Label("Startkapital");
        Label labelMaanedligIndbetaling = new Label("Månedlig indbetaling");
        Label labelRente = new Label("Rente");
        Label labelTermin = new Label("År");
        Label labelAfkast = new Label("Afkast");

        Button buttonCalculate = new Button("Calculate");
        Button buttonBack = new Button("Back to Dashboard");

        textFieldAfkast.setEditable(false);

        this.add(labelStartkapital,0,0);
        this.add(textFieldStartkapital,1,0);
        this.add(labelMaanedligIndbetaling, 0,1);
        this.add(textFieldMaanedlig,1,1);
        this.add(labelRente,0,2);
        this.add(textFieldRente,1,2);
        this.add(labelTermin,0,3);
        this.add(textFieldAar,1,3);
        this.add(buttonCalculate,0,4);
        this.add(labelAfkast,0,5);
        this.add(textFieldAfkast,1,5);
        this.add(buttonBack,0,6);

        buttonCalculate.setOnAction(event -> calculateProfit());
        buttonBack.setOnAction(event -> gui.changePane(new DashboardPane(gui, controller)));


    }

    private void calculateProfit() {
        try {
            double startKapital = Double.parseDouble(textFieldStartkapital.getText().trim());

            // Tjek om månedlig indbetaling er tom eller ej
            String maanedligText = textFieldMaanedlig.getText().trim();
            double maanedligIndbetaling = maanedligText.isEmpty() ? 0 : Double.parseDouble(maanedligText);

            double rente = Double.parseDouble(textFieldRente.getText().trim());
            double aar = Double.parseDouble(textFieldAar.getText().trim());

            double profit = controller.compoundInterestCalculator(startKapital, maanedligIndbetaling, rente, aar);

            // Viser resultatet med 2 decimaler og punktum/komma efter lokation
            textFieldAfkast.setText(String.format("%.2f kr.", profit));

        } catch (NumberFormatException e) {
            textFieldAfkast.setText("Ugyldigt input");
        }
    }
}
