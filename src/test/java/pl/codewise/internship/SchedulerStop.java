package pl.codewise.internship;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by wiewiogr on 24.05.17.
 */
public class SchedulerStop {
    @Test
    public void stop_with_timerid_that_exists_should_not_be_invoked(){
        Scheduler scheduler = new Scheduler();
        Callback callback = mock(Callback.class);
        int id = scheduler.start(10,callback);
        scheduler.stop(id);
        verify(callback, times(0)).invoke();
    }

    @Test(expected = IllegalArgumentException.class)
    public void stop_with_timerid_that_were_not_registered_should_throw_exception(){
        Scheduler scheduler = new Scheduler();
        int idThatWerentRegistered = 100;
        scheduler.stop(idThatWerentRegistered);
    }
}
