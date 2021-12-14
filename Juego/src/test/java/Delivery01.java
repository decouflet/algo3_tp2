import Lists.Cities;
import MainObjects.*;
import MainObjects.Buildings.Bank;
import MainObjects.Buildings.Library;
import Readers.CityReader;
import org.junit.jupiter.api.*;
import org.junit.Test;
import java.io.*;
import java.util.stream.IntStream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class Delivery01 {

    @Test
    public void Case01() throws IOException {
        Suspect suspect = new Suspect("Merey Laroc","Female","Mountain Climbing","Brown","Jewelry","Limousine");
        Clue clue = new Clue("Pista Banco Facil","Pista Banco Facil","Pista Banco Facil");
        Rookie detective = new Rookie();
        Bank bank = new Bank(clue);
        Cities cities = new Cities();
        CityReader cityReader = new CityReader(cities);
        StolenItem stolenItem = new StolenItem("Tesoro Nacional Montreal","Comun","Montreal");

        cityReader.read();

        Assertions.assertEquals(suspect.isGender("Female"),true);
        Assertions.assertEquals(stolenItem.getName(),"Tesoro Nacional Montreal");
        Assertions.assertEquals(detective.enter(bank),"Pista Banco Facil");
    }

    @Test
    public void Case02() throws IOException {
        Clue clueBank = new Clue("Pista Banco Facil","Pista Banco Facil","Pista Banco Facil");
        Clue clueLibrary = new Clue("Pista Libreria Facil","Pista Libreria Facil","Pista Libreria Facil");
        Rookie detective = new Rookie();
        Bank bank = new Bank(clueBank);
        Library library = new Library(clueLibrary);
        Cities cities = new Cities();
        CityReader cityReader = new CityReader(cities);
        StolenItem stolenItem = new StolenItem("Tesoro Nacional Montreal","Comun","Montreal");

        cityReader.read();
        City montreal = cities.find(stolenItem.origin());
        detective.takeCase(montreal);

        Assertions.assertEquals(detective.getCurrentCity(),montreal);
        Assertions.assertEquals(detective.enter(bank),"Pista Banco Facil");
        Assertions.assertEquals(detective.enter(library),"Pista Libreria Facil");
    }

    @Test
    public void Case03() throws IOException {
        Rookie detective = new Rookie();
        City mexico = new City("Mexico", 45.50, -73.57);
        Assertions.assertNotEquals(detective.getCurrentCity(),mexico);
        detective.travel(mexico);
        Assertions.assertEquals(detective.getCurrentCity(),mexico);
    }

    @Test
    public void Case04() {
        Rookie detective = new Rookie();
        Clue clueBank = new Clue("Pista Banco Facil","Pista Banco Media","Pista Banco Dificil");
        Clue clueLibrary = new Clue("Pista Biblioteca Facil","Pista Biblioteca Media","Pista Biblioteca Dificil");
        Bank spyBank = spy(new Bank(clueBank));
        Library spyLibrary = spy(new Library(clueLibrary));

        IntStream.range(0, 5).forEach(i -> {
            detective.enter(spyBank);
        });
        IntStream.range(0, 55).forEach(i -> {
            detective.enter(spyLibrary);
        });

        verify(spyBank,times(5)).deployClue(any(Rookie.class));
        verify(spyLibrary,times(55)).deployClue(any(Rookie.class));
    }

    @Test
    public void Case05() {
        Rookie detective = new Rookie();
        Knife knife = new Knife();
        Assertions.assertEquals(detective.getTimeLeft(),152);
        detective.beAttacked(knife);
        Assertions.assertEquals(detective.getTimeLeft(),150);
        detective.sleep();
        Assertions.assertEquals(detective.getTimeLeft(),142);
    }

}

