package com.jpm.stocks.dto;

import java.util.List;
import java.util.Map;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;

/**
 * 
 * @author user
 *
 */
public interface StockMarketDto {
	
	/**
	 * 
	 * @param tradeObj
	 */
	public void recordTrade(Trade tradeObj);
	
	/**
	 * 
	 * @return Map<String, Stock>
	 */
	public Map<String, Stock> getStockMap();
	
	/**
	 * 
	 * @return List<Trade>
	 */
	public List<Trade> getTradeList();
	
}
