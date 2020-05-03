package kz.iitu.library.controllers;

import kz.iitu.library.Services.UserServ;
import kz.iitu.library.models.Author;
import kz.iitu.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServ userServ;

    @GetMapping
    public List<User> getAllUsers() {
        return userServ.getAllUsers();
    }

    @GetMapping("/create")
    public void createUserByUsernamePassword(String username,
                                             String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        userServ.createUser(user);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        System.out.println("UserController.createUser");
        System.out.println("user = " + user);

        userServ.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        System.out.println("UserController.updateUser");
        System.out.println("id = " + id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());

        userServ.updateUser(id, user);
    }
}
