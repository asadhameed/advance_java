package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoSubscribe {
    public static void main(String[] args) {
        Mono<String> mono_subscribe = Mono.just("Mono Subscribe");
        mono_subscribe.subscribe(
                t-> System.out.println("t = " + t),
                err-> System.out.println("err.getMessage() = " + err.getMessage()),
                ()-> System.out.println("Competed= " + mono_subscribe)
        );

        Mono<Integer> mono_error = Mono.just("Mono Subscribe").map(String::length).map(l-> l/0);
        mono_error.subscribe(
                t-> System.out.println("t = " + t),
                err-> System.out.println("err.getMessage() = " + err.getMessage()),
                ()-> System.out.println("Competed= " + mono_error)
        );

        Mono<String> use_util = Mono.just("Use Util");
        use_util.subscribe(Util.onNext(), Util.onError(), Util.onCompleted());
    }
}
