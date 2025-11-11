# 🧩 Web Services com Spring Boot, JPA e Hibernate

Projeto desenvolvido com base no curso **Java COMPLETO - Programação Orientada a Objetos + Projetos**, ministrado pelo professor **Nélio Alves (DevSuperior)**.

O objetivo é construir uma aplicação **RESTful** com **Spring Boot**, **JPA/Hibernate** e **PostgreSQL**, implementando um modelo de domínio completo com operações CRUD, tratamento de exceções, perfis de ambiente e deploy na nuvem (Heroku).

---

## 🎯 Objetivos do Projeto

- Criar um projeto **Spring Boot Java**  
- Implementar um **modelo de domínio** completo  
- Estruturar camadas lógicas: **Resource**, **Service**, **Repository**  
- Configurar banco de dados de teste (**H2**)  
- Povoar o banco de dados com dados iniciais  
- Implementar operações **CRUD (Create, Retrieve, Update, Delete)**  
- Tratar exceções de forma personalizada  
- Preparar o projeto para **deploy no Heroku** (com PostgreSQL)

---

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em **camadas lógicas**:

```
com.example.project
├── entities        # Classes de modelo de domínio (User, Order, Product, etc.)
├── repositories    # Interfaces JPA Repository
├── services        # Regras de negócio e transações
├── resources       # Controladores REST (endpoints)
└── exceptions      # Tratamento de exceções customizadas
```

---

## 🧱 Modelo de Domínio

Entidades principais:
- **User**
- **Order**
- **OrderItem**
- **Product**
- **Category**
- **Payment**

Relacionamentos:
- `User` 1️⃣:N `Order`  
- `Order` 1️⃣:N `OrderItem`  
- `Product` N️⃣:M `Category` (via `@JoinTable`)  
- `Order` 1️⃣:1 `Payment`

---

## ⚙️ Configuração do Projeto

### Dependências principais (Maven)

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>

<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

---

## 🧩 Perfis de Ambiente

### `application.properties`
```properties
spring.profiles.active=test
spring.jpa.open-in-view=true
```

### `application-test.properties`
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### `application-dev.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot_course
spring.datasource.username=postgres
spring.datasource.password=1234567
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 💾 Banco de Dados

- **Teste**: Banco em memória H2  
- **Desenvolvimento**: PostgreSQL local  
- **Produção**: PostgreSQL no Heroku  

Scripts SQL podem ser exportados via **pgAdmin**:
> Tools → Backup → Format: Plain → Only Schema: YES

---

## 🧰 Funcionalidades Implementadas

✅ CRUD completo de usuários, pedidos e produtos  
✅ Relacionamentos entre entidades com JPA/Hibernate  
✅ Tratamento de exceções customizadas (`ResourceNotFoundException`, `DatabaseException`)  
✅ Padrões RESTful com respostas HTTP adequadas  
✅ Perfis de execução separados (test, dev, prod)  
✅ Deploy automático no **Heroku**

---

## 🚀 Deploy no Heroku

### Etapas principais:

1. Criar app no Heroku e provisionar PostgreSQL  
2. Configurar variáveis de ambiente:
   ```bash
   DATABASE_URL
   JWT_SECRET
   JWT_EXPIRATION
   ```
3. Criar `application-prod.properties`
   ```properties
   spring.datasource.url=${DATABASE_URL}
   spring.jpa.hibernate.ddl-auto=none
   jwt.secret=${JWT_SECRET}
   jwt.expiration=${JWT_EXPIRATION}
   ```
4. Fazer deploy:
   ```bash
   heroku git:remote -a nome-da-sua-app
   git add .
   git commit -m "Deploy app to Heroku"
   git push heroku main
   ```

---

## 🧠 Conceitos Envolvidos

- Spring Boot  
- Spring Data JPA  
- Hibernate ORM  
- RESTful API  
- Maven  
- Perfis de aplicação  
- H2 Database  
- PostgreSQL  
- Heroku Deploy  

---

## 📚 Referência

- Curso: [Java COMPLETO - DevSuperior (Nélio Alves)](https://devsuperior.com.br)  
- Projeto base:  
  - https://github.com/acenelio/workshop-springboot2-jpa  
  - https://github.com/acenelio/workshop-springboot3-jpa

---

## 👨‍💻 Autor

**Rafael Kmohan Paulino Patricio**  
📧 Contato: [LinkedIn](https://www.linkedin.com/in/)  
💻 GitHub: [r121990](https://github.com/r121990)

---

## 📝 Licença

Este projeto é distribuído sob a licença **MIT**.  
Sinta-se livre para usar, estudar e modificar.
