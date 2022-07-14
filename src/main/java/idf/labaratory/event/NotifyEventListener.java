package idf.labaratory.event;

import idf.labaratory.entity.Notify;
import idf.labaratory.repository.NotifyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;


@EnableAsync
@Configuration
public class NotifyEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyEventListener.class);
    private final String message = "Send to user = %s about coin = %s changed more than 1 percent";
    private final NotifyRepository notifyRepository;

    public NotifyEventListener(NotifyRepository notifyRepository) {
        this.notifyRepository = notifyRepository;
    }

    @Async
    @EventListener
    public void notify(NotifyEvent notifyEvent) {
        Double coinPrice = notifyEvent.getCoinPrice();
        String symbol = notifyEvent.getSymbol();
        List<Notify> notifyList = notifyRepository.findUsers(symbol, coinPrice);
        notifyList.forEach(notify ->
                LOGGER.warn(String.format(message, notify.getUserName(), notify.getCoin().getSymbol())));

    }
}
