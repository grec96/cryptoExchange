package idf.labaratory.service;

import idf.labaratory.entity.CoinPrice;
import idf.labaratory.repository.CoinPriceRepository;
import org.springframework.stereotype.Service;

@Service
public class CoinPriceServiceImpl implements CoinPriceService {

    private final CoinPriceRepository coinPriceRepository;

    public CoinPriceServiceImpl(CoinPriceRepository coinPriceRepository) {
        this.coinPriceRepository = coinPriceRepository;
    }

    public CoinPrice getCoinPrice(String symbol) {
        return coinPriceRepository.findFirstByCoinSymbolOrderByIdDesc(symbol);
    }
}
