package model.portfolio;

import model.IDate;

public interface IPortfolio {

  IPortfolio addHolding(IHolding holding);

  double getValue(IDate date);
}
