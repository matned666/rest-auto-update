package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.PostModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class RestConnectionService implements IRestService {

    private final RestTemplate restTemplate;

    public RestConnectionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PostModel getRestPost(Integer id) {
        return restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/" + id, PostModel.class);
    }

    @Override
    public Integer getRestServiceDBSize() {
        return Objects.requireNonNull(restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/", PostModel[].class)).length;
    }


}