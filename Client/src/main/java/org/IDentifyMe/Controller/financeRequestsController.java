package org.IDentifyMe.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import org.IDentifyMe.MainApp;
import org.IDentifyMe.Models.PaymentDetail;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class financeRequestsController implements Initializable {

    @FXML
    private Button ApproveButton;

    @FXML
    private Button DeniedButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<PaymentDetail, String> PaymentDate;

    @FXML
    private TableColumn<PaymentDetail, Integer> PaymentID;

    @FXML
    private TableColumn<PaymentDetail, Integer> RequestID;

    @FXML
    private Label bankName;

    @FXML
    private Pane headerBar;

    @FXML
    private Label paymentDate;

    @FXML
    private Label paymentMethod;

    @FXML
    private Label receiptNumber;

    @FXML
    private TextField searchBar;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<PaymentDetail> tbData;

    private ObservableList<PaymentDetail> originalData = FXCollections.observableArrayList();

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
        if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
            tabPane.getSelectionModel().selectPrevious();
        } else {
            MainApp.router.navigateBack();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
        PaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        PaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        RequestID.setCellValueFactory(new PropertyValueFactory<>("requestID"));

        tbData.setRowFactory(tv -> {
            TableRow<PaymentDetail> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    PaymentDetail clickedRow = row.getItem();
                    handleRowClick(clickedRow);
                }
            });
            row.getStyleClass().add("button");
            return row;
        });

        for (int i = 1; i < 20; i++) {
            Random rand = new Random();
            PaymentDetail paymentDetail = new PaymentDetail(rand.nextInt(100, 1000), rand.nextInt(100, 1000), 250,
                    rand.nextInt(1000, 10000) + "", "2021-05-" + i, "Pending", "2021-05-" + rand.nextInt(1, 30),
                    "Bank Name", "cash");
            originalData.add(paymentDetail);
        }

        tbData.setItems(originalData);

        System.out.println("Finance Request First Page");
    }

    @FXML
    private void search() {
        String search = searchBar.getText().toLowerCase().trim();
        if (search.isEmpty()) {
            tbData.setItems(originalData);
        } else {
            ObservableList<PaymentDetail> filteredData = originalData.filtered(
                    PaymentDetail -> Integer.toString(PaymentDetail.getPaymentID()).toLowerCase().contains(search));
            tbData.setItems(filteredData);
        }
    }

    private void handleRowClick(PaymentDetail clickedRow) {
        tabPane.getSelectionModel().selectNext();
        bankName.setText(clickedRow.getBankName());
        paymentDate.setText(clickedRow.getPaymentDate());
        paymentMethod.setText(clickedRow.getPaymentMethod());
        receiptNumber.setText(clickedRow.getReceiptNumber());
        ApproveButton.setOnAction(e -> {
            if (MainApp.router.CreateCONFIRMATION("Approve","Are you sure you want to approve this payment?", null)) {
                clickedRow.setStatus("Approved");
                reloadPage();
                
            }
        });
        DeniedButton.setOnAction(e -> {
            if (MainApp.router.CreateCONFIRMATION("Deny","Are you sure you want to deny this payment?", null)) {
                clickedRow.setStatus("Denied");
                reloadPage();
            }
        });
    }

}
