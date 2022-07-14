package idf.labaratory.controller;


import idf.labaratory.entity.Coin;
import idf.labaratory.entity.CoinPrice;
import idf.labaratory.entity.Notify;
import idf.labaratory.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/coins")
@Tag(name = "Coins rest endpoint", description = "Coins rest endpoint")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class CoinController {

    private final CoinService coinService;
    private final CoinPriceService coinPriceService;
    private final NotifyService notifyService;

    public CoinController(CoinService coinService, CoinPriceService coinPriceService, NotifyService notifyService) {
        this.coinService = coinService;
        this.coinPriceService = coinPriceService;
        this.notifyService = notifyService;
    }

    @GetMapping("/getAllAvailableCoins")
    @Operation(description = "Get all available coins")
    public ResponseEntity<List<Coin>> getAllAvailableCoins() {
        return ResponseEntity.ok(coinService.getAll());
    }

    @GetMapping("/getPriceCoin/{symbol}")
    @Operation(description = "Get price coin")
    public ResponseEntity<CoinPrice> getPriceCoin(
            @PathVariable String symbol) {
        CoinPrice coinPrice = coinPriceService.getCoinPrice(symbol);
        return ResponseEntity.ok(coinPrice);
    }

    @PostMapping(path = "/addNotification", consumes = MediaType.ALL_VALUE)
    @Operation(description = "Added notification")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Notify> addNotification(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "symbol") String symbol
    ) {
        Notify notify = notifyService.addNotificaion(userName, symbol);
        return ResponseEntity.ok(notify);
    }
}
