package eu.mrndesign.matned.jsonplaceholder.model;

import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;

public interface IPost {

    IPost applyNew(IPostDTO entity);
}
