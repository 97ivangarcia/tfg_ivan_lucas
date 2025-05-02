package org.example.keycraftapp.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.keycraftapp.entities.User;
import org.example.keycraftapp.services.UserService;
import org.example.keycraftapp.util.SessionManager;

public class LoginViewController {

    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Label loginErrorLabel;

    @FXML private TextField registerEmailField;
    @FXML private PasswordField registerPasswordField;
    @FXML private Label registerErrorLabel;

    // Usar el servicio en lugar del DAO directamente
    private final UserService userService = new UserService();

    /** Se ejecuta al hacer clic en "Iniciar sesión" */
    @FXML
    private void onLogin(ActionEvent event) {
        try {
            String email = loginEmailField.getText().trim();
            String password = loginPasswordField.getText();

            if (email.isBlank() || password.isBlank()) {
                loginErrorLabel.setText("Rellena ambos campos.");
                return;
            }

            // Utilizar el servicio para el login
            User user = userService.loginUser(email, password);

            if (user == null) {
                loginErrorLabel.setText("Credenciales incorrectas.");
                return;
            }

            // Guardar el usuario en la sesión
            SessionManager.setCurrentUser(user);

            // Si el login es válido, cargar la vista del catálogo
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/keycraftapp/fxml/MainCatalogoView.fxml"));
                Scene catalogScene = new Scene(loader.load());

                Stage stage = (Stage) loginErrorLabel.getScene().getWindow();
                stage.setScene(catalogScene);
                stage.setTitle("Catálogo de Teclados Mecánicos");
                stage.show();
            } catch (Exception e) {
                loginErrorLabel.setText("No se pudo cargar el catálogo: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            loginErrorLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Se ejecuta al hacer clic en "Registrarse" */
    @FXML
    private void onRegister(ActionEvent event) {
        try {
            String email = registerEmailField.getText().trim();
            String password = registerPasswordField.getText();

            // Validar que los campos no estén vacíos
            if (email.isBlank() || password.isBlank()) {
                registerErrorLabel.setText("Rellena ambos campos.");
                return;
            }

            // Validar formato de email básico
            if (!email.contains("@") || !email.contains(".")) {
                registerErrorLabel.setText("Formato de email inválido.");
                return;
            }

            // Utilizar el servicio para el registro
            boolean success = userService.registerUser(email, password);

            // Verificar si el registro fue exitoso
            if (success) {
                registerErrorLabel.setStyle("-fx-text-fill: green;");
                registerErrorLabel.setText("¡Registro exitoso! Ya puedes iniciar sesión.");
                registerEmailField.clear();
                registerPasswordField.clear();
            } else {
                registerErrorLabel.setText("El email ya está registrado o hubo un error.");
            }
        } catch (Exception e) {
            registerErrorLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}