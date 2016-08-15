package com.jpm.stocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;
import com.jpm.stocks.dto.StockMarketDtoImpl;
import com.jpm.stocks.service.StockMarketService;
import com.jpm.stocks.utils.DateUtils;
import com.jpm.stocks.utils.StockType;
import com.jpm.stocks.utils.TradeType;

/**
 * 
 * @author user
 *
 */

public class ApplicationMain {

	private static ApplicationContext context;
	private static Log log = LogFactory.getLog(ApplicationMain.class.getName());

	@Bean
	static Map<String, Stock> stockMap() {

		// In memory stock objects

		HashMap<String, Stock> stockMap = new HashMap<String, Stock>();
		stockMap.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		stockMap.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		stockMap.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		stockMap.put("GIN", new Stock("GIN", StockType.PREFFERED, 8.0, 0.2, 100.0));
		stockMap.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
		return stockMap;
	}

	@Bean
	static List<Trade> tradeList() {

		// In memory input trades
		List<Trade> inputTradeList = new ArrayList<Trade>();
		inputTradeList.add(new Trade(10, 100.0, TradeType.BUY, "TEA"));
		inputTradeList.add(new Trade(15, 200.0, TradeType.BUY, "POP"));
		inputTradeList.add(new Trade(20, 300.0, TradeType.BUY, "POP"));
		inputTradeList.add(new Trade(30, 400.0, TradeType.SELL, "ALE"));
		inputTradeList.add(new Trade(40, 500.0, TradeType.SELL, "GIN"));
		inputTradeList.add(new Trade(40, 500.0, TradeType.SELL, "JOE"));

		inputTradeList.add(new Trade(10, 100.0, TradeType.BUY, "ALE", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(15, 200.0, TradeType.BUY, "GIN", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(20, 300.0, TradeType.BUY, "POP", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(30, 400.0, TradeType.SELL, "ALE", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(40, 500.0, TradeType.SELL, "GIN", DateUtils.getDateWithDelayInMins(7)));

		inputTradeList.add(new Trade(16, 100.0, TradeType.BUY, "POP", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(5, 200.0, TradeType.BUY, "ALE", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(22, 300.0, TradeType.BUY, "GIN", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(3, 400.0, TradeType.SELL, "ALE", DateUtils.getDateWithDelayInMins(7)));
		inputTradeList.add(new Trade(43, 500.0, TradeType.SELL, "POP", DateUtils.getDateWithDelayInMins(7)));

		return inputTradeList;
	}

	public static void main(String args[]) {

		try {

			// Step 1: Load Spring application context
			context = new ClassPathXmlApplicationContext("application-context.xml");

			StockMarketDtoImpl stockMarketDto = (StockMarketDtoImpl) context.getBean("stockMarketDto");
			stockMarketDto.setStockMap(stockMap());

			StockMarketService stockMarketService = (StockMarketService) context.getBean("stockMarketService");

			// Step 2: Prepare stock input price range list
			double[] inputPriceRange = new double[] { 100.0, 200.0, 300.0, 400.0, 500.0 };

			Set<String> stockType = stockMap().keySet();

			Iterator<String> stockTypeItr = stockType.iterator();

			// Step 3: Calculate dividend yield and P/E ratio for each stock
			// symbol based on the sample input price range
			while (stockTypeItr.hasNext()) {

				Stock stockObj = stockMap().get(stockTypeItr.next());

				for (int i = 0; i < inputPriceRange.length; i++) {

					log.info("Input Price : " + inputPriceRange[i]);

					log.info("Dividend Yield for Stock Type " + stockObj.getStockSymbol() + " : "
							+ stockMarketService.calculateDividend(stockObj, inputPriceRange[i]));

					log.info("P/E ratio for Stock Type " + stockObj.getStockSymbol() + " : "
							+ stockMarketService.calculatePERatio(stockObj, inputPriceRange[i]));

					log.info("*****************************************************");
				}

			}

			Thread.sleep(5000);

			// Step : 4 Record the sample test trades

			for (Trade trade : tradeList()) {
				stockMarketService.recordTrade(trade);
			}

			log.info("*** Recorded Trade : " + stockMarketDto.getTradeList().size() + "*************");

			log.info("*****************  Recorded Trade Details ************************************");

			for (Trade trade : stockMarketDto.getTradeList()) {
				log.info("Recorded Trade : " + trade);
			}

			Thread.sleep(5000);

			// Step 5: Calculate Volume weighted stock price in past 5 minutes based on
			// the each stock symbol

			log.info("**** Volume Weighted Stock Price based on trades in past 5 minutes for a given Stock *****");

			Iterator<String> stockTypeIterator = stockType.iterator();

			while (stockTypeIterator.hasNext()) {

				Stock stockObj = stockMap().get(stockTypeIterator.next());
				log.info("Stock Symbol : " + stockObj.getStockSymbol() + " : "
						+ stockMarketService.volumeWeightedStockPriceWithTimeRange(stockObj, 5));

			}

			Thread.sleep(5000);
			
			// Step 6: Calculate the GBCE All share Index using geometric mean

			log.info(
					"**** GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks *****");

			log.info(stockMarketService.calculateGBCEAllShareIndex());

		} catch (Exception exception) {
			log.error("Exception occured in the Application Main class " + exception.getMessage());
			exception.printStackTrace();
		}

		log.info("**** Super Simple stock service ends here *****");

	}

}
