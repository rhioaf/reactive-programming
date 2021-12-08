package com.xtremax.rxjava.service;

import com.xtremax.rxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {

  Mono<CoinBaseResponse> getCryptoPrice(String priceName);
}
