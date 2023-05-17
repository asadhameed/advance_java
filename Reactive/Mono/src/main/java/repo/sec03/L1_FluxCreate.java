package repo.sec03;

import courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;

public class L1_FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
//            boolean con = true;
//            while (con){
//                String name = Util.faker().country().name();
//
//                if(name.toUpperCase(Locale.ROOT).equals("PAKISTAN")){
//                    con = false;
//                    System.out.println( " Complete  " + name);
//                    fluxSink.complete();
//                }
//                fluxSink.next(name);
//            }

            String countryName;
            do{
                countryName = Util.faker().country().name();
                fluxSink.next(countryName);
            } while (!countryName.toUpperCase(Locale.ROOT).equals("PAKISTAN"));
            fluxSink.complete();

        }).subscribe(Util.subscriber());
    }
}
