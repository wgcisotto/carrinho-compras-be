package com.altran.controllers;

import com.altran.exceptions.ValidationException;
import com.altran.models.User;
import com.altran.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET, value="/users")
    public Iterable<User> users() {
        return userService.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/users")
    public User save(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public Optional<User> show(@PathVariable String id) {
        return userService.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
    public User update(@PathVariable String id, @RequestBody @Valid User user) {
       return userService.update(id, user);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
    public String delete(@PathVariable String id) throws ValidationException {
        userService.delete(id);
        return "";
    }

}
