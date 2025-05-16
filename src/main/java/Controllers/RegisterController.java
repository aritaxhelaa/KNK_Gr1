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
import services.LanguageManager;

import java.util.Locale;

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

    @FXML
    private void handleRegister() {
        ErrorLabel.setText("");

        String name = NameField.getText().trim();
        String email = EmailField.getText().trim();
        String ageText = AgeField.getText().trim();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || ageText.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            ErrorLabel.setText(getText("error.empty_fields"));
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            ErrorLabel.setText(getText("error.age_not_number"));
            return;
        }

        if (!email.matches("^.+@.+\\..+$")) {
            ErrorLabel.setText(getText("error.invalid_email"));
            return;
        }

        if (password.length() < 8) {
            ErrorLabel.setText(getText("error.password_short"));
            return;
        }

        if (!password.equals(confirmPassword)) {
            ErrorLabel.setText(getText("error.password_mismatch"));
            return;
        }

        User existingUser = userService.getByEmail(email);
        if (existingUser != null) {
            ErrorLabel.setText(getText("error.email_exists"));
            return;
        }

        String salt = PasswordHasher.generateSalt();
        String hashedPassword = PasswordHasher.generateSaltedHash(password, salt);

        CreateUserDto dto = new CreateUserDto(name, email, age, "qytetar", hashedPassword, salt);

        try {
            User newUser = userService.create(dto);
            SessionManager.setCurrentUser(newUser);

            ErrorLabel.setText(getText("register.success"));
            SceneManager.load(SceneLocator.LOGIN_PAGE);

        } catch (Exception e) {
            ErrorLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void goToLogin() {
        try {
            SceneManager.load(SceneLocator.LOGIN_PAGE);
        } catch (Exception e) {
            ErrorLabel.setText(getText("register.cannot_open_login"));
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToEn() {
        LanguageManager.getInstance().setLocale(Locale.ENGLISH);
        reloadScene();
    }

    @FXML
    private void switchToSq() {
        LanguageManager.getInstance().setLocale(new Locale("sq"));
        reloadScene();
    }

    private void reloadScene() {
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getText(String key) {
        return LanguageManager.getInstance().getResourceBundle().getString(key);
    }
}
