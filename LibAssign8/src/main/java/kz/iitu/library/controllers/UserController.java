package kz.iitu.library.controllers;

import kz.iitu.library.Services.UserService;
import kz.iitu.library.models.Author;
import kz.iitu.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/")
    public void addUser(@RequestBody User user){
        if(userService.addUser(user)) {
            System.out.println("User " + user + " added");
            return;
        }
        System.out.println(user + " user already exist");
    }

    @PostMapping("/genre/")
    public void addAuthor(@RequestBody Author author){
        if(userService.addAuthor(author)) {
            System.out.println(author + "Author added");
            return;
        }
        System.out.println(author + " author already exist");
    }

    @PatchMapping("/add/")
    public void addBook(@RequestParam("userId") Long userId,@RequestParam("bookId") Long bookId){
        userService.addBook(userId, bookId);
    }

    @GetMapping("/{name}")
    public Author findAuthorByName(@PathVariable("name") String name){
        return userService.findAuthorByName(name);
    }

    @DeleteMapping("/del")
    public void clear() {
        userService.clear();
    }
}
