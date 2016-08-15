package com.jpm.stocks.service;

import java.util.Collection;
import java.util.List;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;

/**
 * @author user
 *
 */
public interface  StockMarketService {
	
	
	/**
	 * @param stock
	 * @param price
	 * @return double
	 */
	public double calculateDividend(Stock stock, double price);
	
	/**
	 * @param stock
	 * @param price
	 * @return double
	 */
	public double calculatePERatio(Stock stock, double price);
	
	/**
	 * @param trade
	 */
	public void recordTrade(Trade trade);
	
	/**
	 * @param stock
	 * @param timeRangeInMins
	 * @return double
	 */
	public double volumeWeightedStockPriceWithTimeRange(Stock stock, int timeRangeInMins);
	
	/**
	 * @param tradeList
	 * @param stockList
	 * @return List<Double>
	 */
	public List<Double> volumeWeightedStockPriceForAllStocks(Collection<Stock> stockList);
		
	/**
	 * @return double
	 */
	public double calculateGBCEAllShareIndex();
}
