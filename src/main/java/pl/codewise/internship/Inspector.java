package pl.codewise.internship;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class Inspector implements Runnable{
    private ConcurrentHashMap<Integer, Task> tasks;

    public Inspector(ConcurrentHashMap<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while(!tasks.isEmpty()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }

            tasks.entrySet().stream()
                    .filter(e -> tasks.get(e.getKey()).shouldTimerTick())
                    .peek(e -> tasks.get(e.getKey()).tick())
                    .filter(e -> tasks.get(e.getKey()).hasEnded())
                    .forEach(e -> tasks.remove(e.getKey()));
        }
    }
}
