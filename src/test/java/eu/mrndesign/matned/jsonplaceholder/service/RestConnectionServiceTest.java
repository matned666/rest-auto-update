package eu.mrndesign.matned.jsonplaceholder.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class RestConnectionServiceTest {

    @Autowired
    private RestConnectionService restConnectionService;

    @Test
    void isConnectionWithRestServiceCorrect_assertingIfRestListHigherThanZero() {
        assertTrue(restConnectionService.getRestServiceDBSize()>0);
    }

    @Test
    void isRestServiceFirstPostNotNull() {
        assertNotNull(restConnectionService.getRestPost(1));
    }
}