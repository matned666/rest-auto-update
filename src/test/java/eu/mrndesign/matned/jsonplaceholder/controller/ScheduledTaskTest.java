package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.config.ScheduledConfig;
import eu.mrndesign.matned.jsonplaceholder.service.RestConnectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@SpringJUnitConfig(ScheduledConfig.class)
class ScheduledTaskTest {


    @Autowired
    private ScheduledTask scheduledTask;

    @MockBean
    private RestConnectionService restConnectionService;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero()
            throws InterruptedException {
        Thread.sleep(1000L);

        verify(restConnectionService, times(1)).getRest();
    }

}