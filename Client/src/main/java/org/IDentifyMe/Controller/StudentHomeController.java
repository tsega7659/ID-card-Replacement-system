package org.IDentifyMe.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import org.IDentifyMe.MainApp;
import org.IDentifyMe.Classes.HttpClientHandler;
import org.IDentifyMe.Classes.validatorFuctory;

public class StudentHomeController implements Initializable {
    @FXML
    Pane headerBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
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

    @FXML
    private void dashboardPage() {
        MainApp.router.navigateTo(MainApp.User+"Dashboard");
    }

    @FXML
    private void profilePage() {
        MainApp.router.navigateTo(MainApp.User+"Profile");
    }

    @FXML
    private void newRequestPage() {
        MainApp.router.navigateTo("studentNewRequest");
    }

    @FXML
    private void viewRequestPage() {
        MainApp.router.navigateTo("studentRequestStatus");
    }


}