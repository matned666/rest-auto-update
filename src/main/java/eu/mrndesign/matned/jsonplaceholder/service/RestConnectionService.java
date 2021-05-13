package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.RestPostModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class RestConnectionService implements IRestService {

    private final RestTemplate restTemplate;
    private final IService postService;

    public RestConnectionService(RestTemplate restTemplate,
                                 PostService postService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
    }

    @Override
    public RestPostModel getRestPost(Integer id) {
        return restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/" + id, RestPostModel.class);
    }

    @Override
    public Integer getRestServiceDBSize() {
        return Objects.requireNonNull(restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/", RestPostModel[].class)).length;
    }

    @Override
    public void getRest() {
        update(postService);
        downloadNew(postService);
    }

    private void downloadNew(IService postService) {
        Integer randomInt = (int) (Math.random() * getRestServiceDBSize());
        if (!postService.existsById(randomInt))
            postService.savePostToDB(getRestPost(randomInt).apply());
    }

    private void update(IService postService) {
        postService.showAllIds().forEach(x -> {
            Long postId = postService.getIdByIdm(x);
            if (!postService.wasEdited(postId) && !postService.wasDeleted(x))
                postService.editPostInDB(postId, getRestPost(x).apply());
        });
    }


}