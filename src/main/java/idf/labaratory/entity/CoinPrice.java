package idf.labaratory.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coin_price")
public class CoinPrice {

    public CoinPrice() {

    }

    public CoinPrice(Coin coin, Double price) {
        this.coin = coin;
        this.price = price;
        this.date = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_price_generator")
    @SequenceGenerator(name = "coin_price_generator", sequenceName = "sq_coin_price", allocationSize = 1)
    private Long id;

    @OneToOne
    private Coin coin;

    private Double price;

    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
