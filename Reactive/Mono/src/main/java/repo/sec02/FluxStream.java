package repo.sec02;

import courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxStream {
    public static void main(String[] args) {
        Stream<Integer> stream = List.of(1,2,3,4,5).stream();

        Flux<Integer> integerFlux = Flux.fromStream(stream);
        integerFlux.subscribe(Util.onNext());

        stream.forEach(System.out::println);
    }
}
