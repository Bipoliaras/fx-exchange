package com.ernestas.danske.fxexhange;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExchangeControllersTests {

  @LocalServerPort
  protected int serverPort;

  @Before
  public void setup() {
    RestAssured.port = serverPort;
  }

  @Test
  public void whenGetExchangeRates_ok() {

    RestAssured.given()
        .pathParam("currencyFrom", "EUR")
        .pathParam("currencyTo", "DKK")
        .pathParam("amount", BigDecimal.ONE)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .log().all()
        .get("/rates/from/{currencyFrom}/to/{currencyTo}/amount/{amount}")
        .then()
        .log().all()
        .statusCode(200);

  }

  @Test
  public void whenGetExchangeRates_throwsBadRequestException() {

    RestAssured.given()
        .pathParam("currencyFrom", "EUR")
        .pathParam("currencyTo", "LTU")
        .pathParam("amount", BigDecimal.ONE)
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .log().all()
        .get("/rates/from/{currencyFrom}/to/{currencyTo}/amount/{amount}")
        .then()
        .log().all()
        .statusCode(400);

  }

}
