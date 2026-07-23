# Book Catalog API (Spring Boot)

CRUD backend for a library's book catalog with search by title or author.

## Tech Stack
- Java 17, Spring Boot 3.3.2
- Spring Data JPA + MySQL
- Lombok

## Setup

1. **Create the database** (or let it auto-create):
   ```sql
   CREATE DATABASE book_catalog_db;
   ```
2. **Update `src/main/resources/application.properties`** with your MySQL username/password.
3. **Run the app**:
   ```bash
   mvn spring-boot:run
   ```
   Runs on `http://localhost:8081` (different port so it can run alongside Todo Backend).

## Book Attributes
- `title` (required)
- `author` (required)
- `genre`
- `publicationYear`
- `availability` (boolean, defaults to `true`)

## API Endpoints

| Method | Endpoint                                  | Description                          |
|--------|--------------------------------------------|----------------------------------------|
| POST   | `/api/books`                               | Add a new book                        |
| GET    | `/api/books`                               | List all books                        |
| GET    | `/api/books/{id}`                          | Get a single book                     |
| PUT    | `/api/books/{id}`                          | Update a book                         |
| DELETE | `/api/books/{id}`                          | Delete a book                         |
| GET    | `/api/books/search?title=xxx`              | Search by title (partial match)       |
| GET    | `/api/books/search?author=yyy`             | Search by author (partial match)      |
| GET    | `/api/books/search?title=xxx&author=yyy`   | Search matching either title or author|

### Sample create request-
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "genre": "Programming",
  "publicationYear": 2008,
  "availability": true
}
```

## Notes
- Search is case-insensitive and matches partial strings (`ContainingIgnoreCase`), so "clean" matches "Clean Code".
- No authentication here since the task didn't ask for it — if you later want it, you can reuse the JWT setup pattern from your Todo Backend.
