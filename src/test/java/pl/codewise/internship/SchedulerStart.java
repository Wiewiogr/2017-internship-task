package pl.codewise.internship;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class SchedulerStart {
    @Test
    public void scheduler_start_callback_should_be_invoked() throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Callback callback = mock(Callback.class);
        Assert.assertEquals(0, scheduler.start(1,callback));
        Thread.sleep(1100);
        verify(callback, times(1)).invoke();
    }

    @Test
    public void schedule_two_tasks_both_should_be_invoked() throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Callback firstCallback = mock(Callback.class);
        Callback secondCallback = mock(Callback.class);
        Assert.assertEquals(0, scheduler.start(1,firstCallback));
        Assert.assertEquals(1, scheduler.start(2,secondCallback));
        Thread.sleep(1100);
        verify(firstCallback, times(1)).invoke();
        Thread.sleep(1100);
        verify(secondCallback, times(1)).invoke();
    }

    @Test
    public void start_task_wait_till_it_ends_then_start_another() throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Callback firstCallback = mock(Callback.class);
        Assert.assertEquals(0, scheduler.start(1,firstCallback));
        Thread.sleep(1100);
        verify(firstCallback, times(1)).invoke();

        Callback secondCallback = mock(Callback.class);
        Assert.assertEquals(1, scheduler.start(2,secondCallback));
        Thread.sleep(2100);
        verify(secondCallback, times(1)).invoke();
    }

    @Test
    public void add_hundred_tasks_they_should_be_invoked() throws InterruptedException {
        Scheduler scheduler = new Scheduler();

        List<Callback> callbacks = new ArrayList<>();
        for(int  i =0 ; i < 100; i ++){
            Callback newCallback = mock(Callback.class);
            callbacks.add(newCallback);
            Assert.assertEquals(i, scheduler.start(1,newCallback));
        }

        Thread.sleep(1100);
        for (int i = 0; i < 100 ; i ++ ){
            verify(callbacks.get(i), times(1)).invoke();
        }
    }
}