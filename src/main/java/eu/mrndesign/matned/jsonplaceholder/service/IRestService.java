package eu.mrndesign.matned.jsonplaceholder.service;

import eu.mrndesign.matned.jsonplaceholder.dto.RestPostModel;

public interface IRestService {

    RestPostModel getRestPost(Integer id);
    Integer getRestServiceDBSize();
    void getRest();

}
