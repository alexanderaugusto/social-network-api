<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/alexanderaugusto/social-network-api?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/alexanderaugusto/social-network-api">
  
  <a href="https://github.com/alexanderaugusto/social-network-api/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/alexanderaugusto/social-network-api">
  </a>
  
   <a href="https://github.com/alexanderaugusto/social-network-api/actions">
    <img alt="GitHub Actions" src="https://github.com/alexanderaugusto/social-network-api/workflows/social-network-api/badge.svg">
  </a>
    

    
   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">  
</p>

<h4 align="center"> 
	✅ Lazy API - Done ✅
</h4>

<p align="center">
 <a href="#-about">About</a> •
 <a href="#-architecture">Architecture</a> • 
  <a href="#-setup">Setup</a> • 
 <a href="#-technologies">Technologies</a> • 
 <a href="#-authors">Authors</a> • 
 <a href="#-license">License</a>
</p>


## 📝 About

This API contains every business rule for a basic social network. To make it clear what it can do, below I present a UML containing all the functionality present in the application. As we can see, the user will be able to create an account, do a login and access all the functionalities of the application, such as: Post a publication, search for other people to follow, view their profile and react and comment on publications.

![Lazy UML](https://user-images.githubusercontent.com/51683816/117968007-19d7ae00-b2fc-11eb-8a6b-d58c4774ce32.jpg)

---


## 💻 Architecture

Here is the summary of the entire architecture of the application, as we can see, I have a React application running on [Vercel](http://vercel.com/) that will always be accessed by the end user. In addition, this React application accesses my API, developed in Spring Boot running in a docker container on Heroku. This API has access to a PostgreSQL database where all application data is stored, and also uploads images to Cloudinary, so that they can be accessed in the cloud. Finally, I have an application using [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin), which monitors my application.

![Lazy Arch](https://user-images.githubusercontent.com/51683816/117969484-d716d580-b2fd-11eb-8ce7-685c93650d0f.png)

## 🚀 Setup

1. Download [Java Eclipse IDE](https://www.eclipse.org/downloads/)
2. Clone this repository: git clone https://github.com/alexanderaugusto/social-network-api.git
3. Open the app on Eclipse IDE
4. Run the project and access http://localhost:8080

## 🛠 Technologies

The following tools were used in the construction of the project:

#### **API**  ([Spring Boot](https://spring.io/projects/spring-boot))
- **Dependencies**:
  -   **[spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)**
  -   **[spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)**
  -   **[spring-boot-devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)**
  -   **[spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)**
  -   **[h2](https://mvnrepository.com/artifact/com.h2database/h2)**
  -   **[spring-boot-starter-validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)**
  -   **[spring-boot-starter-cache](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-cache)**
  -   **[spring-boot-starter-security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)**
  -   **[jjwt](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)**
  -   **[springfox-swagger2](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2)**
  -   **[springfox-swagger-ui](https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui)**
  -   **[cucumber-java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)**
  -   **[cucumber-junit](https://mvnrepository.com/artifact/info.cukes/cucumber-junit)**
  -   **[spring-security-test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test)**
  -   **[postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)**
  -   **[cloudinary-http44](https://mvnrepository.com/artifact/com.cloudinary/cloudinary-http44)**
  -   **[spring-boot-starter-actuator](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator)**
  -   **[spring-boot-admin-starter-client](https://mvnrepository.com/artifact/de.codecentric/spring-boot-admin-starter-client)**

---

## 🦸 Authors

<table>
  <tr>
    <td align="center"><a href="https://github.com/alexanderaugusto/"><img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/51683816?v=4" width="100px;" alt=""/><br /><sub><b>Alexander Augusto</b></sub></a>
    </td>      
  </tr>
</table>

---

## 📝 License

This project is under license [MIT](./LICENSE).
