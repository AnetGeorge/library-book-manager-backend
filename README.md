

# 📚 Library Book Manager Backend

A simple Spring Boot backend project to manage a library's book collection. It supports CRUD operations and advanced filters such as searching by genre, author, published year, and more.

---

## 🚀 Features

- ✅ Add a new book to the library
- 📖 View all books or a single book by ID
- ✏️ Update book details
- ❌ Delete a book
- 🔎 Search filters:
  - Books by **Author**
  - Books by **Genre**
  - Books published **before**, **after**, or **in** a specific year

---

## 🗃️ ER Diagram

```plaintext
+------------+
|   Book     |
+------------+
| id         |
| title      |
| author     |
| genre      |
| published  |
+------------+

```
---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (In-memory)**
- **Maven**
- **Postman** (for API testing)

---

## 📦 API Endpoints

| Method | Endpoint                   | Description                      |
|--------|----------------------------|----------------------------------|
| POST   | `/api/books`               | Add a new book                   |
| GET    | `/api/books`               | Get all books                    |
| GET    | `/api/books/{id}`          | Get a book by ID                 |
| PUT    | `/api/books/{id}`          | Update book by ID                |
| DELETE | `/api/books/{id}`          | Delete book by ID                |
| GET    | `/api/books/search/author?name=...`  | Get books by author      |
| GET    | `/api/books/search/genre?name=...`   | Get books by genre       |
| GET    | `/api/books/search/year?year=...`    | Get books published in year |
| GET    | `/api/books/search/before?year=...`  | Books before a year       |
| GET    | `/api/books/search/after?year=...`   | Books after a year        |

---

## 🧪 Sample Book Object (JSON)

```json
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Classic",
  "published": 1925
}

