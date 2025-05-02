package org.example.keycraftapp.services;

import org.example.keycraftapp.dao.UserDao;
import org.example.keycraftapp.entities.User;

public class UserService {

    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    // Metodo para registrar un nuevo usuario
    public boolean registerUser(String email, String password) {
        try {
            // Verificar si ya existe un usuario con el mismo correo
            if (userDao.findByEmail(email) != null) {
                System.out.println("El email ya está registrado: " + email);
                return false;
            }

            // Crear un nuevo usuario
            User user = new User(email, password);

            // Aquí se podría añadir encriptación de contraseña
            // user.setPassword(encryptPassword(password));

            // Guardar el nuevo usuario en la base de datos
            boolean result = userDao.save(user);
            if (result) {
                System.out.println("Usuario registrado correctamente: " + email);
            } else {
                System.out.println("Error al guardar el usuario: " + email);
            }
            return result;
        } catch (Exception e) {
            System.err.println("Error en el registro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para autenticar al usuario (login)
    public User loginUser(String email, String password) {
        try {
            User user = userDao.findByEmailAndPassword(email, password);
            if (user != null) {
                System.out.println("Login exitoso para: " + email);
            } else {
                System.out.println("Intento de login fallido para: " + email);
            }
            return user;
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}