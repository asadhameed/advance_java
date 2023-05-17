package courseutil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscribe implements Subscriber<Object> {
    private String name;

   public DefaultSubscribe(){
       this.name ="";
   }
    public DefaultSubscribe(String name){
       this.name = name + " - ";
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + "Received  :" + o);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(name + "Error  :" + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "Completed  " );
    }
}
