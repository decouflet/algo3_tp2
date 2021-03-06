package Modelo.Lists;

import Modelo.MainObjects.*;
import Modelo.MainObjects.Polices.Police;

import java.util.ArrayList;
import java.util.Random;

public class Cities {

    private ArrayList<City> cities;
    private City startCity;

    public Cities(){
        cities = new ArrayList<>();
    }

    public void setSuspect(Suspect suspect){
        cities.forEach(c -> {
            c.setSuspect(suspect);
        });
    }

    public void add(City city) {
        cities.add(city);
    }

    public int size() {
        return cities.size();
    }

    public City get(int i) {
        return cities.get(i);
    }

    public City find(String city) {
        //System.out.println(city);
        return cities.stream().filter(s -> city.equals(s.getName()))
                .findAny()
                .orElse(null);
    }

    public void remove(City city) {
        cities.remove(city);
    }

    public void startCity(StolenItem stolenItem) {
        this.startCity = this.find(stolenItem.origin());
    }

    public City getStartCity() {
        return startCity;
    }

    public void setPossibleCities(Police police) {
        City currentCity = police.getCurrentCity();
        if (currentCity.getConnections() != null) return;
        City previousCity = police.previousCity();
        Suspect suspect = currentCity.getSuspect();

        ArrayList<City> possibleCities = new ArrayList<>();
        if (previousCity != null) { possibleCities.add(previousCity); }
        if (suspect.passedThrough(currentCity)) {
            try {
                possibleCities.add(suspect.getNextCity(currentCity));
            } catch (FinalCityException ignored) {}
        }

        int numberOfCities = 4;
        ArrayList<City> availableCities = new ArrayList<>(cities);
        Random random = new Random();
        for (int i = possibleCities.size(); i < numberOfCities; i++) {
            int randomInt = random.nextInt(availableCities.size());
            if (suspect.passedThrough(availableCities.get(randomInt)) || possibleCities.contains(availableCities.get(randomInt))) {
              i--;
              continue;
            }
            possibleCities.add(availableCities.get(randomInt));
            availableCities.remove(randomInt);
        }

        currentCity.setConnections(possibleCities);
    }
}
