import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;  

public  class ExecutorSer {
    public static void main(String args[]){

        ExecutorService ss = Executors.newFixedThreadPool(3);

        for(int i = 0 ; i < 10 ; i ++){
     
             ss.execute(new Task(i + "  thread"));
        }
    
         System.out.println("Main thread is running"  );
        
         
         
            ss.shutdown();
    }

 
     static class Task implements Runnable{
        private String taskName;
        Task(String name){
            taskName = name;
        }
        public void run(){
            try{
                  
                   System.out.println(taskName + "    sleep");
                   Thread.sleep(4000);
                   System.out.println(taskName  + "  awakeup");

            } catch(Exception e){
                System.out.println(e);
            }
          
        }
    }
}