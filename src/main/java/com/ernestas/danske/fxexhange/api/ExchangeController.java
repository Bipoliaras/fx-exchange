package com.ernestas.danske.fxexhange.api;

import com.ernestas.danske.fxexhange.service.ExchangeService;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class ExchangeController {

  private final ExchangeService exchangeService;

  public ExchangeController(ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  @GetMapping("/from/{currencyFrom}/to/{currencyTo}/amount/{amount}")
  public BigDecimal exchange(
      @PathVariable("currencyFrom") String currencyFrom,
      @PathVariable("currencyTo") String currencyTo,
      @PathVariable("amount") BigDecimal amount) {
    return exchangeService.exchange(currencyFrom, currencyTo, amount);
  }

}
