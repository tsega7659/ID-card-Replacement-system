package org.IDentifyMe.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import java.util.function.Function;

import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.json.JSONObject;

public class StudentHomeController implements Initializable {
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
        MainApp.router.navigateTo("studentHome");
    }

    @FXML
    private void previousPage() {
        MainApp.router.navigateBack();
    }

    private Function<HttpResponse<String>, String> validator = (response) -> {
        if (response.statusCode() == 200) {
            JSONObject json = new JSONObject(response.body());
            if (json.getString("status").toLowerCase().equals("successful")) {
                MainApp.router.logout();
                return null;
            } else {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, json.getString("message"));
            }
        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                    Alert.AlertType.ERROR, true, response.statusCode() + "");
        }
        return null;
    };

    @FXML
    private void logout() {
        if (MainApp.router.CreateCONFIRMATION("Logout", "Are you sure you want to logout?", null)) {
            HttpClientHandler client = new HttpClientHandler();
            client.sendGetRequest("/" + MainApp.User + "/logout", validator);
        }
    }

    @FXML
    private void AboutPage() {
        MainApp.router.navigateTo("about");
    }
}