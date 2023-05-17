package repo.sec03;

import courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;

public class L4_FluxCreateIssueFix {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
        String countryName;
        do{
            countryName = Util.faker().country().name();
            System.out.println("--Emit-- " + countryName);
            fluxSink.next(countryName);
        } while (!countryName.toUpperCase(Locale.ROOT).equals("PAKISTAN") && !fluxSink.isCancelled());

        fluxSink.complete();

    }).take(3).subscribe(Util.subscriber());
    }
}
