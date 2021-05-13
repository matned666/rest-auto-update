package eu.mrndesign.matned.jsonplaceholder.dto;

import eu.mrndesign.matned.jsonplaceholder.exception.NullEntityDataProvidedException;
import eu.mrndesign.matned.jsonplaceholder.model.Post;

import java.util.Objects;

public class PostDTO extends BaseDTO implements IPostDTOApply {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public PostDTO() {
    }

    public PostDTO(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public IPostDTO applyNew(Post postToSaveLocally) {
        if (postToSaveLocally != null)
            return new PostDTO(postToSaveLocally.getUserId(),
                    postToSaveLocally.getId(),
                    postToSaveLocally.getTitle(),
                    postToSaveLocally.getBody())
                    .setId(postToSaveLocally.getDataBaseId());
        else throw  new NullEntityDataProvidedException();
    }

    @Override
    public IPostDTOBase applyNewSimple(Post content) {
        return applyNew(content);
    }

    @Override
    public Long getIndexId() {
        return indexId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    private PostDTO setId(Long indexId){
        this.indexId = indexId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(userId, postDTO.userId) && Objects.equals(id, postDTO.id) && Objects.equals(title, postDTO.title) && Objects.equals(body, postDTO.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }
}
