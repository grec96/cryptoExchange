package idf.labaratory.service;

import idf.labaratory.entity.Coin;
import idf.labaratory.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {
    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<Coin> getAll() {
        return coinRepository.findAll();
    }
}
