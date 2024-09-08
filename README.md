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
