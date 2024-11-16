### Info:

Basic stateless token authentication in Spring Boot 3 (Spring & Spring Security 6)
Email && Password authorization
Simple example repo

---

### Tech stack:
    - Java 21
    - Spring Boot 3
    - Spring 6
    - Gradle
    - MySql (in docker)
    - Postman (for testing)

---

### Steps to run the project:

1. Clone the repository
2. Copy .env.example to .env
3. Run docker compose db:
    - you may need to delete `MYSQL_USER` from .env
    - then run docker compose db
    - then revert that change
4. Run application
5. Run healthcheck from Makefile to check if everything works fine

---

### Tests:
1. Import postman endpoints and test if everything works correctly
