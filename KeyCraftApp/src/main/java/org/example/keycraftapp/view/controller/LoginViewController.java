package org.example.keycraftapp.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.keycraftapp.dao.UserDao;
import org.example.keycraftapp.entities.User;

public class LoginViewController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserDao userDAO = new UserDao();

    /** Se ejecuta al hacer clic en "Iniciar sesión" */
    @FXML
    private void onLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            errorLabel.setText("Rellena ambos campos.");
            return;
        }

        User user = userDAO.findByEmailAndPassword(email, password);

        if (user == null) {
            errorLabel.setText("Credenciales incorrectas.");
            return;
        }

        // Si el login es válido, cargar la vista del catálogo
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/keycraftapp/fxml/CatalogView.fxml"));
            Scene catalogScene = new Scene(loader.load());

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(catalogScene);
            stage.setTitle("Catálogo de Teclados Mecánicos");
            stage.show();
        } catch (Exception e) {
            errorLabel.setText("No se pudo cargar el catálogo.");
            e.printStackTrace();
        }
    }
}
