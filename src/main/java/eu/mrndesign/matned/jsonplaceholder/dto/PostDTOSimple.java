package eu.mrndesign.matned.jsonplaceholder.dto;

import eu.mrndesign.matned.jsonplaceholder.exception.NullEntityDataProvidedException;
import eu.mrndesign.matned.jsonplaceholder.model.Post;

public class PostDTOSimple extends BaseDTO implements IPostDTOBase {

    private Long indexId;
    private Integer id;
    private String title;
    private String body;

    public PostDTOSimple() {
    }

    private PostDTOSimple(Integer id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public IPostDTOBase applyNewSimple(Post postToSaveLocally) {
        if (postToSaveLocally != null)
            return new PostDTOSimple(postToSaveLocally.getId(),
                    postToSaveLocally.getTitle(),
                    postToSaveLocally.getBody()).setId(postToSaveLocally.getDataBaseId());
        else throw  new NullEntityDataProvidedException();    }

    @Override
    public Long getIndexId() {
        return indexId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    private PostDTOSimple setId(Long indexId){
        this.indexId = indexId;
        return this;
    }
}
