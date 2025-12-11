package org.example.app;

import org.example.ui.EmploymentFormView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        new EmploymentFormView().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
