package org.IDentifyMe.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

import org.IDentifyMe.MainApp;

public class StudentHomeController implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClasses;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Student Home Page");
    }

    @FXML
    public void reloadPage() {
        MainApp.router.reloadPage();
    }

    @FXML
    public void homePage() {
        MainApp.router.navigateTo("studentHome");
    }
    @FXML
    public void previousPage() {
        MainApp.router.navigateBack();
    }
}