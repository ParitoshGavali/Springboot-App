package cred.firstProject.service;

import cred.firstProject.model.User;
import cred.firstProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List addUser(User user){
        try { // try to add a new user, check if mobile or email already exists and return appropriate message
            var temp = userRepository.findByEmailId(user.emailId);
            if(temp.size()>0) return Arrays.asList(0, "EmailId already exists registered.");

            temp = userRepository.findByMobile(user.mobile);
            if(temp.size()>0) return Arrays.asList(0,"Mobile number already registered.");

            userRepository.save(user);
            log.info("New user with emailId " + user.emailId + " added to the Database.");

            return Arrays.asList(1,"Successfully registered!");
        } catch (Exception e){
            log.error("ERROR Duplicate entry" + e.toString() + " | " + e.getLocalizedMessage());
            return Arrays.asList(-1,"Server error!");
        }
    };

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAllByOrderByDobAsc();
    }

    public List<User> getUserByEmail(String emailId){
        return userRepository.findByEmailId(emailId);
    }

    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> getUserByMobile(Integer mobile){
        return userRepository.findByMobile(mobile);
    }
}
