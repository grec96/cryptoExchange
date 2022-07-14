package idf.labaratory.entity;

import javax.persistence.*;

@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coin_generator")
    @SequenceGenerator(name = "coin_generator", sequenceName = "sq_coin", allocationSize = 1)
    private Long id;

    private String symbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
