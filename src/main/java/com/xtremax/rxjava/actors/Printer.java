package com.xtremax.rxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.xtremax.rxjava.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Printer extends AbstractActor {

  public Printer() {
  }

  public static Props props() {
    return Props.create(Printer.class, () -> new Printer());
  }

  @Override
  public Receive createReceive() {
    return  receiveBuilder()
        .match(CryptoPrice.class,
            msg -> msg.message
                .subscribe(cbr -> System.out.println("[" + LocalDateTime.now()+ "] "
                    + cbr.getData().getBase()
                    + " Buy Price: $" + cbr.getData().getAmount()
                    + " " + cbr.getData().getCurrency()))).build();
  }

  public static class CryptoPrice {
    public final Mono<CoinBaseResponse> message;

    public CryptoPrice(Mono<CoinBaseResponse> message) {
      this.message = message;
    }
  }
}
