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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

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
    private BorderPane mainBody;

    @FXML
    public void initialize() {
        this.userChoise.getItems().addAll(userChoises);
        this.userChoise.setValue(userChoises[0]);
        this.userChoise.styleProperty().set("-fx-text-fill: white;");
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/org/IDentifyMe/image/loginBg.png").toExternalForm(), 800, 600, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        mainBody.setBackground(new Background(backgroundImage));
    }

    private Function<HttpResponse<String>, String> validator = (response) -> {
        if (response.statusCode() == 200) {
            JSONObject json = new JSONObject(response.body());
            if (json.getString("status").toLowerCase().equals("successful")) {
                MainApp.router.CreatePopup("Success", "Successful!",
                        Alert.AlertType.INFORMATION, false, json.getString("message"));
                Platform.runLater(() -> {
                    if (MainApp.User.equals(userChoises[0])) {
                        MainApp.router.navigateTo("studentHome");
                    } else if (MainApp.User.equals(userChoises[1])) {
                        MainApp.router.navigateTo("financeHome");
                    } else if (MainApp.User.equals(userChoises[2])) {
                        MainApp.router.navigateTo("ID_DepartmentHome");
                    }
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
        MainApp.User = userChoise.getValue();
        if (MainApp.User.equals(userChoises[0])) {
            Student student = new Student(username.getText(), password.getText());
            client.sendPostRequest("/student/login", validator, student.toJSON().toString());
        } else if (MainApp.User.equals(userChoises[1])) {

        } else if (MainApp.User.equals(userChoises[2])) {

        }
    }

}