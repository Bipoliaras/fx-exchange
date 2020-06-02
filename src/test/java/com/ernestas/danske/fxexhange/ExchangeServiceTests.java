package com.ernestas.danske.fxexhange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ernestas.danske.fxexhange.domain.ExchangeRequest;
import com.ernestas.danske.fxexhange.exception.BadRequestException;
import com.ernestas.danske.fxexhange.domain.FxCurrency;
import com.ernestas.danske.fxexhange.service.CurrencyExchanger;
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
  CurrencyExchanger exchangeService;

  @Test
  public void convertEURToDKKOk() {
    BigDecimal result = exchangeService.exchange(ExchangeRequest.builder()
        .fxCurrencyFrom(FxCurrency.EUR)
        .fxCurrencyTo(FxCurrency.DKK)
        .amount(BigDecimal.ONE)
        .build());
    assertThat(result).isEqualTo(BigDecimal.valueOf(7.44));
  }

  @Test
  public void convertEURToDKKLargeAmountOk() {
    BigDecimal result = exchangeService.exchange(ExchangeRequest.builder()
        .fxCurrencyFrom(FxCurrency.EUR)
        .fxCurrencyTo(FxCurrency.DKK)
        .amount(BigDecimal.valueOf(135454L))
        .build());
    assertThat(result).isEqualTo(BigDecimal.valueOf(1007777.76));
  }

  @Test
  public void convertEURtoUSDOk() {
    BigDecimal result = exchangeService.exchange(ExchangeRequest.builder()
        .fxCurrencyFrom(FxCurrency.EUR)
        .fxCurrencyTo(FxCurrency.USD)
        .amount(BigDecimal.ONE)
        .build());
    assertThat(result).isEqualTo(BigDecimal.valueOf(1.12));
  }

  @Test
  public void convertEqualCurrenciesOk() {
    BigDecimal result = exchangeService.exchange(ExchangeRequest.builder()
        .fxCurrencyFrom(FxCurrency.EUR)
        .fxCurrencyTo(FxCurrency.EUR)
        .amount(BigDecimal.ONE)
        .build());
    assertThat(result).isEqualTo(BigDecimal.valueOf(1));
  }

  @Test
  public void convertInvalidCurrenciesThrowsError() {
    assertThatThrownBy(() -> exchangeService.exchange(ExchangeRequest.builder()
        .fxCurrencyTo(FxCurrency.DKK)
        .fxCurrencyFrom(FxCurrency.LTU)
        .amount(BigDecimal.TEN)
        .build()))
        .isInstanceOf(BadRequestException.class)
        .hasMessageContaining("Provided currency is not present in the exchange");
  }
}
