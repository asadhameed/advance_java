package repo.sec02;

import courseutil.Util;
import reactor.core.publisher.Flux;

public class MultipleFlux {
    public static void main(String[] args){

        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5, 6);
        just.subscribe(i-> System.out.println("Sub 1  " + i));

        just.subscribe(i-> System.out.println("Sub 2  " + i));
       Flux<Integer> even=  just.filter(i -> i %2 ==0);
               even.subscribe(i-> System.out.println(" Sub 3 "+ i));


    }
}
