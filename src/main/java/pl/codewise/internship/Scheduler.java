package pl.codewise.internship;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class Scheduler {
    private ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();
    private Thread inspector;
    private int currentTimerId = 0;

    public int start(int expirationTime, Callback callback){
        Task newTask = new Task(currentTimerId,callback, expirationTime);
        tasks.put(currentTimerId, newTask);
        if(inspector == null || !inspector.isAlive())
            createAndStartInspector();
        return currentTimerId++;
    }

    public void stop(int timerId){
        if(tasks.remove(timerId) == null)
            throw new IllegalArgumentException("There is no task with this id.");
    }


    private void createAndStartInspector(){
        inspector = new Thread(new Inspector(tasks));
        inspector.start();
    }
}
