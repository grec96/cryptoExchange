package idf.labaratory.repository;

import idf.labaratory.entity.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPrice, Long> {

    CoinPrice findFirstByCoinSymbolOrderByIdDesc(String coinSymbol);
}
