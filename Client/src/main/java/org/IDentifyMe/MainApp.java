package org.IDentifyMe;

import java.io.IOException;

import org.IDentifyMe.Classes.Router;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
    public static final String APP_NAME = "IDentifyMe";
    public static Router router;

    @Override
    public void start(Stage stage) throws IOException {
        router = new Router(stage);
        router.addRoute("home", "scene.fxml");
        router.navigateTo("home");

        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle(APP_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
