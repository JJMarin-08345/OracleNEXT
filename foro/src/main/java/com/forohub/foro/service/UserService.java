package com.forohub.foro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.forohub.foro.dto.UserDto;
import com.forohub.foro.model.User;
import com.forohub.foro.repository.IUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Obtener todos los usuarios
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userRepository.findAll().stream()
                .map(user -> new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                null,
                null
        )).collect(Collectors.toList());
        return users;
    }

    //Obtener un usuario por su id
    public UserDto getUserById(Integer id) {
        User userToTrans = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDto user = new UserDto(
                userToTrans.getUserId(),
                userToTrans.getUser(),
                userToTrans.getPassword(),
                null,
                null
        );
        return user;
    }

    //Obtener todos los usuarios con sus temas y comentarios
    public List<UserDto> getAllUsersWithTopicsAndComments() {
        List<UserDto> users = userRepository.findAll().stream()
                .filter(u -> (!u.getTopics().isEmpty() && !u.getComments().isEmpty()))
                .map(user -> new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                user.getTopics(),
                user.getComments()
        )).collect(Collectors.toList());
        return users;
    }

    //Obtener todos los usuarios con sus temas
    public List<UserDto> getAllUsersWithTopics() {
        List<UserDto> users = userRepository.findAll().stream()
                .filter(u -> (!u.getTopics().isEmpty()))
                .map(user -> new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                user.getTopics(),
                null
        )).collect(Collectors.toList());
        System.out.println(users);
        return users;
    }

    //Obtener todos los usuarios con sus temas y comentarios
    public List<UserDto> getAllUsersWithComments() {
        List<UserDto> users = userRepository.findAll().stream()
                .filter(u -> (!u.getComments().isEmpty()))
                .map(user -> new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                null,
                user.getComments()
        )).collect(Collectors.toList());
        System.out.println(users);
        return users;
    }

    //Obtener un usuario por su id con sus temas
    public UserDto getUserByIdWithTopics(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getTopics().isEmpty()) {
            System.out.println("ESTE USUARIO NO TIENE TEMAS ABIERTOS");
            return null;
        }

        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                user.getTopics(),
                null
        );
        return userDto;
    }

    //Obtener un usuario por su id con sus comentarios
    public UserDto getUserByIdWithComments(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getComments().isEmpty()) {
            System.out.println("ESTE USUARIO NO HA REALIZADO NINGUN COMENTARIO");
            return null;
        }

        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                null,
                user.getComments()
        );
        return userDto;
    }

    //Guardar un usuario
    public String saveUser(User user) {
        User userExist = userExistByUser(user.getUser());
        if (userExist != null) {
            return "ERROR el usuario con este username ya existe";
        }
        return userRepository.save(user).toString();
    }

    //Actualizar un usuario
    public String updateUser(Integer userId, User updateUser) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            System.out.println("El usuario si existe");
            User newUser = optionalUser.get();
            User usernameExists = userExistByUser(updateUser.getUser());
            if (usernameExists != null && usernameExists.getUser().equals(updateUser.getUser()) && usernameExists.getUserId() != userId) {
                return "Este usuario ya esta en uso";
            }
            // newUser.setUserId(updateUser.getUserId());
            newUser.setUser(updateUser.getUser());
            newUser.setPassword(updateUser.getPassword());
            userRepository.save(newUser);
            System.out.println(updateUser.toString());
            System.out.println(newUser.toString());
            return "Usuario actualizado correctamente";
        } else {
            return "El usuario no existe";
        }
    }

    //Eliminar un usuario
    public String deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return "El usuario no existe";
        }
        userRepository.deleteById(id);
        return "Usuario eliminado correctamente";
    }

    //Funciones extra
    private User userExistByUser(String user) {
        return userRepository.findByUser(user);
    }
}
