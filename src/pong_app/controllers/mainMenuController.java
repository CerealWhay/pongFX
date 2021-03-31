package pong_app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pong_app.router.Router;
import java.net.URL;
import java.util.ResourceBundle;

public class mainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button playButton;

    @FXML
    void initialize() {
        playButton.setOnAction(actionEvent -> {
            if (playButton.sceneProperty() != null) {
                playButton.getScene().getWindow().hide();
                Router router = new Router();
                router.toGame(playButton);
            }
        });
    }
}