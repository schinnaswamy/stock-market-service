package com.jpm.stocks.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;

/**
 * 
 * @author user
 *
 */
public class StockMarketDtoImpl implements StockMarketDto {
	
	private List<Trade> tradeList = null;
	private Map<String, Stock> stockMap = null;
	
	public StockMarketDtoImpl() {
		tradeList =  new ArrayList<Trade>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jpm.stocks.dto.StockMarketDto#recordTrade(com.jpm.stocks.beans.Trade)
	 */
	public void recordTrade(Trade tradeObj) {
		if(tradeObj != null){
		   tradeList.add(tradeObj);
		}   
	}
	
	public void setStockMap(Map<String, Stock> stockMap){
		this.stockMap = stockMap;
	}

    /*
     * (non-Javadoc)
     * @see com.jpm.stocks.dto.StockMarketDto#getStockMap()
     */
	public Map<String, Stock> getStockMap() {
		return stockMap;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jpm.stocks.dto.StockMarketDto#getTradeList()
	 */
	public List<Trade> getTradeList() {
		return tradeList;
	}

}
