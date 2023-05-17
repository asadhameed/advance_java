package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoSupplierRefactor {
    public static void main(String[] args) {

     getName();
        getName();
        getName();
        getName().subscribe(Util.onNext());
        getName();
        getName();





    }

    public static Mono<String> getName(){
        System.out.println(" Enter to the getName function");
        return Mono.fromSupplier(()->{

            System.out.println(" Enter to thread");
            Util.sleepSecond(5);
            return Util.faker().name().fullName();
        }).map(String::toLowerCase);
    }
}
