package repo.sec02;

import courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxJust {
    public static void main(String[] args){
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);

        just.subscribe(Util.onNext());
    }
}
