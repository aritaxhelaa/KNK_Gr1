package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.SceneManager;
import services.UserService;
import utils.SceneLocator;
import models.User;
import models.Dto.UserDto.LoginUserDto;
import services.LanguageManager;

import java.util.Locale;

public class LogInController {

    @FXML private TextField Username;
    @FXML private PasswordField Password;
    @FXML private Button LoginBttn;
    @FXML private Label ErrorLable;
    @FXML private Label CreateUsr;

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleLogin() {
        String email = Username.getText().trim();
        String password = Password.getText();

        ErrorLable.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText(LanguageManager.getInstance().getResourceBundle().getString("error.empty_fields"));
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);

            ErrorLable.setText("Login i suksesshëm! Roli: " + user.getRoli());

            switch (user.getRoli()) {
                case "komunal" -> SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
                case "qytetar" -> SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
                default -> ErrorLable.setText("Roli i panjohur.");
            }

        } catch (Exception e) {
            ErrorLable.setText(e.getMessage());
        }
    }

    @FXML
    private void goToRegister() {
        try {
            SceneManager.load(SceneLocator.REGISTER_PAGE);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToEnglish() {
        LanguageManager.getInstance().setLocale(Locale.ENGLISH);
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToAlbanian() {
        LanguageManager.getInstance().setLocale(new Locale("sq"));
        try {
            SceneManager.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
