import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;  

public  class ExecutorScheduled {
    public static void main(String args[]){
    //    ExecutorService ss = Executors.newFixedThreadPool(4);
    //    Future<Integer> fu = ss.submit(new Mytask());
                     
        ScheduledExecutorService ss = Executors.newScheduledThreadPool(4);
         
         System.out.println("------------blocking for future----");
         try {

            //Creates and executes a one-shot action that becomes enabled after the given delay.
            //	schedule(Runnable command, long delay, TimeUnit unit
            // ss.schedule(new Task("Hello"), 4, TimeUnit.SECONDS);

            //Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently with the given period; that is executions will commence after initialDelay then initialDelay+period, then initialDelay + 2 * period, and so on.
            //scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
               ss.scheduleAtFixedRate(new Task("ScheduleAtFixRate"), 2 , 4 , TimeUnit.SECONDS);

            //Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently with the given delay between the termination of one execution and the commencement of the next.
            //scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
          //  ss.scheduleWithFixedDelay(new Task("scheduleWithFixedDelay"),2 ,5 , TimeUnit.SECONDS );
           
         } catch(Exception ex){
            
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
                   Thread.sleep(5000);
                   System.out.println(taskName  + "  awakeup");

            } catch(Exception e){
                System.out.println(e);
            }
          
        }
    }
}