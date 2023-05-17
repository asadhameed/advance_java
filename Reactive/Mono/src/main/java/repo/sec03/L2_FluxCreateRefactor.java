package repo.sec03;

import courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import repo.sec03.helper.NameProducer;

public class L2_FluxCreateRefactor {

    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer).subscribe(Util.subscriber());
        //nameProducer.producer();

        Runnable runnable = ()-> nameProducer.producer();

        for(int i = 1; i < 11; ++i){
            new Thread( runnable).start();
        }

      //  Util.sleepSecond(10);


    }
}
