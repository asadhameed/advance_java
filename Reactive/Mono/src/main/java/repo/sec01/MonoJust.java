package repo.sec01;

import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);

        System.out.println("mono = " + mono);

       // mono.subscribe(m -> System.out.println("Received= " + m));
    }
}
