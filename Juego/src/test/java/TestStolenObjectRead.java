import Lists.StolenItemsList;
import MainObjects.StolenItems;
import Readers.StolenItemsReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStolenObjectRead {

//    @Test
//    public void TestQuantityStolenObjects() throws IOException {
//        ArrayList<StolenItems> stolenItemsList = new ArrayList<StolenItems>();
//        StolenItemsReader reader = new StolenItemsReader(stolenItemsList);
//        reader.read();
//        assertEquals(stolenItemsList.size(),3);
//    }
//
//    @Test
//    public void TestStolenObjectName() throws IOException {
//        ArrayList<StolenItems> stolenItemsList = new ArrayList<StolenItems>();
//        StolenItemsReader reader = new StolenItemsReader(stolenItemsList);
//        reader.read();
//        assertEquals(stolenItemsList.get(0).getName(),"Oro");
//    }

    @Test
    public void TestStolenListFilter() throws IOException {
        StolenItemsList stolenItemsList = new StolenItemsList();
        StolenItemsReader reader = new StolenItemsReader(stolenItemsList);
        reader.read();
        stolenItemsList.assign(3);
        stolenItemsList.show();
    }

}