package org.IDentifyMe.Controller;

import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.IDentifyMe.Models.Student;
import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.function.Function;
import java.net.http.HttpResponse;

public class LoginController {
    private String[] userChoises = { "student", "finance", "ID_Department" };

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
    private ChoiceBox<String> userChoise;

    @FXML
    public void initialize() {
        this.userChoise.getItems().addAll(userChoises);
        this.userChoise.setValue(userChoises[0]);
        this.userChoise.styleProperty().set("-fx-text-fill: white;");
    }

    private Function<HttpResponse<String>, String> validator = (response) -> {
        if (response.statusCode() == 200) {
            JSONObject json = new JSONObject(response.body());
            if (json.getString("status").toLowerCase().equals("successful")) {
                MainApp.router.CreatePopup("Success", "Login successful!",
                        Alert.AlertType.CONFIRMATION, false, "");
                Platform.runLater(() -> {
                    MainApp.router.navigateTo("studentDashboard");
                });
            }
        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                    Alert.AlertType.ERROR, true, response.statusCode() + "");
        }
        return null;
    };

    @FXML
    private void login() {
        HttpClientHandler client = new HttpClientHandler();
        Student student = new Student(username.getText(), password.getText());

        client.sendPostRequest("/student/login", validator, student.toJSON().toString());
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