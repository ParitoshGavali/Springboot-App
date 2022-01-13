package cred.firstProject.controller;

import cred.firstProject.model.User;
import cred.firstProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
@Slf4j
public class MainController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/") // Map ONLY POST Requests
    public ResponseEntity<String> addNewUser (@RequestBody User user){
        log.info("inside user/add/ controller");
        List result = userService.addUser(user);
        Integer conflict=0, exception=-1;
        if (result.get(0) == conflict) {
            return new ResponseEntity<String>(result.get(1).toString(),HttpStatus.CONFLICT);
        } else if (result.get(1) == exception) {
            return new ResponseEntity<String>(result.get(1).toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(result.get(1).toString(),HttpStatus.CREATED);
        }
    }

    @GetMapping(path="/email/{emailId}")
    public ResponseEntity<List> getUserByEmail(@PathVariable String emailId){
        var users = userService.getUserByEmail(emailId);
        if( users.size() == 0) return new ResponseEntity<List>(Collections.singletonList("No user exists."),HttpStatus.OK);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping(path="/mobile/{mobile}")
    public ResponseEntity<List> getUserByMobile(@PathVariable Integer mobile){
        var users = userService.getUserByMobile(mobile);
        if( users.size() == 0) return new ResponseEntity<List>(Collections.singletonList("No user exists."),HttpStatus.OK);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping(path="/id/{id}")
    @ResponseBody
    public ResponseEntity<List> getUserById(@PathVariable Integer id){
        List users = Collections.singletonList(userService.getUserById(id));
        if(users.size() == 0) return new ResponseEntity<List>(Collections.singletonList("No user exists."),HttpStatus.OK);
        return new ResponseEntity<List>(users,HttpStatus.OK);
    }

//    @GetMapping(path="/")
//    public ResponseEntity<List> getUser(@RequestParam(required = false) String emailId,
//                                        @RequestParam(required = false) Integer mobile,
//                                        @RequestParam(required = false) Integer id) {
//        if(null == emailId ^ null == mobile ^ null == id){
//            if (null == emailId && null == mobile && null == id){
//                // all arguments are provided
//                return new ResponseEntity<List>(Collections.singletonList("Please provide only one parameter"),HttpStatus.NOT_ACCEPTABLE);
//            } else { // only one argument provided
//                List<User> search = null;
//                if(null != emailId) search = userService.getUserByEmail(emailId);
//                else if (null != mobile) search = userService.getUserByMobile(mobile);
//                else {
//                    var temp = userService.getUserById(id);
//                    if (temp.isPresent()) search = (List<User>) temp.get();
//                }
//                if (search == null || search.size()==0) {
//                    return new ResponseEntity<List>(Collections.singletonList("No match found."),HttpStatus.NO_CONTENT);
//                } else {
//                    return new ResponseEntity<List>(search,HttpStatus.OK);
//                }
//            }
//        } else {
//            return new ResponseEntity<List>(Collections.singletonList("Please provide only one parameter."),HttpStatus.NOT_ACCEPTABLE);
//        }
//
//    }

    @GetMapping(path="/all")
    public ResponseEntity<List> getAllUsers() {
        log.info("inside user/all controller");
        var allUsers = userService.getAllUsers();
        if( allUsers.size() == 0) return new ResponseEntity<List>(Collections.singletonList("No user exists."),HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}