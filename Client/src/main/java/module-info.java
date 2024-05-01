module org.IDentifyMe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    
    opens org.IDentifyMe.Controller to javafx.fxml;
    exports org.IDentifyMe.Controller;
    opens org.IDentifyMe to javafx.fxml;
    exports org.IDentifyMe to javafx.graphics;
}