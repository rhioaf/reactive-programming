package com.xtremax.rxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.xtremax.rxjava.service.CoinBaseService;

public class PriceRequestor extends AbstractActor {

  private final ActorRef printerActor;
  private final CoinBaseService service;

  public PriceRequestor(ActorRef printerActor, CoinBaseService service) {
    this.printerActor = printerActor;
    this.service = service;
  }

  public static Props props(ActorRef printerActor, CoinBaseService service) {
    return Props.create(PriceRequestor.class, () -> new PriceRequestor(printerActor, service));
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(GetThisCryptoPrice.class,
            wh -> printerActor
                .tell(new Printer.CryptoPrice(service.getCryptoPrice(wh.whatPrice)), getSelf()))
        .build();
  }

  public static class GetThisCryptoPrice {
    public final String whatPrice;

    public GetThisCryptoPrice(String whatPrice) {
      this.whatPrice = whatPrice;
    }
  }
}
