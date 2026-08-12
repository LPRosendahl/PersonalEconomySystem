package storage;

import model.Profile;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        if (!profiles.contains(profile)) {
            profiles.add(profile);
        }
    }

    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles);
    }
}
