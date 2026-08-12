package gui;

import controller.Controller;
import javafx.application.Application;
import model.Profile;
import storage.Storage;

public class Main {
    public static void main(String[] args) {
        Application.launch(Gui.class);
        Storage storage = new Storage();
        Controller controller = new Controller(storage);

        System.out.println(storage.getProfiles());

    }
}
