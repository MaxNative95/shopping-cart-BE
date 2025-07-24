# 🧱 Backend Microservices -  Sistema E-commerce 

Este repositorio contiene los microservicios backend del sistema de ecommerce, desarrollados con **Spring Boot** y organizados bajo una arquitectura de microservicios. Cada servicio tiene su propósito bien definido y se comunica mediante HTTP REST.

---

## 📦 Estructura del Proyecto
/carrito-backend
│
├── authapi/ → Servicio de autenticación (login con JWT)
├── productsapi/ → Gestión del catálogo de productos
├── ordersapi/ → Creación y consulta de órdenes
└── docker-compose.yml → Orquesta los servicios y bases de datos 


---

## 🔧 Microservicios

### 1. **authapi**
- ⚙️ Framework: Spring Boot
- 🔐 Autenticación con JWT
- 📄 Endpoints:
    - `POST /auth/login`: Login de usuario (retorna JWT)
- 🗃 Base de datos: PostgreSQL (usuario/contraseña configurables)
- 📍 Puerto: `8081`

### 2. **productsapi**
- ⚙️ Framework: Spring Boot
- 🛒 Gestión de productos: listar, crear, eliminar
- 📄 Endpoints:
    - `GET /products`: Listar productos
    - `POST /products`: Crear un producto
- 🗃 Base de datos: PostgreSQL
- 📍 Puerto: `8082`

### 3. **ordersapi**
- ⚙️ Framework: Spring Boot
- 📦 Gestión de órdenes (crear y consultar)
- 📄 Endpoints:
    - `GET /orders`: Listar todas las órdenes
    - `POST /orders`: Crear una nueva orden
- 🗃 Base de datos: PostgreSQL
- 📍 Puerto: `8083`

---

## 🐳 Docker & Docker Compose

Para facilitar el desarrollo y ejecución local, todo el sistema puede ser levantado con un solo comando usando Docker Compose.

### Requisitos:
- Docker
- Docker Compose

### Comando para levantar el sistema:

```bash
docker-compose up --build
Este comando:

Compila y levanta los 3 microservicios

Levanta 3 contenedores de PostgreSQL independientes (uno por servicio)

Expone los puertos:

| Servicio    | Puerto                                    |
| ----------- | ----------------------------------------- |
| authapi     | 8081                                      |
| productsapi | 8082                                      |
| ordersapi   | 8083                                      |
| PostgreSQLs | 5432, 5433, 5434 (uno para cada servicio) |

```
🔒 Seguridad

El authapi genera tokens JWT al hacer login.

Los otros microservicios validan el token usando un filtro de autenticación.

El frontend debe incluir el token en el header:
Authorization: Bearer <jwt_token>

🗃 Variables de entorno

Cada microservicio tiene su propio archivo .env o variables configuradas en application.properties.

Ejemplo (authapi/src/main/resources/application.properties):

spring.datasource.url=jdbc:postgresql://auth-db:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=postgres
jwt.secret=mysecretkey


📂 Repositorio relacionado

Frontend del Carrito (React)