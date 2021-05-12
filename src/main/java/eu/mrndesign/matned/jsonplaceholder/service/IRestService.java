package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.PostModel;

public interface IRestService {

    PostModel getRestPost(Integer id);
    Integer getRestServiceDBSize();

}
