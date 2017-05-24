package pl.codewise.internship;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class Task {
    private int timerid;
    private Callback callback;
    private int timeLeft;
    private long lastTickTime;
    Thread thread;
    public Task(int timerid, Callback callback, int time) {
        this.timerid = timerid;
        this.callback = callback;
        this.timeLeft = time;
        this.lastTickTime = System.currentTimeMillis();

        thread = new Thread(() -> {
           for(int i = 0 ; i < time; i++){

           }
        });
    }

    public boolean shouldTimerTick(){
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastTickTime) > 1000;
    }

    public void tick(){
       timeLeft--;
       lastTickTime = System.currentTimeMillis();
       if(hasEnded()){
           clear();
       }
       System.out.println("ticked");
    }

    private void clear(){
        System.out.println("invoked");
        callback.invoke();
    }

    public boolean hasEnded(){
        return timeLeft <= 0;
    }

}
