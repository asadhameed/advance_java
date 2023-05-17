package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoSupplier {

    public static void main(String[] args) {

        //Mono<String> mono = Mono.just(getName());

        Mono<String> fromSupplier = Mono.fromSupplier(() -> getName());
        fromSupplier.subscribe(Util.onNext());


    }

    public static String getName(){
        System.out.println(" The input operation------");
        return Util.faker().name().fullName();
    }
}
