package eu.mrndesign.matned.jsonplaceholder.dto;

import eu.mrndesign.matned.jsonplaceholder.model.Post;

public interface IPostDTOApply extends IPostDTO{

    IPostDTO applyNew(Post postToSaveLocally);

}
