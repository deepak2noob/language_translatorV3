package com.translator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TranslationRequest {
    
    @NotBlank(message = "Text to translate cannot be empty")
    @Size(max = 5000, message = "Text cannot exceed 5000 characters")
    private String text;
    
    @NotBlank(message = "Source language is required")
    @Pattern(regexp = "^[a-z]{2}$", message = "Source language must be a valid 2-letter language code")
    private String fromLang;
    
    @NotBlank(message = "Target language is required")
    @Pattern(regexp = "^[a-z]{2}$", message = "Target language must be a valid 2-letter language code")
    private String toLang;

    // Default Constructor
    public TranslationRequest() {
    }

    // Constructor with all fields
    public TranslationRequest(String text, String fromLang, String toLang) {
        this.text = text;
        this.fromLang = fromLang;
        this.toLang = toLang;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromLang() {
        return fromLang;
    }

    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }

    public String getToLang() {
        return toLang;
    }

    public void setToLang(String toLang) {
        this.toLang = toLang;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "TranslationRequest{" +
                "text='" + text + '\'' +
                ", fromLang='" + fromLang + '\'' +
                ", toLang='" + toLang + '\'' +
                '}';
    }
}