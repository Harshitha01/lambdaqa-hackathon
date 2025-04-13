# LambdaTest QA Hackathon 🚀
### Project: `lambdaqa-hackathon`

This project is a submission for the **"Break the Code: LambdaTest Edition" Hackathon Challenge**, focused on showcasing test automation capabilities using **Selenium with Java and TestNG**.

---

## 📌 Test Cases Implemented

### ✅ Test Case 1: Login Automation
- **URL:** [https://the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login)
- ✅ Valid credentials: `tomsmith / SuperSecretPassword!` → Assert success message
- ❌ Invalid credentials: Assert error message

### ✅ Test Case 2: JavaScript Alert Handling
- **URL:** [https://the-internet.herokuapp.com/javascript_alerts](https://the-internet.herokuapp.com/javascript_alerts)
- 📌 Handled:
  - JavaScript Alert
  - JavaScript Confirm
  - JavaScript Prompt

---

## 🧰 Tech Stack

- **Language:** Java
- **Automation Framework:** Selenium WebDriver
- **Test Runner:** TestNG
- **Build Tool:** Maven (`pom.xml`)
- **Reporting:** TestNG default reports (can be enhanced with ExtentReports)
- **Execution:** Local / LambdaTest
- **Parallel Execution & Retry Logic:** Configured via TestNG XML & listeners

---

## 🔒 Security & Best Practices

- Sensitive data (like credentials) is handled securely via `config.properties`
- Code follows Page Object Model (POM) for reusability
- Retry analyzer implemented to rerun failed tests

---

## 🧪 How to Run

```bash
# Clone the repo
git clone https://github.com/your-username/lambdaqa-hackathon.git
cd lambdaqa-hackathon

# Run tests using Maven
mvn clean test
```

---

## ☁️ LambdaTest Integration

- [ ] Add your LambdaTest credentials in the `config.properties` file (never hardcoded)
- [ ] LambdaTest test ID: *<to be added upon execution>*

---

## ⚠️ Disclaimer

> This project was created solely for the purpose of participating in the LambdaTest Hackathon. Unauthorized use or distribution of this code for commercial purposes is **not permitted**.

---

## 🏆 Author

**Harshitha**  
[LinkedIn](#) | [GitHub](https://github.com/your-username)
