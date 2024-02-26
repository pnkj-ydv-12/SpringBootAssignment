package com.UserManagementAPI.Controllers;
 
import com.UserManagementAPI.Model.User;
import com.UserManagementAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/users")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
 
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
 
    @GetMapping("/sort/{property}")
    public ResponseEntity<List<User>> sortUsers(@PathVariable String property) {
        List<User> sortedUsers = userService.sortUsers(property);
        if (sortedUsers == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(sortedUsers);
        }
}
