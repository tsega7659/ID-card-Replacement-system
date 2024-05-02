package org.IDentifyMe.Controller;

import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.function.Function;
import java.net.http.HttpResponse;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btn_enter;

    @FXML
    private Button btn_register;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    public void initialize() {

    }

    @FXML
    private void login() {
        HttpClientHandler client = new HttpClientHandler();
        Function<HttpResponse<String>, String> validator = (response) -> {
            if (response.statusCode() == 200) {
                Platform.runLater(() -> MainApp.router.CreatePopup("Success", "Login successful!",
                        Alert.AlertType.CONFIRMATION, false, response.body()));
            } else {
                Platform.runLater(
                        () -> MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                                Alert.AlertType.ERROR, true, response.body()));
                System.out.println("f");
            }
            return null;
        };
        System.out.println("Username1");
        client.sendPostRequest("/student/login", validator, "");
    }

    @FXML
    private void register() {
        // Registration logic here...
    }

    @FXML
    private void forgotPassword() {
        // Forgot password logic here...
    }
}