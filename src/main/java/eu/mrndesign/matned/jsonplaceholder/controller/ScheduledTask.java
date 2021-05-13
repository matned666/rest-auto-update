package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.service.IRestService;
import eu.mrndesign.matned.jsonplaceholder.service.RestConnectionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {

    private final IRestService restService;

    public ScheduledTask(RestConnectionService restService) {
        this.restService = restService;
    }

//              86400000 for 24h (1000 for 1 second)
    @Scheduled(fixedRate = 86400000)
    public void downloadNewRandomPost() {
        restService.getRest();
    }
}