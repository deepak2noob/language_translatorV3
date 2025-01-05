package com.translator.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.translator.model.User;
import com.translator.service.UserService;

@Component
public class UserValidator implements Validator {
    
    private final UserService userService;
    
    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        // Username validation
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "duplicate.username", 
                             "Username already exists");
        }
        
        // Email validation
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "duplicate.email", 
                             "Email already exists");
        }
    }
}