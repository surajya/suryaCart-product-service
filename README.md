Remember that I want to update README whenever I add new features to SuryaCart.



# 🛒 SuryaCart – Product Service

The **SuryaCart Product Service** is a core microservice of the SuryaCart e-commerce platform.  
It manages **products and categories**, exposing REST APIs and lightweight static admin pages.

---

## 🚀 Features

### ✅ Product Management
- Create product
- Update product
- Delete product
- List products by category
- Validation using Jakarta Bean Validation

### ✅ Category Management
- Create category
- Update category
- Delete category
- List categories

### ✅ Admin UI (Static Pages)
- Product admin pages
- Category admin pages
- Category dropdown in product create/edit
- Built using **HTML + JavaScript (fetch API)**

---

## 🏗️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jakarta Validation
- Lombok
- Maven

---

suryaCart-product-service
├── src/main/java/com/suryacart/product
│ ├── controller
│ ├── service
│ ├── repository
│ ├── model
│ └── dto
│
├── src/main/resources
│ ├── static
│ │ ├── products
│ │ │ ├── index.html
│ │ │ ├── create.html
│ │ │ └── edit.html
│ │ └── categories
│ │ ├── index.html
│ │ ├── create.html
│ │ └── edit.html
│ └── application.yml
└── pom.xml


---

## 🔗 REST API Endpoints

### Product APIs
GET /api/products
GET /api/products/{id}
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}


### Category APIs
GET /api/categories
GET /api/categories/{id}
POST /api/categories
PUT /api/categories/{id}
DELETE /api/categories/{id}


---

## 🖥️ Admin UI URLs
http://localhost:8080/products/index.html

http://localhost:8080/categories/index.html


---

## 🗄️ Database Design

- One product belongs to one category
- One category can have many products
- JPA relationships handled with proper serialization to avoid infinite recursion

---

## ▶️ How to Run

### 1️⃣ Configure Database
Update `application.yml` with your PostgreSQL credentials.

### 2️⃣ Run Application
```bash
mvn spring-boot:run

3️⃣ Access Admin UI

Open browser:http://localhost:8080/products/index.html
🧠 Design Decisions

Static frontend keeps the service UI-agnostic

REST-first approach makes it easy to replace UI with React later

DTOs used to avoid entity exposure and serialization issues

Validation ensures data integrity at API boundaries

🔮 Future Enhancements

Spring Security (Admin/User roles)

API Gateway integration

Order & Cart service integration

Image upload support

Dockerization

## 📂 Project Structure

