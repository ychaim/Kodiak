package com.clearpool.kodiak.feedlibrary.core.cta;

import java.util.HashMap;
import java.util.Map;

import com.clearpool.messageobjects.marketdata.Exchange;

public class CtaUtils
{
	private static final double[] POWERS = new double[] { 1E1, 1E2, 1E3, 1E4, 1E5, 1E6, 1E7, 1E8 };
	private static final Exchange[] EXCHANGES = new Exchange[26];
	private static final Map<String, Exchange> FINRA_EXCHANGES = new HashMap<>();

	static
	{
		EXCHANGES['A' - 'A'] = Exchange.USEQ_NYSE_MKT;
		EXCHANGES['B' - 'A'] = Exchange.USEQ_NASDAQ_OMX_BX;
		EXCHANGES['C' - 'A'] = Exchange.USEQ_NATIONAL_STOCK_EXCHANGE;
		EXCHANGES['D' - 'A'] = Exchange.USEQ_FINRA_ADF;
		EXCHANGES['E' - 'A'] = Exchange.USEQ_SIP;
		EXCHANGES['I' - 'A'] = Exchange.USEQ_INTERNATIONAL_SECURITIES_EXCHANGE;
		EXCHANGES['J' - 'A'] = Exchange.USEQ_EDGA_EXCHANGE;
		EXCHANGES['K' - 'A'] = Exchange.USEQ_EDGX_EXCHANGE;
		EXCHANGES['M' - 'A'] = Exchange.USEQ_CHICAGO_STOCK_EXCHANGE;
		EXCHANGES['N' - 'A'] = Exchange.USEQ_NYSE_EURONEXT;
		EXCHANGES['P' - 'A'] = Exchange.USEQ_NYSE_ARCA_EXCHANGE;
		EXCHANGES['T' - 'A'] = Exchange.USEQ_NASDAQ_OMX;
		EXCHANGES['W' - 'A'] = Exchange.USEQ_CHICAGO_BOARD_OPTIONS_EXCHANGE;
		EXCHANGES['X' - 'A'] = Exchange.USEQ_NASDAQ_OMX_PHLX;
		EXCHANGES['Y' - 'A'] = Exchange.USEQ_BATS_Y_EXCHANGE;
		EXCHANGES['Z' - 'A'] = Exchange.USEQ_BATS_EXCHANGE;

		FINRA_EXCHANGES.put("FNRA", Exchange.USEQ_FINRA_ADF);
		FINRA_EXCHANGES.put("FLOW", Exchange.USEQ_FINRA_LAVAFLOW);
	}

	public static double getPrice(long value, char denominatorCode)
	{
		if (denominatorCode == '0') return 0;
		if (denominatorCode == 'I') return value;
		return value / POWERS[denominatorCode - 'A'];
	}

	public static Exchange getExchange(char participantId, String finraMarketMakerId)
	{
		int index = participantId - 'A';
		if (index < 0) return null;
		Exchange exchange = EXCHANGES[index];
		if (finraMarketMakerId != null && exchange == Exchange.USEQ_FINRA_ADF)
		{
			Exchange finraExchange = FINRA_EXCHANGES.get(finraMarketMakerId);
			if (finraExchange != null)
			{
				return finraExchange;
			}
		}
		return exchange;
	}
}