
---

## ⚙️ Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **H2 / MySQL** (for persistence)

---

## 🧠 How It Works

This example shows how to:
- Define entities with **bidirectional relationships** (`@OneToMany` and `@ManyToOne`).
- Use **composite keys** via `@Embeddable` and `@EmbeddedId`.
- Manage cascading operations properly (persist, delete, etc.).
- Avoid common pitfalls like infinite recursion during JSON serialization.

---

## 🧰 How to Run the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/One_To_Many_Bidirectional_With_Key_Parameters.git
