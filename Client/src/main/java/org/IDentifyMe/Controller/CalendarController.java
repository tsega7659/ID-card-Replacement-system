package org.IDentifyMe.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

import org.IDentifyMe.MainApp;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CalendarController implements Initializable {
    @FXML
    Pane headerBar;

    @FXML
    VBox body;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApp.router.setBackground("loginBg.png", headerBar);
        System.out.println("Student Dashboard Page");

        CalendarView calendarView = new CalendarView();
        
        calendarView.setMinHeight(700); 
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        calendarView.setWeekFields(WeekFields.of(Locale.US));

        body.getChildren().add(calendarView); 

    }

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
