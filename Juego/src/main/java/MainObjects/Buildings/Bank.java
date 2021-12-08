package MainObjects.Buildings;

public class Bank implements Building {

    private int entries;

    public void enter(MainObjects.Timer timer, String clue) {
        timer.reduce(entries + 1);
        entries++;
        System.out.println(clue);
    }
}