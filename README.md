# Simple Management Employee API

## Tech Stack

* Java / Spring Boot
* Spring Web MVC
* Spring Security
* Spring Data (pagination)
* springdoc-openapi

---

## Authentication

Most endpoints are secured by Spring Security.

If using **HTTP Basic** during development:

* Username & password are required
* Swagger endpoints can be whitelisted in `SecurityFilterChain`

---

## Base URL

```
http://localhost:8080
```

---

## API Endpoints

### Users

| Method | Endpoint                | Description           |
| ------ | ----------------------- | --------------------- |
| GET    | `/api/users`            | Get all users         |
| POST   | `/api/users`            | Create a new user     |
| GET    | `/api/users/{username}` | Find user by username |

**DTOs**

* `UserCreateDTO`: `username`, `password`, `role`
* `UserResponseDTO`: `username`, `enabled`, `roles[]`

---

### Employees

| Method | Endpoint              | Description           |
| ------ | --------------------- | --------------------- |
| GET    | `/api/employees`      | Get all employees     |
| POST   | `/api/employees`      | Create a new employee |
| GET    | `/api/employees/{id}` | Get employee by ID    |
| PUT    | `/api/employees/{id}` | Update employee       |
| PATCH  | `/api/employees/{id}` | Partial update        |
| DELETE | `/api/employees/{id}` | Delete employee       |

**DTOs**

* `EmployeeCreateUpdateDTO`: `name`, `email`, `employeeStatus`
* `EmployeePatchDTO`

---

### Skills

| Method | Endpoint      | Description    |
| ------ | ------------- | -------------- |
| GET    | `/api/skills` | Get all skills |
| POST   | `/api/skills` | Add new skill  |

**DTO**

* `SkillCreateDTO`: `name`, `category (FRONTEND | BACKEND | DEVOPS)`

---

### Projects

| Method | Endpoint             | Description                  |
| ------ | -------------------- | ---------------------------- |
| GET    | `/api/projects`      | Get projects with pagination |
| POST   | `/api/projects`      | Create project               |
| GET    | `/api/projects/{id}` | Get project by ID            |
| PUT    | `/api/projects/{id}` | Update project               |
| PATCH  | `/api/projects/{id}` | Partial update               |

**DTOs**

* `ProjectCreateUpdateDTO`
* `ProjectPatchDTO`

---

### Employee – Skill

| Method | Endpoint                     | Description           |
| ------ | ---------------------------- | --------------------- |
| POST   | `/api/employees/{id}/skills` | Add skill to employee |

**DTO**

* `EmployeeSkillDTO`: `skillId`, `level`, `yearsExperience`

---

### Employee – Project

| Method | Endpoint                       | Description                        |
| ------ | ------------------------------ | ---------------------------------- |
| POST   | `/api/employees/{id}/projects` | Assign employee to project         |
| GET    | `/api/employees/projects`      | Get all employee-project relations |

**DTO**

* `EmployeeProjectCreateDTO`: `projectId`, `role`, `joinDate`

---

## Pagination

Some endpoints support pagination via `Pageable`:

```http
GET /api/projects?page=0&size=10&sort=name,asc
```

---

## Error Handling

* Standard HTTP status codes
* JSON error responses
* Exceptions are logged via AOP

---

## Running the Project

```bash
mvn clean spring-boot:run
```

Then open:

```
http://localhost:8080/swagger-ui/index.html
```
