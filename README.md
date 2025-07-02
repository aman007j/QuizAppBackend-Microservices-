# QuizApp (Monolithic Version)

This is a monolithic QuizApp built with Java, Spring Boot, and Maven. The application provides RESTful APIs for managing quizzes, questions, and user participation.

**Note:** This project will be refactored into a microservices architecture in the future.

## Features

- Create, update, and delete quizzes
- Add and manage questions
- User participation and scoring

## API Endpoints

| Method | Endpoint                | Description                    |
|--------|------------------------|--------------------------------|
| GET    | /api/quizzes           | List all quizzes               |
| POST   | /api/quizzes           | Create a new quiz              |
| GET    | /api/quizzes/{id}      | Get quiz by ID                 |
| PUT    | /api/quizzes/{id}      | Update quiz by ID              |
| DELETE | /api/quizzes/{id}      | Delete quiz by ID              |
| GET    | /api/questions         | List all questions             |
| POST   | /api/questions         | Add a new question             |
| GET    | /api/questions/{id}    | Get question by ID             |
| PUT    | /api/questions/{id}    | Update question by ID          |
| DELETE | /api/questions/{id}    | Delete question by ID          |
| POST   | /api/quiz/{id}/submit  | Submit answers for a quiz      |

## Getting Started

1. Clone the repository.
2. Build with Maven: `mvn clean install`
3. Run the app: `mvn spring-boot:run`

---

This project will be migrated to a microservices architecture in upcoming releases.
