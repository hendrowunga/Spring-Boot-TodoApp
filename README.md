# REST API TODOAPP

## USER

### Register a new user
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/register`
    - **Method**: `POST`
    - **EndPoint**: `("/register")`
    - **Description**: Registers a new user in the system. The request body should contain the user's details (e.g., email, password, etc.).
    - **Request Body Example**:
        ```json
        {
      "firstname": "Hendro",
      "lastname": "wunga",
      "email": "wunga@gmail.com",
      "username": "hendrowunga",
      "password": "password123"
        }
        ```
  - **Successful Response**: HTTP 200 Accepted
![](picture/register.png)

### Email
- **URL**: `localhost:1080`
    - Once users register, they will receive an activation email to verify their email address and activate their account. This email contains a unique activation code, which must be used to activate the account.
  ![](picture/email.png)

### Activate user account
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/activate-account?token=355034`
    - **Method**: `GET`
    - **EndPoint**: `("/activate-account")`
    - **Description**: Activates a user's account using the token sent via email. This is required after the registration process to verify the user's email.
    - **Parameters**:
        - `token`: The token received in the user's email for activation.
    - **Successful Response**: HTTP 200 OK 
![](picture/activate-account.png)

### Login (Authenticate)
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/authenticate`
    - **Method**: `POST`
    - **EndPoint**: `("/authenticate")`
    - **Description**: Authenticates a user and returns a JWT token. This token is used to access protected resources.
    - **Request Body Example**:
    ```json
    {
      "email": "wunga@gmail.com",
      "username": "hendrowunga",
      "password": "password1234"
    }
    ```
    - **Response**:
    ```json
    {
      "token": "eyJhbGciOiJIUzM4NCJ9.eyJmdWxsTmFtZSI6IkhlbmRybyBXdW5nYSIsInN1YiI6Ind1bmdhQGdtYWlsLmNvbSIsImlhdCI6MTcyNTgxMjUzNCwiZXhwIjoxNzI1ODk4OTM0LCJhdXRob3JpdGllcyI6WyJVU0VSIl19.EnThtLDO60Mzek3B6zwtNBz8EqkerBoUNvs8mKzFEwiO4DpDOLd4VlRjCFHoRIBH"
    }
    ```
    - **Successful Response**: HTTP 200 OK 
![](picture/login.png)


## TODO

### Created Todo
- **URL**: `http://localhost:8089/todos`
  - **Method**: `POST`
  - **Description**: Creating a new Todo
  - **Request Body Example**:
      ```json
      {
    "title": "Belajar  Java"
    }
      ```
  - **Successful Response**: HTTP 200 Created 
    ```json
    {
    "id": 5,
    "title": "Belajar  Java",
    "completed": false
    }
      ```
    ![](picture/created.png)


### All Todo
- **URL**: `http://localhost:8089/todos`
  - **Method**: `GET`
  - **Description**: take a list of all Todos.
  - **Successful Response**: HTTP 200 OK 
    ```json
    {
    [
    {
        "id": 2,
        "title": "Belajar Spring Boot",
        "completed": false
    },
    {
        "id": 3,
        "title": "Belajar Spring Laravel",
        "completed": false
    },
    {
        "id": 4,
        "title": "Belajar  HTML",
        "completed": false
    },
    {
        "id": 5,
        "title": "Belajar  Java",
        "completed": false
    }
    ]
    
    }
      ```
    ![](picture/GetAll.png)

### Get By ID
- **URL**: `http://localhost:8089/todos/1`
  - **Method**: `GET`
  - **Description**: Retrieving Todo by ID, and this is retrieving by ID if there is none.
  - **Successful Response**: HTTP 400 NOT FOUND
  
    ![](picture/getbyid.png)

  - **URL**: `http://localhost:8089/todos/5`
    - **Method**: `GET`
    - **Description**: Retrieving Todo by ID, and this is retrieving by ID if there is none.
    - **Successful Response**: HTTP 200 OK
        ```json
      {
      "id": 5,
      "title": "Belajar  Java",
      "completed": false
      }
      ```
    ![](picture/byid.png)

### Update Todo
- **URL**: `http://localhost:8089/todos/3`
  - **Method**: `PUT`
  - **Description**: Update existing Todo based on ID.
  - **Successful Response**: HTTP 200 OK
      ```json
      {
      "id": 3,
      "title": "Belajar Laravel)",
      "completed": false
      }
      ```

    ![](picture/update.png)


### Delete Todo
- **URL**: `http://localhost:8089/todos/3`
  - **Method**: `DELETE`
  - **Description**: Delete Todo by ID.
  - **Successful Response**: HTTP 204 No Content

    ![](picture/delete.png)



### Completed Todo
- **URL**: `http://localhost:8089/todos/3`
  - **Method**: `PATCH`
  - **Description**: Mark Todo as complete.
  - **Successful Response**: HTTP 204 No Content

    ![](picture/completed.png)

## Configuration
- **docker-compose.yml**: 
  ```
  services:
    postgres:
      container_name: postgres-sql-todo
      image: postgres
      environment:
        POSTGRES_USER: postgres
        POSTGRES_PASSWORD: postgres
        PGDATA: /var/lib/postgresql/data
      volumes:
        - postgres:/data/postgres
      ports:
        - 5432:5432
      networks:
        - spring-demo
      restart: unless-stopped
    mail-dev:
      container_name: mail-dev-todo
      image: maildev/maildev
      ports:
        - 1080:1080
        - 1025:1025
  networks:
    spring-demo:
    driver: bridge

  volumes:
    postgres:
    driver: local
  ```

- **application-dev.yml**:
  ```
  spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/todoApp
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: ali
    password: ali
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enabled: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
  application:
    security:
      jwt:
        secret-key: 404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
        expiration: 86400000 # a day
    mailing:
      frontend:
        activation-url: http://localhost:4200/activate-account

  server:
    port: 8088
  ```
- **application.yml**:
  ```
  spring:
  profiles:
    active: dev
  springdoc:
    default-produces-media-type: application/json
  server:
    servlet:
      context-path: /api/v1/
  ```



