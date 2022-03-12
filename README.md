# Creation of CRUD Rest APIS for Supermarkets   
## spring boot, spring data, hibernate, MySQL , JUnit testing and Rest API

Building a Restful system for supermarkets for admin users to list,
create, edit, delete, activate and deactivate supermarkets list.

## Requirements

1. Java - 1.8.x

2. Maven 

3. MySQL  

4. JPA

5. Spring Boot  

6. JUnit testing

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/mohamedragab33/supermarket-adminstration.git
```

**2. Import Mysql database**

```bash
from schema folder Dump20220312.sql
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation


**4. Build and run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs By import  

```bash
 Collection for Supermarkets Api.postman_collection.json from 
```
Requests collection Folder on project 

## Explore Rest APIs By swagger 

#### from postman 
<localhost:8080/v2/api-docs>.
#### For viewing html version
 <http://localhost:8080/swagger-ui.html>.
 

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /admin/api/supermarkets                 >>For getting all Supermarket<<
      
    POST /admin/api/supermarket                 >>For creating new Supermarket<<
             as @RequestPart (arabicName,englishName,address,active,image)
    
    GET /admin/api/supermarket?id=              >>For getting Supermarket by id <<
    
    PUT /admin/api/supermarket?id=               >>For edit a Supermarket by id<<
      as @RequestPart (ID,arabicName,englishName,address,active,image)
      
    put /admin/api/supermarket/activate?id=       >>For activate a Supermarket by id <<
    
    put /admin/api/supermarket/deactivate?id=     >>For deactivate a Supermarket by id <<
    
    DELETE /admin/api/supermarket?id=       >>For delete a Supermarket by id <<

You can test them using postman or any other rest client.

## By: Mohamed Ragab
