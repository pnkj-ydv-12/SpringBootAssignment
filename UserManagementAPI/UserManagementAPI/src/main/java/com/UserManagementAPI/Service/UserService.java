package com.UserManagementAPI.Service;

import com.UserManagementAPI.Model.User;
import com.UserManagementAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
 
@Service
public class UserService {
	
    private List<User> userList = new ArrayList<>();
 
    @Autowired
    private UserRepository userRepository;
    
    
 
    public User saveUser(User user) {
        return userRepository.save(user);
    }
 
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void deleteUserById(Long id){
        userRepository.deleteById(id); 
   }
 
    
    public List<User> sortUsers(String property) {
    	
    	List<User> userList=  userRepository.findAll();
    	
        switch (property.toLowerCase()) {
            case "name":
                return userList.stream()
                               .sorted(Comparator.comparing(User::getName))
                               .collect(Collectors.toList());
            case "age":
                return userList.stream()
                               .sorted(Comparator.comparingInt(User::getAge))
                               .collect(Collectors.toList());
            case "experience":
                return userList.stream()
                               .sorted(Comparator.comparingInt(User::getExperience).reversed())
                               .collect(Collectors.toList());
            default:
                return null;
        }
    }
 
}
