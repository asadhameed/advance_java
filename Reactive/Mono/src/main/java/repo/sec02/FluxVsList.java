package repo.sec02;

import courseutil.Util;

import java.util.List;

public class FluxVsList {
    public static void main(String[] args) {

//        List<String> names = GenerateNames.getNames(5);
//        System.out.println(names);
         GenerateNames.getFluxName(5).subscribe(Util.onNext(), Util.onError(), Util.onCompleted());
    }
}
