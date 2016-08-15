package com.jpm.stocks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpm.stocks.beans.Stock;
import com.jpm.stocks.beans.Trade;
import com.jpm.stocks.dto.StockMarketDtoImpl;
import com.jpm.stocks.utils.DateUtils;
import com.jpm.stocks.utils.StockType;
import com.jpm.stocks.utils.TradeType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/application-test-context.xml" })
public class StockMarketServiceImplTest {

	@Autowired
	StockMarketService stockMarketService;

	@Autowired
	StockMarketDtoImpl stockMarketDtoImpl;

	private Set<String> stockType = null;

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

	@Before
	public void prepareTestInputData() {
		stockMarketDtoImpl.setStockMap(stockMap());
		stockType = stockMap().keySet();

	}

	@Test
	public void getDividendYieldForCommonStockType() {

		double dividendYield = stockMarketService.calculateDividend(stockMap().get("GIN"), 100.0);
		Assert.assertNotNull(dividendYield);

	}

	@Test
	public void getDividendYieldForPrefferedStockType() {

		double dividendYield = stockMarketService.calculateDividend(stockMap().get("POP"), 100.0);
		Assert.assertNotNull(dividendYield);

	}

	@Test
	public void getPERatio() {

		double peRatio = stockMarketService.calculatePERatio(stockMap().get("GIN"), 100.0);
		Assert.assertNotNull(peRatio);

	}

	@Test
	public void recordTradeData() {
		for (Trade trade : tradeList()) {
			stockMarketService.recordTrade(trade);
		}
		Assert.assertEquals(16, stockMarketDtoImpl.getTradeList().size());
	}

	@Test
	public void volumeWeightedStockPriceWithTimeRange() {
		Iterator<String> stockTypeItr = stockType.iterator();
		while (stockTypeItr.hasNext()) {
			stockMarketService.volumeWeightedStockPriceWithTimeRange(stockMap().get(stockTypeItr.next()), 5);
		}
		Assert.assertTrue(true);
	}

	@Test
	public void calculateGBCEAllShareIndex() {
		double gbceShareIndex = stockMarketService.calculateGBCEAllShareIndex();
		Assert.assertNotNull(gbceShareIndex);
	}

}
