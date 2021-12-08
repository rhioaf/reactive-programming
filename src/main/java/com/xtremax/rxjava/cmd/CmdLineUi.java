package com.xtremax.rxjava.cmd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.xtremax.rxjava.actors.Poller;
import com.xtremax.rxjava.actors.PriceRequestor;
import com.xtremax.rxjava.actors.Printer;
//import com.xtremax.rxjava.observer.ConsolePrintObserver;
import com.xtremax.rxjava.service.CoinBaseService;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import java.util.concurrent.TimeUnit;


@Component
public class CmdLineUi implements CommandLineRunner {

  @Autowired
  private CoinBaseService service;

  @Override
  public void run(String... args) throws Exception {

    // NEW
    final ActorSystem system = ActorSystem.create("helloakka");

    System.out.println(
        "\n=============================================="
        + "\n                                                "
        + "\n        Coinbase Price Service                  "
        + "\n        LinkedIn Learning                       "
        + "\n                                                "
        + "\n Built by: Rhio Adjie Fabian                    "
        + "\n ==============================================");

    final ActorRef printer = system
        .actorOf(Printer.props(), "printerActor");
    final ActorRef priceRequestor = system
        .actorOf(PriceRequestor.props(printer, service), "requestor");
    final ActorRef poller = system
        .actorOf(Poller.props("BTC-USD", priceRequestor), "poller");
    final ActorRef ethPoller = system
        .actorOf(Poller.props("ETH-USD", priceRequestor), "ethPoller");

    // OLD
//    System.out.println();
//    System.out.println("LinkedIn Learning Reactive Programming with Java 8");
//    System.out.println();
//
//    Observable
//        .interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
//        .map(e ->
//            service.getCryptoPrice("BTC-USD"))
//        .subscribe(new ConsolePrintObserver());
//
//    Observable
//        .interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
//        .map(e ->
//            service.getCryptoPrice("ETH-USD"))
//        .subscribe(new ConsolePrintObserver());
    }
  }
