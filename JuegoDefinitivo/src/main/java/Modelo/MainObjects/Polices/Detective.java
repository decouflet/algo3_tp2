package Modelo.MainObjects.Polices;


import Modelo.MainObjects.Buildings.Airport;
import Modelo.MainObjects.Buildings.Bank;
import Modelo.MainObjects.Buildings.Library;
import Modelo.MainObjects.PoliceStation;

public class Detective extends Police {

    public Detective() {
        super();
        this.velocityKmH = 1100;
        this.stolenItemDifficulty = 2;
        this.citiesToTravel = 5;
    }

    public Detective(PoliceStation policeStation) {
        super(policeStation);
        this.velocityKmH = 1100;
        this.stolenItemDifficulty = 2;
        this.citiesToTravel = 5;
    }


    @Override
    public String enter(Bank bank) {
        Bank nextBank = this.getCurrentCity().getNextCity().getBank();
        this.reduceTimeEnteringBuilding(currentCity);

        if (this.getCurrentCity().isFinalCity()) {
            return "última ciudad";
        } else if (this.isInCorrectCity()) {
            String bankClue = nextBank.deployClue(this);
            String suspectClue = this.policeStation.getThief().getFeatureClue(this);
            return (bankClue + ".\n" + suspectClue);
        } else {
            return "No paso por Aca";
        }
    }

    @Override
    public String enter(Library library) {
        Library nextLibrary = this.getCurrentCity().getNextCity().getLibrary();
        this.reduceTimeEnteringBuilding(currentCity);

        if (this.getCurrentCity().isFinalCity()) {
            return "última ciudad";
        } else if (this.isInCorrectCity()) {
            String libraryClue = nextLibrary.deployClue(this);
            String suspectClue = this.policeStation.getThief().getFeatureClue(this);
            String returnValue = libraryClue + ".\n" + suspectClue;
            return returnValue;
        } else {
            return "No paso por Aca";
        }
    }

    @Override
    public String enter(Airport airport) {
        Airport nextAirport = this.getCurrentCity().getNextCity().getAirport();
        this.reduceTimeEnteringBuilding(currentCity);

        if (this.getCurrentCity().isFinalCity()) {
            return "última ciudad";
        } else if (this.isInCorrectCity()) {
            String airportClue = nextAirport.deployClue(this);
            String suspectClue = this.policeStation.getThief().getFeatureClue(this);
            String returnValue = airportClue + ".\n" + suspectClue;
            return returnValue;
        } else {
            return "No paso por Aca";
        }
    }

}