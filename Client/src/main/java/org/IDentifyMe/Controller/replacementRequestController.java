package org.IDentifyMe.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.IDentifyMe.MainApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class replacementRequestController implements Initializable {
    @FXML
    Pane headerBar;

    @FXML
    ImageView aastuImage;

    @FXML
    ImageView policeImage;

    @FXML
    TabPane tabPane;

    @FXML
    CheckBox creditCard;

    @FXML
    CheckBox bankTransfer;

    @FXML
    TextField ReceiptNumberTextFiled;

    @FXML
    DatePicker paymentDate;

    @FXML
    TextField bankName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
        System.out.println("replacement Request Page");
    }

    @FXML
    private void checkcreditCard() {
        if (creditCard.isSelected()) {
            bankTransfer.setSelected(false);
        }
    }

    @FXML
    private void checkbankTransfer() {
        if (bankTransfer.isSelected()) {
            creditCard.setSelected(false);
        }
    }

    @FXML
    private void reloadPage() {
        if (aastuImage.getImage() != null || policeImage.getImage() != null) {
            if (MainApp.router.CreateCONFIRMATION("Confirmation", "Are you sure you want to go reload?",
                    "All the data will be lost")) {
                MainApp.router.reloadPage();
            }
        } else {
            MainApp.router.reloadPage();
        }
    }

    @FXML
    private void homePage() {
        if (aastuImage.getImage() != null || policeImage.getImage() != null) {
            if (MainApp.router.CreateCONFIRMATION("Confirmation", "Are you sure you want to go back?",
                    "All the data will be lost")) {
                MainApp.router.navigateTo("studentHome");
            }
        } else {
            MainApp.router.navigateTo("studentHome");
        }
    }

    @FXML
    private void previousPage() {
        if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
            tabPane.getSelectionModel().selectPrevious();
        } else {
            if (aastuImage.getImage() != null || policeImage.getImage() != null) {
                if (MainApp.router.CreateCONFIRMATION("Confirmation", "Are you sure you want to go back?",
                        "All the data will be lost")) {
                    MainApp.router.navigateBack();
                }
            } else {
                MainApp.router.navigateBack();
            }
        }
    }

    @FXML
    private void chooseAASTUImage() {
        String path = MainApp.router.openFileChooser();
        if (path != null) {
            aastuImage.setImage(MainApp.router.getImage(path));
        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while selecting the image", Alert.AlertType.ERROR,
                    true, "Please select a valid image");
        }
    }

    @FXML
    private void choosePoliceImage() {
        String path = MainApp.router.openFileChooser();
        if (path != null) {
            policeImage.setImage(MainApp.router.getImage(path));
        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while selecting the image", Alert.AlertType.ERROR,
                    true, "Please select a valid image");
        }
    }

    @FXML
    private void nextPage() {
        if (aastuImage.getImage() != null && policeImage.getImage() != null) {
            tabPane.getSelectionModel().selectNext();
        } else {
            MainApp.router.CreatePopup("Error", "Please select a valid option and an image", Alert.AlertType.ERROR,
                    true, "Please select a valid image");
        }
    }

    @FXML
    private void submitRequest() {
        if (creditCard.isSelected() || bankTransfer.isSelected() && !ReceiptNumberTextFiled.getText().isEmpty()
                && paymentDate.getValue() != null && !bankName.getText().isEmpty()) {
            MainApp.router.CreatePopup("Success", "Request Submitted Successfully", Alert.AlertType.INFORMATION, true,
                    "Your request has been submitted successfully");
            MainApp.router.navigateTo("studentHome");
        } else {
            MainApp.router.CreatePopup("Error", "Please fill all the fields", Alert.AlertType.ERROR, true,
                    "Please fill all the fields");
        }
    }
}
