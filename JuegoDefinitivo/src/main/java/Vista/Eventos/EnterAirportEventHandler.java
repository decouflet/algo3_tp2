package Vista.Eventos;

import Modelo.MainObjects.Game;
import Vista.DefeatContainer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnterAirportEventHandler implements EventHandler<ActionEvent>{

    private Game game;
    private Label clueLabel, time;
    private Stage stage;

    public EnterAirportEventHandler(Game game, Label label, Label time, Stage stage){
        this.game = game;
        this.clueLabel = label;
        this.time = time;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event){
        this.clueLabel.setText(this.game.deployAirportClue());
        if (game.isOutOfTime()) {
            game.stopMusic();
            String label = "Time's up " + game.getRank() + "! The thief has gotten away. Better luck next time. Are you ready for your next case?";
            DefeatContainer defeatContainer = new DefeatContainer(game, stage, label);
            Scene defeatScene = new Scene(defeatContainer,1180, 660);
            stage.setScene(defeatScene);
        } else {
            this.time.setText(game.time());
        }
    }
}
