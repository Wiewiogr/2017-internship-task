package pl.codewise.internship;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class SchedulerStop {
    @Test
    public void stop_with_timerid_that_exists_callback_should_not_be_invoked_after_expiration_time() throws InterruptedException {
        Scheduler scheduler = new Scheduler();
        Callback callback = mock(Callback.class);
        int id = scheduler.start(1,callback);
        scheduler.stop(id);
        Thread.sleep(1100);
        verify(callback, never()).invoke();
    }

    @Test(expected = IllegalArgumentException.class)
    public void stop_with_timerid_that_were_not_registered_should_throw_exception(){
        Scheduler scheduler = new Scheduler();
        int idThatWerentRegistered = 100;
        scheduler.stop(idThatWerentRegistered);
    }




}
