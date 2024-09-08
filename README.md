# REST API TODOAPP

## USER

### Register a new user
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/register`
    - **Method**: `POST`
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
  - **Successful Response**: HTTP 200 OK (User registered successfully)
![](picture/register.png)

### Email
- **URL**: `localhost:1080`
    - Once users register, they will receive an activation email to verify their email address and activate their account. This email contains a unique activation code, which must be used to activate the account.
  ![](picture/email.png)

### Activate user account
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/activate-account?token=355034`
    - **Method**: `GET`
    - **Description**: Activates a user's account using the token sent via email. This is required after the registration process to verify the user's email.
    - **Parameters**:
        - `token`: The token received in the user's email for activation.
    - **Successful Response**: HTTP 200 OK (Account activated successfully)
![](picture/activate-account.png)

### Login (Authenticate)
- **URL**: `{{api_url}}:{{api_port}}/api/v1/auth/authenticate`
    - **Method**: `POST`
    - **Description**: Authenticates a user and returns a JWT token. This token is used to access protected resources.
    - **Request Body Example**:
    ```json
    {
      "email": "wunga@gmail.com",
      "username": "hendrowunga",
      "password": "password1234"
    }
    ```
    - **Response Example**:
    ```json
    {
      "token": "eyJhbGciOiJIUzM4NCJ9.eyJmdWxsTmFtZSI6IkhlbmRybyBXdW5nYSIsInN1YiI6Ind1bmdhQGdtYWlsLmNvbSIsImlhdCI6MTcyNTgxMjUzNCwiZXhwIjoxNzI1ODk4OTM0LCJhdXRob3JpdGllcyI6WyJVU0VSIl19.EnThtLDO60Mzek3B6zwtNBz8EqkerBoUNvs8mKzFEwiO4DpDOLd4VlRjCFHoRIBH"
    }
    ```
    - **Successful Response**: HTTP 200 OK (Authenticated successfully and JWT token returned)
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
  
    ![](picture/GetById.png)

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




