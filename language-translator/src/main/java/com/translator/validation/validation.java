package com.translator.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.translator.model.User;
import com.translator.service.UserService;

@Component
public class validation implements Validator {
    
    private final UserService userService;
    
    public validation(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        // Check for unique username
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "duplicate.username", 
                             "This username is already taken");
        }
        
        // Check for unique email
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "duplicate.email", 
                             "This email is already registered");
        }
    }
}