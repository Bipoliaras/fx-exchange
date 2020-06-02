package com.ernestas.danske.fxexhange.service;

import com.ernestas.danske.fxexhange.domain.ExchangeRequest;
import com.ernestas.danske.fxexhange.foreignexchange.FxRates;
import com.ernestas.danske.fxexhange.exception.BadRequestException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

  public BigDecimal exchange(ExchangeRequest exchangeRequest) {

    if(!FxRates.rates.containsKey(exchangeRequest.getFxCurrencyFrom()) || !FxRates.rates.containsKey(exchangeRequest.getFxCurrencyTo())) {
      throw new BadRequestException("Provided currency is not present in the exchange");
    }

    if(exchangeRequest.getFxCurrencyFrom().equals(exchangeRequest.getFxCurrencyTo())) {
      return BigDecimal.ONE;
    }

    return FxRates.rates.get(exchangeRequest.getFxCurrencyFrom())
        .divide(FxRates.rates.get(exchangeRequest.getFxCurrencyTo()), RoundingMode.HALF_EVEN)
        .multiply(exchangeRequest.getAmount());
  }

}
