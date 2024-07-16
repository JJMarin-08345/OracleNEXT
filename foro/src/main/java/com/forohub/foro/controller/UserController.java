package com.forohub.foro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forohub.foro.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.forohub.foro.model.User;
import com.forohub.foro.dto.UserDto;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        if(id == null){
            System.out.println("NO HAY ID");
            return null;
        }
        System.out.println("EL ID ES: "+id);
        return userService.getUserById(id);
    }

    //Obtener todos los usuarios con sus temas y comentarios
    @GetMapping("/getUsersWithTopicsAndComments")
    public ResponseEntity<List<UserDto>> getUsersWithTopicsAndComments() {
        List<UserDto> users = userService.getAllUsersWithTopicsAndComments();
        return ResponseEntity.ok(users);
    }

    //Obtener todos los usuarios con sus temas
    @GetMapping("/getUsersWithTopics")
    public ResponseEntity<List<UserDto>> getUsersWithTopics() {
        List<UserDto> users = userService.getAllUsersWithTopics();
        return ResponseEntity.ok(users);
    }

    //Obtener todos los usuarios con sus comentarios
    @GetMapping("/getUsersWithComments")
    public ResponseEntity<List<UserDto>> getUsersWithComments() {
        List<UserDto> users = userService.getAllUsersWithComments();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserByIdWithTopics/{id}")
    public UserDto getUserWithTopicsById(@PathVariable Integer id) {
        return userService.getUserByIdWithTopics(id);
    }

    @GetMapping("/getUserByIdWithComments/{id}")
    public UserDto getUserWithCommentsById(@PathVariable Integer id){
        return userService.getUserByIdWithComments(id);
    }
    

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {
        //TODO: process POST request
        System.out.println("\n\nFUNCION createUser():\n");
        System.out.println("EL USUARIO ES: "+user.getUser());
        // return "Usuario es: "+user;
        return userService.saveUser(user);
    }

    @PutMapping("updateUser/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        System.out.println("\n\nFUNCION updateUser():\n");
        System.out.println("El usuario es: "+user.getUser()+ "\nla clave es: "+user.getPassword() +"\nel id es: "+id);
        
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        System.out.println("\n\nFUNCION deleteUser():\n");
        System.out.println("El id es: "+id);
        return userService.deleteUser(id);
    }

}
