package com.ernestas.danske.fxexhange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ernestas.danske.fxexhange.error.BadRequestException;
import com.ernestas.danske.fxexhange.service.ExchangeService;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeServiceTests {

  @Autowired
  ExchangeService exchangeService;

  @Test
  public void convertEURToDKKOk() {
    BigDecimal result = exchangeService.exchange("EUR", "DKK", BigDecimal.ONE);
    assertThat(result).isEqualTo(BigDecimal.valueOf(7.44));
  }

  @Test
  public void convertEURToDKKLargeAmountOk() {
    BigDecimal result = exchangeService.exchange("EUR", "DKK", BigDecimal.valueOf(135454L));
    assertThat(result).isEqualTo(BigDecimal.valueOf(1007777.76));
  }

  @Test
  public void convertEURtoUSDOk() {
    BigDecimal result = exchangeService.exchange("EUR", "USD", BigDecimal.ONE);
    assertThat(result).isEqualTo(BigDecimal.valueOf(1.12));
  }

  @Test
  public void convertEqualCurrenciesOk() {
    BigDecimal result = exchangeService.exchange("EUR", "EUR", BigDecimal.ONE);
    assertThat(result).isEqualTo(BigDecimal.valueOf(1));
  }

  @Test
  public void convertInvalidCurrenciesThrowsError() {
    assertThatThrownBy(() -> exchangeService.exchange("LTU", "LTU", BigDecimal.TEN))
        .isInstanceOf(BadRequestException.class)
        .hasMessageContaining("Provided currency is not present in the exchange");
  }


}
