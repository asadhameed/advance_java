package repo.sec03.helper;

import courseutil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private  FluxSink  fluxSink ;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
            this.fluxSink = stringFluxSink;
    }

    public  void producer(){
        String name = Util.faker().name().fullName();
        String th = Thread.currentThread().getName();
        this.fluxSink.next("-- " + th +" -- " +name);
    }
}
