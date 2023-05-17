package repo.sec02;

import courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class FluxAssignment {
    public static void main(String[] args) throws InterruptedException {


       // CountDownLatch countDownLatch = new CountDownLatch(1);


        FluxAssignmentPriceGenerate.getPrice().subscribeWith(new Subscriber<Integer>() {
            private  Subscription sp;
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                sp = s;
            }

            @Override
            public void onNext(Integer integer) {
                    System.out.println("---Price--- " +integer);

                    if(integer >= 110 || integer < 90){
                        sp.cancel();
                 //       countDownLatch.countDown();;
                    }

            }

            @Override
            public void onError(Throwable t) {
                System.out.println("---- Error---"+t.getMessage());
             //   countDownLatch.countDown();;
            }

            @Override
            public void onComplete() {
                System.out.println("------Completed----");
             //   countDownLatch.countDown();;
            }
        });
       // countDownLatch.wait();

        Util.sleepSecond(40);


    }
}
