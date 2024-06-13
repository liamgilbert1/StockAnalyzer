package controller.IO.readers;

import model.portfolio.IPortfolio;

public interface IPortfolioReader extends IReader {
  IPortfolio getPortfolio();
}
