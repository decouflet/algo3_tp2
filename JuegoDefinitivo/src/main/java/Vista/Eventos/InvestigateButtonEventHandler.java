package Vista.Eventos;

import Modelo.MainObjects.Game;
import Vista.InvestigateContainer;
import Vista.TravelContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvestigateButtonEventHandler implements EventHandler<ActionEvent> {

    private Game game;
    private Stage stage;

    public InvestigateButtonEventHandler(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        InvestigateContainer investigateContainer = new InvestigateContainer(stage, game);
        investigateContainer.getStylesheets().add(getClass().getResource("/stylesheets/gameScene.css").toExternalForm());
        Scene investigateScene = new Scene(investigateContainer, 1180, 660);
        stage.setScene(investigateScene);
    }
}
