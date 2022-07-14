package idf.labaratory.service;

import idf.labaratory.entity.CoinPrice;

public interface CoinPriceService {
    CoinPrice getCoinPrice(String symbol);
}
