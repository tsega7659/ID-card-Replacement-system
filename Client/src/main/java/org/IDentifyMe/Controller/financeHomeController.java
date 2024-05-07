package org.IDentifyMe.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.IDentifyMe.Classes.validatorFuctory;

public class financeHomeController implements Initializable {
    @FXML
    Pane headerBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/org/IDentifyMe/image/loginBg.png").toExternalForm(), 800, 600, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        headerBar.setBackground(new Background(backgroundImage));
        System.out.println("Student Home Page");
    }

    @FXML
    private void reloadPage() {
        MainApp.router.reloadPage();
    }

    @FXML
    private void homePage() {
        MainApp.router.navigateTo(MainApp.User +"Home");
    }

    @FXML
    private void previousPage() {
        MainApp.router.navigateBack();
    }

    @FXML
    private void logout() {
        if (MainApp.router.CreateCONFIRMATION("Logout", "Are you sure you want to logout?", null)) {
            HttpClientHandler client = new HttpClientHandler();
            client.sendGetRequest("/" + MainApp.User + "/logout", validatorFuctory.createValidator("login"));
        }
    }

    @FXML
    private void AboutPage() {
        MainApp.router.navigateTo("about");
    }
}