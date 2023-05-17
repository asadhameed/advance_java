package courseutil;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {

    public static Faker FAKER = Faker.instance();
    public static Consumer<Object> onNext(){
       
        return o -> System.out.println("Received Data= " + o);
    }

    public static Consumer<Throwable> onError(){
        return  e-> System.out.println("Error = " + e.getMessage());
    }

    public static Runnable onCompleted(){
        return () -> System.out.println(" Completed" );
    }

    public static Faker faker(){
        return  FAKER;
    }

    public static void sleepSecond(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Subscriber subscriber(){
        return  new DefaultSubscribe();
    }

    public static Subscriber subscriber(String name){
        return  new DefaultSubscribe(name);
    }
}
