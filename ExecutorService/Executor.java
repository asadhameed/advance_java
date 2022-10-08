import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;  

public  class Executor {
    public static void main(String args[]){
    //    ExecutorService ss = Executors.newFixedThreadPool(4);
    //    Future<Integer> fu = ss.submit(new Mytask());
                     
        ExecutorService ss = Executors.newFixedThreadPool(4);
        Future<Integer> ff = ss.submit(new Mytask());
         
         try {
            System.out.println("------------blocking for future----");
            Integer value = ff.get();
            System.out.println("Main thread is running"  + value);
         } catch(Exception ex){
            
         }
         
         
         
        ss.shutdown();
    }

    static class Mytask implements Callable<Integer>{
    
        public Integer call() throws Exception{
            try {
                Thread.sleep(4000);
                return 6;
            } catch (Exception e){
                return 10;
            }

        }
    }

     static class Task implements Runnable{
        private String taskName;
        Task(String name){
            taskName = name;
        }
        public void run(){
            try{
                  
                   System.out.println(taskName + "    sleep");
                 //  Thread.sleep(4000);
                   System.out.println(taskName  + "  awakeup");

            } catch(Exception e){
                System.out.println(e);
            }
          
        }
    }
}