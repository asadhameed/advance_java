package repo.sec02;

import courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class FluxSubscription {

    public static void main(String[] args) {
        AtomicReference<Subscription> reference = new AtomicReference<>();
        Flux.range(1 ,10).log().subscribeWith(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(" onSubscription  " + s);

                reference.set(s);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(" -- Received --- " + integer );
            }

            @Override
            public void onError(Throwable t) {
                 System.out.println(" On Error " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(" on Complete ");
            }
        });

        Util.sleepSecond(3);

        reference.get().request(3);
        Util.sleepSecond(5);

        reference.get().request(3);
        Util.sleepSecond(5);

        reference.get().cancel();


    }


}
