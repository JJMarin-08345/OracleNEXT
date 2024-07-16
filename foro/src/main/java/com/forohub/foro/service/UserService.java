package com.forohub.foro.service;

import java.util.List;
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
        List<UserDto> users = userRepository.findUsersWithTopicsAndComments();
        System.out.println(users);
        return users;
    }

    //Obtener un usuario por su id con sus temas
    public UserDto getUserByIdWithTopic(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getUser(),
                user.getPassword(),
                user.getTopics(),
                null
        );
        return userDto;
    }

    //Guardar un usuario
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Actualizar un usuario
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    //Eliminar un usuario
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
