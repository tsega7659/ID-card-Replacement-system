package org.IDentifyMe;

import java.io.IOException;

import org.IDentifyMe.Classes.Router;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static final String APP_NAME = "IDentifyMe";
    public static Router router;

    @Override
    public void start(Stage stage) {
    
        MainApp.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        router = new Router(stage);
        router.addRoute("home", "login.fxml");
        router.navigateTo("home");

        stage.getIcons().add(new Image(getClass().getResource("/org/IDentifyMe/icons/icon.png").toExternalForm()));
        stage.centerOnScreen();
        stage.setTitle(APP_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
