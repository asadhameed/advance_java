package repo.sec02;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FluxAssignmentPriceGenerate {

    public static Flux<Integer> getPrice(){
        Random random = new Random();
        AtomicInteger price = new AtomicInteger(100);
        return Flux.interval(Duration.ofSeconds(1)).map(i -> price.addAndGet(random.nextInt(11) - 5));

    }
}
