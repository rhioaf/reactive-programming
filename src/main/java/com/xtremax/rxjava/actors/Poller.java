package com.xtremax.rxjava.actors;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.time.Duration;

public class Poller extends AbstractActorWithTimers {

  private static Object TICK_KEY = "TickKey";
  private final ActorRef requestActor;
  private final String cryptoName;

  public Poller(ActorRef requestActor, String cryptoName) {
    this.requestActor = requestActor;
    this.cryptoName = cryptoName;
    getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(3000));
  }

  public static Props props(String cryptoName, ActorRef requestActor) {
    return Props.create(Poller.class, () -> new Poller(requestActor, cryptoName));
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(
        FirstTick.class, message -> {
          getTimers().
              startPeriodicTimer(TICK_KEY,
                  new Tick(), Duration.ofSeconds(3));
        }).match(
            Tick.class, message -> requestActor.tell(new PriceRequestor.GetThisCryptoPrice(cryptoName), getSelf())
    ).build();
  }

  private static final class FirstTick {

  }

  private static final class Tick {

  }


}
