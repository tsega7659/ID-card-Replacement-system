package org.IDentifyMe.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
import org.IDentifyMe.MainApp;

public class AboutPageController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    Pane headerBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("http://localhost:8081/");

        MainApp.router.setBackground("loginBg.png", headerBar);

        System.out.println("About Page");
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