package com.xtremax.rxjava.observer;

import com.xtremax.rxjava.model.CoinBaseResponse;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DefaultObserver;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ConsolePrintObserver extends DefaultObserver {
  @Override
  public void onNext(Object o) {
    Mono<CoinBaseResponse> responseMono
        = (Mono<CoinBaseResponse>) o;
    responseMono.subscribe(
        cbr -> {
          System.out.println("[" + LocalDateTime.now()
              + " " + cbr.getData().getBase()
              + " Buy Price: $" + cbr.getData().getAmount()
              + " " + cbr.getData().getCurrency());
        }
    );
  }

  @Override
  public void onError(@NonNull Throwable e) {
    System.out.println("error = " + e);
  }

  @Override
  public void onComplete() {
    System.out.println("Complete");
  }
}
