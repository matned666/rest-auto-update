package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.service.IRestService;
import eu.mrndesign.matned.jsonplaceholder.service.IService;
import eu.mrndesign.matned.jsonplaceholder.service.PostService;
import eu.mrndesign.matned.jsonplaceholder.service.RestConnectionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {

    private final IRestService restService;
    private final IService postService;

    public ScheduledTask(RestConnectionService restService,
                         PostService postService) {
        this.restService = restService;
        this.postService = postService;
    }

//              86400000 for 24h (1000 for 1 second)
    @Scheduled(fixedRate = 86400000)
    public void downloadNewRandomPost() {
        Common.getRest(restService, postService);
    }
}