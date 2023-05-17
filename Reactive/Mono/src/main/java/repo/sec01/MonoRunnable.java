package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoRunnable {
    public static void main(String[] args) {
        Mono.fromRunnable(runnable()).subscribe(Util.onNext(), Util.onError(), Util.onCompleted());
    }

    public static Runnable runnable(){
        return ()->{
            System.out.println(" Time consumer task is start");
            Util.sleepSecond(3);
        };
    }
}
