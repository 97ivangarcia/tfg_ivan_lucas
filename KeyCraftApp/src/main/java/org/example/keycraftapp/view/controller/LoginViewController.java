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


    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;  /* nuevos */
    @FXML private Label loginErrorLabel;

    @FXML private TextField registerEmailField;
    @FXML private PasswordField registerPasswordField;
    @FXML private Label registerErrorLabel;

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

    /** Se ejecuta al hacer clic en "Registrarse" */
    @FXML
    private void onRegister(ActionEvent event) {
        String email = registerEmailField.getText();
        String password = registerPasswordField.getText();

        if (email.isBlank() || password.isBlank()) {
            registerErrorLabel.setText("Rellena ambos campos.");
            return;
        }


        User existingUser = userDAO.findByEmail(email);
      if (existingUser != null) {
           registerErrorLabel.setText("El email ya está registrado.");
            return;
       }

        // Crear nuevo usuario
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password); // OJO: aquí encriptar contraseña

        boolean success = userDAO.save(newUser);

        if (success) {
            registerErrorLabel.setStyle("-fx-text-fill: green;");
            registerErrorLabel.setText("¡Registro exitoso!");
            registerEmailField.clear();
            registerPasswordField.clear();
        } else {
            registerErrorLabel.setText("Error al registrar. Intenta de nuevo.");
        }
    }
}
