package repo.sec02;

import courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxRange {

    public static void main(String[] args){
        Flux.range(1, 10).log().map((e) -> {
                    if(e == 7) throw new RuntimeException("There is an error in 7");
                  return    Util.FAKER.name().fullName();
                }
        ).log().subscribe(Util.onNext(), Util.onError(), Util.onCompleted());
    }
}
