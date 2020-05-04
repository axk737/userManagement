package vTrading.userManagement.controllers;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vTrading.userManagement.UserManagementApplication;
import vTrading.userManagement.models.User;
import vTrading.userManagement.repositories.UserRepository;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createUser")
    public @ResponseBody User createUser(@RequestParam String firstName, @RequestParam String lastName) throws IllegalArgumentException {
        if (firstName == null || firstName.equals("") || lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException();
        }
        User user = new User(firstName, lastName);
        UserManagementApplication.userCache.put(user.getId(), user);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam long id) throws NoSuchElementException {
        if (!UserManagementApplication.userCache.containsKey(id)) {
            throw new NoSuchElementException();
        }
        return UserManagementApplication.userCache.get(id);
    }

    @GetMapping("/getAllUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}