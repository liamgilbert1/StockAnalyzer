package model.portfolio;

import java.time.LocalDate;

public interface IPortfolio {

  IPortfolio addHolding(IHolding holding);

  double getValue(LocalDate date);
}
