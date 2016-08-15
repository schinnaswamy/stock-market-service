package com.jpm.stocks.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math.stat.StatUtils;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;
import com.jpm.stocks.dto.StockMarketDto;
import com.jpm.stocks.utils.DateUtils;
import com.jpm.stocks.utils.StockType;

/**
 * @author user
 *
 */
public class StockMarketServiceImpl implements StockMarketService {

	private StockMarketDto stockMarketDto = null;
	private static Log log = LogFactory.getLog(StockMarketServiceImpl.class.getName());
	
	public void setStockMarketDto(StockMarketDto stockMarketDto) {
		this.stockMarketDto = stockMarketDto;
	}


	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#calculateDividend(com.jpm.stocks.beans.Stock, double)
	 */
	public double calculateDividend(Stock stock, double price) {
		
		log.debug("Calculate Dividend Yield method start");

		double dividendYield = 0.0;

		if (stock != null) {

			if (stock.getStockType().equals(StockType.COMMON)) {
				dividendYield = stock.getLastDividend() / price;
			}

			else {
				dividendYield = (stock.getFixedDividend() * stock.getParValue()) / price;
			}

		}
		log.debug("Calculate Dividend Yield method end");
		return dividendYield;
	}

	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#calculatePERatio(com.jpm.stocks.beans.Stock, double)
	 */
	public double calculatePERatio(Stock stock, double price) {
		double peRatio = 0.0;

		if (stock != null) {
			peRatio = price / stock.getLastDividend();
		}
		return peRatio;
	}

	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#recordTrade(com.jpm.stocks.beans.Trade)
	 */
	public void recordTrade(Trade trade) {
		stockMarketDto.recordTrade(trade);
	}

	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#volumeWeightedStockPriceForAllStocks(java.util.Collection)
	 */
	public List<Double> volumeWeightedStockPriceForAllStocks(Collection<Stock> stockList) {

		double stockPrice = 0.0;
		List<Double> stockPriceList = new ArrayList<Double>();

		for (Stock stock : stockList) {
			stockPrice = volumeWeightedStockPriceWithTimeRange(stock,0);
			stockPriceList.add(stockPrice);
		}
		return stockPriceList;
	}

	
	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#volumeWeightedStockPriceWithTimeRange(com.jpm.stocks.beans.Stock, int)
	 */
	public double volumeWeightedStockPriceWithTimeRange(Stock stock,int timeRangeInMins) {

		double tradePriceQuantitySum = 0.0;
		double tradeQuantitySum = 0.0;

		for (Trade trade : stockMarketDto.getTradeList()) {

			if (DateUtils.isEligibleTimeRange(timeRangeInMins, trade.getTradeDate())) {
				if (stock != null && stock.getStockSymbol().equals(trade.getStockSymbol())) {
					tradePriceQuantitySum += (trade.getPrice() * trade.getQuantity());
					tradeQuantitySum += trade.getQuantity();
				}
			}

		}
		return tradePriceQuantitySum / tradeQuantitySum;
	}

	/* (non-Javadoc)
	 * @see com.jpm.stocks.service.StockMarketService#calculateGBCEAllShareIndex()
	 */
	public double calculateGBCEAllShareIndex() {

		List<Double> stockPricesList = volumeWeightedStockPriceForAllStocks(stockMarketDto.getStockMap().values());

		double[] stockPrices = new double[stockPricesList.size()];
		int index = 0;
		for (Double stockPrice : stockPricesList) {
			stockPrices[index] = stockPrice;
			index++;
		}
		return StatUtils.geometricMean(stockPrices);
	}

}
