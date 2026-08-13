package controller;

import model.Profile;
import storage.Storage;

public class Controller {
    private Storage storage;
    private Profile currentProfile; // Holder styr på den aktuelt indloggede bruger

    public Controller(Storage storage) {
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public Profile login(String username, String password) {
        for (Profile profile : storage.getProfiles()) {
            if (profile.getUsername().equals(username) && profile.getPassword().equals(password)) {
                this.currentProfile = profile;
                return profile;
            }
        }
        return null;
    }

    public void createProfile(String username, String password) {
        Profile profile = new Profile(username, password);
        storage.addProfile(profile);
        this.currentProfile = profile;
    }

    public void changeUsername(Profile profile, String username) {
        boolean match = false;

        for (Profile storageProfile : storage.getProfiles()) {
            if (storageProfile.getUsername().equalsIgnoreCase(username)) {
                match = true;
            }
        }

        if (!match) {
            profile.setUsername(username);
        }
    }

    public void changeEmail(Profile profile, String email) {
       //TODO
    }

    public double compoundInterestCalculator(double startBeloeb, double maanedligIndbetaling, double aarligRenteProcent, double aar) {
        // 1. Omregn årlig rente til reel månedlig rentefaktor (som Nordnet/Danske Bank gør)
        double maanedligRente = Math.pow(1 + (aarligRenteProcent / 100.0), 1.0 / 12.0) - 1;
        double antalMaaneder = aar * 12.0;

        // 2. Vækst på startkapital
        double startbeloebVaekst = startBeloeb * Math.pow(1 + maanedligRente, antalMaaneder);

        // 3. Vækst på månedlige indbetalinger (hvis der er indtastet nogen)
        double indbetalingerVaekst = 0;
        if (maanedligIndbetaling > 0) {
            if (maanedligRente > 0) {
                // Annuitetsformel med primo/ultimo tilpasning svarende til investeringsberegnere
                indbetalingerVaekst = maanedligIndbetaling * ((Math.pow(1 + maanedligRente, antalMaaneder) - 1) / maanedligRente);
            } else {
                indbetalingerVaekst = maanedligIndbetaling * antalMaaneder;
            }
        }

        return startbeloebVaekst + indbetalingerVaekst;
    }
}

