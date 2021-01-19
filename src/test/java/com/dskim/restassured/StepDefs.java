package com.dskim.restassured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class StepDefs {

    // Пользователь по имени Johnny
    //      id = 10
    //      username = Johnny
    //      firstName = John
    //      lastName = Doe
    //      email = johndoe@john.doe
    //      password = johndoe
    //      phone = 123456
    //      userStatus = 0

    @Test
    // Кейс 1. Создание пользователя по имени Johnny
    // POST ​/user
    // ОР: 200 OK
    public void userJohnnyCreate() {
        RestAssured.
                given().
                when().
                header("Content-Type", "application/json").
                body(
                        new JSONObject().
                                put("id", 20).
                                put("username", "Johnny").
                                put("firstName", "John").
                                put("lastName", "Doe").
                                put("email", "johndoe@john.doe").
                                put("password", "johndoe").
                                put("phone", "123456789").
                                put("userStatus", 0).
                                toString()
                ).
                post("https://petstore.swagger.io/v2/user").
                then().
                spec(
                        new ResponseSpecBuilder().
                                expectStatusCode(200).
                                expectBody("code", equalTo(200)).
                                build()
                );
    }

    @Test
    // Кейс 2. Получение пользователя по имени Johnny
    // GET ​/user​/{username}
    // ОР: 200 OK + проверка возвращаемых параметров в body
    public void userJohnnyGet() {
        RestAssured.
                given().
                when().
                get("https://petstore.swagger.io/v2/user/Johnny").
                then().
                spec(
                        new ResponseSpecBuilder().
                                expectStatusCode(200).
                                expectBody("id", equalTo(20)).
                                expectBody("username", equalTo("Johnny")).
                                expectBody("firstName", equalTo("John")).
                                expectBody("lastName", equalTo("Doe")).
                                expectBody("email", equalTo("johndoe@john.doe")).
                                expectBody("password", equalTo("johndoe")).
                                expectBody("phone", equalTo("123456789")).
                                expectBody("userStatus", equalTo(0)).
                                build()
                );
    }

    // Пользователь по имени Bobby
    //      id = 50
    //      username = Bobby
    //      firstName = Bob
    //      lastName = Doe
    //      userStatus = 0

    @Test
    // Кейс 3. Создание пользователя по имени Bobby
    // POST ​/user
    // ОР: 200 OK
    public void userBobby() {
        RestAssured.
                given().
                when().
                header("Content-Type", "application/json").
                body(
                        new JSONObject().
                                put("id", 40).
                                put("username", "Bobby").
                                put("firstName", "Bob").
                                put("lastName", "Doe").
                                put("userStatus", 10).toString()
                ).
                post("https://petstore.swagger.io/v2/user").
                then().
                spec(
                        new ResponseSpecBuilder().
                                expectStatusCode(200).
                                expectBody("code", equalTo(200)).
                                build()
                );
    }

    @Test
    // Кейс 4. Получение пользователя по имени Bobby
    // GET ​/user​/{username}
    // ОР: 200 OK + проверка возвращаемых параметров в body
    public void userBobbyGet() {
        RestAssured.
                given().
                when().
                get("https://petstore.swagger.io/v2/user/Bobby").
                then().
                spec(
                        new ResponseSpecBuilder().
                                expectStatusCode(200).
                                expectBody("id", equalTo(40)).
                                expectBody("username", equalTo("Bobby")).
                                expectBody("firstName", equalTo("Bob")).
                                expectBody("lastName", equalTo("Doe")).
                                expectBody("email", equalTo(null)).
                                expectBody("password", equalTo(null)).
                                expectBody("phone", equalTo(null)).
                                expectBody("userStatus", equalTo(10)).
                                build()
                );
    }

    // Пользователь по имени Anny

    @Test
    // Кейс 5. Получение пользователя по имени Anny
    // GET ​/user​/{username}
    // ОР: 404 Not Found
    public void userAnny() {
        RestAssured.
                given().
                when().
                get("https://petstore.swagger.io/v2/user/Anny").
                then().
                spec(
                        new ResponseSpecBuilder().
                                expectStatusCode(404).
                                build()
                );
    }
}