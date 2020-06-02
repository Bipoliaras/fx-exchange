package com.ernestas.danske.fxexchange.api;

import com.ernestas.danske.fxexchange.domain.ExchangeRequest;
import com.ernestas.danske.fxexchange.domain.CurrencyExchanger;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class ExchangeController {

  private final CurrencyExchanger exchangeService;

  public ExchangeController(CurrencyExchanger exchangeService) {
    this.exchangeService = exchangeService;
  }

  @PostMapping("/exchange")
  public BigDecimal exchange(
     @RequestBody @Valid ExchangeRequest exchangeRequest) {
    return exchangeService.exchange(exchangeRequest);
  }

}
