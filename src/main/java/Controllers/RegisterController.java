package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.UserDto.CreateUserDto;
import models.User;
import services.UserService;
import util.PasswordHasher;
import util.SessionManager;
import services.SceneManager;
import utils.SceneLocator;

public class RegisterController {

    @FXML private TextField NameField;
    @FXML private TextField EmailField;
    @FXML private TextField AgeField;
    @FXML private PasswordField PasswordField;
    @FXML private PasswordField ConfirmPasswordField;
    @FXML private Label ErrorLabel;
    @FXML private Button RegisterBtn;
    @FXML private Label BackToLogin;

    private final UserService userService = new UserService();

    /**
     * Kjo metodë thirret kur klikohet butoni "Register"
     * Lidhet nga Scene Builder me `onAction="handleRegister"`
     */
    @FXML
    private void handleRegister() {
        ErrorLabel.setText("");

        String name = NameField.getText().trim();
        String email = EmailField.getText().trim();
        String ageText = AgeField.getText().trim();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || ageText.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            ErrorLabel.setText("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            ErrorLabel.setText("Mosha duhet të jetë numër.");
            return;
        }

        if (!email.matches("^.+@.+\\..+$")) {
            ErrorLabel.setText("Email nuk është valid.");
            return;
        }

        if (password.length() < 8) {
            ErrorLabel.setText("Passwordi duhet të ketë të paktën 8 karaktere.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            ErrorLabel.setText("Passwordet nuk përputhen.");
            return;
        }

        // Kontrollo nëse emaili ekziston
        User existingUser = userService.getByEmail(email);
        if (existingUser != null) {
            ErrorLabel.setText("Ky email tashmë është në përdorim.");
            return;
        }

        String salt = PasswordHasher.generateSalt();
        String hashedPassword = PasswordHasher.generateSaltedHash(password, salt);

        CreateUserDto dto = new CreateUserDto(name, email, age, "qytetar", hashedPassword, salt);

        try {
            User newUser = userService.create(dto);
            SessionManager.setCurrentUser(newUser);

            SceneManager.load("/view/LoginView.fxml");

            ErrorLabel.setText("Regjistrimi u krye me sukses!");

        } catch (Exception e) {
            ErrorLabel.setText(e.getMessage());
        }
    }

    /**
     * Kjo metodë thirret kur klikon "Already have an account?"
     * Lidhet nga Scene Builder me `onMouseClicked="goToLogin"`
     */
    @FXML
    private void goToLogin() {
        try {
            SceneManager.load("/view/LoginView.fxml");
        } catch (Exception e) {
            ErrorLabel.setText("Nuk mund të hapet faqja e kyçjes.");
            e.printStackTrace();
        }
    }
}
