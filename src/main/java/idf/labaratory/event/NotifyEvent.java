package idf.labaratory.event;

import org.springframework.context.ApplicationEvent;

public class NotifyEvent extends ApplicationEvent {

    private Double coinPrice;
    private String symbol;

    public NotifyEvent(Object source, Double coinPrice, String symbol) {
        super(source);
        this.coinPrice = coinPrice;
        this.symbol = symbol;
    }

    public Double getCoinPrice() {
        return coinPrice;
    }
    public String getSymbol() {
        return symbol;
    }
}
