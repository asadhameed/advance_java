package repo.sec02;

import courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class GenerateNames {

    public static List<String> getNames(int count){
        List<String> names = new ArrayList<>();
        for (int i =0 ;i < count ; i++) {
                names.add(getName());
        }

        return  names;
    }

    public static Flux<String> getFluxName(int count){
        return  Flux.range(1, count).map(i -> getName());
    }

    private static String getName() {
        Util.sleepSecond(1);
        return Util.faker().name().fullName();
    }

}
