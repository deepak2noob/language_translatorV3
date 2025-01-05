// validation.js
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    
    form.addEventListener('submit', function(e) {
        let isValid = true;
        
        // Username validation
        const username = document.getElementById('username');
        if (!/^[a-zA-Z0-9._-]{3,50}$/.test(username.value)) {
            showError(username, 'Username must be between 3 and 50 characters and contain only letters, numbers, dots, underscores, and hyphens');
            isValid = false;
        }
        
        // Email validation
        const email = document.getElementById('email');
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value)) {
            showError(email, 'Please enter a valid email address');
            isValid = false;
        }
        
        // Password validation
        const password = document.getElementById('password');
        if (!/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$/.test(password.value)) {
            showError(password, 'Password must be at least 8 characters long and contain at least one digit, lowercase, uppercase, and special character');
            isValid = false;
        }
        
        if (!isValid) {
            e.preventDefault();
        }
    });
    
    function showError(input, message) {
        const formControl = input.parentElement;
        const errorDiv = formControl.querySelector('.invalid-feedback') || 
                        createErrorDiv();
        formControl.appendChild(errorDiv);
        input.classList.add('is-invalid');
        errorDiv.innerText = message;
    }
    
    function createErrorDiv() {
        const div = document.createElement('div');
        div.className = 'invalid-feedback';
        return div;
    }
});