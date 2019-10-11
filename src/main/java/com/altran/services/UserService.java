package com.altran.services;

import com.altran.exceptions.ValidationException;
import com.altran.models.User;
import com.altran.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }

    public User update(String id, User user){
        Optional<User> optionalUser = userRepository.findById(id);

        User i = optionalUser.get();

        if(user.getName() != null)
            i.setName(user.getName());
        if(user.getEmail() != null)
            i.setEmail(user.getEmail());

        userRepository.save(i);

        return i;

    }

    public void delete(String id) throws ValidationException {
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent())
            throw new ValidationException("001","User not found");

        User user = optionalUser.get();
        userRepository.delete(user);
    }


}
