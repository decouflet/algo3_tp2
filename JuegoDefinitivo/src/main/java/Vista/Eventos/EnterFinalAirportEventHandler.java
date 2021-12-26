package Vista.Eventos;

import Modelo.MainObjects.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class EnterFinalAirportEventHandler implements EventHandler<ActionEvent> {
    private final Game game;
    private Label clueLabel, time;

    public EnterFinalAirportEventHandler(Game game, Label clueLabel, Label textTime) {
        this.game = game;
        this.clueLabel = clueLabel;
        this.time = textTime;
    }

    @Override
    public void handle(ActionEvent event) {

    }
}