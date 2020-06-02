package com.ernestas.danske.fxexchange;

import com.ernestas.danske.fxexchange.domain.ExchangeRequest;
import com.ernestas.danske.fxexchange.domain.FxCurrency;
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
        .body(ExchangeRequest.builder()
            .fxCurrencyFrom(FxCurrency.EUR)
            .fxCurrencyTo(FxCurrency.DKK)
            .amount(BigDecimal.ONE)
            .build())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .log().all()
        .post("/rates/exchange")
        .then()
        .log().all()
        .statusCode(200);

  }

  @Test
  public void whenGetExchangeRates_throwsBadRequestException() {

    RestAssured.given()
        .body(ExchangeRequest.builder()
            .fxCurrencyFrom(FxCurrency.EUR)
            .fxCurrencyTo(FxCurrency.LTU)
            .amount(BigDecimal.ONE)
            .build())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .log().all()
        .post("/rates/exchange")
        .then()
        .log().all()
        .statusCode(400);

  }

  @Test
  public void whenGetExchangeRatesWithInvalidAmount_throwsBadRequestException() {

    RestAssured.given()
        .body(ExchangeRequest.builder()
            .fxCurrencyFrom(FxCurrency.EUR)
            .fxCurrencyTo(FxCurrency.LTU)
            .amount(new BigDecimal("0.5"))
            .build())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .log().all()
        .post("/rates/exchange")
        .then()
        .log().all()
        .statusCode(400);

  }

}
