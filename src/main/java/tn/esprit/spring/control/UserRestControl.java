package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestControl {

    @Autowired
    private IUserService userService;

    // GET all users
    @GetMapping
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    // GET one user
    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable Long id) {
        return userService.retrieveUser(String.valueOf(id));
    }

    // POST create user
    @PostMapping
    public User addUser(@RequestBody User u) {
        return userService.addUser(u);
    }

    // PUT update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        userService.deleteUser(String.valueOf(id));
    }
}
