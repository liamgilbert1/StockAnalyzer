package model.portfolio;

import java.time.LocalDate;
import java.util.List;

public interface IPortfolio {

  IPortfolio addHolding(IHolding holding);

  double getValue(LocalDate date);

  List<IHolding> getHoldings();
}
