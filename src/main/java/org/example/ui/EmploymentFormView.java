package org.example.ui;

import org.example.db.ApplicantDAO;
import org.example.db.EmploymentApplicationDAO;
import org.example.model.Applicant;
import org.example.model.EmploymentApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmploymentFormView {

    private TextField fullNameField = new TextField();
    private TextField addressField = new TextField();
    private TextField contactField = new TextField();
    private TextField emailField = new TextField();
    private ComboBox<String> educationCombo = new ComboBox<>();

    private ToggleGroup genderGroup = new ToggleGroup();
    private RadioButton maleRB = new RadioButton("Male");
    private RadioButton femaleRB = new RadioButton("Female");
    private RadioButton otherRB = new RadioButton("Other");

    private DatePicker dateAvailablePicker = new DatePicker();
    private TextField desiredPositionField = new TextField();
    private TextField desiredSalaryField = new TextField();

    private ToggleGroup authorizedGroup = new ToggleGroup();
    private RadioButton authYes = new RadioButton("Yes");
    private RadioButton authNo = new RadioButton("No");

    private ToggleGroup relativesGroup = new ToggleGroup();
    private RadioButton relYes = new RadioButton("Yes");
    private RadioButton relNo = new RadioButton("No");

    private TextField relativesExplainField = new TextField();

    private Label statusLabel = new Label();

    public void show(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        ImageView logoView = new ImageView();
        try {
            Image img = new Image(getClass().getResourceAsStream("/images/company_logo.png"));
            logoView.setImage(img);
            logoView.setFitHeight(90);
            logoView.setPreserveRatio(true);
        } catch (Exception ignored) {
        }

        Label title = new Label("Employment Application");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        header.getChildren().addAll(logoView, title);
        root.setTop(header);

        VBox centerBox = new VBox(20);
        centerBox.getChildren().addAll(buildPersonalSection(), buildEligibilitySection(), buildFooterSection());
        root.setCenter(centerBox);

        statusLabel.setStyle("-fx-text-fill: #b00020; -fx-font-weight: bold;");
        root.setBottom(statusLabel);
        BorderPane.setMargin(statusLabel, new Insets(10, 0, 0, 0));

        setupChoices();
        setupFieldHelpers();

        Scene scene = new Scene(root, 820, 720);
        stage.setTitle("Employment Application");
        stage.setScene(scene);
        stage.show();
    }

    private Pane buildPersonalSection() {
        VBox box = new VBox(10);

        Label sectionTitle = sectionLabel("Personal Information");

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Full Name"), 0, 0);
        grid.add(fullNameField, 1, 0, 3, 1);

        grid.add(new Label("Current Address"), 0, 1);
        grid.add(addressField, 1, 1, 3, 1);

        grid.add(new Label("Contact Number"), 0, 2);
        grid.add(contactField, 1, 2);

        grid.add(new Label("Email Address"), 2, 2);
        grid.add(emailField, 3, 2);

        grid.add(new Label("Highest Educational Attainment"), 0, 3);
        grid.add(educationCombo, 1, 3);

        maleRB.setToggleGroup(genderGroup);
        femaleRB.setToggleGroup(genderGroup);
        otherRB.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleRB, femaleRB, otherRB);
        grid.add(new Label("Gender"), 2, 3);
        grid.add(genderBox, 3, 3);

        ColumnConstraints c0 = new ColumnConstraints();
        c0.setMinWidth(180);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setMinWidth(140);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(c0, c1, c2, c3);

        box.getChildren().addAll(sectionTitle, grid);
        box.setStyle("-fx-border-color: #d0d0d0; -fx-border-radius: 4; -fx-padding: 10;");
        return box;
    }

    private Pane buildEligibilitySection() {
        VBox box = new VBox(10);

        Label sectionTitle = sectionLabel("Employment Eligibility");

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Date Available"), 0, 0);
        grid.add(dateAvailablePicker, 1, 0);

        grid.add(new Label("Desired Position"), 2, 0);
        grid.add(desiredPositionField, 3, 0);

        grid.add(new Label("Desired Salary"), 0, 1);
        grid.add(desiredSalaryField, 1, 1, 3, 1);

        authYes.setToggleGroup(authorizedGroup);
        authNo.setToggleGroup(authorizedGroup);
        HBox authBox = new HBox(10, authYes, authNo);

        grid.add(new Label("Are you legally authorized to work in the country?"), 0, 2, 2, 1);
        grid.add(authBox, 1, 3, 2, 1);

        relYes.setToggleGroup(relativesGroup);
        relNo.setToggleGroup(relativesGroup);
        HBox relBox = new HBox(10, relYes, relNo);

        grid.add(new Label("Do you have relatives working for our company?"), 0, 4, 2, 1);
        grid.add(relBox, 1, 5, 2, 1);

        grid.add(new Label("If yes, please explain further"), 0, 6);
        grid.add(relativesExplainField, 1, 6, 3, 1);

        ColumnConstraints c0 = new ColumnConstraints();
        c0.setMinWidth(220);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setMinWidth(140);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(c0, c1, c2, c3);

        box.getChildren().addAll(sectionTitle, grid);
        box.setStyle("-fx-border-color: #d0d0d0; -fx-border-radius: 4; -fx-padding: 10;");
        return box;
    }

    private Pane buildFooterSection() {
        HBox footer = new HBox(20);
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setStyle("-fx-border-color: #d0d0d0; -fx-border-radius: 4;");

        Label policy = new Label(
                "By submitting this application, you agree\n" +
                        "to adhere to company policies and\n" +
                        "provide accurate information throughout\n" +
                        "the employment process."
        );

        Button submitBtn = new Button("Submit");
        submitBtn.setPrefWidth(160);
        submitBtn.setPrefHeight(50);
        submitBtn.setOnAction(e -> handleSubmit());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        footer.getChildren().addAll(policy, spacer, submitBtn);
        return footer;
    }

    private Label sectionLabel(String text) {
        Label l = new Label(text);
        l.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 6 12 6 12; -fx-font-weight: bold;");
        return l;
    }

    private void setupChoices() {
        educationCombo.getItems().addAll("Masters", "Bachelors", "College Diploma");
        educationCombo.setPromptText("Select");

        dateAvailablePicker.setPromptText("mm/dd/yyyy");

        relativesExplainField.setDisable(true);
        relativesGroup.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            boolean yes = (newT == relYes);
            relativesExplainField.setDisable(!yes);
            if (!yes) relativesExplainField.clear();
        });
    }

    private void setupFieldHelpers() {
        contactField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return newText.matches("\\d{0,10}") ? change : null;
        }));

        desiredSalaryField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return newText.matches("\\d{0,8}(\\.\\d{0,2})?") ? change : null;
        }));
    }

    private void handleSubmit() {
        statusLabel.setText("");

        List<String> errors = validateForm();
        if (!errors.isEmpty()) {
            statusLabel.setText(String.join(" | ", errors));
            return;
        }

        try {
            Applicant applicant = new Applicant(
                    fullNameField.getText().trim(),
                    addressField.getText().trim(),
                    contactField.getText().trim(),
                    emailField.getText().trim(),
                    educationCombo.getValue(),
                    getSelectedGender()
            );

            ApplicantDAO applicantDAO = new ApplicantDAO();
            int applicantId = applicantDAO.insertApplicant(applicant);

            EmploymentApplication app = new EmploymentApplication(
                    applicantId,
                    dateAvailablePicker.getValue(),
                    desiredPositionField.getText().trim(),
                    desiredSalaryField.getText().trim(),
                    authorizedGroup.getSelectedToggle() == authYes,
                    relativesGroup.getSelectedToggle() == relYes,
                    relativesExplainField.getText().trim()
            );

            EmploymentApplicationDAO appDAO = new EmploymentApplicationDAO();
            appDAO.insertApplication(app);

            statusLabel.setStyle("-fx-text-fill: #0a7a0a; -fx-font-weight: bold;");
            statusLabel.setText("Application submitted successfully!");

            clearForm();

        } catch (SQLException ex) {
            statusLabel.setStyle("-fx-text-fill: #b00020; -fx-font-weight: bold;");
            statusLabel.setText("Database error: " + ex.getMessage());
        }
    }

    private List<String> validateForm() {
        List<String> errors = new ArrayList<>();

        String fullName = fullNameField.getText().trim();
        String contact = contactField.getText().trim();
        String salary = desiredSalaryField.getText().trim();
        LocalDate date = dateAvailablePicker.getValue();

        if (fullName.isEmpty() || !fullName.matches("[A-Za-z ]{1,50}")) {
            errors.add("Full Name must be letters/spaces only (max 50)");
        }

        if (!contact.matches("\\d{10}")) {
            errors.add("Contact Number must be exactly 10 digits");
        }

        if (educationCombo.getValue() == null) {
            errors.add("Select Highest Education");
        }

        if (date == null) {
            errors.add("Select Date Available");
        }

        if (!salary.matches("\\d{1,8}(\\.\\d{2})?")) {
            errors.add("Salary format must be 8 digits");
        }

        if (desiredPositionField.getText().trim().isEmpty()) {
            errors.add("Desired Position is required");
        }

        if (authorizedGroup.getSelectedToggle() == null) {
            errors.add("Select work authorization (Yes/No)");
        }

        if (relativesGroup.getSelectedToggle() == null) {
            errors.add("Select relatives question (Yes/No)");
        }

        if (relativesGroup.getSelectedToggle() == relYes &&
                relativesExplainField.getText().trim().isEmpty()) {
            errors.add("Explain relatives working in company");
        }

        return errors;
    }

    private String getSelectedGender() {
        Toggle t = genderGroup.getSelectedToggle();
        if (t == maleRB) return "Male";
        if (t == femaleRB) return "Female";
        if (t == otherRB) return "Other";
        return null; // optional
    }

    private void clearForm() {
        fullNameField.clear();
        addressField.clear();
        contactField.clear();
        emailField.clear();
        educationCombo.setValue(null);
        genderGroup.selectToggle(null);

        dateAvailablePicker.setValue(null);
        desiredPositionField.clear();
        desiredSalaryField.clear();

        authorizedGroup.selectToggle(null);
        relativesGroup.selectToggle(null);
        relativesExplainField.clear();
        relativesExplainField.setDisable(true);
    }
}
