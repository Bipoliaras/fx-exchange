package com.ernestas.danske.fxexhange.service;

import com.ernestas.danske.fxexhange.fx.FxRates;
import com.ernestas.danske.fxexhange.error.BadRequestException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

  public BigDecimal exchange(String currencyFrom, String currencyTo, BigDecimal amount) {

    if(!FxRates.rates.containsKey(currencyFrom) || !FxRates.rates.containsKey(currencyTo)) {
      throw new BadRequestException("Provided currency is not present in the exchange");
    }

    if(currencyFrom.equals(currencyTo)) {
      return BigDecimal.ONE;
    }

    return FxRates.rates.get(currencyFrom)
        .divide(FxRates.rates.get(currencyTo), RoundingMode.HALF_EVEN)
        .multiply(amount);
  }

}
