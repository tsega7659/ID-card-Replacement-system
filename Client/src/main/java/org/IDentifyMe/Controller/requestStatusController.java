package org.IDentifyMe.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.IDentifyMe.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class requestStatusController implements Initializable {
    @FXML
    Pane headerBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
        System.out.println("Request First Page");
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

}
