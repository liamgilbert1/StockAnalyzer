package model;

public interface IPortfolio {

  IPortfolio addHolding(IHolding holding);

  double getValue(IDate date);
}
