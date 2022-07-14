package idf.labaratory.repository;

import idf.labaratory.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Long> {

    @Query(value = "select n from Notify n " +
            "where n.coin.symbol = :symbol " +
            "and (:price < n.priceNotify * 0.9999 or :price > n.priceNotify * 1.0001)")
    List<Notify> findUsers(@Param(value = "symbol") String symbol,
                           @Param(value = "price") Double price);
}
