package com.jpm.stocks.beans;

import java.util.Date;

import com.jpm.stocks.utils.TradeType;

/**
 * @author user
 *
 */
public class Trade {

	private int quantity;
	private double price;
	private TradeType tradeType;
	private Date tradeDate;
	private String stockSymbol;
	
	
	public Trade(int quantity, double price, TradeType tradeType,String stockSymbol,Date tradeDate){
		this.quantity = quantity;
		this.price = price;
		this.tradeType = tradeType;
		this.tradeDate =  tradeDate;
		this.stockSymbol = stockSymbol;
	}
	
	public Trade(int quantity, double price, TradeType tradeType,String stockSymbol){
		this(quantity,price,tradeType,stockSymbol,new Date());
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		String pattern = "Trade Object [tradeDate: %tF %tT, quantity: %d, tradeType: %s, price: %8.2f, stockSymbol: %s]";
		return String.format(pattern, tradeDate, tradeDate, quantity, tradeType, price, stockSymbol);
	}
}
