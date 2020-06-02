package com.ernestas.danske.fxexhange.domain;

import com.ernestas.danske.fxexhange.foreignexchange.FxCurrency;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {

  private FxCurrency fxCurrencyFrom;
  private FxCurrency fxCurrencyTo;

  @DecimalMin(value = "1.0")
  private BigDecimal amount;
}
