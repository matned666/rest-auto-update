package eu.mrndesign.matned.jsonplaceholder.dto;

import eu.mrndesign.matned.jsonplaceholder.model.Post;

import java.util.List;

public interface IPostDTOBase extends IDTO{

    Long getIndexId();
    Integer getId();
    String getTitle();
    String getBody();


    IPostDTOBase applyNewSimple(Post content);
}
