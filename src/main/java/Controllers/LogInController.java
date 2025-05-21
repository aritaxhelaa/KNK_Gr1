package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.SceneManager;
import services.UserService;
import utils.SceneLocator;
import models.User;
import models.Dto.UserDto.LoginUserDto;
import services.LanguageManager;

public class LogInController extends BaseController {

    @FXML private TextField Username;
    @FXML private PasswordField Password;
    @FXML private Button LoginBttn;
    @FXML private Label ErrorLable;
    @FXML private Label CreateUsr;

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        // Mund të shtosh ndonjë inicizalim në të ardhmen nëse duhet
    }

    @FXML
    private void handleLogin() {
        String email = Username.getText().trim();
        String password = Password.getText();

        ErrorLable.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText(LanguageManager.getInstance()
                    .getResourceBundle()
                    .getString("error.empty_fields"));
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);

            if (user == null) {
                ErrorLable.setText(LanguageManager.getInstance()
                        .getResourceBundle()
                        .getString("error.login_failed"));
                return;
            }

            utils.SessionManager.setCurrentUser(user);

            switch (user.getRoli()) {
                case "admin" -> SceneManager.load(SceneLocator.ADMIN_DASHBOARD);
                case "zyrtar_komunal" -> SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
                case "qytetar" -> SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
                default -> ErrorLable.setText("Roli i panjohur.");
            }

        } catch (Exception e) {
            ErrorLable.setText("Gabim gjatë kyçjes.");
        }
    }

    @FXML
    private void goToRegister() {
        try {
            SceneManager.load(SceneLocator.REGISTER_PAGE);
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
        }
    }
}
