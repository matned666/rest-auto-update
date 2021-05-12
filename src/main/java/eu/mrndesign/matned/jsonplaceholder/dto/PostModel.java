package eu.mrndesign.matned.jsonplaceholder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTO;
import eu.mrndesign.matned.jsonplaceholder.dto.IPostDTOBase;
import eu.mrndesign.matned.jsonplaceholder.dto.PostDTO;
import eu.mrndesign.matned.jsonplaceholder.exception.NullEntityDataProvidedException;
import eu.mrndesign.matned.jsonplaceholder.model.Post;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostModel implements IPostDTO{

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public PostModel() {
    }

    public IPostDTO apply() {
        return new PostDTO(userId,id,title,body);
    }


    @Override
    public Integer getUserId() {
        return this.userId;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getBody() {
        return this.body;
    }



    @Override
    public Long getIndexId() {
        return null;
    }

    @Override
    public IPostDTOBase applyNewSimple(Post content) {
        return null;
    }
}
