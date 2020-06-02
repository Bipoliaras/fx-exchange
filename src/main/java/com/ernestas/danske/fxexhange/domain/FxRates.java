package com.ernestas.danske.fxexhange.domain;

import java.math.BigDecimal;
import java.util.Map;

public final class FxRates {

  public static final Map<FxCurrency, BigDecimal> rates = Map.of(
      FxCurrency.EUR, new BigDecimal("743.94"),
      FxCurrency.USD, new BigDecimal("663.11"),
      FxCurrency.GBP, new BigDecimal("852.85"),
      FxCurrency.SEK, new BigDecimal("76.10"),
      FxCurrency.NOK, new BigDecimal("78.40"),
      FxCurrency.CHF, new BigDecimal("683.58"),
      FxCurrency.JPY, new BigDecimal("5.9740"),
      FxCurrency.DKK, new BigDecimal("100")
  );
}
