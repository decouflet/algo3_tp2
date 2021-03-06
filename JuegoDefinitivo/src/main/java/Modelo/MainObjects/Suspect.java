package Modelo.MainObjects;

import Modelo.Lists.Cities;
import Modelo.MainObjects.Polices.Detective;
import Modelo.MainObjects.Polices.Investigator;
import Modelo.MainObjects.Polices.Rookie;
import Modelo.MainObjects.Polices.Sergeant;

import java.util.ArrayList;
import java.util.Random;

public class Suspect {

    private ArrayList<String> featureClues;
    private String name;
    private ArrayList<Feature> features;
    private boolean isRobber, isArrested;
    private java.util.ArrayList<City> path;
    private final int GENDER = 0, HOBBY = 1, HAIR = 2, ACCESSORY = 3, VEHICLE = 4;

    public Suspect(String name, Feature gender, Feature hobby, Feature hair, Feature accessory, Feature vehicle) {
        this.name = name;
        this.features = new ArrayList<>();
        this.features.add(gender);
        this.features.add(hobby);
        this.features.add(hair);
        this.features.add(accessory);
        this.features.add(vehicle);
        this.isRobber = false;
        this.isArrested = false;
    }

    public Suspect() {
        this.isRobber = false;
        this.isArrested = false;
    }

    public void createFeatureClues() {
        featureClues = new ArrayList<>();
        featureClues.add("I heard that the suspect liked " + features.get(HOBBY).getFeature() + ".");
        featureClues.add("The suspect had " + features.get(HAIR).getFeature() + " hair.");
        featureClues.add("The suspect had a distinctive " + features.get(ACCESSORY).getFeature() + ".");
        featureClues.add("The suspect was driving a " + features.get(VEHICLE).getFeature() + ".");
    }

    @Override
    public boolean equals(Object obj){
        return this.name.equals( ( (Suspect) obj).getName() );
    }

    public String getName() {
        return name;
    }

    public ArrayList<Feature> getFeatures() { return features; }

    public boolean isGender(Feature feature) { return this.features.get(GENDER).equals(feature); }

    public boolean hasHobby(Feature feature) {
        return this.features.get(HOBBY).equals(feature);
    }

    public boolean hasHair(Feature feature) {
        return this.features.get(HAIR).equals(feature);
    }

    public boolean hasAccessory(Feature feature) { return this.features.get(ACCESSORY).equals(feature); }

    public boolean hasVehicle(Feature feature) { return this.features.get(VEHICLE).equals(feature); }

    public void convertToRobber() {
        this.isRobber = true;
    }

    public void arrest() {
        isArrested = true;
    }

    public boolean passedThrough(City city) {
        return path.contains(city);
    }

    public boolean isInCity(City city) {
        if (path.size() == 0) { return false; }
        return city.equals(path.get(path.size() - 1));
    }

    public ArrayList<City> getPath(){
        return path;
    }

    public void createPath(Cities cities, int citiesToTravel) {
        ArrayList<City> path = new ArrayList<>();
        City origin = cities.getStartCity();
        origin.setSuspect(this);
        Random random = new Random();
        for (int i = 0; i < citiesToTravel; i++) {
            cities.remove(origin);
            path.add(origin);
            if (cities.size() == 0) break;
            int randomInt = random.nextInt(cities.size());
            City nextCity = cities.get(randomInt);
            origin.setNextCity(nextCity);
            origin = nextCity;
            origin.setSuspect(this);
        }
        Coordinates coordinates = new Coordinates(0.00, 0.00);
        City suspectCurrenCity = new City("Ganador", coordinates);
        origin.setNextCity(suspectCurrenCity);
        this.path = path;
    }

    public City getNextCity(City city) {
        if (city.isFinalCity()) throw new FinalCityException();
        return path.get(path.indexOf(city) + 1);
    }

    public String getGender() {
        return this.features.get(GENDER).getFeature();
    }

    public String getFeatureClue(Rookie police) {
        Random random = new Random();
        if (random.nextInt(10) <= 6 || featureClues.size() == 0)
            return "";
        int randomInt = random.nextInt(featureClues.size());
        String featureClue = featureClues.get(randomInt);
        featureClues.remove(randomInt);
        return featureClue;
    }

    public String getFeatureClue(Detective police) {
        Random random = new Random();
        if (random.nextInt(10) <= 7 || featureClues.size() == 0)
            return "";
        int randomInt = random.nextInt(featureClues.size());
        String featureClue = featureClues.get(randomInt);
        featureClues.remove(randomInt);
        return featureClue;
    }

    public String getFeatureClue(Investigator police) {
        Random random = new Random();
        if (random.nextInt(10) <= 8 || featureClues.size() == 0)
            return "";
        int randomInt = random.nextInt(featureClues.size());
        String featureClue = featureClues.get(randomInt);
        featureClues.remove(randomInt);
        return featureClue;
    }

    public String getFeatureClue(Sergeant police) {

        Random random = new Random();
        if (random.nextInt(10) <= 8 || featureClues.size() == 0)
            return "";
        int randomInt = random.nextInt(featureClues.size());
        String featureClue = featureClues.get(randomInt);
        featureClues.remove(randomInt);
        return featureClue;

    }

    public boolean isArrested() {
        return this.isArrested;
    }
}
