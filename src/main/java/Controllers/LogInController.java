package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Dto.UserDto.LoginUserDto;
import models.User;
import services.LanguageManager;
import services.SceneManager;
import services.UserService;
import utils.SceneLocator;
import utils.SessionManager;

public class LogInController extends BaseController {

    @FXML private TextField Username;
    @FXML private PasswordField Password;
    @FXML private TextField PasswordVisibleField;
    @FXML private CheckBox showPasswordCheckBox;
    @FXML private Button LoginBttn;
    @FXML private Label ErrorLable;

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        Username.setOnAction(e -> Password.requestFocus());
        Password.setOnAction(e -> handleLogin());
        PasswordVisibleField.setOnAction(e -> handleLogin());

        // Toggle mes fushave të fjalëkalimit
        showPasswordCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                PasswordVisibleField.setText(Password.getText());
                PasswordVisibleField.setVisible(true);
                PasswordVisibleField.setManaged(true);
                Password.setVisible(false);
                Password.setManaged(false);
            } else {
                Password.setText(PasswordVisibleField.getText());
                Password.setVisible(true);
                Password.setManaged(true);
                PasswordVisibleField.setVisible(false);
                PasswordVisibleField.setManaged(false);
            }
        });

        // Sinkronizim mes fushave në kohë reale
        Password.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!showPasswordCheckBox.isSelected()) {
                PasswordVisibleField.setText(newVal);
            }
        });

        PasswordVisibleField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (showPasswordCheckBox.isSelected()) {
                Password.setText(newVal);
            }
        });
    }

    @FXML
    private void handleLogin() {
        String email = Username.getText().trim();
        String password = showPasswordCheckBox.isSelected()
                ? PasswordVisibleField.getText()
                : Password.getText();

        ErrorLable.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText(LanguageManager.getInstance()
                    .getResourceBundle().getString("error.empty_fields"));
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);

            if (user == null) {
                ErrorLable.setText(LanguageManager.getInstance()
                        .getResourceBundle().getString("error.login_failed"));
                return;
            }

            SessionManager.setCurrentUser(user);

            switch (user.getRoli()) {
                case "admin" -> SceneManager.load(SceneLocator.ADMIN_DASHBOARD);
                case "zyrtar_komunal" -> SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
                case "qytetar" -> SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
                default -> ErrorLable.setText(LanguageManager.getInstance()
                        .getResourceBundle().getString("error.unknown_role"));
            }

        } catch (Exception e) {
            ErrorLable.setText(LanguageManager.getInstance()
                    .getResourceBundle().getString("error.database_failed1"));
        }
    }

    @FXML
    private void goToRegister() {
        try {
            SceneManager.load(SceneLocator.REGISTER_PAGE);
        } catch (Exception e) {
            ErrorLable.setText(LanguageManager.getInstance()
                    .getResourceBundle().getString("error.open_register_failed"));
        }
    }
}
