package org.IDentifyMe.Controller;

import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.IDentifyMe.Classes.validatorFuctory;
import org.IDentifyMe.Models.FinanceDepartment;
import org.IDentifyMe.Models.IDReplacementDepartment;
import org.IDentifyMe.Models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
        MainApp.User = null;
        this.userChoise.getItems().addAll(userChoises);
        this.userChoise.styleProperty().set("-fx-text-fill: white;");
        MainApp.router.setBackground("loginBg.png", mainBody);
        HttpClientHandler.clearCookie();
    }

    @FXML
    private void login() {
        HttpClientHandler client = new HttpClientHandler();
        MainApp.User = userChoise.getValue();
        try {
            if (MainApp.User.equals(userChoises[0])) {
                Student student = new Student(username.getText(), password.getText());
                client.sendPostRequest("/"+MainApp.User+"/login", validatorFuctory.createValidator(MainApp.User+"Home"), student.toJSON().toString());
            } else if (MainApp.User.equals(userChoises[1])) {
                FinanceDepartment finance = new FinanceDepartment(Integer.parseInt(username.getText()), password.getText());
                client.sendPostRequest("/"+MainApp.User+"/login", validatorFuctory.createValidator(MainApp.User+"Home"), finance.toJSON().toString());
            } else if (MainApp.User.equals(userChoises[2])) {
                IDReplacementDepartment id = new IDReplacementDepartment(Integer.parseInt(username.getText()), password.getText());
                client.sendPostRequest("/"+MainApp.User+"/login", validatorFuctory.createValidator(MainApp.User+"Home"), id.toJSON().toString());
            }
        } catch (NumberFormatException e) {
            MainApp.router.CreatePopup("Error", "Invalid input", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

}