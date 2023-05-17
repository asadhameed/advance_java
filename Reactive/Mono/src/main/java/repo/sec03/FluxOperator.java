package repo.sec03;

import courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxOperator {
    public static void main(String[] args) {
        Flux.range(1, 10).map(i-> i*2).subscribe(Util.subscriber());
    }
}
