package Modelo.MainObjects.Buildings;


import Modelo.MainObjects.*;

public class Library extends Building {

    public Library(){};

    private String name;

    public Library(Clue clue) {
        super(clue);
        this.name = "Library";
    }

    @Override
    public String deployClue(Rookie rookie) {
        return clues.deploy(rookie);
    }

    @Override
    public String deployClue(Detective detective) {
        return clues.deploy(detective);
    }

    @Override
    public String deployClue(Investigator investigator) {
        return (clues.deploy(investigator) + clues.deploy(investigator));
    }

    @Override
    public String deployClue(Sergeant sergeant) {
        return clues.deploy(sergeant);
    }

    @Override
    public String getName(){
        return name;
    }

}