package idf.labaratory.service;

import idf.labaratory.entity.CoinPrice;
import idf.labaratory.entity.Notify;
import idf.labaratory.repository.CoinPriceRepository;
import idf.labaratory.repository.NotifyRepository;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {
    private final NotifyRepository notifyRepository;
    private final CoinPriceRepository coinPriceRepository;

    public NotifyServiceImpl(NotifyRepository notifyRepository, CoinPriceRepository coinPriceRepository) {
        this.notifyRepository = notifyRepository;
        this.coinPriceRepository = coinPriceRepository;
    }

    @Override
    public Notify addNotificaion(String userName, String symbol) {
        CoinPrice coinPrice = coinPriceRepository.findFirstByCoinSymbolOrderByIdDesc(symbol);

        Notify notify = new Notify(userName, coinPrice.getCoin(), coinPrice.getPrice());
        return notifyRepository.save(notify);
    }
}
