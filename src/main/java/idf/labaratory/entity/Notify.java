package idf.labaratory.entity;

import javax.persistence.*;

@Entity
@Table(name = "notify")
public class Notify {

    public Notify() {

    }

    public Notify(String userName, Coin coin, Double priceNotify) {
        this.userName = userName;
        this.coin = coin;
        this.priceNotify = priceNotify;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notify_generator")
    @SequenceGenerator(name = "notify_generator", sequenceName = "sq_notify", allocationSize = 1)
    private Long id;

    @OneToOne
    private Coin coin;

    private String userName;

    private Double priceNotify;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getPriceNotify() {
        return priceNotify;
    }

    public void setPriceNotify(Double priceNotify) {
        this.priceNotify = priceNotify;
    }
}
