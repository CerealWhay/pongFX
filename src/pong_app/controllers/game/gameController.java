package pong_app.controllers.game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pong_app.router.Router;

public class gameController {

    public boolean isKeyUpOne = false;
    public boolean isKeyDownOne = false;
    public boolean isKeyUpTwo = false;
    public boolean isKeyDownTwo = false;
    public int ballDirX = 1;
    public int ballDirY = 1;
    public int oneScore = 0;
    public int twoScore = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rectangle playerOneStick;

    @FXML
    private Rectangle playerTwoStick;

    @FXML
    private Circle ball;

    @FXML
    private Label playerOneScore;

    @FXML
    private Label playerTwoScore;

    @FXML
    private Button menuButton;

    @FXML
    private Button resumeButton;

    @FXML
    private AnchorPane pauseAnchor;

    @FXML
    void initialize() {
        menuButton.setOnAction(actionEvent -> {
            Router router = new Router();
            router.toMenu(menuButton);
        });

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(5), e -> run(
                ball,
                playerOneStick,
                playerTwoStick
        )));
        tl.setCycleCount(Timeline.INDEFINITE);

        tl.play();
        playerMoveAT.start();
//        pauseAnchor.setOnKeyPressed(keyEvent -> {
//            KeyCode keyCode = keyEvent.getCode();
//            System.out.println(pauseAnchor.getScene());
//            if (keyCode.equals(KeyCode.UP)) {
//                pauseAnchor.setVisible(true);
//            }
//        });
    }

    AnimationTimer playerMoveAT = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (playerOneStick.getTranslateY() == 0) {
                isKeyUpOne = false;
            }
            if (playerTwoStick.getTranslateY() == 0) {
                isKeyUpTwo = false;
            }
            if (playerOneStick.getTranslateY() == 500) {
                isKeyDownOne = false;
            }
            if (playerTwoStick.getTranslateY() == 500) {
                isKeyDownTwo = false;
            }
            if (isKeyUpOne) {
                playerOneStick.setTranslateY(playerOneStick.getTranslateY() - 5);
            }
            if (isKeyDownOne) {
                playerOneStick.setTranslateY(playerOneStick.getTranslateY() + 5);
            }
            if (isKeyUpTwo) {
                playerTwoStick.setTranslateY(playerTwoStick.getTranslateY() - 5);
            }
            if (isKeyDownTwo) {
                playerTwoStick.setTranslateY(playerTwoStick.getTranslateY() + 5);
            }
        }
    };

    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.S)) {
                isKeyDownOne = true;
            }
            if (keyCode.equals(KeyCode.W)) {
                isKeyUpOne = true;
            }
            if (keyCode.equals(KeyCode.DOWN)) {
                isKeyDownTwo = true;
            }
            if (keyCode.equals(KeyCode.UP)) {
                isKeyUpTwo = true;
            }
        });
        scene.setOnKeyReleased(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.S)) {
                isKeyDownOne = false;
            }
            if (keyCode.equals(KeyCode.W)) {
                isKeyUpOne = false;
            }
            if (keyCode.equals(KeyCode.DOWN)) {
                isKeyDownTwo = false;
            }
            if (keyCode.equals(KeyCode.UP)) {
                isKeyUpTwo = false;
            }
        });
    }

    void run(Circle ball, Rectangle playerOne, Rectangle playerTwo) {
        addKeyHandler(playerOne.getScene());

        ball.setTranslateX(ball.getTranslateX() + ballDirX);
        ball.setTranslateY(ball.getTranslateY() + ballDirY);

        if (
                (ball.getTranslateX() == 540) & ((ball.getTranslateY() >= playerTwo.getTranslateY()) & (ball.getTranslateY() <= playerTwo.getTranslateY() + 300)) |
                        (ball.getTranslateX() == -540) & ((ball.getTranslateY() >= playerOne.getTranslateY()) & (ball.getTranslateY() <= playerOne.getTranslateY() + 300))
        ) {
            ballDirX = ballDirX * (-1);
        }
        if ((ball.getTranslateY() == 0) |
                (ball.getTranslateY() == 780)
        ) {
            ballDirY = ballDirY * (-1);
        }

        if (ball.getTranslateX() > 650) {
            oneScore += 1;
            playerOneScore.setText(String.valueOf(oneScore));
            ball.setTranslateX(0);
            ball.setTranslateY(400);
        } else if (ball.getTranslateX() < -650) {
            twoScore += 1;
            playerTwoScore.setText(String.valueOf(twoScore));
            ball.setTranslateX(0);
            ball.setTranslateY(400);
        }
    }
}
