package com.ernestas.danske.fxexhange.fx;

import java.math.BigDecimal;
import java.util.Map;

public final class FxRates {

  public static final Map<String, BigDecimal> rates = Map.of(
      "EUR", new BigDecimal("743.94"),
      "USD", new BigDecimal("663.11"),
      "GBP", new BigDecimal("852.85"),
      "SEK", new BigDecimal("76.10"),
      "NOK", new BigDecimal("78.40"),
      "CHF", new BigDecimal("683.58"),
      "JPY", new BigDecimal("5.9740"),
      "DKK", new BigDecimal("100")
  );
}
