# Language Translator V3 🌍

A robust web-based language translation application built with Spring Boot and modern web technologies.

## 🌟 Features

- Real-time text translation
- Support for multiple languages including:
  - English
  - Spanish
  - French
  - German
  - Hindi
  - Japanese
  - Korean
  - Chinese
- Secure user authentication
- Responsive design
- Intuitive user interface
- Language swap functionality
- Error handling with user feedback

## 🛠️ Technologies Used

- **Backend:**
  - Java 17
  - Spring Boot 3.x
  - Spring Security
  - Spring Data JPA
  - H2 Database

- **Frontend:**
  - HTML5
  - Bootstrap 5
  - JavaScript (Async/Await)
  - Thymeleaf

## 📋 Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6+
- Git

## 🚀 Getting Started

1. **Clone the repository**
```bash
git clone https://github.com/deepak2noob/language_translatorV3.git
cd language_translatorV3
```

2. **Build the project**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring-boot:run
```

4. **Access the application**
- Open your browser and navigate to `http://localhost:8080`
- Default credentials:
  - Username: `admin`
  - Password: `admin123`

## 💡 Usage

1. Log in with your credentials
2. Select source and target languages
3. Enter text to translate
4. Click the "Translate" button
5. View the translated text in the result area

## 🔒 Security Features

- User authentication
- Password encryption
- Session management
- CSRF protection
- Secure form submission

## 📁 Project Structure

```
language_translatorV3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/translator/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── pom.xml
└── README.md
```

## 🔧 Configuration

The application can be configured through `application.properties`:

```properties
server.port=8080
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:translatordb
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 🐛 Bug Reports

If you discover any bugs, please create an issue in the GitHub repository with:
- Detailed description of the bug
- Steps to reproduce
- Expected behavior
- Screenshots (if applicable)

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ✨ Author

**Deepak** - [deepak2noob](https://github.com/deepak2noob)

## 🙏 Acknowledgments

- MyMemory Translation API for providing translation services
- Spring Boot community for excellent documentation
- Bootstrap team for responsive design components

---
⭐ Star this repository if you find it helpful!

_Last updated: 2025-01-05_
