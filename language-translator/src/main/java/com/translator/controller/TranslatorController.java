package com.translator.controller;

import com.translator.dto.ErrorResponse;
import com.translator.dto.TranslationRequest;
import com.translator.model.User;
import com.translator.service.UserService;
import com.translator.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TranslatorController {
    
    private static final Logger logger = LoggerFactory.getLogger(TranslatorController.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;

    // Home page redirect
    @GetMapping("/")
    public String redirectToTranslator() {
        return "redirect:/translator";
    }
    
    // Main translator page
    @GetMapping("/translator")
    public String translator(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
            logger.info("User {} accessed translator page", authentication.getName());
        }
        model.addAttribute("translationRequest", new TranslationRequest());
        return "translator";
    }
    
    // Login page
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, 
                       @RequestParam(required = false) String logout,
                       Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
            logger.warn("Failed login attempt");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out");
            logger.info("User logged out successfully");
        }
        return "login";
    }
    
    // Registration page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    // Handle registration
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                             BindingResult bindingResult, 
                             Model model) {
        // Custom validation
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            logger.warn("Registration validation failed");
            return "register";
        }
        
        try {
            userService.save(user);
            logger.info("New user registered successfully: {}", user.getUsername());
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            logger.error("Registration error: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    
    // Translation API endpoint
    @PostMapping("/translate")
    @ResponseBody
    public ResponseEntity<?> translate(@Valid @RequestBody TranslationRequest request,
                                     BindingResult bindingResult,
                                     Authentication authentication) {
        // Validation check
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
            
            logger.warn("Translation validation failed: {}", errors);
            return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Validation failed", errors));
        }
        
        try {
            // Log translation attempt
            logger.info("Translation requested by user: {} from {} to {}", 
                       authentication.getName(), 
                       request.getFromLang(), 
                       request.getToLang());

            // URL encode the text
            String encodedText = java.net.URLEncoder.encode(request.getText(), "UTF-8");
            
            // Build API URL
            String url = String.format(
                "https://api.mymemory.translated.net/get?q=%s&langpair=%s|%s", 
                encodedText, request.getFromLang(), request.getToLang()
            );
            
            // Make API call
            String response = restTemplate.getForObject(url, String.class);
            
            logger.info("Translation completed successfully");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Translation error: {}", e.getMessage(), e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Translation service error", 
                      Collections.singletonList(e.getMessage())));
        }
    }
    
    // Handle validation errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("An unexpected error occurred", 
                  Collections.singletonList(e.getMessage())));
    }
}