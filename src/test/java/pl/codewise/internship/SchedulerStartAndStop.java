package pl.codewise.internship;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class SchedulerStartAndStop {

    @Test
    public void start_two_tasks_and_stop_one_of_them_one_callback_should_be_invoked_the_other_one_not() throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Callback firstCallback = mock(Callback.class);
        Callback secondCallback = mock(Callback.class);
        int firstTimerId = scheduler.start(2,firstCallback);
        int secondTimerId = scheduler.start(3,secondCallback);

        Assert.assertEquals(0, firstTimerId);
        Assert.assertEquals(1, secondTimerId);

        Thread.sleep(2100);
        verify(firstCallback, times(1)).invoke();
        verify(secondCallback, never()).invoke();
        scheduler.stop(secondTimerId);
        Thread.sleep(1100);
        verify(firstCallback, times(1)).invoke();
        verify(secondCallback, never()).invoke();

    }

    @Test
    public void add_hundered_tasks_then_stop_half_of_them() throws InterruptedException {
        Scheduler scheduler = new Scheduler();

        List<Callback> callbacks = new ArrayList<>();
        for(int  i =0 ; i < 100; i ++){
            Callback newCallback = mock(Callback.class);
            callbacks.add(newCallback);
            Assert.assertEquals(i, scheduler.start(1,newCallback));
        }

        for (int i = 0; i < 50; i++) {
            scheduler.stop(i);
        }

        Thread.sleep(1100);
        for (int i = 50; i < 100 ; i ++ ){
            verify(callbacks.get(i), times(1)).invoke();
        }

        for (int i = 0; i < 50 ; i ++ ){
            verify(callbacks.get(i), never()).invoke();
        }
    }
}
