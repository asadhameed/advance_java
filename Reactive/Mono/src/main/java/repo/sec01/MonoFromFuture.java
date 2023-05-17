package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {
    public static void main(String[] args) {
         Mono.fromFuture(getName()).subscribe(Util.onNext());
         Util.sleepSecond(1);
    }

    public static CompletableFuture<String> getName(){
        return  CompletableFuture.supplyAsync(()-> Util.faker().name().fullName().toUpperCase(Locale.ROOT));
    }
}
