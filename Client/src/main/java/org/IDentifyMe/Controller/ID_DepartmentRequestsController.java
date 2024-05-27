package org.IDentifyMe.Controller;

import org.IDentifyMe.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class ID_DepartmentRequestsController {

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private Pane headerBar;

    @FXML
    private TableColumn<?, ?> lastName;

    @FXML
    private TableColumn<?, ?> lastName1;

    @FXML
    private TableColumn<?, ?> studentId;

    @FXML
    private TableView<?> tbData;

    @FXML
    private void reloadPage() {
        MainApp.router.reloadPage();
    }

    @FXML
    private void homePage() {
        MainApp.router.navigateTo(MainApp.User + "Home");
    }

    @FXML
    private void previousPage() {
        MainApp.router.navigateBack();
    }

}
