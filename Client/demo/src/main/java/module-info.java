module com.identifyme {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.identifyme to javafx.fxml;
    exports com.identifyme;
}
