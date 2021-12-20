package MainObjects;

import Lists.Suspects;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class PoliceStation {

    Suspects suspects;
    Suspect robber;
    ArrayList<Suspect> possibleSuspects;
    ArrayList<Feature> features;
    Player player;
    Planisphere planisphere;

    public PoliceStation(Suspects suspects, Planisphere planisphere){
        this.planisphere = planisphere;
        this.suspects = suspects;
        this.startFeatures();
        this.robber = suspects.getRobber();
        this.possibleSuspects = new ArrayList<>();
    }

    public void getSuspects(ArrayList<Feature> features) {
        ArrayList<Suspect> possibleSuspects = suspects.filter(features);
        possibleSuspects.forEach(suspect -> {
            System.out.println(suspect.getName());
        });
        this.possibleSuspects = possibleSuspects;
    }

    public int getPossibleSuspectsSize() {
        return possibleSuspects.size();
    }

    public void obtainFeatures(Feature feature1, Feature feature2, Feature feature3, Feature feature4, Feature feature5) {
        updateFeatures(feature1, feature2, feature3, feature4, feature5);
    }

    private void updateFeatures(Feature feature1, Feature feature2, Feature feature3, Feature feature4, Feature feature5) {
        this.features.set(0, feature1);
        this.features.set(1, feature2);
        this.features.set(2, feature3);
        this.features.set(3, feature4);
        this.features.set(4, feature5);
    }

    private void startFeatures() {
        features = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            features.add(new Feature(""));
        });
    }

    public ArrayList<Suspect> findSuspects() {;
        this.possibleSuspects = suspects.filter(features);
        return this.possibleSuspects;
    }

    public Police assignCase(Player player){
        this.player = player;
        int casesWon = this.player.totalCasesWon();

        if(casesWon >= 0 && casesWon < 5)
            return new Rookie(this,this.planisphere);
        else if(casesWon >= 5 && casesWon < 10)
            return new Detective(this,this.planisphere);
        else if(casesWon >= 10 && casesWon < 20)
            return new Investigator(this,this.planisphere);

        return new Sergeant(this,this.planisphere);
    }

    public City caseFrom() {
        return planisphere.getOrigin();
    }

    public Suspect getRobber() {
        return suspects.getRobber();
    }

    public void completeCase() {
        this.player.addFinishedCase(1);
    }
}