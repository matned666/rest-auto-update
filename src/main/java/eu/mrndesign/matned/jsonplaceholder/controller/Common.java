package eu.mrndesign.matned.jsonplaceholder.controller;

import eu.mrndesign.matned.jsonplaceholder.service.IRestService;
import eu.mrndesign.matned.jsonplaceholder.service.IService;

public class Common {

    static void getRest(IRestService restService, IService postService) {
        update(restService, postService);
        downloadNew(restService, postService);
    }

    private static void downloadNew(IRestService restService, IService postService) {
        Integer randomInt = (int) (Math.random() * restService.getRestServiceDBSize());
        if (postService.existsById(randomInt))
            postService.savePostToDB(restService.getRestPost(randomInt).apply());
    }

    private static void update(IRestService restService, IService postService) {
        postService.showAllIds().forEach(x -> {
            Long postId = postService.getIdByIdm(x);
            if (!postService.wasEdited(postId) && !postService.wasDeleted(x))
                postService.editPostInDB(postId, restService.getRestPost(x).apply());
        });
    }
}
