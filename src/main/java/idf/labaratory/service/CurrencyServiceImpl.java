package idf.labaratory.service;


import idf.labaratory.dto.Ticker;
import idf.labaratory.entity.Coin;
import idf.labaratory.entity.CoinPrice;
import idf.labaratory.event.NotifyEvent;
import idf.labaratory.repository.CoinPriceRepository;
import idf.labaratory.repository.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final CoinRepository coinRepository;
    private final CoinPriceRepository coinPriceRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private static final String MESSAGE = "Price %s not received";


    public CurrencyServiceImpl(CoinRepository coinRepository, CoinPriceRepository coinPriceRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.coinRepository = coinRepository;
        this.coinPriceRepository = coinPriceRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Value("${crypto.url}")
    private String url;

    @Override
    public void updatePrice() {
        List<Coin> coinList = coinRepository.findAll();
        coinList
                .forEach(coin -> {
                    String urlWithParam = url + "?id=" + coin.getId();
                    try {
                        Ticker[] tickerArray = restTemplate.getForObject(urlWithParam, Ticker[].class);
                        if( tickerArray.length > 0) {
                            Ticker ticker = tickerArray[0];
                            NotifyEvent notifyEvent = new NotifyEvent(this, Double.parseDouble(ticker.getPrice_usd()), coin.getSymbol());
                            applicationEventPublisher.publishEvent(notifyEvent);

                            CoinPrice coinPrice = new CoinPrice(coin, Double.parseDouble(ticker.getPrice_usd()));
                            coinPriceRepository.save(coinPrice);
                        }
                    } catch (Exception e) {
                        LOGGER.warn(String.format(MESSAGE, coin.getSymbol()));
                    }

                });
    }

}
