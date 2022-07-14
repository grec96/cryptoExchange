package idf.labaratory.configuration;

import idf.labaratory.service.CurrencyService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {

    private final CurrencyService currencyService;

    public Scheduler(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(fixedDelayString = "${spring.sheduler.timer}")
    public void updatePrice() {
        currencyService.updatePrice();
    }
}
