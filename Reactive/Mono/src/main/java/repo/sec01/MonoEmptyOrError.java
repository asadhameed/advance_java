package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {
    public static void main(String[] args) {
        Mono<String> getUser = userRepository(1);

        getUser.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onCompleted()
        );

    }

    public  static Mono<String> userRepository(int userid){

        if(userid==1){
            return Mono.just(Util.FAKER.name().fullName());
        }
        else if( userid ==2){
            return  Mono.empty();
        }
        else {
            return Mono.error(new RuntimeException("db connection is Error"));
        }
    }
}
