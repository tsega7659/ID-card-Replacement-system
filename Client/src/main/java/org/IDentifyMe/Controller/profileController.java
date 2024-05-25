package org.IDentifyMe.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.IDentifyMe.Classes.validatorFuctory;
import org.IDentifyMe.Models.FinanceDepartment;
import org.IDentifyMe.Models.IDReplacementDepartment;
import org.IDentifyMe.Models.Student;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;



public class profileController implements Initializable {
    @FXML
    Pane headerBar;

    @FXML
    TextField idLable;

    @FXML
    TextField nameLable;

    @FXML
    TextField emailLable;

    @FXML
    TextField passwordLable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
        HttpClientHandler client = new HttpClientHandler();
        JSONObject data = new JSONObject(client.sendGetRequest("/" + MainApp.User + "/getInfo"));
        if (MainApp.User.equals("student")) {
            Student student = new Student(data);
            idLable.setText(student.getStudentID());
            nameLable.setText(student.getName());
            emailLable.setText(student.getEmail());
            passwordLable.setText(student.getPassword());
        } else if (MainApp.User.equals("finance")) {
            FinanceDepartment finance = new FinanceDepartment(data);
            idLable.setText(finance.getEmployeeID()+"");
            nameLable.setText(finance.getName());
            emailLable.setText(finance.getEmail());
            passwordLable.setText(finance.getPassword());
        } else if (MainApp.User.equals("ID_Department")) {
            IDReplacementDepartment id = new IDReplacementDepartment(data);
            idLable.setText(id.getEmployeeID()+"");
            nameLable.setText(id.getName());
            emailLable.setText(id.getEmail());
            passwordLable.setText(id.getPassword());
        }
        System.out.println("Profile Page");
    }

    @FXML
    private void reloadPage() {
        MainApp.router.reloadPage();
    }

    @FXML
    private void homePage() {
        MainApp.router.navigateBack();
    }

    @FXML
    private void previousPage() {
        MainApp.router.navigateBack();
    }

    @FXML
    private void updateAccount() {
        if (MainApp.User.equals("student")) {
            Student student = new Student(idLable.getText(), nameLable.getText(), emailLable.getText(), passwordLable.getText());
            HttpClientHandler client = new HttpClientHandler();
            client.sendPostRequest("/"+MainApp.User+"/update", validatorFuctory.createValidator(null) ,student.toJSON().toString());
            MainApp.router.reloadPage();

        } else if (MainApp.User.equals("finance")) {
            FinanceDepartment finance = new FinanceDepartment(Integer.parseInt(idLable.getText()), nameLable.getText(), emailLable.getText(), passwordLable.getText());
            HttpClientHandler client = new HttpClientHandler();
            client.sendPostRequest("/"+MainApp.User+"/update", validatorFuctory.createValidator(null) ,finance.toJSON().toString());
            MainApp.router.reloadPage();

        } else if (MainApp.User.equals("ID_Department")) {
            IDReplacementDepartment id = new IDReplacementDepartment(Integer.parseInt(idLable.getText()), nameLable.getText(), emailLable.getText(), passwordLable.getText());
            HttpClientHandler client = new HttpClientHandler();
            client.sendPostRequest("/"+MainApp.User+"/update", validatorFuctory.createValidator(null) ,id.toJSON().toString());
            MainApp.router.reloadPage();

        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, "Invalid User");
        }
    }
    
}
