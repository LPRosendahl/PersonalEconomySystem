package controller;

import model.Profile;
import storage.Storage;

public class Controller {
    Storage storage;

    public Controller(Storage storage) {
        this.storage = storage;
    }

    public Profile createProfile(String username, String password) {
        Profile profile = new Profile(username, password);
        storage.addProfile(profile);
        return profile;
    }

    public double calculateYearlyProfit(double income, double expense) {
        return income - expense;
    }

    public Storage getStorage() {
        return storage;
    }
}

